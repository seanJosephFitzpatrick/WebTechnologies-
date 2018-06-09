package com.movies.movies_app.data;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.movies.movies_app.model.MoviesGenre;


@Stateless
@LocalBean
public class MoviesGenreDAO {
	
    @PersistenceContext
    private EntityManager em;
    
	public List<MoviesGenre> getAllMoviesGenres() {
    	Query query=em.createQuery("SELECT w FROM MoviesGenre w");
        return query.getResultList();
    }
	
	public List<MoviesGenre> getMoviesGenresByName(String name) {
    	Query query=em.createQuery("SELECT w FROM MoviesGenre AS w "+
    								"WHERE w.genre LIKE ?1");
    	query.setParameter(1, "%"+name.toUpperCase()+"%");
        return query.getResultList();
    }
	
	public MoviesGenre getMoviesGenre(int id ) {
        return em.find(MoviesGenre.class, id);
    }
	
	public void save(MoviesGenre moviesGenre){
		em.persist(moviesGenre);
	}
	
	public void update(MoviesGenre moviesGenre) {
		em.merge(moviesGenre);
	}
	
	public void delete(int id) {
		em.remove(getMoviesGenre(id));
	}
	public void deleteTable(){
		em.createQuery("DELETE FROM MoviesGenre").executeUpdate();
	}

}
