package com.Turq.DigitalSchool.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Turq.DigitalSchool.exception.ResourceNotFoundException;
import com.Turq.DigitalSchool.model.DetailNotes;
import com.Turq.DigitalSchool.model.activity;
import com.Turq.DigitalSchool.model.activityCourse;
import com.Turq.DigitalSchool.model.course;
import com.Turq.DigitalSchool.model.getActivity;
import com.Turq.DigitalSchool.model.grade;
import com.Turq.DigitalSchool.model.lesson;
import com.Turq.DigitalSchool.model.notes;
import com.Turq.DigitalSchool.model.notesCategory;
import com.Turq.DigitalSchool.model.school;
import com.Turq.DigitalSchool.model.student;
import com.Turq.DigitalSchool.repository.DetailNotesRepository;
import com.Turq.DigitalSchool.repository.SchoolRepository;
import com.Turq.DigitalSchool.repository.activityCourseRepository;
import com.Turq.DigitalSchool.repository.activityRepository;
import com.Turq.DigitalSchool.repository.courseRepository;
import com.Turq.DigitalSchool.repository.coursesTeacherRepository;
import com.Turq.DigitalSchool.repository.gradeRepository;
import com.Turq.DigitalSchool.repository.lessonReposity;
import com.Turq.DigitalSchool.repository.notesCategoryRepository;
import com.Turq.DigitalSchool.repository.notesRepository;
import com.Turq.DigitalSchool.repository.studentRepository;
import com.Turq.DigitalSchool.repository.teacherRepository;
import com.Turq.DigitalSchool.repository.userRepository;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/Notes")
public class NotesController {

	
	@Autowired
	private notesRepository notesRep;
	
	@Autowired
	private lessonReposity lessonRep;
	
	@Autowired
	private notesCategoryRepository notesCatRep;
	
	@Autowired
	private DetailNotesRepository DetailsNotesRep;
	
	
	@Autowired
	private coursesTeacherRepository AssTeachCourseRep;
	
	@Autowired
	private studentRepository studentsRep;
	
	@Autowired
	private userRepository userRep;
	
	@Autowired
	private SchoolRepository schoolRep;
	
	@Autowired
	private courseRepository coursesRep;
	
	
	@PostMapping("/putCategoryNote/{Description}/{idSchool}")
	public notesCategory newUser(@PathVariable(value = "Description") String Description,
						@PathVariable(value = "idSchool") Long idSchool						
						)throws ResourceNotFoundException {
		
		List<Long> getExistingGrade = this.notesCatRep.getCatByDescription(Description.toLowerCase(), idSchool);
		
				if(getExistingGrade.size()>0) {
					throw new ResourceNotFoundException("This Grade already exist");  
					}
				
		school school= this.schoolRep.getOne(idSchool);
		notesCategory note = new notesCategory(Description,school);
		return 	this.notesCatRep.save(note);
		
			}
	
	

	
	
	
	@GetMapping("/getNotes/{idSchool}")
	public List<notesCategory> getCategory(@PathVariable(value = "idSchool") Long idSchool						
						)throws ResourceNotFoundException {
		
		
		return 	this.notesCatRep.getAll(idSchool);
		
			}
	
	
	
	@PostMapping("/setNotes")
	  public notes newNotes(getNotes getNotes) throws ResourceNotFoundException {
	   notesCategory  category = notesCatRep.getOne(getNotes.getIdCategoryNote());
		course Course = coursesRep.getOne(getNotes.getIdCourse());
		String getDate =getNotes.getGetDate();
			
		Date date1=Date.valueOf(getDate);
		System.out.println(date1);
		notes NewNote = new notes(null,getNotes.getTitle(),date1,category,null,Course);
		NewNote = notesRep.save(NewNote);
		return NewNote;
	  }
	
	
	@PostMapping("/setResults/{idStudent}/{result}/{IdNotes}")
	  public String newResults(@PathVariable(value = "idStudent") Long idStudent,
				@PathVariable(value = "result") Long result, @PathVariable(value = "IdNotes") Long idNotes) throws ResourceNotFoundException {
	  System.out.println("get id notes " +idNotes);
		notes gtNotes = notesRep.getOne(idNotes);
	  student gtStudent = studentsRep.getOne(idStudent);
	  DetailNotes dnotes = new DetailNotes(gtNotes,gtStudent,result);
	  DetailsNotesRep.save(dnotes);
	  
	  return "success";
	 
	  }
	
	
	@GetMapping("/GetResultsByStudent/{idStudent}/{idCategory}/"
			+ "{dayStartDate}/{MonthStartDate}/{YearStartDate}/"
			+ "{dayFinishDate}/{MonthFinishDate}/{YearFinishDate}")
	public List<SendStudentReport> getResultByStudent(@PathVariable(value = "idStudent") Long idStudent,
			@PathVariable(value = "idCategory") Long idCategory, 
			@PathVariable(value = "dayStartDate") String dayStart,
			@PathVariable(value = "MonthStartDate") String monthStart,
			@PathVariable(value = "YearStartDate") String yearStart,
			@PathVariable(value = "dayFinishDate") String dayFinish,
			@PathVariable(value = "MonthFinishDate") String monthFinish,
			@PathVariable(value = "YearFinishDate") String yearFinish
			
			)
			throws ResourceNotFoundException {
		int getRealMonth = Integer.parseInt(monthStart) + 1;
		int getRealMonth2  = Integer.parseInt(monthFinish) + 1;
		String fullDateS = yearStart+"-"+getRealMonth+"-"+dayStart;
		String fullDateF = yearFinish+"-"+getRealMonth2+"-"+dayFinish;

	
		Date dateStart=Date.valueOf(fullDateS);
		Date dateEnd=Date.valueOf(fullDateF);
List<SendStudentReport> answer = new ArrayList<SendStudentReport>();

student getStudent = studentsRep.getOne(idStudent);
List<course> coursesGroupStudent = coursesRep.getAllfromGrade(getStudent.getIdGrade().getIdgrade());
for(int index=0; index<coursesGroupStudent.size();index++) {
	SendStudentReport reportIteration = new SendStudentReport();
	reportIteration.setLesson(coursesGroupStudent.get(index).getIdLesson().getDescription());
	List<notes> Note = notesRep.getNotesByIdCourseXCategory(coursesGroupStudent.get(index), idCategory, dateStart, dateEnd);
	List<DetailStudentReport> ListResultsOfThisLesson = new ArrayList<DetailStudentReport>();
	for(int indexDetail=0; indexDetail<Note.size();indexDetail++) {
		DetailStudentReport	ItResultOfThisLesson = new DetailStudentReport();
		ItResultOfThisLesson.setTitle(Note.get(indexDetail).getNotesTitle());
		ItResultOfThisLesson.setDate(Note.get(indexDetail).getNotesyDate().toString());
		ItResultOfThisLesson.setResult(DetailsNotesRep.getNotesByidStudent(Note.get(indexDetail), getStudent));
		ListResultsOfThisLesson.add(ItResultOfThisLesson);
		}
	reportIteration.setReporte(ListResultsOfThisLesson);
	answer.add(reportIteration);
}
		
return answer;
		
			}

	
	
	
	/*
	@GetMapping("/GetResultsByNotes/{idGrade}/{idCategory}/{StartDate}/{EndDate}")
	public List<GeneralReport> getAllResults(@PathVariable(value = "idGrade") Long idGrade,
											@PathVariable(value = "idCategory") Long idCategory,
											@PathVariable(value = "StartDate") String StartDate,
											@PathVariable(value = "StartDate") String EndDate
											
			)
			throws ResourceNotFoundException {
List<GeneralReport> gReport = new ArrayList<GeneralReport>();

List<course> courses = coursesRep.getAllfromGrade(idGrade);

		for(int index=0;index<courses.size();index++) {
			
			course getCourse = courses.get(index);
			lesson Lesson = getCourse.getIdLesson();
			Date dateStart=Date.valueOf(StartDate);
			Date dateEnd=Date.valueOf(EndDate);
			List<notes> Note = notesRep.getNotesByIdCourseXCategory(getCourse, idCategory, dateStart, dateEnd);
			System.out.println("how many Notes we have for the Course " + getCourse.getIdGrade().getDescription() + " : " +getCourse.getIdLesson().getDescription() + " - > " + Note.size());
			int mediaOfMedia = 0;
			GeneralReport grIteration = new GeneralReport();
			grIteration.setNameLesson(Lesson.getDescription());
			
			if(Note.size()>0) {
				List<ReportList> AnswerList = new ArrayList<ReportList>();
				for(int indexNote =0;indexNote<Note.size();indexNote++ ) {
					  
					List<DetailNotes> dN = DetailsNotesRep.getNotesByIdNotes(Note.get(indexNote));
					int sizeDetailNote = dN.size();
					int result =0;
					for (int DetailNotesResult =0; DetailNotesResult<dN.size();DetailNotesResult++) {
						
						if(dN.get(DetailNotesResult).getResult()>=0) {
							result+= dN.get(DetailNotesResult).getResult();
						}else {
							sizeDetailNote = sizeDetailNote-1;
						}
						
					}
				
					int media = result / sizeDetailNote;
					mediaOfMedia=+media;
					String nameLesson = Lesson.getDescription();
					ReportList rL = new ReportList();
					rL.setHeader(nameLesson);
					rL.setResult(media);
					rL.setDate(Note.get(indexNote).getNotesyDate().toString());
					rL.setTitle(Note.get(indexNote).getNotesTitle());
					AnswerList.add(rL);
				}
				mediaOfMedia=mediaOfMedia/Note.size();
				grIteration.setResult(mediaOfMedia);
				grIteration.setMediaOfIterations(AnswerList);
				gReport.add(grIteration);
				
			}
			
		}
		
		
return gReport;
		
			}*/

	
	
	@GetMapping("/GetResultsByCourses/{idCourse}/{idCategory}/"
			+ "{dayStartDate}/{MonthStartDate}/{YearStartDate}/"
			+ "{dayFinishDate}/{MonthFinishDate}/{YearFinishDate}")
	public List<CourseResultsHeader> getResultByCourse(@PathVariable(value = "idCourse") Long idCourse,
			@PathVariable(value = "idCategory") Long idCategory, 
			@PathVariable(value = "dayStartDate") String dayStart,
			@PathVariable(value = "MonthStartDate") String monthStart,
			@PathVariable(value = "YearStartDate") String yearStart,
			@PathVariable(value = "dayFinishDate") String dayFinish,
			@PathVariable(value = "MonthFinishDate") String monthFinish,
			@PathVariable(value = "YearFinishDate") String yearFinish
			
			)
			throws ResourceNotFoundException {

System.out.println(idCategory);
int getRealMonth = Integer.parseInt(monthStart) + 1;
int getRealMonth2  = Integer.parseInt(monthFinish) + 1;
String fullDateS = yearStart+"-"+getRealMonth+"-"+dayStart;
String fullDateF = yearFinish+"-"+getRealMonth2+"-"+dayFinish;

course gCourse = coursesRep.getOne(idCourse);
Date dateStart=Date.valueOf(fullDateS);
Date dateEnd=Date.valueOf(fullDateF);
List<notes> Note = notesRep.getNotesByIdCourseXCategory(gCourse, idCategory, dateStart, dateEnd);
List<CourseResultsHeader> answer = new ArrayList<CourseResultsHeader>();
for(int index=0; index<Note.size(); index++) {
	
	String title = Note.get(index).getNotesTitle();
	String date = Note.get(index).getNotesyDate().toString();
	CourseResultsHeader ResultIteration = new CourseResultsHeader();
	ResultIteration.setTitle(title);
	ResultIteration.setDate(date);
	
	List<DetailNotes> dN = DetailsNotesRep.getNotesByIdNotes(Note.get(index));
	List<CourseResult> cR = new ArrayList<CourseResult>();
	for (int indexDetail =0; indexDetail<dN.size();indexDetail++) {
		
		CourseResult cRIteration = new CourseResult();
		cRIteration.setNameStudent(dN.get(indexDetail).getStudent().getName());
		cRIteration.setSurnameStudent(dN.get(indexDetail).getStudent().getSurname());
		cRIteration.setPictureStudent(dN.get(indexDetail).getStudent().getPhoto());
		cRIteration.setNote(dN.get(indexDetail).getResult());
		cRIteration.setIdStudent(dN.get(indexDetail).getStudent().getId());
		cR.add(cRIteration);
	}
	
	ResultIteration.setResult(cR);
	answer.add(ResultIteration);
	
	
	

}
		
		
return answer;
		
			}






	private int parseInt(notes notes) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}




class getNotes {
	
	private Long idCategoryNote;
	private Long idCourse;
	private String getDate;
	private String title;
	
	

	public getNotes(Long idCategoryNote, Long idCourse, String getDate, String title) {
		super();
		this.idCategoryNote = idCategoryNote;
		this.idCourse = idCourse;
		this.getDate = getDate;
		this.title = title;
		
	}

	public getNotes() {
		super();
	}

	public Long getIdCategoryNote() {
		return idCategoryNote;
	}

	public void setIdCategoryNote(Long idCategoryNote) {
		this.idCategoryNote = idCategoryNote;
	}

	public Long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}

	public String getGetDate() {
		return getDate;
	}

	public void setGetDate(String getDate) {
		this.getDate = getDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	
	
	
}

class GeneralReport{
	private String nameLesson;
	private int result;
	private List<ReportList> MediaOfIterations;
	
	
	public GeneralReport() {
		super();
	}
	public GeneralReport(String nameLesson, int result, List<ReportList> mediaOfIterations) {
		super();
		this.nameLesson = nameLesson;
		this.result = result;
		MediaOfIterations = mediaOfIterations;
	}
	public String getNameLesson() {
		return nameLesson;
	}
	public void setNameLesson(String nameLesson) {
		this.nameLesson = nameLesson;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<ReportList> getMediaOfIterations() {
		return MediaOfIterations;
	}
	public void setMediaOfIterations(List<ReportList> mediaOfIterations) {
		MediaOfIterations = mediaOfIterations;
	}
	
	
	
}



class ReportList{
	
	private String Header;
	private int result;
	private String date;
	private String title;
	
	
	
	public ReportList() {
		super();
	}

	public String getHeader() {
		return Header;
	}
	public void setHeader(String header) {
		Header = header;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getResult() {
		return result;
	}
	public void setResult(int result2) {
		this.result = result2;
	}
	
}

class CourseResultsHeader{
	private String title;
	private String date;
	private List<CourseResult> result;
	public CourseResultsHeader(String title, String date, List<CourseResult> result) {
		super();
		this.title = title;
		this.date = date;
		this.result = result;
	}
	public CourseResultsHeader() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<CourseResult> getResult() {
		return result;
	}
	public void setResult(List<CourseResult> result) {
		this.result = result;
	}
	
}

class CourseResult{
	private Long IdStudent;
	private String nameStudent;
	private String surnameStudent;
	private String pictureStudent;
	private Long Note;
	public CourseResult(String nameStudent, String surnameStudent, String pictureStudent, Long note, Long IdStudent) {
		super();
		this.nameStudent = nameStudent;
		this.surnameStudent = surnameStudent;
		this.pictureStudent = pictureStudent;
		this.IdStudent= IdStudent;
		Note = note;
	}
	public CourseResult() {
		super();
	}
	public String getNameStudent() {
		return nameStudent;
	}
	public void setNameStudent(String nameStudent) {
		this.nameStudent = nameStudent;
	}
	public String getSurnameStudent() {
		return surnameStudent;
	}
	public void setSurnameStudent(String surnameStudent) {
		this.surnameStudent = surnameStudent;
	}
	public String getPictureStudent() {
		return pictureStudent;
	}
	public void setPictureStudent(String pictureStudent) {
		this.pictureStudent = pictureStudent;
	}
	public Long getNote() {
		return Note;
	}
	public void setNote(Long note) {
		Note = note;
	}
	public Long getIdSLong() {
		return IdStudent;
	}
	public void setIdStudent(Long idStudent) {
		this.IdStudent = idStudent;
	}	
}

class SendStudentReport{
	
	private String Lesson;
	private List<DetailStudentReport> reporte;
	public SendStudentReport(String lesson, List<DetailStudentReport> reporte) {
		super();
		Lesson = lesson;
		this.reporte = reporte;
	}
	
	
	public SendStudentReport() {
		super();
	}


	public String getLesson() {
		return Lesson;
	}
	public void setLesson(String lesson) {
		Lesson = lesson;
	}
	public List<DetailStudentReport> getReporte() {
		return reporte;
	}
	public void setReporte(List<DetailStudentReport> reporte) {
		this.reporte = reporte;
	}
	
	
	
}

class DetailStudentReport{
	
	private String title;
	private String date;
	private Long result;
	public DetailStudentReport() {
		super();
	}
	public DetailStudentReport(String title, String date, Long result) {
		super();
		this.title = title;
		this.date = date;
		this.result = result;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getResult() {
		return result;
	}
	public void setResult(Long result) {
		this.result = result;
	}
	
}