package com.Turq.DigitalSchool.model;

import java.util.List;

public class ListUsersDetail {

	private Long idUser;
	
	private Long IdTeacher_Tutor;
	
	private String name;
	
	private String surname;
	
	private String username;
	
	private int category;
	
	private String email;
	
	private String address;
	
	private String picture;
	
	private Long phone;
	
	private List<ListCoursesbyTeacher> courses;
	
	private boolean flagActivity;
	
	private List<StudentDetails> childrens;
	
	public boolean flagChildren;

	public ListUsersDetail(Long idUser, Long idTeacher_Tutor, String name, String surname, String username, int category,
			String email, String address, Long phone, String picture) {
		super();
		
		this.category=category;		
		this.idUser = idUser;
		IdTeacher_Tutor = idTeacher_Tutor;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.picture = picture;
		this.flagActivity = false;
		this.flagChildren = false;
	}

	

	
	public ListUsersDetail() {
		super();
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdTeacher_Tutor() {
		return IdTeacher_Tutor;
	}

	public void setIdTeacher_Tutor(Long idTeacher_Tutor) {
		IdTeacher_Tutor = idTeacher_Tutor;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCategory() {
		
		return category;
		
	}

	public void setCategory(int category) {
		this.category=category;
		
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}	
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}	
	
	public List<ListCoursesbyTeacher> getCourses() {
		return courses;
	}

	public void setCourses(List<ListCoursesbyTeacher> courses) {
		this.courses = courses;
	}	
	

	public boolean getFlagActivity() {
		return flagActivity;
	}

	public void setFlagActivity(boolean factivity) {
		this.flagActivity = factivity;
	}	
	
	
	public boolean getFlagChildren() {
		return flagChildren;
	}

	public void setFlagChildren(boolean children) {
		this.flagChildren = children;
	}	
	

	public List<StudentDetails> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<StudentDetails> childrens) {
		this.childrens = childrens;
	}	
	
}