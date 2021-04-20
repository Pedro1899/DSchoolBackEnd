package com.Turq.DigitalSchool.Controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Turq.DigitalSchool.exception.ResourceNotFoundException;
import com.Turq.DigitalSchool.model.ActivityDetail;
import com.Turq.DigitalSchool.model.ListCoursesbyTeacher;
import com.Turq.DigitalSchool.model.ListUsersDetail;
import com.Turq.DigitalSchool.model.StudentDetails;
import com.Turq.DigitalSchool.model.activityCourse;
import com.Turq.DigitalSchool.model.school;
import com.Turq.DigitalSchool.model.teacher;
import com.Turq.DigitalSchool.model.tutor;
import com.Turq.DigitalSchool.model.user;
import com.Turq.DigitalSchool.repository.SchoolRepository;
import com.Turq.DigitalSchool.repository.activityCourseRepository;
import com.Turq.DigitalSchool.repository.activityRepository;
import com.Turq.DigitalSchool.repository.coursesTeacherRepository;
import com.Turq.DigitalSchool.repository.studentRepository;
import com.Turq.DigitalSchool.repository.teacherRepository;
import com.Turq.DigitalSchool.repository.tutorRepository;
import com.Turq.DigitalSchool.repository.userRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/managamentUser")
public class userController {



@Bean
public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    MappingJackson2HttpMessageConverter converter = 
        new MappingJackson2HttpMessageConverter(mapper);
    return converter;
}
	
	
	@Autowired
	private userRepository UserRep;
	
	
	@Autowired
	private userRepository Users;
	

	@Autowired
	private teacherRepository teacherRep;
	
	@Autowired
	private SchoolRepository SchoolRep;
	
	@Autowired
	private teacherRepository TeacherRep;
	
	@Autowired
	private tutorRepository tutorRep;
	
	@Autowired
	private coursesTeacherRepository coursesRep;

	
	@Autowired
	private activityCourseRepository activityRep;
	
	@Autowired
	private studentRepository studentRep;
	
	
	
	//find all the users
	@GetMapping("getallusers")
	public List<user> GetAllUser(){
	return UserRep.findAll();
			}
	
	//create a new user

	
	
	@PostMapping("/putuserWC/{idSchool}/{name}/{surname}/{username}/{password}/{category}/{email}/{picture}")
	public ListUsersDetail newUserwithChildren(@PathVariable(value = "idSchool") Long idSchool,
						@PathVariable(value = "name") String name,
						@PathVariable(value = "surname") String surname,
						@PathVariable(value = "username") String username,
						@PathVariable(value = "password") String password,
						@PathVariable(value = "category") Integer category,
						@PathVariable(value = "email") String email,
						@PathVariable(value = "picture") String picture
						)throws ResourceNotFoundException    {
		
		
		ListUsersDetail detailsNew = new ListUsersDetail();
		
		school school = this.SchoolRep.getOne(idSchool);
		user User = new user(username,password, category, school, picture );
		
		/*Long idUser, Long idTeacher_Tutor, String name, String surname, String username, int category,
		String email, String address, Long phone */
		
		try {
			user UserSaved =this.UserRep.save(User);
			detailsNew.setIdUser(UserSaved.getIdUser());
			detailsNew.setCategory(category);
			detailsNew.setUsername(username);
			detailsNew.setName(name);
			detailsNew.setSurname(surname);
			detailsNew.setPhone(null);
			detailsNew.setAddress(" ");
			detailsNew.setEmail(email);
			detailsNew.setPicture(picture);
			
			if(category==1 | category==3 ){
				
				teacher teacher = new teacher(name,surname,null, email,null, UserSaved );
			 teacher teacherSaved=	this.TeacherRep.save(teacher);
			 detailsNew.setIdTeacher_Tutor(teacherSaved.getIdTeacher());
			 
			}else {
				tutor tutor = new tutor(name,surname,null, email,null, UserSaved );
				tutor tutorSaved = this.tutorRep.save(tutor);
				detailsNew.setIdTeacher_Tutor(tutorSaved.getId());
			}
			
		}catch(Exception e) {
			
			throw new ResourceNotFoundException("Imposible to save the new User." + e.getMessage());  
		}
		
		return detailsNew;
			}
	
	

	@PostMapping("/newSchool")
	public String CreateNewSchool(NewSchool getSchool)throws ResourceNotFoundException{
		System.out.println("lavidaesunaloco");
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
	
	@GetMapping("/getDetailBySchool/{idSchool}")
    public List<ListUsersDetail> getschoolById(@PathVariable(value = "idSchool") Long idSchool)
    		throws ResourceNotFoundException  {
    	
		List<ListUsersDetail> maestros = this.TeacherRep.getAllUserbyIdSchool(idSchool);
				List<ListUsersDetail> padres = this.tutorRep.getAllTutorsbyIdSchool(idSchool);
		List<ListUsersDetail> Users = new   ArrayList<ListUsersDetail>();
	
		
		
		if((maestros == null) &((padres == null)) ){
			
			throw new ResourceNotFoundException("theres not users...");  
		}else  {
			if(maestros!=null) {
			
				 
				for(int indice = 0;indice<maestros.size();indice++)
				{
					List<ListCoursesbyTeacher> courses = this.coursesRep.getCoursesbyT(maestros.get(indice).getIdTeacher_Tutor());
				   				   
				   if(courses!=null) {
					   for(int indiceActivities = 0;indiceActivities<courses.size();indiceActivities++)
						{
						   
						   List<ActivityDetail> Activities = this.activityRep.findActivityByIdUser(courses.get(indiceActivities).getId_Course());
						 if(Activities.size()>0) {
							 courses.get(indiceActivities).setActivityDetail(Activities);
						 }
						   

						   		
						}
					   
					  maestros.get(indice).setCourses(courses);
				   }
				
				   
				   
				
			}
				
				Users.addAll(maestros);
				
			}
			
			if(padres!=null)
				
				for(int indiceStudent = 0;indiceStudent<padres.size();indiceStudent++)
				{
					
					List<StudentDetails> children = this.studentRep.getAllStudentsByTutor(padres.get(indiceStudent).getIdTeacher_Tutor());
		  
				   if(children.size()>0) {					
					   
					  padres.get(indiceStudent).setChildrens(children);
					  padres.get(indiceStudent).setFlagChildren(true);
				   }
				
				   
				   
				
			}
				
				
				Users.addAll(padres);
			
			
			
		}
			
			return Users;
		
		
    }
	
		
	@GetMapping("/Login/{username}/{password}")
    public user getschoolById(@PathVariable(value = "username") String username,
    		@PathVariable(value = "password") String password) throws ResourceNotFoundException  {
		
		user usuario = this.UserRep.getUser(username, password);
		if(usuario == null) {
			throw new ResourceNotFoundException("Incorrect credentials");  
			
		}else {
			
		return usuario;
				
		}
      
    }
	

	
	@GetMapping("/NewSchoolCred/{username}/{password}")
    public boolean ValidateCredentialNewS(@PathVariable(value = "username") String username,
    		@PathVariable(value = "password") String password)  {
		
		if(username.equals("Pedro18991999") && password.equals("Lavidaesunaloco")) {
			return true;
		}else {
			
		return false;
				
		}
      
    }
	
	

	@DeleteMapping("/deleteteacher/{Userid}")
	public String newUser(@PathVariable(value = "Userid") Long idUser					
						) {
		try {
			user usuario = UserRep.getOne(idUser);
			int categoria = usuario.getCategory();
			if (categoria == 1) {
							}
			
			UserRep.deleteById(idUser);
			return "Success";
		}
		catch(Exception e) {
			
			return "Ups... something get wrong " + e.toString();
		}
		}
	
	
	@PutMapping("/putuser")
	public String UpdateUser(ChangeUser getUser)throws ResourceNotFoundException    {
		
		System.out.println("recibe password " + getUser.getPassword());
		user User = UserRep.getOne(getUser.getIdUser());
		teacher Teacher = TeacherRep.teacherByIdUser(getUser.getIdUser());
		
		String name = getUser.getName();
		String surname = getUser.getSurname();
		String picture ="";
		String password="";
		if(getUser.getPicture().equals("")) {
			 picture = User.getPicture();
		}else {			
			picture = getUser.getPicture();
			}
		
		if(getUser.getPassword().equals("")) {
			 password = User.getPassword();
		}else {			
			 password = getUser.getPassword();
			}
		
		
		
		
		try {
			UserRep.UpdateUser(picture, password, getUser.getIdUser());
		}catch(Exception e) {
			
			throw new ResourceNotFoundException("Imposible to save the User." + e.getMessage());  
		}
		
		
		try {
			TeacherRep.UpdateTeacher(name, surname,Teacher.getIdTeacher());
		}catch(Exception e) {
			
			throw new ResourceNotFoundException("Imposible to save the teacher." + e.getMessage());  
		}
		
		
		return "success";
			}
		}

class ChangeUser {
	
	public Long idUser;
	public String name;
	public String surname;
	public String password;
	public String picture;
	public Long getIdUser() {
		return idUser;
	}
	
	
	
	public ChangeUser(Long idUser, String name, String surname, String password, String picture) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.picture = picture;
	}



	public ChangeUser() {
		super();
	}



	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
}
