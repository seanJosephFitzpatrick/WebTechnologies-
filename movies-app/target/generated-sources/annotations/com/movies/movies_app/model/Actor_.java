package com.movies.movies_app.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Actor.class)
public abstract class Actor_ {

	public static volatile SingularAttribute<Actor, String> firstName;
	public static volatile SingularAttribute<Actor, String> lastName;
	public static volatile SingularAttribute<Actor, String> gender;
	public static volatile SingularAttribute<Actor, Integer> id;

}

