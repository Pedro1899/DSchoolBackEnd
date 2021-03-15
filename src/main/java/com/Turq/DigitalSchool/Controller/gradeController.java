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
import com.Turq.DigitalSchool.model.activity;
import com.Turq.DigitalSchool.model.assignmentTeacherCourse;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.lesson;
import com.Turq.DigitalSchool.model.school;
import com.Turq.DigitalSchool.repository.SchoolRepository;
import com.Turq.DigitalSchool.repository.activityRepository;
import com.Turq.DigitalSchool.repository.courseRepository;
import com.Turq.DigitalSchool.repository.coursesTeacherRepository;
import com.Turq.DigitalSchool.repository.gradeRepository;
import com.Turq.DigitalSchool.repository.lessonReposity;
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/managamentGrade")
public class gradeController {
	
	@Autowired
	private gradeRepository gradeRep;
	
	@Autowired
	private SchoolRepository schoolRep;
	
	
	@Autowired
	private lessonReposity LessonRep;
	
	@Autowired
	private activityRepository ActivityRep;
	

	
	@Autowired
	private coursesTeacherRepository AssRep;
	
	
	@Autowired
	private courseRepository CourseRep;
	
	//get School
	@GetMapping("grades")
	public List<grade> getAllgrades(){
		return this.gradeRep.findAll();
		
	}

	@PostMapping("/putgrade/{Description}/{idSchool}")
	public grade newUser(@PathVariable(value = "Description") String Description,
						@PathVariable(value = "idSchool") Long idSchool						
						)throws ResourceNotFoundException {
		
		List<Long> getExistingGrade = this.gradeRep.getGradeByDescription(Description, idSchool);
		
				if(getExistingGrade.size()>0) {
					throw new ResourceNotFoundException("This Grade already exist");  
					}
				
		school school= this.schoolRep.getOne(idSchool);
		grade grade = new grade(Description,school);
		return 	this.gradeRep.save(grade);
			}
	
	
	@GetMapping("getGrade/{id}")
	 public List<grade> getLessonById(@PathVariable(value = "id") Long idSchool) {
				List<grade> grade = gradeRep.getGrade(idSchool);
				return grade;
		
	}
	 
		@DeleteMapping("/deleteGrade/{idGrade}/{idSchool}")
		public String newUser(@PathVariable(value = "idGrade") Long idGrade,
				@PathVariable(value = "idSchool") Long idSchool
				){
			
			//delete all the activities
			/*List<activity> act = ActivityRep.getAllfromGrade(idGrade);
			if(act.size()>0) {
				ActivityRep.deletebyList(act);}*/
			
			
		
		// delete all the notes 
		
		//delete the assignment with the teacher in this Lesson
			
		List<assignmentTeacherCourse> ass = AssRep.getAllfromGrade(idGrade);
		if(ass.size()>0) {
			AssRep.deletefromList(ass);}
		
		
		
		//delete Course
		List<course> cou  = CourseRep.getAllfromGrade(idGrade);
		if(cou.size()>0) {
			CourseRep.deletefromList(cou);}
		
		
		//delete Lesson
		gradeRep.deleteById(idGrade);
	
		return "Success";
				
				}
	
}
