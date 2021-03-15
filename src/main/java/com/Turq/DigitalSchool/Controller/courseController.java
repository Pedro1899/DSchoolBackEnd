package com.Turq.DigitalSchool.Controller;

import java.util.ArrayList;
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
import com.Turq.DigitalSchool.model.ListCoursesbyTeacher;
import com.Turq.DigitalSchool.model.activity;
import com.Turq.DigitalSchool.model.activityCourse;
import com.Turq.DigitalSchool.model.assignmentTeacherCourse;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.getActivity;
import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.lesson;
import com.Turq.DigitalSchool.model.student;
import com.Turq.DigitalSchool.model.teacher;
import com.Turq.DigitalSchool.model.user;
import com.Turq.DigitalSchool.repository.activityCourseRepository;
import com.Turq.DigitalSchool.repository.activityRepository;
import com.Turq.DigitalSchool.repository.courseRepository;
import com.Turq.DigitalSchool.repository.coursesTeacherRepository;
import com.Turq.DigitalSchool.repository.gradeRepository;
import com.Turq.DigitalSchool.repository.lessonReposity;
import com.Turq.DigitalSchool.repository.studentRepository;
import com.Turq.DigitalSchool.repository.teacherRepository;
import com.Turq.DigitalSchool.repository.userRepository;
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/course")
public class courseController {
	
	@Autowired
	private courseRepository courseRep;
	
	@Autowired
	private gradeRepository gradeRep;
	
	@Autowired
	private lessonReposity lessonRep;
	
	@Autowired
	private activityCourseRepository ActivityCourseRep;
	
	@Autowired
	private coursesTeacherRepository AssRep;
	
	@Autowired
	private userRepository UserRep;
	

	@Autowired
	private teacherRepository TeacherRep;
	
	
	@Autowired
	private studentRepository StudentRep;
	
	
	//Get course by Id
	@GetMapping("getcoursesbyIdCourse/{idcourse}")
	public course getAllcourses(@PathVariable(value="idcourse") Long idcourse){
		return courseRep.getOne(idcourse);
		
	}

	@GetMapping("getcourses/{idSchool}")
	public List<ListCoursesbyTeacher> getAllcoursesbyGrade(@PathVariable(value="idSchool") Long idSchool){
		
				List<ListCoursesbyTeacher> course = courseRep.getCourses(idSchool);		
		return course;
		
	}

	
	
	@GetMapping("getTeacherCourses/{idUser}")
	public List<ListCoursesbyTeacher> getAllcoursesbyTeacher(@PathVariable(value="idUser") Long idUser){
				
		teacher Teacher = this.TeacherRep.teacherByIdUser(idUser);
		
		List<ListCoursesbyTeacher> course = AssRep.getCoursesbyT(Teacher.getIdTeacher());
		
	
				
				
		return course;
		
	}
	
	@GetMapping("getTeacherCoursesByIdTeacher/{idTeacher}")
	public List<ListCoursesbyTeacher> getAllcoursesbyIdTeacher(@PathVariable(value="idTeacher") Long idTeacher){
				
		teacher Teacher = this.TeacherRep.getOne(idTeacher);
		
		List<ListCoursesbyTeacher> course = AssRep.getCoursesbyT(Teacher.getIdTeacher());
		
	
				
				
		return course;
		
	}

	
	@PostMapping("/putcourse")
	public  String CreateNewCourse(addNewCourseByGrade getCourse) throws ResourceNotFoundException {
		
		Long grade = getCourse.getIdGrade();
		List<Long> Lessons = getCourse.getIdCourses();
		
		grade grado= this.gradeRep.getOne(grade);
		
		for (int counterLesson =0; counterLesson<Lessons.size();counterLesson++) {
			
			
			lesson L = lessonRep.getOne(Lessons.get(counterLesson));
			course curso = new course(grado, L);
			courseRep.save(curso);
			
			
		}
		
		
		
		return 	"success";
			}
	
	


	
	
	

	@DeleteMapping("/deleteCourse/{idCourse}/{idSchool}")
	public String newUser(@PathVariable(value = "idCourse") Long idCourse,
			@PathVariable(value = "idSchool") Long idSchool
			){
		
		//delete all the activities
		
		System.out.println("get idCourse "  +idCourse);
		 List<activityCourse>  act = ActivityCourseRep.getAssActivitybyCourse(idCourse);
		
		
			System.out.println("this course has activity? "  +act.size()); 
		if(act.size()>0) {
			ActivityCourseRep.deleteActivityByList(act);
			}
		
		
	
	// delete all the notes 
	
	//delete the assignment with the teacher in this Lesson
		
	List<assignmentTeacherCourse> ass = AssRep.getAllfromCourse(idCourse);
	System.out.println("  ******** esta mandando a eliminar el curso: "+ idCourse +"***************  tamano de asignaciones" + ass.size()+ "************* ");
	if(ass.size()>0) {
		AssRep.deletefromList(ass);}
	
	
	

	
	//delete Lesson
	courseRep.deleteById(idCourse);

	return "Success";
			
			}
	
	
}
 class addNewCourseByGrade {
	
	public Long idGrade;
	
	public List<Long> idCourses;

	public Long getIdGrade() {
		return idGrade;
	}

	public void setIdGrade(Long idGrade) {
		this.idGrade = idGrade;
	}

	public List<Long> getIdCourses() {
		return idCourses;
	}

	public void setIdCourses(List<Long> idCourses) {
		this.idCourses = idCourses;
	}

	public addNewCourseByGrade(Long idGrade, List<Long> idCourses) {
		super();
		this.idGrade = idGrade;
		this.idCourses = idCourses;
	}

	public addNewCourseByGrade() {
		super();
	}

}
 