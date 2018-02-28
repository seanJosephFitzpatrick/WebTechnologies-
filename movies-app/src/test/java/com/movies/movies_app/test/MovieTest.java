package com.movies.movies_app.test;

import static org.junit.Assert.assertEquals;
import java.util.List;
import javax.ejb.EJB;
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
public class MovieTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestMovie.jar")
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
		movie.setId(28315);
		movie.setName("Bald and the Beautiful, The");
		movie.setYear(2002);
		movieDAO.save(movie);
	}
	
	@Test
	public void testGetAllMovies() {
		List<Movie> movieList = movieDAO.getAllMovies();
		assertEquals("Data fetch = data persisted", movieList.size(), 1);
	}
	
}
