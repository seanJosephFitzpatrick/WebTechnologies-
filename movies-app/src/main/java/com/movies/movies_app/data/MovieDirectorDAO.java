package com.movies.movies_app.data;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.movies.movies_app.model.MoviesDirector;


@Stateless
@LocalBean
public class MovieDirectorDAO {
	
    @PersistenceContext
    private EntityManager em;
    
	public List<MoviesDirector> getAllMovieDirectors() {
    	Query query=em.createQuery("SELECT w FROM MoviesDirector w");
        return query.getResultList();
    }
	
	public List<MoviesDirector> getMovieDirectorsByName(String name) {
    	Query query=em.createQuery("SELECT w FROM MoviesDirector AS w "+
    								"WHERE w.name LIKE ?1");
    	query.setParameter(1, "%"+name.toUpperCase()+"%");
        return query.getResultList();
    }
	
	public MoviesDirector getMovieDirector(int id ) {
        return em.find(MoviesDirector.class, id);
    }
	
	public void save(MoviesDirector movieDirector){
		em.persist(movieDirector);
	}
	
	public void update(MoviesDirector movieDirector) {
		em.merge(movieDirector);
	}
	
	public void delete(int id) {
		em.remove(getMovieDirector(id));
	}
	public void deleteTable(){
		em.createQuery("DELETE FROM MoviesDirector").executeUpdate();
	}

}
