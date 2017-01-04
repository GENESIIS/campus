package com.genesiis.campus.entity.model;

//DJ 20161231 c52-report-banner-statistics-MP-dj created BannerStatSearchDTO.java

import java.util.Date;

public class BannerStatSearchDTO {	
	private int pageCode;
	private int pageSlotCode;
	private int bannerProviderCode;
	private Date fromDate;
	private Date toDate;	
	
	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	public int getPageSlotCode() {
		return pageSlotCode;
	}
	public void setPageSlotCode(int pageSlotCode) {
		this.pageSlotCode = pageSlotCode;
	}	
	public int getBannerProviderCode() {
		return bannerProviderCode;
	}
	public void setBannerProviderCode(int bannerProviderCode) {
		this.bannerProviderCode = bannerProviderCode;
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
