package com.genesiis.campus.entity.model;

import java.sql.Date;

//DJ 20161227 c52-report-banner-statistics-MP-dj created BannerStat.java

public class BannerStat {
	private int code;	
	private int banner;	
	private String callerPage;
	private Date viewDate;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getBanner() {
		return banner;
	}
	public void setBanner(int banner) {
		this.banner = banner;
	}
	public String getCallerPage() {
		return callerPage;
	}
	public void setCallerPage(String callerPage) {
		this.callerPage = callerPage;
	}
	public Date getViewDate() {
		return viewDate;
	}
	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}	

}
