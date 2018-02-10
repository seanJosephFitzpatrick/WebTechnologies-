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

import com.movies.movies_app.model.MoviesGenre;

@RequestScoped
@Path("/moviesgenres")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MoviesGenreWS {

	@POST
	public Response create(final MoviesGenre moviesgenre) {
		//TODO: process the given moviesgenre 
		//here we use MoviesGenre#getId(), assuming that it provides the identifier to retrieve the created MoviesGenre resource. 
		return Response
				.created(UriBuilder.fromResource(MoviesGenreWS.class).path(String.valueOf(moviesgenre.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the moviesgenre 
		MoviesGenre moviesgenre = null;
		if (moviesgenre == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(moviesgenre).build();
	}

	@GET
	public List<MoviesGenre> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the moviesgenres 
		final List<MoviesGenre> moviesgenres = null;
		return moviesgenres;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final MoviesGenre moviesgenre) {
		//TODO: process the given moviesgenre 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the moviesgenre matching by the given id 
		return Response.noContent().build();
	}

}