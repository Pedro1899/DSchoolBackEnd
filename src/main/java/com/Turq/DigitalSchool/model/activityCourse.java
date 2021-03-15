package com.Turq.DigitalSchool.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"course_idCourse", "Activity_idActivity"})
	}) 
public class activityCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_idCourse")
	private course Course;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Activity_idActivity")
	private activity Activity;

	

	

	public activityCourse() {
		super();
	}


	public activityCourse(course course, activity activity) {
		super();
		Course = course;
		Activity = activity;

	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public course getCourse() {
		return Course;
	}


	public void setCourse(course course) {
		Course = course;
	}


	public activity getActivity() {
		return Activity;
	}


	public void setActivity(activity activity) {
		Activity = activity;
	}




	




	
}
