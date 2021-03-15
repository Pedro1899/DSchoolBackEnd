package com.Turq.DigitalSchool.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Turq.DigitalSchool.model.ListTeacher;
import com.Turq.DigitalSchool.model.tutor;
import com.Turq.DigitalSchool.model.user;

import com.Turq.DigitalSchool.repository.tutorRepository;
import com.Turq.DigitalSchool.repository.userRepository;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/managamenttutors")
public class tutorController {


	
	
	@Autowired
	private userRepository UserRep;
	
	
	@Autowired
	private tutorRepository tutorRep;
	
	//find all the users
	@GetMapping("getalltutor")
	public List<tutor> GetAllUser(){
	return tutorRep.findAll();
			}
	
	
	@GetMapping("getalltutor/{idSchool}")
	public List<ListTeacher> GetAllTutors(@PathVariable(value = "idSchool") Long idSchool){
		
		
	return tutorRep.getTutorsbyIdSchool(idSchool);
			}
	//create a new tutor
	
	@PostMapping("/puttutor/{name}/{surname}{address}/{mail}/{phone}/{idUser}")
	public tutor newUser(@PathVariable(value = "name") String name,
			@PathVariable(value = "surname") String surname,
						@PathVariable(value = "address") String address,
						@PathVariable(value = "mail") String email,
						@PathVariable(value = "phone") Long phone,
						@PathVariable(value = "idUser") Long idUser
						) {
		
		user User = this.UserRep.getOne(idUser);
		tutor tut = new tutor(name,surname, address, email, phone, User );
		return 	this.tutorRep.save(tut);
			}
	
		
}
