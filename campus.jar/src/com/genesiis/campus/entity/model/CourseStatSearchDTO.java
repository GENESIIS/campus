package com.genesiis.campus.entity.model;

//20170421 DJ c54-report-course-stats-MP-dj -Init:CourseStatSearchDTO.java

import java.util.Date;

public class CourseStatSearchDTO {
	
	private int programmeCode;
	private int providerCode;	
	private Date fromDate;
	private Date toDate;
	
	
	public int getProgrammeCode() {
		return programmeCode;
	}
	public void setProgrammeCode(int programmeCode) {
		this.programmeCode = programmeCode;
	}
	public int getProviderCode() {
		return providerCode;
	}
	public void setProviderCode(int providerCode) {
		this.providerCode = providerCode;
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
