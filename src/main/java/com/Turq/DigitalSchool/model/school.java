package com.Turq.DigitalSchool.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="school" )
public class school {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSchool;
	
	@Column(name="name")
	private String name;
	
	@Column(name="adress")
	private String address;
	
	@Column(name="email")
	private String email;
	
	@Column(name="idCountry")
	private int idCountry;
	
	
	@Column(name="Logo")
	private String Logo;
	
	@Column(name="dateStart")
	private Date dateStart;
	
	@Column(name="dateFinish")
	private Date dateFinish;
	
	
	@Column(name="cycle")
	private int cycle;
	
	@Column(name="Language")
	private int Language;
	
	
	
	public school() {
		super();
	}
	public school(String name, String address, String email, int idCountry) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.idCountry = idCountry;
	}
	public Long getIdSchool() {
		return idSchool;
	}
	public void setIdSchool(Long idSchool) {
		this.idSchool = idSchool;
	}
	public String getName() {
		return name;
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
	public int getIdCountry() {
		return idCountry;
	}
	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}
	
	
	public String getLogo() {
		return Logo;
	}
	
	public void setLogo(String Logo) {
		this.Logo = Logo;
	}
	
	public Date getDateStart(){
		return dateStart;
	}
	
	public void setDateStart(Date dateStart){
		this.dateStart = dateStart;
	}
	public Date getDatefinish(){
		return dateFinish;
	}
	
	public void setDateFinish(Date dateFinish){
		this.dateFinish = dateFinish;
	}
	
	
	public int getLanguage(){
		return Language;
	}
	
	public void setLanguage(int Language){
		this.Language = Language;
	}
	
	public int getCicle(){
		return cycle;
	}
	
	public void setCicle(int Cicle){
		this.cycle = Cicle;
	}
	
	
	
	
	
}
