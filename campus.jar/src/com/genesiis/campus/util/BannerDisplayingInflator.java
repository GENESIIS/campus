package com.genesiis.campus.util;
/*
 * 20170309 DN BannerDisplayingInflator class has been created
 * 20170403 DN c86-admin-manage-banner-search-banner-dn bannerCode field is added.
 * 				getter and setter for the field bannerCode is implemented
 */

/**
 * The Class JasonBannerDisplayingInflator. The benefit of the class is
 * to encapsulate the filtering criteria that has been specified
 * to list down all the banner.
 * @author dushantha DN
 */
public class BannerDisplayingInflator {
	
	/** The commencing date. */
	private String commencingDate;
	
	/** The cessation date. */
	private String cessationDate;
	
	/** The active inactive status. */
	private String activeInactiveStatus;
	
	/** The Banner Code */
	private String bannerCode;
	
	/**
	 * getBannerCode retrieve the banner code<br>
	 * if the banner code is not set then the<br>
	 *  method returns empty string.<br>
	 * @return String banner Code
	 */
	public String getBannerCode() {
		return bannerCode;
	}

	/**
	 * setBannerCode accepts a String banner Code.<br>
	 * sets the banner code to the pass in parameter.<br>
	 * @param bannerCode String
	 * @return void
	 */
	public void setBannerCode(String bannerCode) {
		this.bannerCode = bannerCode;
	}

	/**
	 * Gets the commencing date.
	 *
	 * @return the commencing date
	 */
	public String getCommencingDate() {
		return commencingDate;
	}
	
	/**
	 * Sets the commencing date.
	 *
	 * @param commencingDate the new commencing date
	 */
	public void setCommencingDate(String commencingDate) {
		this.commencingDate = commencingDate;
	}
	
	/**
	 * Gets the cessation date.
	 *
	 * @return the cessation date
	 */
	public String getCessationDate() {
		return cessationDate;
	}
	
	/**
	 * Sets the cessation date.
	 *
	 * @param cessationDate the new cessation date
	 */
	public void setCessationDate(String cessationDate) {
		this.cessationDate = cessationDate;
	}
	
	/**
	 * Gets the active inactive status.
	 *
	 * @return the active inactive status
	 */
	public String getActiveInactiveStatus() {
		return activeInactiveStatus;
	}
	
	/**
	 * Sets the active inactive status.
	 *
	 * @param activeInactiveStatus the new active inactive status
	 */
	public void setActiveInactiveStatus(String activeInactiveStatus) {
		this.activeInactiveStatus = activeInactiveStatus;
	}
}
