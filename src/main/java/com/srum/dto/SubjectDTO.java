package com.srum.dto;

public class SubjectDTO {
	private Long id;
	private String name;
	private String description;
	private Float duration;
	private String trainerName;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Float getDuration() {
		return duration;
	}
	
	public void setDuration(Float duration) {
		this.duration = duration;
	}
	
	public String getTrainerName() {
		return trainerName;
	}
	
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
}
