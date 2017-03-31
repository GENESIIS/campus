// 20170330 MM c117-display-banners-record-viewcount-back-end INIT class added properties and getters/setters

package com.genesiis.campus.entity.model;

import java.sql.Date;

public class BannerViewStat {
	private int code;
	private String callerPage;
	private java.sql.Date viewDate;
	private java.sql.Time viewTime;
	private int banner;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getCallerPage() {
		return callerPage;
	}
	public void setCallerPage(String callerPage) {
		this.callerPage = callerPage;
	}
	public java.sql.Date getViewDate() {
		return viewDate;
	}
	public void setViewDate(java.sql.Date viewDate) {
		this.viewDate = viewDate;
	}
	public java.sql.Time getViewTime() {
		return viewTime;
	}
	public void setViewTime(java.sql.Time viewTime) {
		this.viewTime = viewTime;
	}
	public int getBanner() {
		return banner;
	}
	public void setBanner(int banner) {
		this.banner = banner;
	}
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

}
