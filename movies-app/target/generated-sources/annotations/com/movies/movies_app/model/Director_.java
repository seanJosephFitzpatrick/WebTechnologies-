package com.movies.movies_app.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Director.class)
public abstract class Director_ {

	public static volatile SingularAttribute<Director, Integer> id;
	public static volatile SingularAttribute<Director, String> lastName;
	public static volatile SingularAttribute<Director, String> firstName;

}

