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
import javax.ws.rs.core.UriBuilder;
import com.movies.movies_app.data.MovieDirectorDAO;
import com.movies.movies_app.model.MoviesDirector;

@Path("/moviesdirectors")
@Stateless
@LocalBean
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MovieDirectorWS {
	
	@EJB
	private MovieDirectorDAO movieDirectorDAO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
			List<MoviesDirector> movieDirector = movieDirectorDAO.getAllMovieDirectors();
			return Response.status(200).entity(movieDirector).build();
	}

	@POST
	@Consumes("application/json")
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(final MoviesDirector moviesdirector) {
		movieDirectorDAO.save(moviesdirector);
		return Response.status(201).entity(moviesdirector).build();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("id") int id) {
		MoviesDirector moviesdirector = movieDirectorDAO.getMovieDirector(id);
		if (moviesdirector == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(moviesdirector).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({MediaType.APPLICATION_JSON})
	public Response update(MoviesDirector movieDirector) {
		movieDirectorDAO.update(movieDirector);
		return Response.status(200).entity(movieDirector).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteById(int id) {
		movieDirectorDAO.delete(id);
		return Response.status(204).build();
	}
}
