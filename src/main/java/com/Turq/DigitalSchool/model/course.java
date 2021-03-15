package com.Turq.DigitalSchool.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"grade_idGrade", "lesson_idlesson"})
	}) 
public class course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_idGrade")
	private grade idGrade;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lesson_idlesson")
	private lesson idLesson;

	
	

	public course() {
		super();
	}


	public course(grade idGrade, lesson idLesson) {
		super();
		this.idGrade = idGrade;
		this.idLesson = idLesson;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public grade getIdGrade() {
		return idGrade;
	}


	
	
	
	
	public void setIdGrade(grade idGrade) {
		this.idGrade = idGrade;
	}


	public lesson getIdLesson() {
		return idLesson;
	}


	public void setIdLesson(lesson idLesson) {
		this.idLesson = idLesson;
	}
	
	
	
	
	
	
}
