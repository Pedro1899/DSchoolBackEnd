package com.Turq.DigitalSchool.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Turq.DigitalSchool.exception.ResourceNotFoundException;


import com.Turq.DigitalSchool.model.school;
import com.Turq.DigitalSchool.model.user;
import com.Turq.DigitalSchool.model.teacher;
import com.Turq.DigitalSchool.repository.SchoolRepository;
import com.Turq.DigitalSchool.repository.teacherRepository;
import com.Turq.DigitalSchool.repository.userRepository;
import com.sun.el.stream.Optional;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/School")
public class schoolController {
	
	@Autowired
	private SchoolRepository SchoolRep;
	
	@Autowired
	private userRepository Users;
	

	@Autowired
	private teacherRepository teacherRep;
	
	//get School
	@GetMapping("/schools")
	public List<school> getAllSchools(){
		return this.SchoolRep.findAll();
		
	}

	
	//get school by ID
	@GetMapping("/getCredentialsNS/{User}/{password}")
	public boolean Credential(@PathVariable (value = "User") String User, @PathVariable (value = "password") String Password)  {
		if(User.equals("Pedro1899199") && Password.equals("LaVidaEsUnaPapa159753") ) {
			return true;
		}else {return false;}
		
	}
	
	//get school by ID
	@GetMapping("/getSchool/{id}")
	public school getUserById(@PathVariable (value = "id") long userId) throws ResourceNotFoundException {
		return this.SchoolRep.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	
		
	}
	
	//save new School
    @PostMapping("/school")
    public school createSchool(@RequestBody school shchool) {
    return this.SchoolRep.save(shchool);	
    	    }

    
    
    
	@PutMapping("/update")
	public String GUpdateSchool(updateSchool getSchool)throws ResourceNotFoundException{
	
		 System.out.println("id school " + getSchool.getIdSchool());
		    System.out.println("id date " + getSchool.getDateStart());
		    
		    Long IdSchool = getSchool.getIdSchool();
		    school bkSchool = SchoolRep.getOne(IdSchool);
		    String Logo = getSchool.getLogo();
		   if(Logo.equals("")) {
			   Logo = bkSchool.getLogo();
		   }
		    int Cycle = getSchool.getCicle();
		    Date dateStart=Date.valueOf(getSchool.getDateStart());
		    Date dateFinish=Date.valueOf( getSchool.getDateFinish());
		    
		    this.SchoolRep.UpdateUser(Logo, dateStart, dateFinish, Cycle, getSchool.getLanguage(), IdSchool);
		    
		    return "true";
		    
			}
	
	
	@PostMapping("/newSchool")
	public String CreateNewSchool(NewSchool getSchool)throws ResourceNotFoundException{
	
			String getPassword = getSchool.getPassword();
			if(getPassword.equals("lavidaesunaloco")) {
				System.out.println("lavidaesunaloco");
				school newSchool = new school();
				newSchool.setName(getSchool.getName());
				newSchool.setCicle(getSchool.getCicle());
				newSchool.setLogo(getSchool.getLogo());
				newSchool.setDateStart(Date.valueOf(getSchool.getDateStart()));
				newSchool.setDateFinish(Date.valueOf(getSchool.getDateFinish()));
				school  getNewSchool=    this.SchoolRep.save(newSchool);
				String newUser = getNewSchool.getName().replace(" ","") + "_" +getNewSchool.getIdSchool();
				user ClassUser = new user();
				ClassUser.setCategory(3);
				ClassUser.setUsername(newUser);
				ClassUser.setPassword(newUser);
				ClassUser.setSchool(getNewSchool);
				ClassUser.setPicture("n");
				user NewClassUser = Users.save(ClassUser);
				System.out.println("It Creates the User");
				teacher newTeacher = new teacher();
				newTeacher.setEmail(newUser);
				newTeacher.setName(newUser);
				newTeacher.setSurname(newUser);
				newTeacher.setUser(NewClassUser);
				teacherRep.save(newTeacher);
				System.out.println("It Creates the Teacher");
				return newUser;
			}else {
				
				return "false";
			}
		    
		    		    
			}
    	//delete school
	
}

/*class getChangeStudent {
	
	private Long Id;
	private String name;
	private String surname;
	private String photo;
	public getChangeStudent(Long id, String name, String surname, String photo) {
		super();
		Id = id;
		this.name = name;
		this.surname = surname;
		this.photo = photo;
	}
	public getChangeStudent() {
		super();
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}*/

class updateSchool{
	private Long idSchool;
	private String Logo;
	private int Cicle;
	private String dateStart;
	private String dateFinish;
	private int Language;
	public updateSchool(Long idSchool, String logo, int cicle, String dateStart, String dateFinish, int language) {
		super();
		this.idSchool = idSchool;
		Logo = logo;
		Cicle = cicle;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		Language = language;
	}
	public updateSchool() {
		super();
	}
	public Long getIdSchool() {
		return idSchool;
	}
	public void setIdSchool(Long idSchool) {
		this.idSchool = idSchool;
	}
	public String getLogo() {
		return Logo;
	}
	public void setLogo(String logo) {
		Logo = logo;
	}
	public int getCicle() {
		return Cicle;
	}
	public void setCicle(int cicle) {
		Cicle = cicle;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateFinish() {
		return dateFinish;
	}
	public void setDateFinish(String dateFinish) {
		this.dateFinish = dateFinish;
	}
	public int getLanguage() {
		return Language;
	}
	public void setLanguage(int language) {
		Language = language;
	}
	
	
	
}


class NewSchool{
	private String Password;
	private String name;
	private String Logo;
	private int Cicle;
	private String dateStart;
	private String dateFinish;
	private int Language;
	public NewSchool(String Password, String logo, int cicle, String dateStart, String dateFinish, int language, String name) {
		super();
		this.name= name;
		this.Password = Password;
		Logo = logo;
		Cicle = cicle;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		Language = language;
	}
	public NewSchool() {
		super();
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return Logo;
	}
	public void setLogo(String logo) {
		Logo = logo;
	}
	public int getCicle() {
		return Cicle;
	}
	public void setCicle(int cicle) {
		Cicle = cicle;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateFinish() {
		return dateFinish;
	}
	public void setDateFinish(String dateFinish) {
		this.dateFinish = dateFinish;
	}
	public int getLanguage() {
		return Language;
	}
	public void setLanguage(int language) {
		Language = language;
	}
	
	
}

