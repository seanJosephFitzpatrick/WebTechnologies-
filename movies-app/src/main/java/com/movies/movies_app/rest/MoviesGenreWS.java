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
import com.movies.movies_app.data.MoviesGenreDAO;
import com.movies.movies_app.model.MoviesGenre;

@Path("/moviesgenres")
@Stateless
@LocalBean
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MoviesGenreWS {
	
	@EJB
	private MoviesGenreDAO moviesGenreDAO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		List<MoviesGenre> moviesGenre = moviesGenreDAO.getAllMoviesGenres();
		return Response.status(200).entity(moviesGenre).build();
		
	}
	
	@POST
	@Consumes("application/json")
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(final MoviesGenre moviesgenre) {
		moviesGenreDAO.save(moviesgenre);
		return Response.status(201).entity(moviesgenre).build();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("id") int id) { 
		MoviesGenre moviesgenre = moviesGenreDAO.getMoviesGenre(id);
		if (moviesgenre == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(moviesgenre).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({MediaType.APPLICATION_JSON})
	public Response update(MoviesGenre moviesgenre) {
		moviesGenreDAO.update(moviesgenre);
		return Response.status(200).entity(moviesgenre).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteById(int id) {
		moviesGenreDAO.delete(id);
		return Response.status(204).build();
	}

}
