// 20161116 MM c2-integrate-google-banners INIT Created class Banner
// 20161116 MM c2-integrate-google-banners Moved class inside com.genesiis.campus.entity.model package
// 20161116 MM c2-integrate-google-banners Added fields crtOn, crtBy, modOn, modBy and their getters & setters
// 20161218 MM c2-integrate-google-banners Made default fields private

package com.genesiis.campus.entity.model;

import java.util.Date;

public class Banner {
	private int code;
	private String image;
	private Date expirationDate;
	private String type;
	private double displayDuration;
	private String linkType;
	private String url;
	private int bannerStatus;
	private boolean isActive;
	private int pageSlot;
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
