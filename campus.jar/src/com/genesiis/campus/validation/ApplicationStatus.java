package com.genesiis.campus.validation;

//20161124 JH c7-higher-education-landing-page-MP ApplicationStatus.java created

/**
 * ApplicationStatus enum created to manage all the status values
 * of the application. 
 * @author jayani
 *
 */
public enum ApplicationStatus {

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
