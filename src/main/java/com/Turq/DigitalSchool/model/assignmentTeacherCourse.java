package com.Turq.DigitalSchool.model;

import javax.persistence.*;

@Entity
@Table
public class assignmentTeacherCourse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="teacher_idteacher")
	private teacher teacher;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_idCourse", unique=true)
	private course course;

	
	
	
	
	
	
	public assignmentTeacherCourse() {
		super();
	}

	public assignmentTeacherCourse(com.Turq.DigitalSchool.model.teacher teacher,
			com.Turq.DigitalSchool.model.course course) {
		super();
		this.teacher = teacher;
		this.course = course;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(teacher teacher) {
		this.teacher = teacher;
	}

	public course getCourse() {
		return course;
	}

	public void setCourse(course course) {
		this.course = course;
	}
	
	
	
	
	
}
