package com.Turq.DigitalSchool.model;

import java.util.List;

import ch.qos.logback.core.joran.action.Action;

public class ListCoursesbyTeacher {

	private Long id_Course;
	
	private String NameGrade;
	
	private String NameLesson;
	
	private List<ActivityDetail> activities;
	
	private Long idGrade;
	
	private Long idLesson;
	
	public ListCoursesbyTeacher(Long id_Course, String nameGrade, String nameLesson) {
		super();
		this.id_Course = id_Course;
		NameGrade = nameGrade;
		NameLesson = nameLesson;
		
	}
	
	public ListCoursesbyTeacher(Long id_Course, String nameGrade, String nameLesson, Long idGrade, Long idLesson) {
		super();
		this.id_Course = id_Course;
		NameGrade = nameGrade;
		NameLesson = nameLesson;
		this.idLesson = idLesson;
		this.idGrade = idGrade;
		
	}

	public ListCoursesbyTeacher() {
		super();
	}

	public Long getId_Course() {
		return id_Course;
	}

	public void setId_Course(Long id_Course) {
		this.id_Course = id_Course;
	}
	
	
	public Long getIdGrade() {
		return idGrade;
	}

	public void setIdGrade(Long idGrade) {
		this.idGrade = idGrade;
	}
	
	
	public Long getIdLesson() {
		return idLesson;
	}

	public void setIdLesson(Long idLesson) {
		this.idLesson = idLesson;
	}


	public String getNameGrade() {
		return NameGrade;
	}

	public void setNameGrade(String nameGrade) {
		NameGrade = nameGrade;
	}

	public String getNameLesson() {
		return NameLesson;
	}

	public void setNameLesson(String nameLesson) {
		NameLesson = nameLesson;
	}

	public List<ActivityDetail> getActivityDetail() {
		return activities;
	}

	public void setActivityDetail(List<ActivityDetail> activities) {
		this.activities= activities;
	}




}


