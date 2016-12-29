package com.genesiis.campus.entity.model;

//20161121 MM c25-student-create-dashboard-MP INIT - Initialised file with student and numberOfProgrammes fields

public class RecommendedProgrammesSearchDTO {
	private int student;
	private int numberOfProgrammes;
	
	public int getStudent() {
		return student;
	}
	public int getNumberOfProgrammes() {
		return numberOfProgrammes;
	}
	public void setStudent(int student) {
		this.student = student;
	}
	public void setNumberOfProgrammes(int numberOfProgrammes) {
		this.numberOfProgrammes = numberOfProgrammes;
	}
}
