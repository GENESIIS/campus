package com.genesiis.campus.validation;

//20161124 JH c7-higher-education-landing-page-MP ApplicationStatus.java created
//20170103 JH changed the value of INACTIVE to '0' to support isActive field values.

/**
 * ApplicationStatus enum created to manage all the status values
 * of the application. 
 * @author jayani
 *
 */
public enum ApplicationStatus {
	
	INACTIVE(0),
	ACTIVE(1),
	PENDING(2),
	EXPIRED(3);	
	
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