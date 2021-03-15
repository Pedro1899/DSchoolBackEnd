package com.Turq.DigitalSchool.model;

import java.util.List;

public class StudentDetails {
	
	private Long id;
	
	private String name;
	
	private String surname;
	
	private String bday;
	
	private String picture;
	
	private String Grade;
	
	private List<ActivityDetail> activities;
	
	private List<notesCoursesDetails> notes;

	public StudentDetails(Long id, String name,String surname, String bday, String picture, String grade
			) {
		super();
		this.id = id;
		this.name = name;
		this.bday = bday;
		this.picture = picture;
		Grade = grade;
		this.surname = surname;
	}

	
	
	
	public StudentDetails() {
		super();
	}




	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBday() {
		return bday;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGrade() {
		return Grade;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}

	public List<ActivityDetail> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityDetail> activities) {
		this.activities = activities;
	}

	public List<notesCoursesDetails> getNotes() {
		return notes;
	}

	public void setNotes(List<notesCoursesDetails> notes) {
		this.notes = notes;
	}
	
	
	
	
	
	

}
