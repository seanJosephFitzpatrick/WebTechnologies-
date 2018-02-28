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
import com.movies.movies_app.data.ActorDAO;
import com.movies.movies_app.model.Actor;
import com.movies.movies_app.model.MoviesDirector;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.model.Role;
import com.movies.movies_app.rest.ActorWS;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class ActorTest {
	
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
	}
	
	@Test
	public void testGetAllActors() {
		List<Actor> actorList = actorDAO.getAllActors();
		assertEquals("Data fetch = data persisted", actorList.size(), 1);
	}
	
}
