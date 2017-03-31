// 20170330 MM c117-display-banners-record-viewcount-back-end INIT class added properties and getters/setters
// 20170331 MM c117-display-banners-record-viewcount-back-end Modified fields and getters/setters to reflect the changes made to the BANNERVIEWSTAT table

package com.genesiis.campus.entity.model;

import java.sql.Date;

public class BannerViewStat {
	private int code;
	private int banner;
	private int viewCount;
	private String callerPage;
	private java.sql.Date lastViewDate;
	private java.sql.Time lastViewTime;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	public int getCode() {
		return code;
	}
	public int getBanner() {
		return banner;
	}
	public int getViewCount() {
		return viewCount;
	}
	public String getCallerPage() {
		return callerPage;
	}
	public java.sql.Date getLastViewDate() {
		return lastViewDate;
	}
	public java.sql.Time getLastViewTime() {
		return lastViewTime;
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
	public void setBanner(int banner) {
		this.banner = banner;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public void setCallerPage(String callerPage) {
		this.callerPage = callerPage;
	}
	public void setLastViewDate(java.sql.Date lastViewDate) {
		this.lastViewDate = lastViewDate;
	}
	public void setLastViewTime(java.sql.Time lastViewTime) {
		this.lastViewTime = lastViewTime;
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
