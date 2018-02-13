package com.movies.movies_app.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
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
import javax.ws.rs.core.UriBuilder;

import com.movies.movies_app.data.RoleDAO;
import com.movies.movies_app.model.Actor;
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
	public Response listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		List<Role> role = roleDAO.getAllRoles();
		return Response.status(200).entity(role).build();
	}
	
	/*
	@POST
	public Response create(final Role role) {
		//TODO: process the given role 
		//here we use Role#getId(), assuming that it provides the identifier to retrieve the created Role resource. 
		return Response.created(UriBuilder.fromResource(RoleWS.class).path(String.valueOf(role.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the role 
		Role role = null;
		if (role == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(role).build();
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final Role role) {
		//TODO: process the given role 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the role matching by the given id 
		return Response.noContent().build();
	}
	*/

}
