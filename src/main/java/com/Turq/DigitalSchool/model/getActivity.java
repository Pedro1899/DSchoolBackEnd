package com.Turq.DigitalSchool.model;




import java.util.List;

import javax.persistence.*;




public class getActivity {

	
	
	
	
	private String Title;
	
	
	private String Description;
	
	
	private String DateActivity;
	
	
	
	private String photo;
	
	private String Category;
	

	private List<Long> idCourses ;

	
	
	private Long idAdmin;



	public getActivity(String title, String description, String dateActivity, String photo, String category,
			List<Long> idCourses, Long idAdmin) {
		super();
		Title = title;
		Description = description;
		DateActivity = dateActivity;
		this.photo = photo;
		Category = category;
		this.idCourses = idCourses;
		this.idAdmin = idAdmin;
	}



	public getActivity() {
		super();
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



	public List<Long> getIdCourses() {
		return idCourses;
	}



	public void setIdCourses(List<Long> idCourses) {
		this.idCourses = idCourses;
	}



	public Long getIdAdmin() {
		return idAdmin;
	}



	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}

	
	
	
	
}