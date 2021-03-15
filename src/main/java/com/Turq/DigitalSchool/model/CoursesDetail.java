package com.Turq.DigitalSchool.model;

public class CoursesDetail {
	
private Long Id;
	
	private String grade;
	
	private String Lesson;

	public CoursesDetail(Long id, String grade, String lesson) {
		super();
		Id = id;
		this.grade = grade;
		Lesson = lesson;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLesson() {
		return Lesson;
	}

	public void setLesson(String lesson) {
		Lesson = lesson;
	}
	
	
	

}
