package com.Turq.DigitalSchool.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.ActivityDetail;
import com.Turq.DigitalSchool.model.activity;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.lesson;

public interface activityRepository extends JpaRepository<activity, Long> {
	
/*

    
    @Query("SELECT new com.Turq.DigitalSchool.model.ActivityDetail(t.Id,t.ActivityTitle,t.ActivityDescription, t.Activityfoto, t.ActivityDate, t.ActivityCategory)"
    		+ " FROM activity t WHERE t.idCourse.Id = ?1")
    List<ActivityDetail> findByActivity(Long idCourse); 
    */
    
	/*
    @Transactional
	@Modifying
    @Query("delete FROM activity t WHERE t.idCourse.idLesson.Id = ?1")
    void deletebyLesson(Long idLesson); 
    */
    
    
    //Get all  activities from the lesson
    /*
    @Query("select t FROM activity t WHERE t.idCourse.idGrade.Idgrade = ?1")
    List<activity> getAllfromGrade(Long idLesson);
    
    //Get all activities from grada
    
    @Query("select t FROM activity t WHERE t.idCourse.idLesson.Id = ?1")
    List<activity> getAllfromLesson(Long idGrade);
    
    //get all activities from course
    @Query("select t FROM activity t WHERE t.idCourse.Id = ?1")
    List<activity> getAllfromCourse(Long idCourse);
    
    //delete all the activities from the list
 
    @Transactional
   	@Modifying
       @Query("delete FROM activity t WHERE t in(?1)")
       void deletebyList(List<activity> activity); 
    
   */
    
 
}



