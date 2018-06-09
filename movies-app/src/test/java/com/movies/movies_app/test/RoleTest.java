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
import com.movies.movies_app.data.RoleDAO;
import com.movies.movies_app.model.MoviesDirector;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.model.Role;
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
						JaxRsActivator.class, UtilsDAO.class, RoleWS.class, MoviesDirector.class,
						MoviesGenre.class)
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
		utilsDAO.deleteTableRole();
		Role role = new Role();
		role.setId(0);
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
