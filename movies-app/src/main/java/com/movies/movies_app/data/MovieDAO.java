package com.movies.movies_app.data;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.movies.movies_app.model.Movie;

@Stateless
@LocalBean
public class MovieDAO {
	
    @PersistenceContext
    private EntityManager em;
    
	public List<Movie> getAllMovies() {
    	Query query=em.createQuery("SELECT w FROM Movie w");
        return query.getResultList();
    }
	
	public List<Movie> getMoviesByName(String name) {
    	Query query=em.createQuery("SELECT w FROM Movie AS w "+
    								"WHERE w.name LIKE ?1");
    	query.setParameter(1, "%"+name.toUpperCase()+"%");
        return query.getResultList();
    }
	
	public Movie getMovie(int id ) {
        return em.find(Movie.class, id);
    }
	
	public void save(Movie movie){
		em.persist(movie);
	}
	
	public void update(Movie movie) {
		em.merge(movie);
	}
	
	public void delete(int id) {
		em.remove(getMovie(id));
	}
	public void deleteTable(){
		em.createQuery("DELETE FROM Movie").executeUpdate();
	}

}
