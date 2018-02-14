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
import com.movies.movies_app.data.MovieDAO;
import com.movies.movies_app.model.Movie;

@Path("/movies")
@Stateless
@LocalBean
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MovieWS {
	
	@EJB
	private MovieDAO movieDAO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		List<Movie> movie = movieDAO.getAllMovies();
		return Response.status(200).entity(movie).build();
	}
	
	
	@POST
	public Response save(final Movie movie) {
		movieDAO.save(movie);
		return Response.status(200).entity(movie).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("id/{id}")
	public Response findById(@PathParam("id") final int id) {
		Movie movie = movieDAO.getMovie(id);
		
		if (movie == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(200).entity(movie).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("name/{name}")
	public Response findByName(@PathParam("name") final String name) {
		List<Movie> movie = movieDAO.getMoviesByName(name);
		
		if (movie == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(200).entity(movie).build();
	}

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") int id, final Movie movie) {
		movieDAO.update(movie);
		return Response.status(200).entity(movie).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteById(@PathParam("id") final int id) {
		movieDAO.delete(id);
		return Response.status(200).build();
	}
	

}
