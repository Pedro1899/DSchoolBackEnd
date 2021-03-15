package com.Turq.DigitalSchool.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Turq.DigitalSchool.exception.ResourceNotFoundException;
import com.Turq.DigitalSchool.model.activity;
import com.Turq.DigitalSchool.model.assignmentTeacherCourse;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.lesson;
import com.Turq.DigitalSchool.model.school;
import com.Turq.DigitalSchool.repository.SchoolRepository;
import com.Turq.DigitalSchool.repository.activityRepository;
import com.Turq.DigitalSchool.repository.courseRepository;
import com.Turq.DigitalSchool.repository.coursesTeacherRepository;
import com.Turq.DigitalSchool.repository.lessonReposity;
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/Lesson")
public class lessonController {
	
	@Autowired
	private lessonReposity LessonRep;
	
	@Autowired
	private activityRepository ActivityRep;
	
	@Autowired
	private SchoolRepository SchoolRep;
	
	@Autowired
	private coursesTeacherRepository AssRep;
	
	
	@Autowired
	private courseRepository CourseRep;
	
	
	
	
	//get School
	@GetMapping("getLesson/{id}")
	 public List<lesson> getLessonById(@PathVariable(value = "id") Long idLesson) {
				List<lesson> Leson = LessonRep.getLesson(idLesson);
				return Leson;
		
	}


	@PostMapping("putLesson/{Description}/{idSchool}")
	 public lesson newLesson(@PathVariable(value = "Description") String Description,
			 @PathVariable(value = "idSchool") Long idSchool) throws ResourceNotFoundException {
		
		List<Long> getExistingLesson = LessonRep.getExistingLesson(Description, idSchool);
		if (getExistingLesson.size()>0) {
						throw new ResourceNotFoundException("This Lesson already exist");  
					}
		
		school s = SchoolRep.getOne(idSchool);
		
		lesson Lesson = new lesson(Description, s);
				
				return this.LessonRep.save(Lesson);
		
	}
	
	

	
	@DeleteMapping("/deleteLesson/{idLesson}/{idSchool}")
	public String newUser(@PathVariable(value = "idLesson") Long idLesson,
			@PathVariable(value = "idSchool") Long idSchool
			){
			//delete all activities of this Lesson
		/*List<activity> act = ActivityRep.getAllfromLesson(idLesson);
				if(act.size()>0) {
					ActivityRep.deletebyList(act);}
				*/
				
			
			//notes
			
			//delete the assignment with the teacher with this Lesson
				
			List<assignmentTeacherCourse> ass = AssRep.getAllfromLesson(idLesson);
			if(ass.size()>0) {
				AssRep.deletefromList(ass);}
			
			//delete Course
			List<course> cou  = CourseRep.getAllfromLesson(idLesson);
			if(cou.size()>0) {
				CourseRep.deletefromList(cou);}
			
			
			//delete Lesson
			LessonRep.deleteById(idLesson);
		
						
			return "Success";
			
			}
	//update school
    	//delete school
	
}
