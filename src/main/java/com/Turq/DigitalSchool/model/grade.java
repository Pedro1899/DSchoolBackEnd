package com.Turq.DigitalSchool.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"Description", "school_idschool"})
	}) 
public class grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Idgrade;
	
	@Column
	private String Description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_idschool")
	private school school;

	
	
	
	public grade() {
		super();
	}

	public grade(String description, com.Turq.DigitalSchool.model.school school) {
		super();
		Description = description;
		this.school = school;
	}

	public Long getIdgrade() {
		return Idgrade;
	}

	public void setIdgrade(Long idgrade) {
		Idgrade = idgrade;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public school getSchool() {
		return school;
	}

	public void setSchool(school school) {
		this.school = school;
	}
	
	
	
	
	
	
	
	
}
