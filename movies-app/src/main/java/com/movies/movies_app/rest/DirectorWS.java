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
import com.movies.movies_app.data.DirectorDAO;
import com.movies.movies_app.model.Director;
import com.movies.movies_app.model.Movie;

@Path("/directors")
@Stateless
@LocalBean
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class DirectorWS {
	
	@EJB
	private DirectorDAO directorDAO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<Director> director = directorDAO.getAllDirectors();
		return Response.status(200).entity(director).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("name/{name}")
	public Response findByName(@PathParam("name") final String name) {
		List<Director> director = directorDAO.getDirectorsByName(name);
		
		if (director == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(200).entity(director).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response findById(@PathParam("id") final int id) {
		 
		Director director = directorDAO.getDirector(id);
		if (director == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(200).entity(director).build();
	}

	
	@POST
	@Consumes("application/json")
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(final Director director) {
		directorDAO.save(director);
		return Response.status(201).entity(director).build();
	}



	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({MediaType.APPLICATION_JSON})
	public Response update(Director director) {
		directorDAO.update(director);
		return Response.status(200).entity(director).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteDirectorById(@PathParam("id") final int id) {
		directorDAO.delete(id);
		return Response.status(204).build();
	}
	

}
