package com.business.DemoRest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class UserResource 
{
	UserRepository userrepo = new UserRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<User> getUsers()
	{
		
		return userrepo.getUsers();
		
	}
	
	@GET
	@Path("user/{id}")
	public User getUser(@PathParam("id") int id)
	{
		return userrepo.getUser(id);
	}
	
	@POST
	@Path("create")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String createUser(User u1)
	{
		userrepo.createUser(u1);
		return "Data Added!";
	}
	
	@PUT
	@Path("update")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String updateUser(User u1)
	{
		userrepo.updateUser(u1);
		return "Data Updated!";
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(@PathParam ("id") int id)
	{
		userrepo.deleteUser(id);
		return "Data Deleted!";
	}
}
