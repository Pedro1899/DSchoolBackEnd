package com.Turq.DigitalSchool.model;


import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTeacher;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="address")
	private String adress;
	
	@Column(name="phone")
	private Long phone;
	
	
	
	@Column(name="email", unique=true)
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_IdUser")
	private user user;

	
	
	
	public teacher() {
		super();
	}

	public teacher(String name, String surname, String adress, String email, Long phone,  com.Turq.DigitalSchool.model.user user) {
		super();
		this.name = name;
		this.surname = surname;
		this.adress = adress;
		this.email = email;
		this.user = user;
		this.phone =  phone;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public user getUser() {
		return user;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	public Long getPhone() {
		return phone;
	}

	public void setUser(user user) {
		this.user = user;
	}
	

}
