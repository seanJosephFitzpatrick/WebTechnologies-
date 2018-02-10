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

import com.movies.movies_app.model.Director;

@Path("/directors")
@Stateless
@LocalBean
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class DirectorWS {

	@POST
	public Response create(final Director director) {
		//TODO: process the given director 
		//here we use Director#getId(), assuming that it provides the identifier to retrieve the created Director resource. 
		return Response
				.created(UriBuilder.fromResource(DirectorWS.class).path(String.valueOf(director.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the director 
		Director director = null;
		if (director == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(director).build();
	}

	@GET
	public List<Director> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the directors 
		final List<Director> directors = null;
		return directors;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final Director director) {
		//TODO: process the given director 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the director matching by the given id 
		return Response.noContent().build();
	}

}
