package com.Turq.DigitalSchool.model;



import javax.persistence.*;

@Entity
@Table
public class DetailNotes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notes_idNotes")
	private notes Notes;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_idStudent")
	private student Student;
	
	@Column
	private Long Result;

	public DetailNotes(notes notes, student student, Long result) {
		super();
		Notes = notes;
		Student = student;
		Result = result;
	}

	public DetailNotes() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public notes getNotes() {
		return Notes;
	}

	public void setNotes(notes notes) {
		Notes = notes;
	}

	public student getStudent() {
		return Student;
	}

	public void setStudent(student student) {
		Student = student;
	}

	public Long getResult() {
		return Result;
	}

	public void setResult(Long result) {
		Result = result;
	}




	
	
	
}