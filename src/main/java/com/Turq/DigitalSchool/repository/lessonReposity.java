package com.Turq.DigitalSchool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.lesson;
import com.Turq.DigitalSchool.model.user;

public interface lessonReposity extends JpaRepository<lesson, Long> {

	@Query("Select t from lesson t where t.school.idSchool = ?1")
	List<lesson> getLesson(Long idSchool);
	
	
	@Query("Select t.Id from lesson t where t.Description = ?1 and t.school.idSchool = ?2")
	List<Long> getExistingLesson(String Description, Long idSchool);
	
}
