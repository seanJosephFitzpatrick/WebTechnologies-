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
import com.movies.movies_app.data.ActorDAO;
import com.movies.movies_app.model.Actor;
import com.movies.movies_app.model.MoviesDirector;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.model.Role;
import com.movies.movies_app.rest.ActorWS;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class ActorWSTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestActor.jar")
				.addClasses(ActorDAO.class, Actor.class,
						JaxRsActivator.class, UtilsDAO.class, ActorWS.class,
						MoviesDirector.class, MoviesGenre.class, Role.class)
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	 
	@EJB
	private ActorWS actorWS;
	
	@EJB
	private ActorDAO actorDAO;
	
	@EJB
	private UtilsDAO utilsDAO;
	 
	@Before
	public void setUp() {
		utilsDAO.deleteTableActor();
		Actor actor = new Actor();
		actor.setId(745996);
		actor.setFirstName("Amy");
		actor.setLastName("Paisley");
		actor.setGender("F");
		actorDAO.save(actor);
		actor = new Actor();
		actor.setId(28593);
		actor.setFirstName("Tom");
		actor.setLastName("Hanks");
		actor.setGender("M");
		actorDAO.save(actor);
	}
	
	@Test
	public void testGetAllActorsWS() {
		Response response = actorWS.listAll();
		List<Actor> actorList = (List<Actor>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", actorList.size(), 2);
		Actor actor = actorList.get(0);
		assertEquals(745996, actor.getId());
		assertEquals("Amy", actor.getFirstName());
		assertEquals("Paisley", actor.getLastName());
		assertEquals("F", actor.getGender());
	}
	
	@Test
	public void testFindById() {
		Response response = actorWS.findById(745996);
		Actor actor = (Actor) response.getEntity();
		assertEquals(745996, actor.getId());
		assertEquals("Amy", actor.getFirstName());
		assertEquals("Paisley", actor.getLastName());
		assertEquals("F", actor.getGender());
	}
	
	@Test
	public void testAddActor() {
		Actor actor = new Actor();
		actor.setId(1234);
		actor.setFirstName("John");
		actor.setLastName("Wayne");
		actor.setGender("M");
		Response response = actorWS.create(actor);
		assertEquals(HttpStatus.SC_CREATED, response.getStatus());
		actor = (Actor) response.getEntity();
		assertEquals(1234, actor.getId());
		assertEquals("John", actor.getFirstName());
		assertEquals("Wayne", actor.getLastName());
		assertEquals("M", actor.getGender());
	}
	
	@Test
	public void testRemoveActor() {
		Response response = actorWS.listAll();
		List<Actor> actorList = (List<Actor>) response.getEntity();
		assertEquals("Data fetch = data persisted", actorList.size(), 2);
		actorWS.deleteActorById(28593);
		actorList = (List<Actor>) response.getEntity();
		assertEquals(actorList.size(), 2);
		response = actorWS.findById(28593);
		Actor actor = (Actor) response.getEntity();
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
		assertEquals(null, actor);
	}
	
	@Test
	public void testUpdateActor() {
		Response response = actorWS.findById(745996);
		Actor actor = (Actor) response.getEntity();
		actor.setFirstName("Jennifer");
		actor.setLastName("Lawrence");
		response = actorWS.update(actor);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		actor = (Actor) response.getEntity();
		assertEquals(745996, actor.getId());
		assertEquals("Jennifer", actor.getFirstName());
		assertEquals("Lawrence", actor.getLastName());
		assertEquals("F", actor.getGender());
	}
	
	@Test
	public void testSearchActorByName() {
		Response response = actorWS.findByName("Amy");
		List<Actor> actorList = (List<Actor>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals(actorList.size(), 1);
		Actor actor = actorList.get(0);
		assertEquals(745996, actor.getId());
		assertEquals("Amy", actor.getFirstName());
		assertEquals("Paisley", actor.getLastName());
		assertEquals("F", actor.getGender());

	}
	
}
