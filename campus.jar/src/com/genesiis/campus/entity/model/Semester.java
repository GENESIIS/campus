package com.genesiis.campus.entity.model;

//20161024 CM  c13 display course details INIT Semester.java.
//20161024 CM  c13 display course details Create variables, getters and setters.
//20161101 CM  c13 display course details Add private modifier to the variables.

import java.sql.Date;

public class Semester {

	private int code;
	private String name;
	private String description;
	private int yearNo;
	private int semesterNo;
	private int isActive;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	private int programme;

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the yearNo
	 */
	public int getYearNo() {
		return yearNo;
	}

	/**
	 * @param yearNo
	 *            the yearNo to set
	 */
	public void setYearNo(int yearNo) {
		this.yearNo = yearNo;
	}

	/**
	 * @return the semesterNo
	 */
	public int getSemesterNo() {
		return semesterNo;
	}

	/**
	 * @param semesterNo
	 *            the semesterNo to set
	 */
	public void setSemesterNo(int semesterNo) {
		this.semesterNo = semesterNo;
	}

	/**
	 * @return the isActive
	 */
	public int getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the crtOn
	 */
	public Date getCrtOn() {
		return crtOn;
	}

	/**
	 * @param crtOn
	 *            the crtOn to set
	 */
	public void setCrtOn(Date crtOn) {
		this.crtOn = crtOn;
	}

	/**
	 * @return the crtBy
	 */
	public String getCrtBy() {
		return crtBy;
	}

	/**
	 * @param crtBy
	 *            the crtBy to set
	 */
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}

	/**
	 * @return the modOn
	 */
	public Date getModOn() {
		return modOn;
	}

	/**
	 * @param modOn
	 *            the modOn to set
	 */
	public void setModOn(Date modOn) {
		this.modOn = modOn;
	}

	/**
	 * @return the modBy
	 */
	public String getModBy() {
		return modBy;
	}

	/**
	 * @param modBy
	 *            the modBy to set
	 */
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	/**
	 * @return the programme
	 */
	public int getProgramme() {
		return programme;
	}

	/**
	 * @param programme
	 *            the programme to set
	 */
	public void setProgramme(int programme) {
		this.programme = programme;
	}

}
