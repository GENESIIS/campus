package com.genesiis.campus.entity.model;

import java.util.Date;

//DJ 20161228 c53-report-registered-students-MP-dj created Student.java
//DJ 20170102 c53-report-registered-students-MP-dj Renamed as StudentSearchDTO.java
//DJ 20170104 c53-report-registered-students-MP-dj Add  districtCode entity
/**
 * The class {@code StudentSearchDTO} is a form of SearchDTO created for the purpose of transfer input parameters to DAO level. 
 * @author dumani DJ
 *
*/
public class StudentSearchDTO {	
	
	private int districtCode;
	private String firstName;
	private String LastName;	
	private int studentStatus;
	private int accountType;
	private Date fromDate;
	private Date toDate;
			
	public int getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public int getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(int status) {
		this.studentStatus = status;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	

}
