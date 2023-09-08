package com.example.demo.model;


import java.util.Arrays;

import org.springframework.data.annotation.Id;


public class Image {

	@Id
	
	private String id;
	private String name;
	private String description;
	
	private byte[] image;
	private long userId;

	public Image() {
		super();
	}

	public Image(String id, String name, String description, byte[] image, long userId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.userId = userId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", description=" + description + ", image="
				+ Arrays.toString(image) + ", userId=" + userId + "]";
	}

}
