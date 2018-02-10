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

import com.movies.movies_app.model.Movie;

@RequestScoped
@Path("/movies")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MovieWS {

	@POST
	public Response create(final Movie movie) {
		//TODO: process the given movie 
		//here we use Movie#getId(), assuming that it provides the identifier to retrieve the created Movie resource. 
		return Response
				.created(UriBuilder.fromResource(MovieWS.class).path(String.valueOf(movie.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the movie 
		Movie movie = null;
		if (movie == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(movie).build();
	}

	@GET
	public List<Movie> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the movies 
		final List<Movie> movies = null;
		return movies;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final Movie movie) {
		//TODO: process the given movie 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the movie matching by the given id 
		return Response.noContent().build();
	}

}