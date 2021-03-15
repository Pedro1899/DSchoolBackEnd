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
import com.Turq.DigitalSchool.model.ActivityDetail;
import com.Turq.DigitalSchool.model.ListAssignmntIds;
import com.Turq.DigitalSchool.model.ListCoursesbyTeacher;
import com.Turq.DigitalSchool.model.activity;
import com.Turq.DigitalSchool.model.assignmentTeacherCourse;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.lesson;
import com.Turq.DigitalSchool.model.teacher;
import com.Turq.DigitalSchool.repository.activityRepository;
import com.Turq.DigitalSchool.repository.courseRepository;
import com.Turq.DigitalSchool.repository.coursesTeacherRepository;
import com.Turq.DigitalSchool.repository.gradeRepository;
import com.Turq.DigitalSchool.repository.lessonReposity;
import com.Turq.DigitalSchool.repository.teacherRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/TeacherCourse")
public class TeacherCourseController {
	
	@Autowired
	private coursesTeacherRepository teacherCourseRep;
	
	@Autowired
	private courseRepository courseRep;
	
	@Autowired
	private teacherRepository teacherRep;
	
	@Autowired
	private activityRepository activityRep;
	
	
	
	/*
	//Get course by IdTeacher
	@GetMapping("getCoursebyIdTeacher/{idTeacher}")
	public List<Object[]> getAllcourses(@PathVariable(value="idTeacher") Long idTeacher){
		
		return  teacherCourseRep.getCourses(idTeacher);
		
	}*/


	
	@PostMapping("/putAssignment/{idTeacher}/{idCourse}")
	public assignmentTeacherCourse newUser(@PathVariable(value = "idTeacher") Long idTeacher,
						@PathVariable(value = "idCourse") Long idCourse
						) {
		
		course curso= this.courseRep.getOne(idCourse);
		teacher maestro= this.teacherRep.getOne(idTeacher);
				
		assignmentTeacherCourse asignacion = new assignmentTeacherCourse(maestro, curso);
		return 	this.teacherCourseRep.save(asignacion);
			}
	
	
	


	/*@DeleteMapping("/putAssignment/{id}")
	public String DeleteAssignment(@PathVariable(value = "id") Long id
						) {
		
		course curso= this.courseRep.getOne(idCourse);
		teacher maestro= this.teacherRep.getOne(idTeacher);
				
		assignmentTeacherCourse asignacion = new assignmentTeacherCourse(maestro, curso);
		return 	this.teacherCourseRep.save(asignacion);
			}*/
	
	
	@GetMapping("getDetailTeacher/{idTeacher}")
	public List<ListCoursesbyTeacher> getDetailTeacher(
			@PathVariable(value="idTeacher") Long idTeacher) throws ResourceNotFoundException{
		
		List<ListCoursesbyTeacher> Detail = teacherCourseRep.getCoursesbyT(idTeacher);
		
		if (Detail == null) {
			
			throw new ResourceNotFoundException("Imposible to save the new User." );  
		}/*else {
			
			for(int indice = 0;indice<Detail.size();indice++)
			{
				
			   List<ActivityDetail> Activities = this.activityRep.findByActivity(Detail.get(indice).getId_Course());
			   if(Activities!=null) {
				   System.out.print("Las actividades del curso " + Detail.get(indice).getId_Course() + " son: " + Activities.size() );
				   Detail.get(indice).setActivities(Activities);
			   }
			   
			   
			}
		}*/

				
		
		
		return  Detail;
		
	}


	@GetMapping("getDetailAssignment/{idSchool}")
	public List<ListAssignmntIds> getDetailAssignmntBySchool(
			@PathVariable(value="idSchool") Long idSchool){
		
		List<ListAssignmntIds> Detail = teacherCourseRep.getCoursesbySchool(idSchool);
		
			
		return  Detail;
		
	}
	

	@PostMapping("/putAss")
	public  String CreateNewCourse(addNewAssignmentByTeacher getAss) throws ResourceNotFoundException {
		
		
		List<Long> courses = getAss.getIdCourses();
		teacher Teacher= this.teacherRep.getOne(getAss.getidTeacher());
		
		for (int counterAss =0; counterAss<courses.size();counterAss++) {
			
			
			course C = courseRep.getOne(courses.get(counterAss));
			assignmentTeacherCourse ass = new assignmentTeacherCourse(Teacher,C);
			teacherCourseRep.save(ass);
			
			
		}
		
		
		
		return 	"success";
			}
	
	

	
	@DeleteMapping("/DeleteAss/{idAss}")
	public String deleteAss(@PathVariable(value = "idAss") Long idAss){
		
		this.teacherCourseRep.deleteById(idAss);
		
		return "success";
	}
	
	
}



class addNewAssignmentByTeacher {
	
	public Long idTeacher;
	
	public List<Long> idCourses;

	public Long getidTeacher() {
		return idTeacher;
	}

	public void setidTeacher(Long idTeacher) {
		this.idTeacher = idTeacher;
	}

	public List<Long> getIdCourses() {
		return idCourses;
	}

	public void setIdCourses(List<Long> idCourses) {
		this.idCourses = idCourses;
	}

	public addNewAssignmentByTeacher(Long idTeacher, List<Long> idCourses) {
		super();
		this.idTeacher = idTeacher;
		this.idCourses = idCourses;
	}

	public addNewAssignmentByTeacher() {
		super();
	}
	
	

}
