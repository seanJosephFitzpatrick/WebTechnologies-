package com.movies.movies_app.rest;

import java.util.List;

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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import com.movies.movies_app.data.ActorDAO;
import com.movies.movies_app.model.Actor;

@Path("/actors")
@Stateless
@LocalBean
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ActorWS {
	
	private ActorDAO actorDAO;
	
	@GET
	public Response listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		List<Actor> actor = actorDAO.getAllActors();
		return Response.status(200).entity(actor).build();
	}

	/*
	@POST
	public Response create(final Actor actor) {
		//TODO: process the given actor 
		//here we use Actor#getId(), assuming that it provides the identifier to retrieve the created Actor resource. 
		return Response
				.created(UriBuilder.fromResource(ActorWS.class).path(String.valueOf(actor.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the actor 
		Actor actor = null;
		if (actor == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(actor).build();
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final Actor actor) {
		//TODO: process the given actor 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the actor matching by the given id 
		return Response.noContent().build();
	}
	 */
}
