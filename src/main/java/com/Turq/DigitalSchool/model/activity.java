package com.Turq.DigitalSchool.model;

import javax.persistence.*;

@Entity
@Table
public class activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column
	private String ActivityDescription;
	
	@Column
	private String ActivityTitle;
	
	@Column
	private String Activityfoto;
	
	@Column 
	private String ActivityDate;
	
		
	@Column
	private String ActivityCategory;
	
	
	@Column
	private Long idAdmin;

	
	
	public activity() {
		super();
	}

	public activity(String activityDescription, String activityfoto, String activityDate, String activityCategory,String activityTite, Long idadmin) {
		super();
		ActivityDescription = activityDescription;
		Activityfoto = activityfoto;
		ActivityDate = activityDate;
		ActivityCategory = activityCategory;
		this.idAdmin = idadmin;
		
		this.ActivityTitle =  activityTite;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
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
		ActivityDescription = activityTitle;
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
