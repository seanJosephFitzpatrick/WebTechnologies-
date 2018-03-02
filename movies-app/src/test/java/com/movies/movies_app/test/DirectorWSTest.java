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
import com.movies.movies_app.data.DirectorDAO;
import com.movies.movies_app.model.Director;
import com.movies.movies_app.model.MoviesDirector;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.model.Role;
import com.movies.movies_app.rest.DirectorWS;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class DirectorWSTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestDirector.jar")
				.addClasses(DirectorDAO.class, Director.class,
						JaxRsActivator.class, UtilsDAO.class, DirectorWS.class,
						MoviesDirector.class, MoviesGenre.class, Role.class)
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
		utilsDAO.deleteTableDirector();
		Director director = new Director();
		director.setId(123);
		director.setFirstName("Martin");
		director.setLastName("Scorsese");
		directorDAO.save(director);
		director = new Director();
		director.setId(8653);
		director.setFirstName("Quentin");
		director.setLastName("Tarantino");
		directorDAO.save(director);
	}
	
	@Test
	public void testGetAllDirectorsWS() {
		Response response = directorWS.listAll();
		List<Director> directorList = (List<Director>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", directorList.size(), 2);
		Director director = directorList.get(0);
		assertEquals(123, director.getId());
		assertEquals("Martin", director.getFirstName());
		assertEquals("Scorsese", director.getLastName());
	}
	
	@Test
	public void testFindById() {
		Response response = directorWS.findById(123);
		Director director = (Director) response.getEntity();
		assertEquals(123, director.getId());
		assertEquals("Martin", director.getFirstName());
		assertEquals("Scorsese", director.getLastName());
	}
	
	@Test
	public void testAddDirector() {
		Director director = new Director();
		director.setId(4856);
		director.setFirstName("Steven");
		director.setLastName("Spielberg");
		Response response = directorWS.create(director);
		assertEquals(HttpStatus.SC_CREATED, response.getStatus());
		director = (Director) response.getEntity();
		director.setId(4856);
		director.setFirstName("Steven");
		director.setLastName("Spielberg");
	}
	
	@Test
	public void testRemoveDirector() {
		Response response = directorWS.listAll();
		List<Director> directorList = (List<Director>) response.getEntity();
		assertEquals("Data fetch = data persisted", directorList.size(), 2);
		directorWS.deleteDirectorById(8653);
		directorList = (List<Director>) response.getEntity();
		assertEquals(directorList.size(), 2);
		response = directorWS.findById(8653);
		Director director = (Director) response.getEntity();
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
		assertEquals(null, director);
	}
	
	@Test
	public void testUpdateDirector() {
		Response response = directorWS.findById(123);
		Director director = (Director) response.getEntity();
		director.setFirstName("Martin");
		director.setLastName("Lawrence");
		response = directorWS.update(director);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		director = (Director) response.getEntity();
		assertEquals(123, director.getId());
		assertEquals("Martin", director.getFirstName());
		assertEquals("Lawrence", director.getLastName());
	}
	
	@Test
	public void testSearchDirectorByName() {
		Response response = directorWS.findByName("Martin");
		List<Director> directorList = (List<Director>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals(directorList.size(), 1);
		Director director = directorList.get(0);
		assertEquals(123, director.getId());
		assertEquals("Martin", director.getFirstName());
		assertEquals("Scorsese", director.getLastName());

	}
	
}
