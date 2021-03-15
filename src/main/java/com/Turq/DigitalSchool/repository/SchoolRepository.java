package com.Turq.DigitalSchool.repository;

import java.sql.Date;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.school;

public interface SchoolRepository extends JpaRepository<school, Long> {


	 
	@Transactional
	@Modifying
	@Query("update school e SET e.Logo = ?1, e.dateStart=?2, e.dateFinish=?3, e.cycle=?4, e.Language=?5  where e.idSchool=?6")
	void UpdateUser(String Logo, Date dateStart, Date dateFinish, int Cycle, int Language, Long idSchool );
	
	
}
