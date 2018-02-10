package com.movies.movies_app.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MoviesDirector.class)
public abstract class MoviesDirector_ {

	public static volatile SingularAttribute<MoviesDirector, Integer> id;
	public static volatile SingularAttribute<MoviesDirector, Integer> movieId;
	public static volatile SingularAttribute<MoviesDirector, Integer> directorId;

}

