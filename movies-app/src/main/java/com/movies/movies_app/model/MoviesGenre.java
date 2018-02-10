package com.movies.movies_app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the movies_genres database table.
 * 
 */
@Entity
@Table(name="movies_genres")
@NamedQuery(name="MoviesGenre.findAll", query="SELECT m FROM MoviesGenre m")
public class MoviesGenre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String genre;

	@Column(name="movie_id")
	private int movieId;

	public MoviesGenre() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

}