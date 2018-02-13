package com.movies.movies_app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the movies_directors database table.
 * 
 */
@Entity
@Table(name="movies_directors")
@NamedQuery(name="MovieDirector.findAll", query="SELECT m FROM MovieDirector m")
public class MovieDirector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="director_id")
	private int directorId;

	@Column(name="movie_id")
	private int movieId;

	public MovieDirector() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDirectorId() {
		return this.directorId;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}

	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

}