package com.Turq.DigitalSchool.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Turq.DigitalSchool.exception.ResourceNotFoundException;
import com.Turq.DigitalSchool.model.ListTeacher;
import com.Turq.DigitalSchool.model.teacher;
import com.Turq.DigitalSchool.model.user;
import com.Turq.DigitalSchool.repository.coursesTeacherRepository;
import com.Turq.DigitalSchool.repository.teacherRepository;
import com.Turq.DigitalSchool.repository.userRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/managamentteacher")
public class teacherController {


	
	
	@Autowired
	private userRepository UserRep;
	
	
	@Autowired
	private teacherRepository teacherRep;
	
	
	@Autowired
	private coursesTeacherRepository teacherCourseRep;
	
	
	//find all the users
	@GetMapping("getallteachers")
	public List<teacher> GetAllUser(){
	return teacherRep.findAll();
			}
	
	@GetMapping("GetbyIDSchool/{idSchool}")
	public List<ListTeacher> GetAllTeacherbyIdSchool(@PathVariable(value="idSchool") Long idSchool){
			return teacherRep.getTeacherbyIdSchool(idSchool);
			}
	
	//find teacher by IdUser
@GetMapping("getTeacherByIdUser/{Userid}")
	public teacher GetTeacerbyIdUser(@PathVariable(value = "Userid") Long idUser) throws ResourceNotFoundException{
		
		teacher Maestro = this.teacherRep.teacherByIdUser(idUser);
		if(Maestro == null) {
			throw new ResourceNotFoundException("Incorrect credentials");  
			
		}else {
			
		return Maestro;
				
		}
		
	
			}
	
	//create a new user
	@PostMapping("/putteacher/{address}/{email}/{name}/{surname}/{Userid}/{phone}")
	public teacher newUser(@PathVariable(value = "Userid") Long idUser,
						@PathVariable(value = "address") String address,
						@PathVariable(value = "email") String email,
						@PathVariable(value = "name") String name, 
						@PathVariable(value = "surname") String surname, 
						@PathVariable(value = "phone") Long phone
						) {
		
		user User = this.UserRep.getOne(idUser);
		teacher teacher = new teacher(name,surname,address, email,phone, User );
		return 	this.teacherRep.save(teacher);
			}
	
	

	@DeleteMapping("/deleteteacher/{UsuarioId}/{Category}/{Teacherid}")
	public String newUser(@PathVariable(value = "Teacherid") Long idTeacher,
			@PathVariable(value = "Category") Long cat,
			@PathVariable(value = "UsuarioId") Long idUsuario
			)   throws ResourceNotFoundException {
		if (cat ==1 | cat==3) {
			teacherCourseRep.deletefromTeacher(idTeacher);
			teacherRep.deleteById(idTeacher);
			UserRep.deleteById(idUsuario);
		}else {
			throw new ResourceNotFoundException("Delete first the tutor's children");  
}
			
			
			
			return "Success";
	
		
		
			}
	
	
	
		
}
