package com.Turq.DigitalSchool.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.activity;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.notes;

public interface notesRepository  extends JpaRepository<notes, Long>{
	
	  @Query("SELECT t " 
		  		+ "  from notes t "
			  		+ " where t.NotesCourse=?1 and t.Category.Id=?2  and  t.NotesyDate BETWEEN ?3 AND ?4  order by t.NotesyDate asc")
			    	
			    List<notes> getNotesByIdCourseXCategory(course idCourse, Long idCategory, Date dateStart, Date dateFinish); 

			    

}
