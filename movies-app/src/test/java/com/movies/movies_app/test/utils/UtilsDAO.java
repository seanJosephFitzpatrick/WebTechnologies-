package com.movies.movies_app.test.utils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UtilsDAO {

    @PersistenceContext
    private EntityManager em;
    
	public void deleteTableActor(){
		em.createQuery("DELETE FROM Actor").executeUpdate();
		em.createNativeQuery("ALTER TABLE actors AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
	
	public void deleteTableDirector(){
		em.createQuery("DELETE FROM Director").executeUpdate();
		em.createNativeQuery("ALTER TABLE directors AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
	
	public void deleteTableMovieDirector(){
		em.createQuery("DELETE FROM MovieDirector").executeUpdate();
		em.createNativeQuery("ALTER TABLE movies_directors AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
      
}
