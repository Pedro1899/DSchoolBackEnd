package com.Turq.DigitalSchool.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table
public class notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column
	private String NotesDescription;
	
	@Column
	private String NotesTitle;
	
	
	
	@Column 
	private Date NotesyDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Category_idCategory")
	private notesCategory Category;

	
	@Column 
	private String NotesFile;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Course_idCourse")
	private course NotesCourse;


	public notes(String notesDescription, String notesTitle, Date notesyDate, notesCategory category, String notesFile,
			course notesCourse) {
		super();
		NotesDescription = notesDescription;
		NotesTitle = notesTitle;
		NotesyDate = notesyDate;
		Category = category;
		NotesFile = notesFile;
		NotesCourse = notesCourse;
	}


	public notes() {
		super();
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getNotesDescription() {
		return NotesDescription;
	}


	public void setNotesDescription(String notesDescription) {
		NotesDescription = notesDescription;
	}


	public String getNotesTitle() {
		return NotesTitle;
	}


	public void setNotesTitle(String notesTitle) {
		NotesTitle = notesTitle;
	}


	public Date getNotesyDate() {
		return NotesyDate;
	}


	public void setNotesyDate(Date notesyDate) {
		NotesyDate = notesyDate;
	}


	public notesCategory getCategory() {
		return Category;
	}


	public void setCategory(notesCategory category) {
		Category = category;
	}


	public String getNotesFile() {
		return NotesFile;
	}


	public void setNotesFile(String notesFile) {
		NotesFile = notesFile;
	}


	public course getNotesCourse() {
		return NotesCourse;
	}


	public void setNotesCourse(course notesCourse) {
		NotesCourse = notesCourse;
	}

	
	
	
	
	
	
		
}
