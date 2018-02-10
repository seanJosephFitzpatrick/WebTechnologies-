package com.movies.movies_app.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MoviesGenre.class)
public abstract class MoviesGenre_ {

	public static volatile SingularAttribute<MoviesGenre, String> genre;
	public static volatile SingularAttribute<MoviesGenre, Integer> movieId;
	public static volatile SingularAttribute<MoviesGenre, Integer> id;

}

