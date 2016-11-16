// 20161116 MM c2-integrate-google-banners INIT Created class Banner

package com.genesiis.campus.entity;

import java.util.Date;

public class Banner {
	int code;
	Date expirationDate;
	String page;
	String type;
	double displayDuration;
	String linkType;
	String url;
	int bannerStatus;
	boolean isActive;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getDisplayDuration() {
		return displayDuration;
	}
	public void setDisplayDuration(double displayDuration) {
		this.displayDuration = displayDuration;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getBannerStatus() {
		return bannerStatus;
	}
	public void setBannerStatus(int bannerStatus) {
		this.bannerStatus = bannerStatus;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
