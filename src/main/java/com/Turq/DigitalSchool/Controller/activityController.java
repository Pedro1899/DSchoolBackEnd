package com.Turq.DigitalSchool.Controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.getActivity;
import com.Turq.DigitalSchool.model.getStudent;
import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.lesson;
import com.Turq.DigitalSchool.model.sendActivityWithCoursesList;
import com.Turq.DigitalSchool.model.student;
import com.Turq.DigitalSchool.model.teacher;
import com.Turq.DigitalSchool.model.tutor;
import com.Turq.DigitalSchool.model.user;
import com.Turq.DigitalSchool.repository.activityCourseRepository;
import com.Turq.DigitalSchool.repository.activityRepository;
import com.Turq.DigitalSchool.repository.courseRepository;
import com.Turq.DigitalSchool.repository.coursesTeacherRepository;
import com.Turq.DigitalSchool.repository.gradeRepository;
import com.Turq.DigitalSchool.repository.lessonReposity;
import com.Turq.DigitalSchool.repository.teacherRepository;
import com.Turq.DigitalSchool.repository.userRepository;
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/Activity")
public class activityController {
	
	@Autowired
	private courseRepository courseRep;
	
	@Autowired
	private gradeRepository gradeRep;
	
	@Autowired
	private activityRepository activityRep;
	
	
	@Autowired
	private activityCourseRepository activityCourseRep;
	
	@Autowired
	private teacherRepository teacherRep;
	
	@Autowired
	private userRepository userRep;
	
	@Autowired
	private coursesTeacherRepository teacherCoursesRep;
	
	
	
	
	
	
	
	
	
	//Get course by Id




//String activityDescription, String activityfoto, String activityDate, String activityCategory,
//	grade idCourse) {	
	/*
	 *@PostMapping("/putActivity/")
	public activity newUser(@PathVariable(value = "Description") String descripcion,
						@PathVariable(value = "title") String title,
						@PathVariable(value = "Picture") String foto,
						@PathVariable(value = "date") String fecha,
						@PathVariable(value = "Category") String categoria,
						@PathVariable(value = "idCourse") Long idcourse
						) {
		
		course curso= this.courseRep.getOne(idcourse);
				
		activity actividad = new activity(descripcion, title, foto, fecha, categoria, curso);
		return 	this.activityRep.save(actividad);
			}
	*/
	
	
	
	
	@PostMapping("/setActivity")
	  public String greetingSubmit(getActivity getActivity) throws ResourceNotFoundException {
	   
	activity setActivity = new activity(getActivity.getDescription(), getActivity.getPhoto(), getActivity.getDateActivity(), getActivity.getCategory(), getActivity.getTitle(), getActivity.getIdAdmin());
	setActivity = this.activityRep.save(setActivity);
	
	List<Long> courses  = getActivity.getIdCourses();
	
	
	for(int index =0; index<courses.size(); index++) {
	
		
		course getCourse = courseRep.getOne(courses.get(index));
		System.out.println("get id course to save " + getCourse.getId() + " the admin who create this is " + getActivity.getIdAdmin());
		activityCourse newAssignmntCourseActivity = new activityCourse(getCourse, setActivity);
		activityCourseRep.save(newAssignmntCourseActivity);
		System.out.println("saved " );
		
	}
	
		
	    return "greeting";
	  }
	
	
	

	@GetMapping("/getActivity/{idUser}")
	public List<sendActivityWithCoursesList> getAllActivitybyTeacher(@PathVariable(value="idUser") Long idUser) throws ResourceNotFoundException {
		System.out.println("INSIDE ");
		
		List<sendActivityWithCoursesList> ActivityAnswer = new ArrayList<>();
		teacher getTeacher = teacherRep.teacherByIdUser(idUser);
		
		List<course> courses = teacherCoursesRep.getCourses(getTeacher.getIdTeacher());
		System.out.println("get courses " + courses.size());
		System.out.println("List courses ****************************** ");
		
		
		List<activity> ListActivities= activityCourseRep.CountAcitivyforCourse(courses);
		System.out.println("actividades " + ListActivities.size());
		for (int countActivity =0; countActivity<ListActivities.size(); countActivity++  ) {
			
			
			sendActivityWithCoursesList activityPivot = new sendActivityWithCoursesList();
			activityPivot.setTitle(ListActivities.get(countActivity).getActivityTitle());
			activityPivot.setDescription(ListActivities.get(countActivity).getActivityDescription());
			 System.out.println("/******************************************************/");
			  System.out.println("/****************C A M B I O  **************************************/");
			  String startDateString = ListActivities.get(countActivity).getActivityDate();
			    SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    try {
			    	startDateString =sdf2.format(sdf.parse(startDateString));
					System.out.println(sdf2.format(sdf.parse(startDateString)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	activityPivot.setDateActivity(startDateString);
	 System.out.println("SEND DATE ACTIVITY "  + startDateString);
			activityPivot.setPhoto(ListActivities.get(countActivity).getActivityfoto());
			activityPivot.setCategory(ListActivities.get(countActivity).getActivityCategory());
			activityPivot.setIdCourses(activityCourseRep.getCoursesbyActivity(ListActivities.get(countActivity),courses));
			//get the picture of the admin who create the Activity
			user getUser = userRep.getOne(ListActivities.get(countActivity).getIdAdmin());
			activityPivot.setPictureAdmin(getUser.getPicture());
			//get name and surname of admin who create this activity
			teacher t = teacherRep.teacherByIdUser(ListActivities.get(countActivity).getIdAdmin());
			activityPivot.setNameAdmin(t.getName());
			activityPivot.setSurnameAdmin(t.getSurname());
			
			ActivityAnswer.add(activityPivot);
			
						
			
		}
	
	
		
	    return ActivityAnswer;
	  }
	
	

	@GetMapping("/getActivityAdmin/{idUser}")
	public List<sendActivityWithCoursesList> getAllActivityCreatedBtTeacher(@PathVariable(value="idUser") Long idUser) throws ResourceNotFoundException {

		System.out.println("get user " +idUser);
		
		List<sendActivityWithCoursesList> ActivityAnswer = new ArrayList<>();
		teacher getTeacher = teacherRep.teacherByIdUser(idUser);
		
		
		
		
		List<activity> ListActivities= activityCourseRep.CountAcitivyforAdmin(idUser);
		
		
		System.out.println("actividades " + ListActivities.size());
		for (int countActivity =0; countActivity<ListActivities.size(); countActivity++  ) {
			
			List<course> courses = activityCourseRep.getCoursebyidActivity(ListActivities.get(countActivity));
			sendActivityWithCoursesList activityPivot = new sendActivityWithCoursesList();
			activityPivot.setId(ListActivities.get(countActivity).getId());
			activityPivot.setTitle(ListActivities.get(countActivity).getActivityTitle());
			activityPivot.setDescription(ListActivities.get(countActivity).getActivityDescription());
			activityPivot.setDateActivity(ListActivities.get(countActivity).getActivityDate());
			activityPivot.setPhoto(ListActivities.get(countActivity).getActivityfoto());
			activityPivot.setCategory(ListActivities.get(countActivity).getActivityCategory());
			activityPivot.setIdCourses(activityCourseRep.getCoursesbyActivity(ListActivities.get(countActivity),courses));
			//get the picture of the admin who create the Activity
			user getUser = userRep.getOne(ListActivities.get(countActivity).getIdAdmin());
			activityPivot.setPictureAdmin(getUser.getPicture());
			//get name and surname of admin who create this activity
			teacher t = teacherRep.teacherByIdUser(ListActivities.get(countActivity).getIdAdmin());
			activityPivot.setNameAdmin(t.getName());
			activityPivot.setSurnameAdmin(t.getSurname());
			
			ActivityAnswer.add(activityPivot);
			
						
			
		}
	
	
		
	    return ActivityAnswer;
	  }
	
	
	@DeleteMapping("/deleteActivity/{idActivity}")
	  public String DeleteActivity(@PathVariable(value="idActivity") Long idActivity) throws ResourceNotFoundException {
		   
		//delete all activities assigned to courses
		
		activity act = activityRep.getOne(idActivity);
	activityCourseRep.deleteActivityByIdAct(act);
		
		activityRep.delete(act);
		
		
			
		    return "greeting";
		  }

}


