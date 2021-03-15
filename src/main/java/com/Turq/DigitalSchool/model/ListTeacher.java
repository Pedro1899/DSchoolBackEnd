package com.Turq.DigitalSchool.model;


import javax.persistence.*;


public class ListTeacher {

	private Long idTeacher;
	
	private String name;
	
	private String surname;

	private String picture;
	

	
	
	
	public ListTeacher() {
		super();
	}





	public ListTeacher(Long idTeacher, String name, String surname, String picture) {
		super();
		this.idTeacher = idTeacher;
		this.name = name;
		this.surname = surname;
		this.picture = picture;
	}





	public Long getIdTeacher() {
		return idTeacher;
	}





	public void setIdTeacher(Long idTeacher) {
		this.idTeacher = idTeacher;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getSurname() {
		return surname;
	}





	public void setSurname(String surname) {
		this.surname = surname;
	}





	public String getPicture() {
		return picture;
	}





	public void setPicture(String picture) {
		this.picture = picture;
	}
}