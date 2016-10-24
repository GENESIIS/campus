package com.genesiis.campus.entity.model;

//20161024 CM  c13 display course details INIT Module.java.
//20161024 CM  c13 display course details Create variables, getters and setters.

public class Module {

	int code;
	String name;
	String description;
	String internalCodeModule;
	double creditValue;
	int compulsoryStatus;
	String tutoredBy;
	int isActive;
	int semester;
	int tutor;
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
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
	 * @param name the name to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the internalCodeModule
	 */
	public String getInternalCodeModule() {
		return internalCodeModule;
	}
	/**
	 * @param internalCodeModule the internalCodeModule to set
	 */
	public void setInternalCodeModule(String internalCodeModule) {
		this.internalCodeModule = internalCodeModule;
	}
	/**
	 * @return the creditValue
	 */
	public double getCreditValue() {
		return creditValue;
	}
	/**
	 * @param creditValue the creditValue to set
	 */
	public void setCreditValue(double creditValue) {
		this.creditValue = creditValue;
	}
	/**
	 * @return the compulsoryStatus
	 */
	public int getCompulsoryStatus() {
		return compulsoryStatus;
	}
	/**
	 * @param compulsoryStatus the compulsoryStatus to set
	 */
	public void setCompulsoryStatus(int compulsoryStatus) {
		this.compulsoryStatus = compulsoryStatus;
	}
	/**
	 * @return the tutoredBy
	 */
	public String getTutoredBy() {
		return tutoredBy;
	}
	/**
	 * @param tutoredBy the tutoredBy to set
	 */
	public void setTutoredBy(String tutoredBy) {
		this.tutoredBy = tutoredBy;
	}
	/**
	 * @return the isActive
	 */
	public int getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}
	/**
	 * @return the tutor
	 */
	public int getTutor() {
		return tutor;
	}
	/**
	 * @param tutor the tutor to set
	 */
	public void setTutor(int tutor) {
		this.tutor = tutor;
	}
	
}
