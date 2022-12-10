package com.tutorials.jdbc.bo;

public class Movies
{
	private int movie_id;
	
	private String movie_code;

	private String movie_name;

	private String genre;
	
	private String cast ;
	
	private String director;
	
	private String crew;
	
	/**
	 * @param movieid
	 * @param moviename
	 * @param genre
	 * @param cast
	 * @param director
	 * @param crew
	 * @param language
	 * @param releasedate
	 * @param music
	 * @param rateing
	 */
	
	private String language;

	private String release_date;

	private String music;
	
	private String rateing;
	


	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_code() {
		return movie_code;
	}

	public void setMovie_code(String movie_code) {
		this.movie_code = movie_code;
	}
	

	
	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getRateing() {
		return rateing;
	}

	public void setRateing(String rateing) {
		this.rateing = rateing;
	}
	public Movies() {
		System.out.println("Movie() - instantiated..");
	}
	public Movies(int  movie_id,String movie_code, String movie_name, String genre, String cast, String director, String crew,String language, String release_date, String music, String rateing ) {
		System.out.println("Movies(  movie_id, movie_code,  movie_name,  genre,  cast,  director,  crew, language,  release_date,  music, rateing ) - instantiated..");
	
		
		this.movie_id=movie_id;
		this.movie_code = movie_code;
		this.movie_name = movie_name;
		this.genre = genre;
		this.cast = cast;
		this.director = director;
		this.crew = crew;
		this.language = language;
		this.release_date = release_date;
		this.music = music;
		this.rateing = rateing;
	}
	public String toString() {
		return "[Movie] hashCode=" + this.hashCode()
			+ ", MovieId = " + movie_id  
			+",MovieCode ="+ movie_code
			+ ", MovieName = " + movie_name 
			+ ", Genre = " +genre
			+ ", Cast ="+ cast
			+ ", Director = " + director
			+ ", Crew ="+ crew 
			+ ", Language="+ language 
			+ ", Release_date ="+release_date 
			+",Music ="+music
			+",Rateing ="+rateing;
		    
	}
	}

	
