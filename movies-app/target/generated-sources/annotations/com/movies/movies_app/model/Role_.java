package com.movies.movies_app.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile SingularAttribute<Role, Integer> actorId;
	public static volatile SingularAttribute<Role, String> role;
	public static volatile SingularAttribute<Role, Integer> movieId;
	public static volatile SingularAttribute<Role, Integer> id;

}

