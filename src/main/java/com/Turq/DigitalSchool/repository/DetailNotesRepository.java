package com.Turq.DigitalSchool.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.DetailNotes;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.notes;
import com.Turq.DigitalSchool.model.student;

public interface DetailNotesRepository extends JpaRepository<DetailNotes, Long> {
	
	  @Query("SELECT t " 
		  		+ "  from DetailNotes t "
			  		+ " where t.Notes=?1")
			    	
			    List<DetailNotes> getNotesByIdNotes(notes idNote); 
	  
	  
	  @Query("SELECT t.Result " 
		  		+ "  from DetailNotes t "
			  		+ " where t.Notes=?1 and t.Student=?2 ")
			    	
			   Long getNotesByidStudent(notes idNote, student idStudent); 
	  
	
		@Transactional
		@Modifying
		@Query("delete from DetailNotes t where t.Student.Id= ?1")
		void deletefromStudent(Long idStudent);
}
