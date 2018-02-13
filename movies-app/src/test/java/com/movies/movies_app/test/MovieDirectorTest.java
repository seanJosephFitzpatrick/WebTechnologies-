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
import com.movies.movies_app.data.MovieDirectorDAO;
import com.movies.movies_app.model.MovieDirector;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.rest.MovieDirectorWS;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class MovieDirectorTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestMovieDirector.jar")
				.addClasses(MovieDirectorDAO.class, MovieDirector.class,
						JaxRsActivator.class, UtilsDAO.class, MovieDirectorWS.class)
			//	.addPackage(Actor.class.getPackage())
			//	.addPackage(ActorDAO.class.getPackage())
						//this line will pick up the production db
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
		//this function means that we start with an empty table
		//And add one actors
		//it should be possible to test with an in memory db for efficiency
		utilsDAO.deleteTableMovieDirector();
		MovieDirector movieDirector = new MovieDirector();
		movieDirector.setDirectorId(15156);
		movieDirector.setMovieId(142424);
		movieDirectorDAO.save(movieDirector);
	}
	
	@Test
	public void testGetAllMovieDirectors() {
		List<MovieDirector> movieDirectorList = movieDirectorDAO.getAllMovieDirectors();
		assertEquals("Data fetch = data persisted", movieDirectorList.size(), 1);
	}
	
}
