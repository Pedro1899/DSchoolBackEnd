package com.Turq.DigitalSchool.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.ListCoursesbyTeacher;
import com.Turq.DigitalSchool.model.ListUsersDetail;
import com.Turq.DigitalSchool.model.assignmentTeacherCourse;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.lesson;

public interface courseRepository extends JpaRepository<course, Long> {
	
	
	    
	    
	    @Query("Select new com.Turq.DigitalSchool.model.ListCoursesbyTeacher(t.Id,t.idGrade.Description,t.idLesson.Description, t.idGrade.Idgrade, t.idLesson.Id) "
	    		+ "from course t where t.idGrade.school.idSchool = ?1")
		List<ListCoursesbyTeacher> getCourses(Long IdSchool);
	    
	    @Transactional
		@Modifying
		@Query("delete from course t where t in(?1)")
		void deletefromList(List<course> course);
	    
		
		
		  @Query("select t FROM course t WHERE t.idLesson.Id = ?1")
		    List<course> getAllfromLesson(Long idLesson);
		    
			  @Query("select t FROM course t WHERE t.idGrade.Idgrade = ?1")
			    List<course> getAllfromGrade(Long idGrade);
		    
		    @Query("select t.Id FROM course t WHERE t.idLesson= ?1 and t.idGrade = ?2")
		    List<Long> getExistCourse(lesson idLesson, grade idGrade);
		    
		    
	
}
