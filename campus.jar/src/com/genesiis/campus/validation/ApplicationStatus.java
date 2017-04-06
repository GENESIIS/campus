package com.genesiis.campus.validation;

//20161124 JH c7-higher-education-landing-page-MP ApplicationStatus.java created
//20161221 DJ c51-report-courses-by-course-provider-MP-dj Implement generic method getApplicationStatus((String statusValue))
//20161221 DJ c51-report-courses-by-course-provider-MP-dj Implement generic method getApplicationStatus(int statusValue)
//20170207 DJ c138-add-basic-programme-MP-dj  Add ARCHIVE(5) enum to status values
//20170406 DJ c138 changed values in application status enums


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
	EXPIRED(3),
	ARCHIVE(5);
	
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
		if (UtilityHelper.isNotEmpty(statusValue)) {
			if (statusValue.equalsIgnoreCase("ACTIVE")) {
				applicationStatus = ACTIVE.getStatusValue();
			}
			if (statusValue.equalsIgnoreCase("INACTIVE")) {
				applicationStatus = INACTIVE.getStatusValue();
			}
			if (statusValue.equalsIgnoreCase("PENDING")) {
				applicationStatus = PENDING.getStatusValue();
			}
			if (statusValue.equalsIgnoreCase("EXPIRED")) {
				applicationStatus = EXPIRED.getStatusValue();
			}
			if (statusValue.equalsIgnoreCase("ARCHIVE")) {
				applicationStatus = ARCHIVE.getStatusValue();
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
		
		if(statusValue ==ACTIVE.statusValue){
			applicationStatus = "Active";
		}
		if(statusValue==INACTIVE.statusValue){
			applicationStatus = "InActive";
		}
		if(statusValue==PENDING.statusValue ){
			applicationStatus = "Pending";
		}
		if(statusValue==EXPIRED.statusValue ){
			applicationStatus = "Expired";
		}
		
		return applicationStatus;
	}
}

