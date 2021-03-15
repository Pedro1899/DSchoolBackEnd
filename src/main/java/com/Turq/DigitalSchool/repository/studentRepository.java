package com.Turq.DigitalSchool.repository;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.ListUsersDetail;
import com.Turq.DigitalSchool.model.StudentDetails;
import com.Turq.DigitalSchool.model.getStudent;
import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.student;

public interface studentRepository extends JpaRepository<student, Long> {


	  @Query("Select new com.Turq.DigitalSchool.model.getStudent(t.Id,t.name,t.surname ,t.birthday,t.photo, t.idTutor.Id,t.idGrade.Idgrade ) "
	    		+ "from student t where t.idGrade.Idgrade= ?1 order by t.Id desc")
	List<getStudent> getStudentbyGrade(Long idGrade);
	
	@Query("Select t.idGrade.Idgrade, t.name, t.photo from student t where  t.idTutor.Id =?1")
	List<Object[]> getStudentbytut(Long idTutor);
	

	
	  @Query("Select new com.Turq.DigitalSchool.model.StudentDetails(t.Id,t.name,t.surname ,t.birthday,t.photo, t.idGrade.Description) "
	    		+ "from student t where t.idTutor.Id= ?1")
		List<StudentDetails> getAllStudentsByTutor(Long IdTutor);
	  
	  
	  @Query("Select t.Id from student t where t.Id= ?1")
		List<Long> ExistAlready(Long idStudent);
	  
	  
		@Transactional
		@Modifying
		@Query("update student e SET e.name = ?1, e.surname=?2, e.photo =?3 where e.Id=?4")
		void UpdateStudent(String name, String surname, String photo, Long idStudent );
	
		
		@Transactional
		@Modifying
		@Query("update student e SET e.idGrade = ?1 where e.Id=?2")
		void UpdateGradeStudent(grade newGrade,  Long idStudent );
}
