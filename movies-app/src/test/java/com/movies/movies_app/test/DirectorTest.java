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
import com.movies.movies_app.data.DirectorDAO;
import com.movies.movies_app.model.Director;
import com.movies.movies_app.rest.DirectorWS;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class DirectorTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestDirector.jar")
				.addClasses(DirectorDAO.class, Director.class,
						JaxRsActivator.class, UtilsDAO.class, DirectorWS.class)
			//	.addPackage(Actor.class.getPackage())
			//	.addPackage(ActorDAO.class.getPackage())
						//this line will pick up the production db
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	 
	@EJB
	private DirectorWS directorWS;
	
	@EJB
	private DirectorDAO directorDAO;
	
	@EJB
	private UtilsDAO utilsDAO;
	 
	@Before
	public void setUp() {
		//this function means that we start with an empty table
		//And add one actors
		//it should be possible to test with an in memory db for efficiency
		utilsDAO.deleteTableDirector();
		Director director = new Director();
		director.setId(33799);
		director.setFirstName("Jean");
		director.setLastName("Hennin");
		directorDAO.save(director);
	}
	
	@Test
	public void testGetAllDirectors() {
		List<Director> directorList = directorDAO.getAllDirectors();
		assertEquals("Data fetch = data persisted", directorList.size(), 1);
	}
	
}
