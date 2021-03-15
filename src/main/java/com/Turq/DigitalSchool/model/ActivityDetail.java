package com.Turq.DigitalSchool.model;

import java.util.List;

import javax.persistence.*;


public class ActivityDetail {

	
	private Long Id;
	
	private String ActivityTitle;
	
	private String ActivityDescription;
	
	
	private String Activityfoto;
	
	 
	private String ActivityDate;
	
	
	private String ActivityCategory;
	


	
	
	public ActivityDetail() {
		super();
	}

	public ActivityDetail(Long id,String activityTitle,String activityDescription, String activityfoto, String activityDate, String activityCategory) {
		super();
		Id = id;
		ActivityDescription = activityDescription;
		Activityfoto = activityfoto;
		ActivityDate = activityDate;
		ActivityCategory = activityCategory;
		ActivityTitle = activityTitle;
		
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getActivityDescription() {
		return ActivityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		ActivityDescription = activityDescription;
	}

	public String getActivityTitle() {
		return ActivityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		ActivityTitle = activityTitle;
	}
	public String getActivityfoto() {
		return Activityfoto;
	}

	public void setActivityfoto(String activityfoto) {
		Activityfoto = activityfoto;
	}

	public String getActivityDate() {
		return ActivityDate;
	}

	public void setActivityDate(String activityDate) {
		ActivityDate = activityDate;
	}

	public String getActivityCategory() {
		return ActivityCategory;
	}

	public void setActivityCategory(String activityCategory) {
		ActivityCategory = activityCategory;
	}


	

	
	
}
