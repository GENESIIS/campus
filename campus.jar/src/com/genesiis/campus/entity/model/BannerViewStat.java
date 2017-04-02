// 20170330 MM c117-display-banners-record-viewcount-back-end - INIT class added properties and getters/setters
// 20170331 MM c117-display-banners-record-viewcount-back-end - Modified fields and getters/setters to reflect the changes made to the BANNERVIEWSTAT table
// 20170401 MM c117-display-banners-record-viewcount-back-end - Removed callerPage member and its getter and setter

package com.genesiis.campus.entity.model;

import java.sql.Date;

public class BannerViewStat {
	private int code;
	private int banner;
	private int viewCount;
	private java.sql.Date lastViewDate;
	private java.sql.Time lastViewTime;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the banner
	 */
	public int getBanner() {
		return banner;
	}
	/**
	 * @param banner the banner to set
	 */
	public void setBanner(int banner) {
		this.banner = banner;
	}
	/**
	 * @return the viewCount
	 */
	public int getViewCount() {
		return viewCount;
	}
	/**
	 * @param viewCount the viewCount to set
	 */
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	/**
	 * @return the lastViewDate
	 */
	public java.sql.Date getLastViewDate() {
		return lastViewDate;
	}
	/**
	 * @param lastViewDate the lastViewDate to set
	 */
	public void setLastViewDate(java.sql.Date lastViewDate) {
		this.lastViewDate = lastViewDate;
	}
	/**
	 * @return the lastViewTime
	 */
	public java.sql.Time getLastViewTime() {
		return lastViewTime;
	}
	/**
	 * @param lastViewTime the lastViewTime to set
	 */
	public void setLastViewTime(java.sql.Time lastViewTime) {
		this.lastViewTime = lastViewTime;
	}
	/**
	 * @return the crtOn
	 */
	public Date getCrtOn() {
		return crtOn;
	}
	/**
	 * @param crtOn the crtOn to set
	 */
	public void setCrtOn(Date crtOn) {
		this.crtOn = crtOn;
	}
	/**
	 * @return the crtBy
	 */
	public String getCrtBy() {
		return crtBy;
	}
	/**
	 * @param crtBy the crtBy to set
	 */
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	/**
	 * @return the modOn
	 */
	public Date getModOn() {
		return modOn;
	}
	/**
	 * @param modOn the modOn to set
	 */
	public void setModOn(Date modOn) {
		this.modOn = modOn;
	}
	/**
	 * @return the modBy
	 */
	public String getModBy() {
		return modBy;
	}
	/**
	 * @param modBy the modBy to set
	 */
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

}
