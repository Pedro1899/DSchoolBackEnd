package com.Turq.DigitalSchool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.ListTeacher;
import com.Turq.DigitalSchool.model.ListUsersDetail;
import com.Turq.DigitalSchool.model.tutor;

public interface tutorRepository extends JpaRepository<tutor, Long>{

	
	
	  @Query("Select new com.Turq.DigitalSchool.model.ListUsersDetail(t.user.IdUser,t.Id ,t.name,t.surname,t.user.username,t.user.category, t.email,t.address, t.phone, t.user.picture) "
	    		+ "from tutor t where t.user.school.idSchool = ?1")
		List<ListUsersDetail> getAllTutorsbyIdSchool(Long IdSchool);
		

	  
	  @Query("Select new com.Turq.DigitalSchool.model.ListTeacher(t.Id ,t.name,t.surname,t.user.picture) "
	    		+ "from tutor t where t.user.school.idSchool = ?1 order by t.Id desc")
		List<ListTeacher> getTutorsbyIdSchool(Long IdSchool);
}
