package com.genesiis.campus.validation;

//20161124 JH c7-higher-education-landing-page-MP ApplicationStatus.java created
//20161221 DJ c51-report-courses-by-course-provider-MP-dj Implement generic method getApplicationStatus()

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
	
	/**
	 * Generic method for map the status.Able to call from whole campus application
	 * @author DJ
	 * @param selected status Value string
	 * @return applicationStatus
	 * @throws Exception
	 */
	public static int getApplicationStatus(String statusValue){
		int applicationStatus=0;		
		if(statusValue.equalsIgnoreCase("ACTIVE")){
			applicationStatus = ApplicationStatus.ACTIVE.getStatusValue();
		}
		if(statusValue.equalsIgnoreCase("INACTIVE")){
			applicationStatus = ApplicationStatus.INACTIVE.getStatusValue();
		}
		if(statusValue.equalsIgnoreCase("PENDING") ){
			applicationStatus = ApplicationStatus.PENDING.getStatusValue();
		}
		
		return applicationStatus;
	}
}

