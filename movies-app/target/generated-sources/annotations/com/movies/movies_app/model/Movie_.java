package com.movies.movies_app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movie.class)
public abstract class Movie_ {

	public static volatile SingularAttribute<Movie, Integer> id;
	public static volatile SingularAttribute<Movie, Float> rank;
	public static volatile SingularAttribute<Movie, String> name;
	public static volatile SingularAttribute<Movie, Integer> year;

}

