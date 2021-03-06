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
		em.createQuery("DELETE FROM MoviesDirector").executeUpdate();
		em.createNativeQuery("ALTER TABLE movies_directors AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
	
	public void deleteTableMoviesGenre(){
		em.createQuery("DELETE FROM MoviesGenre").executeUpdate();
		em.createNativeQuery("ALTER TABLE movies_genres AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
	
	public void deleteTableMovies(){
		em.createQuery("DELETE FROM Movie").executeUpdate();
		em.createNativeQuery("ALTER TABLE movies AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
	public void deleteTableRole(){
		em.createQuery("DELETE FROM Role").executeUpdate();
		em.createNativeQuery("ALTER TABLE roles AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
      
}
