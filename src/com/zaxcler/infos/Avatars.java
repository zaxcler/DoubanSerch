package com.zaxcler.infos;

import java.io.Serializable;

public class Avatars implements Serializable {
	private String photo_url;
	private String alt;
	private String id;
	private String name;
	public Avatars() {
		// TODO Auto-generated constructor stub
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
