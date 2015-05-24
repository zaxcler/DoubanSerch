package com.zaxcler.infos;

import java.io.Serializable;

public class MusicInfo implements Serializable{
	private String music_id;
	private String music_score;
	private String music_author;
	private String music_photo;
	private String music_genres;
	private String music_name;
	
	public MusicInfo() {
		
	}

	public String getMusic_id() {
		return music_id;
	}

	public void setMusic_id(String music_id) {
		this.music_id = music_id;
	}

	public String getMusic_score() {
		return music_score;
	}

	public void setMusic_score(String music_score) {
		this.music_score = music_score;
	}

	public String getMusic_author() {
		return music_author;
	}

	public void setMusic_author(String music_author) {
		this.music_author = music_author;
	}

	public String getMusic_photo() {
		return music_photo;
	}

	public void setMusic_photo(String music_photo) {
		this.music_photo = music_photo;
	}

	public String getMusic_genres() {
		return music_genres;
	}

	public void setMusic_genres(String music_genres) {
		this.music_genres = music_genres;
	}

	public String getMusic_name() {
		return music_name;
	}

	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	

}
