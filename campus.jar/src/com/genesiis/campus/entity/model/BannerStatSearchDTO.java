package com.genesiis.campus.entity.model;

//DJ 20161231 c52-report-banner-statistics-MP-dj created BannerStatSearchDTO.java

import java.util.Date;

public class BannerStatSearchDTO {
	
	private int bannerCode;
	private int pageSlotCode;
	private Date fromDate;
	private Date toDate;
	
	public int getBannerCode() {
		return bannerCode;
	}
	public void setBannerCode(int bannerCode) {
		this.bannerCode = bannerCode;
	}
	
	public int getPageSlotCode() {
		return pageSlotCode;
	}
	public void setPageSlotCode(int pageSlotCode) {
		this.pageSlotCode = pageSlotCode;
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
