package com.movies.movies_app.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Movie.class)
public abstract class Movie_ {

	public static volatile SingularAttribute<Movie, Integer> year;
	public static volatile SingularAttribute<Movie, String> name;
	public static volatile SingularAttribute<Movie, Float> rank;
	public static volatile SingularAttribute<Movie, Integer> id;

}

