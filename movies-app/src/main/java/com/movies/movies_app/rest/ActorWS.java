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
import com.movies.movies_app.data.ActorDAO;
import com.movies.movies_app.model.Actor;

@Path("/actors")
@Stateless
@LocalBean
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ActorWS {
	
	@EJB
	private ActorDAO actorDAO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		List<Actor> actor = actorDAO.getAllActors();
		return Response.status(200).entity(actor).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public Response findById(@PathParam("id") int id) {
		Actor actor = actorDAO.getActor(id);
		
		if (actor == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(200).entity(actor).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteActorById(@PathParam("id") int id) {
		actorDAO.delete(id);
		return Response.status(200).build();
	}
	
	@POST
	public Response save(final Actor actor) {
		actorDAO.save(actor);
		return Response.status(200).entity(actor).build();
	}

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") int id, final Actor actor) {
		actorDAO.update(actor);
		return Response.status(200).entity(actor).build();
	}
	
}
