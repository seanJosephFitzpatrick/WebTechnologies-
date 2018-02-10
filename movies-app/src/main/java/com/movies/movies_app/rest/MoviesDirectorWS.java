package com.movies.movies_app.rest;

import java.util.List;

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

import com.movies.movies_app.model.MoviesDirector;

@RequestScoped
@Path("/moviesdirectors")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MoviesDirectorWS {

	@POST
	public Response create(final MoviesDirector moviesdirector) {
		//TODO: process the given moviesdirector 
		//here we use MoviesDirector#getId(), assuming that it provides the identifier to retrieve the created MoviesDirector resource. 
		return Response.created(
				UriBuilder.fromResource(MoviesDirectorWS.class).path(String.valueOf(moviesdirector.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the moviesdirector 
		MoviesDirector moviesdirector = null;
		if (moviesdirector == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(moviesdirector).build();
	}

	@GET
	public List<MoviesDirector> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the moviesdirectors 
		final List<MoviesDirector> moviesdirectors = null;
		return moviesdirectors;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final MoviesDirector moviesdirector) {
		//TODO: process the given moviesdirector 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the moviesdirector matching by the given id 
		return Response.noContent().build();
	}

}
