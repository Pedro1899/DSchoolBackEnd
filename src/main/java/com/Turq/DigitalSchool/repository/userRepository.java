package com.Turq.DigitalSchool.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Turq.DigitalSchool.model.user;

public interface userRepository extends JpaRepository<user, Long> {

	@Query("Select t from user t where t.username = ?1 and t.password = ?2 ")
	user getUser(String username, String password);
	
	@Query("Select t.IdUser from user t where t.school.idSchool = ?1")
	List<Long> getAllUserbyIdSchool(Long IdSchool);
	
	@Transactional
	@Modifying
	@Query("update user e SET e.picture = ?1, e.password=?2 where e.IdUser=?3")
	void UpdateUser(String picture, String Password, Long idUser);
	
            

}
