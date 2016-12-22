package com.genesiis.campus.entity.model;
//20161026 PN c11-criteria-based-filter-search: INIT Town.java class
//20161028 PN c11-criteria-based-filter-search: added class attributes and data access methods.
//20161222 DN CAMP:18 made field access modifier private as per the crev comments.
import java.sql.Date;


public class Town {
	private int code;
	private int isActive;
	private int town;
	private int sequence;
	private int country;
	private int province;
	private int district;
	private int dsd;
	private String name;
	private int sort;
	private int status;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	
	public Date getCrtOn() {
		return crtOn;
	}
	public void setCrtOn(Date crtOn) {
		this.crtOn = crtOn;
	}
	public String getCrtBy() {
		return crtBy;
	}
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	public Date getModOn() {
		return modOn;
	}
	public void setModOn(Date modOn) {
		this.modOn = modOn;
	}
	public String getModBy() {
		return modBy;
	}
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getTown() {
		return town;
	}
	public void setTown(int town) {
		this.town = town;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getDistrict() {
		return district;
	}
	public void setDistrict(int district) {
		this.district = district;
	}
	public int getDsd() {
		return dsd;
	}
	public void setDsd(int dsd) {
		this.dsd = dsd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}