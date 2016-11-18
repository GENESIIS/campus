// 20161116 MM c2-integrate-google-banners INIT - Initialised class
// 20161116 MM c2-integrate-google-banners Moved class inside com.genesiis.campus.entity.model package

package com.genesiis.campus.entity.model;

import java.sql.Date;

public class BannerStat {
	
	int code;
	String callerPage;
	java.sql.Date viewDate;
	java.sql.Time viewTime;
	int banner;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	
	public int getCode() {
		return code;
	}
	public String getCallerPage() {
		return callerPage;
	}
	public java.sql.Date getViewDate() {
		return viewDate;
	}
	public java.sql.Time getViewTime() {
		return viewTime;
	}
	public int getBanner() {
		return banner;
	}
	public Date getCrtOn() {
		return crtOn;
	}
	public String getCrtBy() {
		return crtBy;
	}
	public Date getModOn() {
		return modOn;
	}
	public String getModBy() {
		return modBy;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setCallerPage(String callerPage) {
		this.callerPage = callerPage;
	}
	public void setViewDate(java.sql.Date viewDate) {
		this.viewDate = viewDate;
	}
	public void setViewTime(java.sql.Time viewTime) {
		this.viewTime = viewTime;
	}
	public void setBanner(int banner) {
		this.banner = banner;
	}
	public void setCrtOn(Date crtOn) {
		this.crtOn = crtOn;
	}
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	public void setModOn(Date modOn) {
		this.modOn = modOn;
	}
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
}
