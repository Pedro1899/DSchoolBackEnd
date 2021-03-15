package com.Turq.DigitalSchool.repository;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.ActivityDetail;
import com.Turq.DigitalSchool.model.CoursesDetail;
import com.Turq.DigitalSchool.model.activity;
import com.Turq.DigitalSchool.model.activityCourse;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.lesson;

public interface activityCourseRepository extends JpaRepository<activityCourse, Long> {

	  @Query("SELECT new com.Turq.DigitalSchool.model.ActivityDetail("
	  		+ "t.Activity.Id,"
	  		+ "t.Activity.ActivityTitle,"
	  		+ "t.Activity.ActivityDescription,"
	  		+ "t.Activity.Activityfoto, "
	  		+ "t.Activity.ActivityDate,  "
	  		+ "t.Activity.ActivityCategory )  from activityCourse t "
	  		+ " where t.Course.Id = ?1 and to_timestamp(t.Activity.ActivityDate, 'YYYY-MM-DD') > current_date "
	  		)
	    	
	    List<ActivityDetail> findActivityByIdUser(Long idCourse); 
	  

		

	  @Query("SELECT DISTINCT( t.Activity)  " 
	  		+ "  from activityCourse t "
		  		+ " where t.Course in (?1) and to_timestamp(t.Activity.ActivityDate, 'YYYY-MM-DD') > current_date order by t.Activity.ActivityDate desc")
		    	
		    List<activity> CountAcitivyforCourse(List<course> idCourse); 
	    

			  @Query("SELECT DISTINCT( t.Activity)  " 
			  		+ "  from activityCourse t "
				  		+ " where t.Activity.idAdmin=?1 and to_timestamp(t.Activity.ActivityDate, 'YYYY-MM-DD') > current_date order by t.Activity.ActivityDate desc")
				    	
				    List<activity> CountAcitivyforAdmin(Long idUser); 
		    
		    @Query("SELECT new com.Turq.DigitalSchool.model.CoursesDetail(t.Course.Id, t.Course.idGrade.Description, t.Course.idLesson.Description )"
		    		+ " from activityCourse t "
			  		+ " where t.Activity= ?1  and t.Course in(?2) and to_timestamp(t.Activity.ActivityDate, 'YYYY-MM-DD') > current_date ")
			    	
			    List<CoursesDetail> getCoursesbyActivity(activity IdActivity, List<course> courses ); 
		    
		    @Query("SELECT t"
		    		+ " from activityCourse t "
			  		+ " where  t.Course.Id = ?1 ")
			    	
			    List<activityCourse> getAssActivitybyCourse(Long idCourse ); 
		    
		    
			    
			    @Query("SELECT t.Course"
			    		+ " from activityCourse t "
				  		+ " where  t.Activity = ?1 ")
				    	
				    List<course> getCoursebyidActivity(activity activity); 
				    
				    
			    	@Transactional
				   	@Modifying
				   	@Query("Delete from activityCourse t  where  t in(?1) ")
				    	
				    void deleteActivityByList(List<activityCourse> activity); 
			    	
			    	
			    	@Transactional
				   	@Modifying
				   	@Query("Delete from activityCourse t  where  t.Activity in(?1) ")
				    	
				    void deleteActivityByIdAct(activity activity); 
			    
			    
				  
				  
}				


