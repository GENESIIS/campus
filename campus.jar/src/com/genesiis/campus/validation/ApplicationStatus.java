package com.genesiis.campus.validation;

import java.util.HashMap;

//20161124 JH c7-higher-education-landing-page-MP ApplicationStatus.java created
//20161221 DJ c51-report-courses-by-course-provider-MP-dj Implement generic method getApplicationStatus((String statusValue))
//20161221 DJ c51-report-courses-by-course-provider-MP-dj Implement generic method getApplicationStatus(int statusValue)
//20170403 CW c157-add-tutor-employment-details-cw copied the ApplicationStatus from c51-report-courses-by-course-provider-MP-dj issue
//20170425 CW c159-courseprovider-accept-tutor-request-cw command DELETED added
//20170426 CW c159-courseprovider-accept-tutor-request-cw add getApplicationStatus method
//20170427 CW c159-courseprovider-accept-tutor-request-cw command DELETED modified into DELETE
//20170504 CW c159-courseprovider-accept-tutor-request-cw add getapplicationStatusMap method
//20170504 CW c159-courseprovider-accept-tutor-request-cw command UNDEFINED added

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
	EXPIRED(3),
	DELETE(4);	
	
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
	
	/**
	 * Generic method for map the status.Able to call from whole campus application
	 * @author DJ
	 * @param statusValue selected status Value string
	 * @return integer applicationStatus
	 * @throws Exception
	 */
	public static int getApplicationStatus(String statusValue){
		int applicationStatus=0; 
		if (Validator.isNotEmpty(statusValue)) {
			
			if (statusValue.equalsIgnoreCase("UNDEFINED")) {
				applicationStatus = UNDEFINED.getStatusValue();
			}
			if (statusValue.equalsIgnoreCase("INACTIVE")) {
				applicationStatus = INACTIVE.getStatusValue();
			}
			if (statusValue.equalsIgnoreCase("ACTIVE")) {
				applicationStatus = ACTIVE.getStatusValue();
			}
			if (statusValue.equalsIgnoreCase("PENDING")) {
				applicationStatus = PENDING.getStatusValue();
			}
			if (statusValue.equalsIgnoreCase("EXPIRED")) {
				applicationStatus = EXPIRED.getStatusValue();
			}
			if (statusValue.equalsIgnoreCase("DELETE")) {
				applicationStatus = DELETE.getStatusValue();
			}
		}
		
		return applicationStatus;
	}
	
	/**
	 * Generic method for map the status int value with String.Purpose of status view in front end.Able to call from whole campus application
	 * @author DJ
	 * @param statusValue selected int status Value 
	 * @return String applicationStatus
	 * @throws Exception
	 */
	public static String getApplicationStatus(int statusValue){
		String applicationStatus="";				

		if(statusValue==UNDEFINED.statusValue){
			applicationStatus = "Undefined";
		}
		if(statusValue==INACTIVE.statusValue){
			applicationStatus = "InActive";
		}
		if(statusValue ==ACTIVE.statusValue){
			applicationStatus = "Active";
		}
		if(statusValue==PENDING.statusValue ){
			applicationStatus = "Pending";
		}
		if(statusValue==EXPIRED.statusValue ){
			applicationStatus = "Expired";
		}
		if(statusValue==DELETE.statusValue ){
			applicationStatus = "Delete";
		}
		
		return applicationStatus;
	}
	
	/**
	 * Generic method to get the int value with String as key, value pairs in HashMap. 
	 * @author CW
	 * @return HashMap<Integer, String> applicationStatusMap
	 */
	public static HashMap<Integer, String> getapplicationStatusMap(){
		HashMap<Integer, String> applicationStatusMap = new HashMap<Integer, String>();
		
		for (ApplicationStatus statusValues : ApplicationStatus.values()) {
			int statusValueInt = ApplicationStatus.getApplicationStatus(statusValues.toString());
			applicationStatusMap.put(statusValueInt, ApplicationStatus.getApplicationStatus(statusValueInt));
		}
		return applicationStatusMap;
	}
}