// 20161116 MM c2-integrate-google-banners INIT Created class Banner

package com.genesiis.campus.entity;

import java.util.Date;

public class Banner {
	int code;
	String image;
	Date expirationDate;
	String type;
	double displayDuration;
	String linkType;
	String url;
	int bannerStatus;
	boolean isActive;
	int pageSlot;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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
	public int getPageSlot() {
		return pageSlot;
	}
	public void setPageSlot(int pageSlot) {
		this.pageSlot = pageSlot;
	}
	
}
