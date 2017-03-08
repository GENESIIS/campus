package com.genesiis.campus.util;
/*
 * 20170220-DN c131-admin-manage-banner-upload-banner-image-dn 
 *             created the class JasonInflator.java
 * 20170308 DN c131-admin-manage-banner-upload-banner-image-dn corrected
 *             as per the CREV comments 20170307.1645h PN and added
 *             java doc comments to the class.
 */

/**
 * JasonInflator is used to store deserialized data
 * that comes from the client side, this is a custom class 
 * and the fields should be sync with the client input
 *  which is provided from the view "dist/js/banner/uploadBanner.jsp" page.
 * @author dushantha DN
 *
 */

public class JasonInflator {
	
	/*
	 * The following field identifiers are named in a way to
	 * sync with the field names of the flattened (serialized)
	 * Gson instance received from the browser. Thus should not be changed.
	 *  If it ought to be changed, the field identifiers should be analogous to 
	 * array jasonBanner [name] property of method 
	 * dist/js/banner/uploadBanner.js#sendBannerPaageFieldInputs()
	 */
	private String advertiserCode;	
	private String codeOfSelectedPage;	
	private String bannerSlotCode;	
	private String displayDusration;	
	private String banerToBeActive; //"true"
	private String bannerPublishingDate; //"2017-02-14"
	private String bannerPublishingEndDate;		
	private String urlMiniWebOrPage;	//"Page:0","URL: 1" or "Mini Web:2"
	private String urlToBeDirectedOnBannerClick;	
	private String bannerImageName;
	
	
	public String getUrlMiniWebOrPage() {
		return urlMiniWebOrPage;
	}

	public void setUrlMiniWebOrPage(String urlMiniWebOrPage) {
		this.urlMiniWebOrPage = urlMiniWebOrPage;
	}

	
	public String getDisplayDusration() {
		return displayDusration;
	}

	public void setDisplayDusration(String displayDusration) {
		this.displayDusration = displayDusration;
	}

	public String getBannerImageName() {
		return bannerImageName;
	}

	public void setBannerImageName(String bannerImageName) {
		this.bannerImageName = bannerImageName;
	}

	/**
	 * Gets the advertiser code.
	 *
	 * @return the advertiser code
	 */
	public String getAdvertiserCode() {
		return advertiserCode;
	}
	
	/**
	 * Sets the advertiser code.
	 *
	 * @param advertiserCode the new advertiser code
	 */
	public void setAdvertiserCode(String advertiserCode) {
		this.advertiserCode = advertiserCode;
	}
	
	/**
	 * Gets the code of selected page.
	 *
	 * @return the code of selected page
	 */
	public String getCodeOfSelectedPage() {
		return codeOfSelectedPage;
	}
	
	/**
	 * Sets the code of selected page.
	 *
	 * @param codeOfSelectedPage the new code of selected page
	 */
	public void setCodeOfSelectedPage(String codeOfSelectedPage) {
		this.codeOfSelectedPage = codeOfSelectedPage;
	}
	
	/**
	 * Gets the banner slot code.
	 *
	 * @return the banner slot code
	 */
	public String getBannerSlotCode() {
		return bannerSlotCode;
	}
	
	/**
	 * Sets the banner slot code.
	 *
	 * @param bannerSlotCode the new banner slot code
	 */
	public void setBannerSlotCode(String bannerSlotCode) {
		this.bannerSlotCode = bannerSlotCode;
	}
	
	/**
	 * Gets the dusration.
	 *
	 * @return the dusration
	 */
	public String getDusration() {
		return displayDusration;
	}
	
	/**
	 * Sets the dusration.
	 *
	 * @param dusration the new dusration
	 */
	public void setDusration(String dusration) {
		this.displayDusration = dusration;
	}
	
	/**
	 * Gets the baner to be active.
	 *
	 * @return the baner to be active
	 */
	public String getBanerToBeActive() {
		return banerToBeActive;
	}
	
	/**
	 * Sets the baner to be active.
	 *
	 * @param banerToBeActive the new baner to be active
	 */
	public void setBanerToBeActive(String banerToBeActive) {
		this.banerToBeActive = banerToBeActive;
	}
	
	/**
	 * Gets the banner publishing date.
	 *
	 * @return the banner publishing date
	 */
	public String getBannerPublishingDate() {
		return bannerPublishingDate;
	}
	
	/**
	 * Sets the banner publishing date.
	 *
	 * @param bannerPublishingDate the new banner publishing date
	 */
	public void setBannerPublishingDate(String bannerPublishingDate) {
		this.bannerPublishingDate = bannerPublishingDate;
	}
	
	/**
	 * Gets the banner publishing end date.
	 *
	 * @return the banner publishing end date
	 */
	public String getBannerPublishingEndDate() {
		return bannerPublishingEndDate;
	}
	
	/**
	 * Sets the banner publishing end date.
	 *
	 * @param bannerPublishingEndDate the new banner publishing end date
	 */
	public void setBannerPublishingEndDate(String bannerPublishingEndDate) {
		this.bannerPublishingEndDate = bannerPublishingEndDate;
	}
	
	/**
	 * Gets the url to be directed on banner click.
	 *
	 * @return the url to be directed on banner click
	 */
	public String getUrlToBeDirectedOnBannerClick() {
		return urlToBeDirectedOnBannerClick;
	}
	
	/**
	 * Sets the url to be directed on banner click.
	 *
	 * @param urlToBeDirectedOnBannerClick the new url to be directed on banner click
	 */
	public void setUrlToBeDirectedOnBannerClick(String urlToBeDirectedOnBannerClick) {
		this.urlToBeDirectedOnBannerClick = urlToBeDirectedOnBannerClick;
	}
	
}