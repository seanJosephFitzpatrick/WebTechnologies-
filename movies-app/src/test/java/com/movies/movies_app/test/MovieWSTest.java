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
import com.movies.movies_app.data.MovieDAO;
import com.movies.movies_app.model.Movie;
import com.movies.movies_app.model.MoviesDirector;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.model.Role;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.rest.MovieWS;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class MovieWSTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestActor.jar")
				.addClasses(MovieDAO.class, Movie.class,
						JaxRsActivator.class, UtilsDAO.class, MovieWS.class,
						MoviesDirector.class, MoviesGenre.class, Role.class)
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	 
	@EJB
	private MovieWS movieWS;
	
	@EJB
	private MovieDAO movieDAO;
	
	@EJB
	private UtilsDAO utilsDAO;
	 
	@Before
	public void setUp() {
		utilsDAO.deleteTableMovies();
		Movie movie = new Movie();
		movie.setId(14);
		movie.setName("Inception");
		movie.setYear(2010);
		movie.setRank(8.7f);
		movieDAO.save(movie);
		movie = new Movie();
		movie.setId(30);
		movie.setName("Interstellar");
		movie.setYear(2014);
		movie.setRank(8.5f);
		movieDAO.save(movie);
	}
	
	@Test
	public void testGetAllMoviesWS() {
		Response response = movieWS.listAll();
		List<Movie> movieList = (List<Movie>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", movieList.size(), 2);
		Movie movie = movieList.get(0);
		assertEquals(14, movie.getId());
		assertEquals("Inception", movie.getName());
		assertEquals(2010, movie.getYear());
		assertEquals(8.7, movie.getRank(),1);
	}
	
	@Test
	public void testFindById() {
		Response response = movieWS.findById(14);
		Movie movie = (Movie) response.getEntity();
		assertEquals(14, movie.getId());
		assertEquals("Inception", movie.getName());
		assertEquals(2010, movie.getYear());
		assertEquals(8.7, movie.getRank(),1);
	}
	
	@Test
	public void testAddMovie() {
		Movie movie = new Movie();
		movie.setId(46);
		movie.setName("Gladiator");
		movie.setYear(2000);
		movie.setRank(8.5f);
		Response response = movieWS.create(movie);
		assertEquals(HttpStatus.SC_CREATED, response.getStatus());
		movie = (Movie) response.getEntity();
		assertEquals(46, movie.getId());
		assertEquals("Gladiator", movie.getName());
		assertEquals(2000, movie.getYear());
		assertEquals(8.5, movie.getRank(),1);
	}
	
	@Test
	public void testRemoveMovie() {
		Response response = movieWS.listAll();
		List<Movie> movieList = (List<Movie>) response.getEntity();
		assertEquals("Data fetch = data persisted", movieList.size(), 2);
		movieWS.deleteMovieById(30);
		movieList = (List<Movie>) response.getEntity();
		assertEquals(movieList.size(), 2);
		response = movieWS.findById(30);
		Movie movie = (Movie) response.getEntity();
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
		assertEquals(null, movie);
	}
	
	@Test
	public void testUpdateMovie() {
		Response response = movieWS.findById(14);
		Movie movie = (Movie) response.getEntity();
		movie.setId(14);
		movie.setName("Alien");
		movie.setYear(1979);
		movie.setRank(8.4f);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		movie = (Movie) response.getEntity();
		assertEquals(14, movie.getId());
		assertEquals("Alien", movie.getName());
		assertEquals(1979, movie.getYear());
		assertEquals(8.4, movie.getRank(),1);
	}
	
	@Test
	public void testSearchMovieByName() {
		Response response = movieWS.findByName("Inception");
		List<Movie> movieList = (List<Movie>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals(movieList.size(), 1);
		Movie movie = movieList.get(0);
		assertEquals(14, movie.getId());
		assertEquals("Inception", movie.getName());
		assertEquals(2010, movie.getYear());
		assertEquals(8.7, movie.getRank(),1);

	}
	
}
