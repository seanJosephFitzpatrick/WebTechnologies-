package com.movies.movies_app.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MovieDirector.class)
public abstract class MoviesDirector_ {

	public static volatile SingularAttribute<MovieDirector, Integer> id;
	public static volatile SingularAttribute<MovieDirector, Integer> movieId;
	public static volatile SingularAttribute<MovieDirector, Integer> directorId;

}

