package com.business.DemoRest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository 
{
	Connection con = null;
	
	public UserRepository()
	{
		String url = "jdbc:sqlserver://localhost;database=RestDB";
		String uname = "root";
		String pw = "root@123";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, uname, pw);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<User> getUsers()
	{
		List<User> users = new ArrayList<>();
		String sql = "select * from user_data";
		try 
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				User u = new User();
				u.setId(rs.getInt(1));
				u.setName((rs.getString(2)));
				u.setPoints(rs.getInt(3));
				users.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	public User getUser(int id)
	{
		User user = new User();
		String sql = "select * from user_data where id="+id;
		try 
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				user.setId(rs.getInt(1));
				user.setName((rs.getString(2)));
				user.setPoints(rs.getInt(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public void createUser(User u)
	{
		System.out.println("Going to add user...");
		String sql = "INSERT INTO user_data values (?,?)";
		try 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setInt(2, u.getPoints());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUser(User u)
	{
		System.out.println("Going to update user...");
		String sql = "Update user_data set name=? , points=? where id=?";
		try 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setInt(2, u.getPoints());
			ps.setInt(3, u.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int id)
	{
		System.out.println("Going to delete user...");
		String sql = "Delete from user_data where id="+id;
		try 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
