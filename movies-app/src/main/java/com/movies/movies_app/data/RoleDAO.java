package com.movies.movies_app.data;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.movies.movies_app.model.Role;

@Stateless
@LocalBean
public class RoleDAO {
	
    @PersistenceContext
    private EntityManager em;
    
	public List<Role> getAllRoles() {
    	Query query=em.createQuery("SELECT w FROM Role w");
        return query.getResultList();
    }
	
	public List<Role> getRolesByName(String name) {
    	Query query=em.createQuery("SELECT w FROM Role AS w "+
    								"WHERE w.role LIKE ?1");
    	query.setParameter(1, "%"+name.toUpperCase()+"%");
        return query.getResultList();
    }
	
	public Role getRole(int id ) {
        return em.find(Role.class, id);
    }
	
	public void save(Role role){
		em.persist(role);
	}
	
	public void update(Role role) {
		em.merge(role);
	}
	
	public void delete(int id) {
		em.remove(getRole(id));
	}
	public void deleteTable(){
		em.createQuery("DELETE FROM Role").executeUpdate();
	}

}
