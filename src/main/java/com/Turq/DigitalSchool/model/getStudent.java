package com.Turq.DigitalSchool.model;




import javax.persistence.*;




public class getStudent {

	
	private Long Id;
	
	
	private String name;
	
	
	private String surname;
	
	
	private String birthday;
	
	
	
	private String photo;
	

	private Long idTutor;

	
	
	private Long idGrade;

	
	

	public getStudent() {
		super();
	}


	public getStudent(Long id, String name, String surname, String birthday, String photo, Long idTutor, Long idGrade) {
		super();
		Id = id;
		this.name = name;
		this.birthday = birthday;
		this.photo = photo;
		this.idTutor = idTutor;
		this.idGrade = idGrade;
		this.surname = surname;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
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
	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Long getIdTutor() {
		return idTutor;
	}


	public void setIdTutor(Long idTutor) {
		this.idTutor = idTutor;
	}


	public Long getIdGrade() {
		return idGrade;
	}


	public void setIdGrade(Long idGrade) {
		this.idGrade = idGrade;
	}

	
	
	
}