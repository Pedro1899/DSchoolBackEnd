package com.Turq.DigitalSchool.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Turq.DigitalSchool.exception.ResourceNotFoundException;
import com.Turq.DigitalSchool.model.activity;
import com.Turq.DigitalSchool.model.assignmentTeacherCourse;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.getStudent;
import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.student;
import com.Turq.DigitalSchool.model.teacher;
import com.Turq.DigitalSchool.model.tutor;
import com.Turq.DigitalSchool.model.user;
import com.Turq.DigitalSchool.repository.DetailNotesRepository;
import com.Turq.DigitalSchool.repository.gradeRepository;
import com.Turq.DigitalSchool.repository.studentRepository;
import com.Turq.DigitalSchool.repository.tutorRepository;


@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/managamentStudent")
public class studentController {


	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("ddMMyyyy"), true, 10));   
	}
	
	@Autowired
	private studentRepository studentRep;
	
	
	@Autowired
	private tutorRepository tutorRep;
	
	@Autowired
	private gradeRepository gradeRep;
	
	@Autowired
	private DetailNotesRepository notesRep;
	
	
	//find all the users
	@GetMapping("getallstudents")
	public List<student> GetAllUser(){
	return studentRep.findAll();
			}
	
	@GetMapping("getOneStudent/{idStudent}")
	public student GetOneStudent(@PathVariable(value = "idStudent") Long idStudent){
		
	return studentRep.getOne(idStudent);
			}
	
	@GetMapping("getstudentsByGrade/{idgrade}")
	public List<getStudent> GetStudentsByGrade(@PathVariable(value = "idgrade") Long idGrade){
		
	return studentRep.getStudentbyGrade(idGrade);
			}
	
	@PutMapping("/putStudent")
	public String UpdateUser(getChangeStudent getStudent)throws ResourceNotFoundException    {
		
		try {
			studentRep.UpdateStudent(getStudent.getName(), getStudent.getSurname(), getStudent.getPhoto(), getStudent.getId());
		}catch(Exception e) {
			
			throw new ResourceNotFoundException("Imposible to save the User." + e.getMessage());  
		}		
		
		return "success";
			}

	
	@PutMapping("/putNewGrade/{idStudent}/{idNewGrade}")
	public String UpdateGrade(@PathVariable(value = "idStudent") Long idStudent, @PathVariable(value = "idNewGrade") Long idGrade)throws ResourceNotFoundException    {
		
				
				grade getGrade = gradeRep.getOne(idGrade);
		
		try {
			studentRep.UpdateGradeStudent(getGrade, idStudent);
		}catch(Exception e) {
			
			throw new ResourceNotFoundException("Imposible to save the User." + e.getMessage());  
		}		
		
		return "success";
			}
	
	@GetMapping("getstudentsByTutor/{idtutor}")
	public List<Object[]> GetStudentsBytut(@PathVariable(value = "idtutor") Long idtutor){
	return studentRep.getStudentbytut(idtutor);
			}
	
	
	
	


	
	
	@PostMapping("/greeting")
	  public String greetingSubmit(getStudent getstudent) throws ResourceNotFoundException {
	   
		List<Long> id = studentRep.ExistAlready(getstudent.getId());
		if(id.size()>0) {
			
			throw new ResourceNotFoundException("this student already Exist");  
		}
	tutor tutor = tutorRep.getOne(getstudent.getIdTutor());
	grade grado = gradeRep.getOne(getstudent.getIdGrade());
	System.out.println(getstudent.getPhoto());
	student st = new student();
	
	
	
	st.setId(getstudent.getId());
	st.setBirthday(getstudent.getBirthday());
	st.setName(getstudent.getName());
	st.setSurname(getstudent.getSurname());
	st.setPhoto(getstudent.getPhoto());
	st.setIdTutor(tutor);
	st.setIdGrade(grado);
	studentRep.save(st);
	 
		
		
	    return "greeting";
	  }
	
	
	@DeleteMapping("/deleteStudent/{idStudent}")
	public String newUser(
			@PathVariable(value = "idStudent") Long idStudent
			){
		
	notesRep.deletefromStudent(idStudent);
		
	studentRep.deleteById(idStudent);

	return "Success";
			
			}
	
		
}

class getChangeStudent {
	
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
	
	
}
