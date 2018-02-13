package com.movies.movies_app.data;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.movies.movies_app.model.MovieDirector;


@Stateless
@LocalBean
public class MovieDirectorDAO {
	
    @PersistenceContext
    private EntityManager em;
    
	public List<MovieDirector> getAllMovieDirectors() {
    	Query query=em.createQuery("SELECT w FROM MovieDirector w");
        return query.getResultList();
    }
	
	public List<MovieDirector> getMovieDirectorsByName(String name) {
    	Query query=em.createQuery("SELECT w FROM MovieDirector AS w "+
    								"WHERE w.name LIKE ?1");
    	query.setParameter(1, "%"+name.toUpperCase()+"%");
        return query.getResultList();
    }
	
	public MovieDirector getMovieDirector(int id ) {
        return em.find(MovieDirector.class, id);
    }
	
	public void save(MovieDirector movieDirector){
		em.persist(movieDirector);
	}
	
	public void update(MovieDirector movieDirector) {
		em.merge(movieDirector);
	}
	
	public void delete(int id) {
		em.remove(getMovieDirector(id));
	}
	public void deleteTable(){
		em.createQuery("DELETE FROM MovieDirector").executeUpdate();
	}

}
