package com.Turq.DigitalSchool.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class user {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdUser;
	
	@Column(name = "username", unique =true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "category")
	private int category;
	
	@Column(name = "picture")
	private String picture;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="school_idSchool")
	private school school;
	
	

	public user() {
		super();
	}

	public user(String username, String password, int category, com.Turq.DigitalSchool.model.school school, String picture) {
		super();
		this.username = username;
		this.password = password;
		this.category = category;
		this.school = school;
		this.picture = picture;
	}

	public Long getIdUser() {
		return IdUser;
	}

	public void setIdUser(Long idUser) {
		IdUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public school getSchool() {
		return school;
	}

	public void setSchool(school school) {
		this.school = school;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}	
	
	

}
