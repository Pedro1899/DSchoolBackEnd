package com.Turq.DigitalSchool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.lesson;

public interface gradeRepository extends JpaRepository<grade, Long> {

	@Query("Select t from grade t where t.school.idSchool = ?1")
	List<grade> getGrade(Long idSchool);
	
	
	@Query("Select t.Idgrade from grade t where t.Description= ?1 and t.school.idSchool = ?2")
	List<Long> getGradeByDescription(String Description, Long idSchool);
}
