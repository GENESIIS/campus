package com.genesiis.campus.entity.model;

//20170421 DJ c54-report-course-stats-MP-dj Init:ProgrammeSearchDTO.java 

import java.util.Date;
/**
 * The class {@code ProgrammeSearchDTO} is a form of SearchDTO created for the purpose of transfer input parameters to DAO level. 
 * @author dumani DJ
 *
*/
public class ProgrammeSearchDTO {

	private int programmeCode;
	private int courseProviderCode;
	private Date displayStartDate;
	private Date expiryDate;
	private int providerStatus;
	private int programmeStatus;
	
	public int getCode() {
		return programmeCode;
	}

	public void setCode(int code) {
		this.programmeCode = code;
	}

	public int getCourseProvider() {
		return courseProviderCode;
	}

	public void setCourseProvider(int courseProvider) {
		this.courseProviderCode = courseProvider;
	}

	public Date getDisplayStartDate() {
		return displayStartDate;
	}

	public void setDisplayStartDate(Date displayStartDate) {
		this.displayStartDate = displayStartDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getProviderStatus() {
		return providerStatus;
	}

	public void setProviderStatus(int providerStatus) {
		this.providerStatus = providerStatus;
	}

	public int getProgrammeStatus() {
		return programmeStatus;
	}

	public void setProgrammeStatus(int programmeStatus) {
		this.programmeStatus = programmeStatus;
	}
}
