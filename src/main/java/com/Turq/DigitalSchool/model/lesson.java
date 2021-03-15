package com.Turq.DigitalSchool.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"Description", "school_idschool"})
	}) 
public class lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	
	private String Description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_idschool")
	private school school;

	public lesson(String description, com.Turq.DigitalSchool.model.school school) {
		super();
		Description = description;
		this.school = school;
	}

	public lesson() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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
