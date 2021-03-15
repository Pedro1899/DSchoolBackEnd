package com.Turq.DigitalSchool.repository;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Turq.DigitalSchool.model.ListTeacher;
import com.Turq.DigitalSchool.model.ListUsersDetail;
import com.Turq.DigitalSchool.model.school;
import com.Turq.DigitalSchool.model.teacher;
import com.Turq.DigitalSchool.model.user;


public interface teacherRepository extends JpaRepository<teacher, Long>{

	@Query("Select t from teacher t where t.user.IdUser = ?1  ")
	teacher teacherByIdUser(Long idUsuario);
	
	
	@Query("Select t from teacher t where t.user.IdUser IN (:IdUser)")     // 2. Spring JPA In cause using @Query
    List<teacher> AllTeacherByIdUser(@Param("IdUser")List<Long> IdUser);
	
	
    
    @Query("Select new com.Turq.DigitalSchool.model.ListUsersDetail(t.user.IdUser,t.idTeacher,t.name,t.surname,t.user.username,t.user.category, t.email,t.adress, t.phone, t.user.picture) "
    		+ "from teacher t where t.user.school.idSchool = ?1")
	List<ListUsersDetail> getAllUserbyIdSchool(Long IdSchool);
	
    
    
    @Query("Select new com.Turq.DigitalSchool.model.ListTeacher(t.idTeacher, t.name, t.surname, t.user.picture) "
    		+ "from teacher t where t.user.school.idSchool = ?1")
	List<ListTeacher> getTeacherbyIdSchool(Long IdSchool);
    
	@Transactional
	@Modifying
	@Query("update teacher e SET e.name = ?1, e.surname=?2 where e.idTeacher=?3")
	void UpdateTeacher(String name, String surname, Long idTeacher);
	
}
