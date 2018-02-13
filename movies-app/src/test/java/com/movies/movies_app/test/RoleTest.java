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
import com.movies.movies_app.data.RoleDAO;
import com.movies.movies_app.model.Actor;
import com.movies.movies_app.model.Role;
import com.movies.movies_app.rest.ActorWS;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.rest.RoleWS;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class RoleTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestRole.jar")
				.addClasses(RoleDAO.class, Role.class,
						JaxRsActivator.class, UtilsDAO.class, RoleWS.class)
			//	.addPackage(Actor.class.getPackage())
			//	.addPackage(ActorDAO.class.getPackage())
						//this line will pick up the production db
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	 
	@EJB
	private RoleWS roleWS;
	
	@EJB
	private RoleDAO roleDAO;
	
	@EJB
	private UtilsDAO utilsDAO;
	 
	@Before
	public void setUp() {
		//this function means that we start with an empty table
		//And add one actors
		//it should be possible to test with an in memory db for efficiency
		utilsDAO.deleteTableRole();
		Role role = new Role();
		role.setActorId(18826);
		role.setMovieId(322929);
		role.setRole("Himself");
		roleDAO.save(role);
	}
	
	@Test
	public void testGetAllRoles() {
		List<Role> roleList = roleDAO.getAllRoles();
		assertEquals("Data fetch = data persisted", roleList.size(), 1);
	}
	
}
