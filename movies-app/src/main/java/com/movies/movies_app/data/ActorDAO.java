package com.movies.movies_app.data;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.movies.movies_app.model.Actor;

@Stateless
@LocalBean
public class ActorDAO {
	
    @PersistenceContext
    private EntityManager em;
    
	public List<Actor> getAllActors() {
    	Query query=em.createQuery("SELECT w FROM Actor w");
        return query.getResultList();
    }
	
	public List<Actor> getActorsByName(String name) {
    	Query query=em.createQuery("SELECT w FROM Actor AS w "+
    								"WHERE w.name LIKE ?1");
    	query.setParameter(1, "%"+name.toUpperCase()+"%");
        return query.getResultList(); 
    }
	
	public Actor getActor(int id ) {
        return em.find(Actor.class, id); 
    }
	
	public void save(Actor actor){
		em.persist(actor);
	}
	
	public void update(Actor actor) {
		em.merge(actor);
	}
	
	public void delete(int id) {
		em.remove(getActor(id));
	}
	public void deleteTable(){
		em.createQuery("DELETE FROM Actor").executeUpdate();
	}

}
