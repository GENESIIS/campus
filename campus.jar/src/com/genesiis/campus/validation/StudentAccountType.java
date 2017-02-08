package com.genesiis.campus.validation;

//DJ 20170102 c53-report-registered-students-MP-dj created StudentAccountType.java
/**
* StudentAccountType enum created to manage all Student account type values
* of the application. 
* @author dumani
*
*/
public enum StudentAccountType {
	
	REGISTERED(1),
	TEMPORARY(2);
	
	private final int accountTypeValue;
	
	StudentAccountType(int accountType){
		this.accountTypeValue = accountType;		
	}
	
	/**
	 * @return the accountTypeValue
	 */
	public int getAccountTypeValue() {
		return accountTypeValue;
	}

}
