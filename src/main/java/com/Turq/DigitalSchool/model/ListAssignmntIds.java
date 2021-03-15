package com.Turq.DigitalSchool.model;

public class ListAssignmntIds {

	
	private Long idAssignment;
	
	private Long idTeacher;
	
	private Long idCourseLong;

	public ListAssignmntIds(Long idAssignment, Long idTeacher, Long idCourseLong) {
		super();
		this.idAssignment = idAssignment;
		this.idTeacher = idTeacher;
		this.idCourseLong = idCourseLong;
	}

	public Long getIdAssignment() {
		return idAssignment;
	}

	public void setIdAssignment(Long idAssignment) {
		this.idAssignment = idAssignment;
	}

	public Long getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(Long idTeacher) {
		this.idTeacher = idTeacher;
	}

	public Long getIdCourseLong() {
		return idCourseLong;
	}

	public void setIdCourseLong(Long idCourseLong) {
		this.idCourseLong = idCourseLong;
	}
	
	
	
	
	
}
