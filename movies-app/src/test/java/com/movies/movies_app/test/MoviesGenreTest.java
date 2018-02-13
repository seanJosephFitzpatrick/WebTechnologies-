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
import com.movies.movies_app.data.MoviesGenreDAO;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.rest.MoviesGenreWS;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class MoviesGenreTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestMoviesGenre.jar")
				.addClasses(MoviesGenreDAO.class, MoviesGenre.class,
						JaxRsActivator.class, UtilsDAO.class, MoviesGenreWS.class)
			//	.addPackage(Actor.class.getPackage())
			//	.addPackage(ActorDAO.class.getPackage())
						//this line will pick up the production db
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	 
	@EJB
	private MoviesGenreWS moviesGenreWS;
	
	@EJB
	private MoviesGenreDAO moviesGenreDAO;
	
	@EJB
	private UtilsDAO utilsDAO;
	 
	@Before
	public void setUp() {
		//this function means that we start with an empty table
		//And add one actors
		//it should be possible to test with an in memory db for efficiency
		utilsDAO.deleteTableMoviesGenre();
		MoviesGenre moviesGenre = new MoviesGenre();
		moviesGenre.setMovieId(51784);
		moviesGenre.setGenre("Drama");
		moviesGenreDAO.save(moviesGenre);
	}
	
	@Test
	public void testGetAllMoviesGenres() {
		List<MoviesGenre> moviesGenreList = moviesGenreDAO.getAllMoviesGenres();
		assertEquals("Data fetch = data persisted", moviesGenreList.size(), 1);
	}
	
}
