package com.movies.movies_app.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.movies.movies_app.data.RoleDAO;
import com.movies.movies_app.model.Movie;
import com.movies.movies_app.model.Role;

@Path("/roles")
@Stateless
@LocalBean
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class RoleWS {
	
	@EJB
	private RoleDAO roleDAO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<Role> role = roleDAO.getAllRoles();
		return Response.status(200).entity(role).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(Role role) {
		roleDAO.save(role);
		return Response.status(201).entity(role).build();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("id") int id) {
		Role role = roleDAO.getRole(id);
		if (role == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(role).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({MediaType.APPLICATION_JSON})
	public Response update(Role role) {
		roleDAO.update(role);
		return Response.status(200).entity(role).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("name/{name}")
	public Response findByName(@PathParam("name") final String name) {
		List<Role> role = roleDAO.getRolesByName(name);
		
		if (role == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(200).entity(role).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteRoleById(int id) {
		roleDAO.delete(id);
		return Response.status(204).build();
	}

}
