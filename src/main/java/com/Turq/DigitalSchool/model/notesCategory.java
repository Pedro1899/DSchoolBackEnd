package com.Turq.DigitalSchool.model;

import javax.persistence.*;

@Entity
@Table
public class notesCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column
	private String CategoryDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_idschool")
	private school school;

	public notesCategory(String categoryDescription, com.Turq.DigitalSchool.model.school school) {
		super();
		CategoryDescription = categoryDescription;
		this.school = school;
	}

	public notesCategory() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCategoryDescription() {
		return CategoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		CategoryDescription = categoryDescription;
	}

	public school getSchool() {
		return school;
	}

	public void setSchool(school school) {
		this.school = school;
	}
	
	


}
