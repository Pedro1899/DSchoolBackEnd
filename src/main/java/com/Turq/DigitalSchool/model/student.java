package com.Turq.DigitalSchool.model;




import javax.persistence.*;



@Entity
@Table
public class student {

	@Id
	private Long Id;
	
	@Column 
	private String name;
	
	@Column 
	private String surname;
	
	@Column
	private String birthday;
	
	
	@Column
	private String photo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tutor_id")
	private tutor idTutor;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="grade_id")
	private grade idGrade;

	
	

	public student() {
		super();
	}


	public student(Long id, String name, String surname, String birthday, String photo, tutor idTutor, grade idGrade) {
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


	public tutor getIdTutor() {
		return idTutor;
	}


	public void setIdTutor(tutor idTutor) {
		this.idTutor = idTutor;
	}


	public grade getIdGrade() {
		return idGrade;
	}


	public void setIdGrade(grade idGrade) {
		this.idGrade = idGrade;
	}

	
	
	
}