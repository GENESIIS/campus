package com.genesiis.campus.entity.model;

//20170327 CW c157-add-tutor-employment-details-cw INIT Employment.java

import java.sql.Date;

public class Employment {
	private int code;
	private int varificationstatus;
	private Date crton;
	private String crtby;
	private Date modon;
	private String modby;
	private int tutor;
	private int courseprovider;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getVarificationstatus() {
		return varificationstatus;
	}
	public void setVarificationstatus(int varificationstatus) {
		this.varificationstatus = varificationstatus;
	}
	public Date getCrton() {
		return crton;
	}
	public void setCrton(Date crton) {
		this.crton = crton;
	}
	public String getCrtby() {
		return crtby;
	}
	public void setCrtby(String crtby) {
		this.crtby = crtby;
	}
	public Date getModon() {
		return modon;
	}
	public void setModon(Date modon) {
		this.modon = modon;
	}
	public String getModby() {
		return modby;
	}
	public void setModby(String modby) {
		this.modby = modby;
	}
	public int getTutor() {
		return tutor;
	}
	public void setTutor(int tutor) {
		this.tutor = tutor;
	}
	public int getCourseprovider() {
		return courseprovider;
	}
	public void setCourseprovider(int courseprovider) {
		this.courseprovider = courseprovider;
	}
}
