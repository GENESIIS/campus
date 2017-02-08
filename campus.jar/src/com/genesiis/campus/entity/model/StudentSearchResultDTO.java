package com.genesiis.campus.entity.model;

//DJ 20170131 c53-report-registered-students-MP-dj created StudentSearchResultDTO.java
//DJ 20170131 c53-report-registered-students-MP-dj Add getters and setters

import java.util.Date;
/**
 * The class {@code StudentSearchResultDTO} is a form of SearchDTO created for the purpose of transfer retrieved manipulated parameters from DAO level. 
 * @author dumani DJ
 *
*/
public class StudentSearchResultDTO {
	
	private int studentCode;
	private String studentName;		
	private int studentStatus;
	private String StudentInterest;
	private String town;	
	private Date registeredDate;
	private Date lastLoginDate;
	
	public int getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(int studentCode) {
		this.studentCode = studentCode;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(int studentStatus) {
		this.studentStatus = studentStatus;
	}
	public String getStudentInterest() {
		return StudentInterest;
	}
	public void setStudentInterest(String studentInterest) {
		StudentInterest = studentInterest;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

}
