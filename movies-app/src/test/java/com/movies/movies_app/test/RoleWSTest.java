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
import com.movies.movies_app.data.RoleDAO;
import com.movies.movies_app.model.Role;
import com.movies.movies_app.model.MoviesDirector;
import com.movies.movies_app.model.MoviesGenre;
import com.movies.movies_app.model.Role;
import com.movies.movies_app.rest.RoleWS;
import com.movies.movies_app.rest.JaxRsActivator;
import com.movies.movies_app.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class RoleWSTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "TestRole.jar")
				.addClasses(RoleDAO.class, Role.class,
						JaxRsActivator.class, UtilsDAO.class, RoleWS.class,
						MoviesDirector.class, MoviesGenre.class, Role.class)
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
		role.setId(2);
		role.setMovieId(14);
		role.setActorId(28593);
		role.setRole("Cobb");
		roleDAO.save(role);
		role = new Role();
		role.setId(3);
		role.setMovieId(30);
		role.setActorId(745996);
		role.setRole("Cooper");
		roleDAO.save(role);
	}
	
	@Test
	public void testGetAllRolesWS() {
		Response response = roleWS.listAll();
		List<Role> roleList = (List<Role>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", roleList.size(), 2);
		Role role = roleList.get(0);
		assertEquals(2, role.getId());
		assertEquals(14, role.getMovieId());
		assertEquals(28593, role.getActorId());
		assertEquals("Cobb", role.getRole());
	}
	
	@Test
	public void testFindById() {
		Response response = roleWS.findById(2);
		Role role = (Role) response.getEntity();
		assertEquals(2, role.getId());
		assertEquals(14, role.getMovieId());
		assertEquals(28593, role.getActorId());
		assertEquals("Cobb", role.getRole());
	}
	
	@Test
	public void testAddRole() {
		Role role = new Role();
		role.setId(4);
		role.setMovieId(27);
		role.setActorId(47734);
		role.setRole("Cowboy");
		Response response = roleWS.create(role);
		assertEquals(HttpStatus.SC_CREATED, response.getStatus());
		role = (Role) response.getEntity();
		assertEquals(4, role.getId());
		assertEquals(27, role.getMovieId());
		assertEquals(47734, role.getActorId());
		assertEquals("Cowboy", role.getRole());
	}
	
	@Test
	public void testRemoveRole() {
		Response response = roleWS.listAll();
		List<Role> roleList = (List<Role>) response.getEntity();
		assertEquals("Data fetch = data persisted", roleList.size(), 2);
		roleWS.deleteRoleById(3);
		roleList = (List<Role>) response.getEntity();
		assertEquals(roleList.size(), 2);
		response = roleWS.findById(3);
		Role role = (Role) response.getEntity();
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
		assertEquals(null, role);
	}
	
	@Test
	public void testUpdateRole() {
		Response response = roleWS.findById(2);
		Role role = (Role) response.getEntity();
		role.setId(2);
		role.setMovieId(14);
		role.setActorId(28593);
		role.setRole("Himself");
		roleDAO.update(role);
		response = roleWS.update(role);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		role = (Role) response.getEntity();
		assertEquals(2, role.getId());
		assertEquals(14, role.getMovieId());
		assertEquals(28593, role.getActorId());
		assertEquals("Himself", role.getRole());
	}
	
	@Test
	public void testSearchRoleByID() {
		Response response = roleWS.findByName("Cobb");
		List<Role> roleList = (List<Role>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals(roleList.size(), 1);
		Role role = roleList.get(0);
		assertEquals(2, role.getId());
		assertEquals(14, role.getMovieId());
		assertEquals(28593, role.getActorId());
		assertEquals("Cobb", role.getRole());

	}
	
}
