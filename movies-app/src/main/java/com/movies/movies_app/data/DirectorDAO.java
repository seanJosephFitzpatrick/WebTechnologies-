package com.movies.movies_app.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.movies.movies_app.model.Actor;
import com.movies.movies_app.model.Director;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@LocalBean
public class DirectorDAO {
	
    @PersistenceContext
    private EntityManager em;
    
	public List<Director> getAllDirector() {
    	Query query=em.createQuery("SELECT w FROM Director w");
        return query.getResultList();
    }
	
	public List<Director> getDirectorsByName(String name) {
    	Query query=em.createQuery("SELECT w FROM Director AS w "+
    								"WHERE w.name LIKE ?1");
    	query.setParameter(1, "%"+name.toUpperCase()+"%");
        return query.getResultList();
    }
	
	public Director getDirector(int id ) {
        return em.find(Director.class, id);
    }
	
	public void save(Director director){
		em.persist(director);
	}
	
	public void update(Director director) {
		em.merge(director);
	}
	
	public void delete(int id) {
		em.remove(getDirector(id));
	}
	public void deleteTable(){
		em.createQuery("DELETE FROM Director").executeUpdate();
	}

}
