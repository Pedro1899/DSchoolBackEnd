package com.Turq.DigitalSchool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.notesCategory;

public interface notesCategoryRepository extends JpaRepository<notesCategory, Long> {
	
	
	@Query("Select t.id from notesCategory t where lower(t.CategoryDescription)= ?1 and t.school.idSchool = ?2")
	List<Long> getCatByDescription(String Description, Long idSchool);

	
	@Query("Select t from notesCategory t where t.school.idSchool = ?1")
	List<notesCategory> getAll( Long idSchool);
}

