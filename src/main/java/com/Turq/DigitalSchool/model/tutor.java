package com.Turq.DigitalSchool.model;

import javax.persistence.*;

@Entity
@Table(name="tutor")
public class tutor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private String address;
	
	@Column 
	private String email;
	
	@Column
	private Long phone;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_IdUser")
	private user user;


	
	

	public tutor() {
		super();
	}



	public tutor(String name, String surname, String address, String email, Long phone, com.Turq.DigitalSchool.model.user user) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.user = user;
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



	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	public String getSurname() {
		return surname;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Long getPhone() {
		return phone;
	}



	public void setPhone(Long phone) {
		this.phone = phone;
	}



	public user getUser() {
		return user;
	}



	public void setUser(user user) {
		this.user = user;
	}
	
	
	
	
	
}
