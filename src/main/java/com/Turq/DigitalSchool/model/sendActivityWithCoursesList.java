

package com.Turq.DigitalSchool.model;




import java.util.List;





public class sendActivityWithCoursesList {

	
	
	public Long id;
	
	private String Title;
	
	
	private String Description;
	
	
	private String DateActivity;
	
	
	
	private String photo;
	
	private String Category;
	

	private List<CoursesDetail> idCourses ;

	
	
	private String pictureAdmin;
	

	private String nameAdmin;
	

	private String surnameAdmin;
	
	



	public sendActivityWithCoursesList() {
		super();
	}



	public sendActivityWithCoursesList(String title, String description, String dateActivity, String photo,
			String category) {
		super();
		Title = title;
		Description = description;
		DateActivity = dateActivity;
		this.photo = photo;
		Category = category;
		
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getTitle() {
		return Title;
	}
	
	



	public void setTitle(String title) {
		Title = title;
	}



	public String getDescription() {
		return Description;
	}



	public void setDescription(String description) {
		Description = description;
	}


	
	public String getNameAdmin() {
		return nameAdmin;
	}



	public void setNameAdmin(String nameAdmin) {
		this.nameAdmin = nameAdmin;
	}

	
	public String getSurnameAdmin() {
		return surnameAdmin;
	}



	public void setSurnameAdmin(String surnameAdmin) {
		this.surnameAdmin = surnameAdmin;
	}

	

	public String getDateActivity() {
		return DateActivity;
	}



	public void setDateActivity(String dateActivity) {
		DateActivity = dateActivity;
	}



	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public String getCategory() {
		return Category;
	}



	public void setCategory(String category) {
		Category = category;
	}



	public List<CoursesDetail> getIdCourses() {
		return idCourses;
	}



	public void setIdCourses(List<CoursesDetail> idCourses) {
		this.idCourses = idCourses;
	}



	public String getIdAdmin() {
		return pictureAdmin;
	}



	public void setPictureAdmin(String pictureAdmin) {
		this.pictureAdmin = pictureAdmin;
	}

	
	
	
}