package com.genesiis.campus.validation;

//20161124 JH c7-higher-education-landing-page-MP ApplicationStatus.java created
//20170309 DN c81-admin-manage-banner-add-and-view-banner-dn add a new enum UNDEFINED to the list

/**
 * ApplicationStatus enum created to manage all the status values
 * of the application. 
 * @author jayani
 *
 */
public enum ApplicationStatus {

	UNDEFINED(0),
	ACTIVE(1),
	INACTIVE(2),
	PENDING(3),
	EXPIRED(4);	
	
	
	private final int statusValue;

	ApplicationStatus(final int statusValue){
		this.statusValue = statusValue;
	}

	/**
	 * @return the statusValue
	 */
	public int getStatusValue() {
		return statusValue;
	}

}