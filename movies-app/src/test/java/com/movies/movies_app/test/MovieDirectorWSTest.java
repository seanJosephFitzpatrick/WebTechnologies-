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
import com.movies.movies_app.data.MovieDirectorDAO;
import com.movies.movies_app.model.MoviesDirector;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.model.Role;
import com.movies.movies_app.rest.MovieDirectorWS;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class MovieDirectorWSTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestMovieDirector.jar")
				.addClasses(MovieDirectorDAO.class, MoviesDirector.class,
						JaxRsActivator.class, UtilsDAO.class, MovieDirectorWS.class,
						MoviesDirector.class, MoviesGenre.class, Role.class)
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	 
	@EJB
	private MovieDirectorWS movieDirectorWS;
	
	@EJB
	private MovieDirectorDAO movieDirectorDAO;
	
	@EJB
	private UtilsDAO utilsDAO;
	 
	@Before
	public void setUp() {
		utilsDAO.deleteTableMovieDirector();
		MoviesDirector movieDirector = new MoviesDirector();
		movieDirector.setId(2);
		movieDirector.setMovieId(14);
		movieDirector.setDirectorId(123);
		movieDirectorDAO.save(movieDirector);
		movieDirector = new MoviesDirector();
		movieDirector.setId(3);
		movieDirector.setMovieId(30);
		movieDirector.setDirectorId(123);
		movieDirectorDAO.save(movieDirector);
	}
	
	@Test
	public void testGetAllMovieDirectorsWS() {
		Response response = movieDirectorWS.listAll();
		List<MoviesDirector> movieDirectorList = (List<MoviesDirector>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", movieDirectorList.size(), 2);
		MoviesDirector movieDirector = movieDirectorList.get(0);
		assertEquals(2, movieDirector.getId());
		assertEquals(14, movieDirector.getMovieId());
		assertEquals(123, movieDirector.getDirectorId());
	}
	
	@Test
	public void testFindById() {
		Response response = movieDirectorWS.findById(2);
		MoviesDirector movieDirector = (MoviesDirector) response.getEntity();
		assertEquals(2, movieDirector.getId());
		assertEquals(14, movieDirector.getMovieId());
		assertEquals(123, movieDirector.getDirectorId());
	}
	
	@Test
	public void testAddMovieDirector() {
		MoviesDirector movieDirector = new MoviesDirector();
		movieDirector.setId(4);
		movieDirector.setMovieId(67);
		movieDirector.setDirectorId(3764);
		Response response = movieDirectorWS.create(movieDirector);
		assertEquals(HttpStatus.SC_CREATED, response.getStatus());
		movieDirector = (MoviesDirector) response.getEntity();
		assertEquals(4, movieDirector.getId());
		assertEquals(67, movieDirector.getMovieId());
		assertEquals(3764, movieDirector.getDirectorId());
	}
	
	@Test
	public void testRemoveMovieDirector() {
		Response response = movieDirectorWS.listAll();
		List<MoviesDirector> movieDirectorList = (List<MoviesDirector>) response.getEntity();
		assertEquals("Data fetch = data persisted", movieDirectorList.size(), 2);
		movieDirectorWS.deleteMovieDirectorById(2);
		movieDirectorList = (List<MoviesDirector>) response.getEntity();
		assertEquals(movieDirectorList.size(), 2);
		response = movieDirectorWS.findById(2);
		MoviesDirector movieDirector = (MoviesDirector) response.getEntity();
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
		assertEquals(null, movieDirector);
	}
	
	@Test
	public void testUpdateMovieDirector() {
		Response response = movieDirectorWS.findById(3);
		MoviesDirector movieDirector = (MoviesDirector) response.getEntity();
		movieDirector.setMovieId(3900);
		movieDirector.setDirectorId(2341);
		response = movieDirectorWS.update(movieDirector);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		movieDirector = (MoviesDirector) response.getEntity();
		assertEquals(3, movieDirector.getId());
		assertEquals(3900, movieDirector.getMovieId());
		assertEquals(2341, movieDirector.getDirectorId());
	}
	
}
