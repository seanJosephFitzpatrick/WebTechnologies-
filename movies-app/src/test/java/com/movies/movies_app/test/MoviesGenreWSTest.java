package com.movies.movies_app.test;

import static org.junit.Assert.assertEquals;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.movies.movies_app.data.MoviesGenreDAO;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.model.Role;
import com.movies.movies_app.model.MoviesDirector;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.rest.MoviesGenreWS;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class MoviesGenreWSTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestMoviesGenre.jar")
				.addClasses(MoviesGenreDAO.class, MoviesGenre.class,
						JaxRsActivator.class, UtilsDAO.class, MoviesGenreWS.class,
						MoviesDirector.class, MoviesGenre.class, MoviesGenre.class, Role.class)
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	 
	@EJB
	private MoviesGenreWS movieGenreWS;
	
	@EJB
	private MoviesGenreDAO movieGenreDAO;
	
	@EJB
	private UtilsDAO utilsDAO;
	 
	@Before
	public void setUp() {
		utilsDAO.deleteTableMoviesGenre();
		MoviesGenre movieGenre = new MoviesGenre();
		movieGenre.setId(2);
		movieGenre.setMovieId(14);
		movieGenre.setGenre("Science Fiction");
		movieGenreDAO.save(movieGenre);
		movieGenre = new MoviesGenre();
		movieGenre.setId(3);
		movieGenre.setMovieId(30);
		movieGenre.setGenre("Action");
		movieGenreDAO.save(movieGenre);
	}
	
	@Test
	public void testGetAllMoviesGenresWS() {
		Response response = movieGenreWS.listAll();
		List<MoviesGenre> movieGenreList = (List<MoviesGenre>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", movieGenreList.size(), 2);
		MoviesGenre movieGenre = movieGenreList.get(0);
		assertEquals(2, movieGenre.getId());
		assertEquals(14, movieGenre.getMovieId());
		assertEquals("Science Fiction", movieGenre.getGenre());
	}
	
	@Test
	public void testFindById() {
		Response response = movieGenreWS.findById(2);
		MoviesGenre movieGenre = (MoviesGenre) response.getEntity();
		assertEquals(2, movieGenre.getId());
		assertEquals(14, movieGenre.getMovieId());
		assertEquals("Science Fiction", movieGenre.getGenre());
	}
	
	@Test
	public void testAddMoviesGenre() {
		MoviesGenre movieGenre = new MoviesGenre();
		movieGenre.setId(4);
		movieGenre.setMovieId(45);
		movieGenre.setGenre("Drama");
		Response response = movieGenreWS.create(movieGenre);
		assertEquals(HttpStatus.SC_CREATED, response.getStatus());
		movieGenre = (MoviesGenre) response.getEntity();
		assertEquals(4, movieGenre.getId());
		assertEquals(45, movieGenre.getMovieId());
		assertEquals("Drama", movieGenre.getGenre());
	}
	
	@Test
	public void testRemoveMoviesGenre() {
		Response response = movieGenreWS.listAll();
		List<MoviesGenre> movieGenreList = (List<MoviesGenre>) response.getEntity();
		assertEquals("Data fetch = data persisted", movieGenreList.size(), 2);
		movieGenreWS.deleteMoviesGenreById(3);
		movieGenreList = (List<MoviesGenre>) response.getEntity();
		assertEquals(movieGenreList.size(), 2);
		response = movieGenreWS.findById(3);
		MoviesGenre movieGenre = (MoviesGenre) response.getEntity();
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
		assertEquals(null, movieGenre);
	}
	
	@Test
	public void testUpdateMoviesGenre() {
		Response response = movieGenreWS.findById(2);
		MoviesGenre movieGenre = (MoviesGenre) response.getEntity();
		movieGenre.setId(2);
		movieGenre.setMovieId(14);
		movieGenre.setGenre("Action");
		movieGenreDAO.update(movieGenre);
		response = movieGenreWS.update(movieGenre);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		movieGenre = (MoviesGenre) response.getEntity();
		assertEquals(2, movieGenre.getId());
		assertEquals(14, movieGenre.getMovieId());
		assertEquals("Action", movieGenre.getGenre());
	}
	
	@Test
	public void testSearchMoviesGenreByName() {
		Response response = movieGenreWS.findByName("Action");
		List<MoviesGenre> movieGenreList = (List<MoviesGenre>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals(movieGenreList.size(), 1);
		MoviesGenre movieGenre = movieGenreList.get(0);
		assertEquals(3, movieGenre.getId());
		assertEquals(30, movieGenre.getMovieId());
		assertEquals("Action", movieGenre.getGenre());

	}
	
}
