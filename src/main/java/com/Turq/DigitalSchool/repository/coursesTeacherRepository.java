package com.Turq.DigitalSchool.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.ListAssignmntIds;
import com.Turq.DigitalSchool.model.ListCoursesbyTeacher;
import com.Turq.DigitalSchool.model.ListUsersDetail;
import com.Turq.DigitalSchool.model.activity;
import com.Turq.DigitalSchool.model.assignmentTeacherCourse;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.teacher;

public interface coursesTeacherRepository extends JpaRepository<assignmentTeacherCourse, Long> {
	
	@Query("Select t.course from assignmentTeacherCourse t where t.teacher.idTeacher = ?1")
	List<course> getCourses(Long idTeacher); 
	
	@Transactional
	@Modifying
	@Query("delete from assignmentTeacherCourse t where t.teacher.idTeacher = ?1")
	void deletefromTeacher(Long idTeacher);
	
	
	@Query("Select new com.Turq.DigitalSchool.model.ListCoursesbyTeacher("
			+ "t.course.Id,"
			+ "t.course.idGrade.Description,"
			+ "t.course.idLesson.Description, "
			+ "t.course.idGrade.Idgrade,"
			+ "t.course.idLesson.Id"
			+ ")"
			
			+ " from assignmentTeacherCourse t where t.teacher.idTeacher = ?1 order by t.course.idGrade.Description ")
	List<ListCoursesbyTeacher> getCoursesbyT(Long idTeacher);
	
	
	@Query("Select new com.Turq.DigitalSchool.model.ListAssignmntIds("
			+ "t.Id,"
			+ "t.teacher.idTeacher,"
			+ "t.course.id)"
			
			+ " from assignmentTeacherCourse t where t.course.idGrade.school.idSchool= ?1")
	List<ListAssignmntIds> getCoursesbySchool(Long idSchool);
	 
	
	@Transactional
	@Modifying
	@Query("delete from assignmentTeacherCourse t where t in(?1)")
	void deletefromList(List<assignmentTeacherCourse> courses);
	
	
	//getAll Assignment with the idLesson
	  @Query("select t FROM assignmentTeacherCourse t WHERE t.course.idLesson.Id = ?1")
	    List<assignmentTeacherCourse> getAllfromLesson(Long idLesson);
	    
	 //get Assignment with idGrade
	    
	    @Query("select t FROM assignmentTeacherCourse t WHERE t.course.idGrade.Idgrade = ?1")
	    List<assignmentTeacherCourse> getAllfromGrade(Long idGrade);
	    
	 //get Assignment with course
	    
	    @Query("select t FROM assignmentTeacherCourse t WHERE t.course.Id = ?1")
	    List<assignmentTeacherCourse> getAllfromCourse(Long idGrade);
	    
	    //delete assignment from a list 
	    
		  @Query("select t.Id FROM assignmentTeacherCourse t WHERE t.teacher= ?1 and t.course = ?2 ")
		    List<Long> getExistsAssignment(teacher t, course c);
	    
	
		  
		 }
