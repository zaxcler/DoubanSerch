package com.zaxcler.infos;

import java.io.Serializable;
import java.util.List;

public class MovieInfo implements Serializable{
	private int socre;
	private String genres;
	private int collect_count;
	private List<Avatars> actors;
	private String name;
	private List<Avatars> directors;
	private String time;
	private String photo_url;
	private String describe;
	private String id;

	public MovieInfo() {
		// TODO Auto-generated constructor stub
	}

	public int getSocre() {
		return socre;
	}

	public void setSocre(int socre) {
		this.socre = socre;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public int getCollect_count() {
		return collect_count;
	}

	public void setCollect_count(int collect_count) {
		this.collect_count = collect_count;
	}

	public List<Avatars> getActors() {
		return actors;
	}

	public void setActors(List<Avatars> actors) {
		this.actors = actors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Avatars> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Avatars> directors) {
		this.directors = directors;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
