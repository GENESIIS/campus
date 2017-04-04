package com.genesiis.campus.entity.model;

//20170327 CW c157-add-tutor-employment-details-cw INIT Employment.java
//20170404 CW c157-add-tutor-employment-details-cw add doc comments

import java.sql.Date;

/**
 * @author chinthaka
 */
public class Employment {
	private int code;
	private int varificationstatus;
	private Date crton;
	private String crtby;
	private Date modon;
	private String modby;
	private int tutor;
	private int courseprovider;
	
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
	 * @return the varificationstatus
	 */
	public int getVarificationstatus() {
		return varificationstatus;
	}
	/**
	 * @param varificationstatus the varificationstatus to set
	 */
	public void setVarificationstatus(int varificationstatus) {
		this.varificationstatus = varificationstatus;
	}
	/**
	 * @return the crton
	 */
	public Date getCrton() {
		return crton;
	}
	/**
	 * @param crton the crton to set
	 */
	public void setCrton(Date crton) {
		this.crton = crton;
	}
	/**
	 * @return the crtby
	 */
	public String getCrtby() {
		return crtby;
	}
	/**
	 * @param crtby the crtby to set
	 */
	public void setCrtby(String crtby) {
		this.crtby = crtby;
	}
	/**
	 * @return the modon
	 */
	public Date getModon() {
		return modon;
	}
	/**
	 * @param modon the modon to set
	 */
	public void setModon(Date modon) {
		this.modon = modon;
	}
	/**
	 * @return the modby
	 */
	public String getModby() {
		return modby;
	}
	/**
	 * @param modby the modby to set
	 */
	public void setModby(String modby) {
		this.modby = modby;
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
	/**
	 * @return the courseprovider
	 */
	public int getCourseprovider() {
		return courseprovider;
	}
	/**
	 * @param courseprovider the courseprovider to set
	 */
	public void setCourseprovider(int courseprovider) {
		this.courseprovider = courseprovider;
	}
}
