package com.genesiis.campus.validation;

//20161124 JH c7-higher-education-landing-page-MP ApplicationStatus.java created
//20170103 JH changed the value of INACTIVE to '0' to support isActive field values.
//20170309 DN c81-admin-manage-banner-add-and-view-banner-dn add a new enum UNDEFINED to the list
//20170323 DN c83-admin-manage-banner-update-banner-info-dn changed the literal value of UNDEFINED to -1

/**
 * ApplicationStatus enum created to manage all the status values
 * of the application. 
 * @author jayani
 *
 */
public enum ApplicationStatus {
	
	UNDEFINED(-1),
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