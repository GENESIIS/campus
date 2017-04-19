package com.genesiis.campus.validation;

//20161124 JH c7-higher-education-landing-page-MP AccountType.java created
//20161127 MM c5-corporate-training-landing-page-MP Added getAccountTypeByTypeValue(int)
// 				method to retrieve enum constant by passing its value for "typeValue"
//20170418 CW c158-send-email-tutor-employment-confirmation-cw add getAccountType method
//20170419 CW c158-send-email-tutor-employment-confirmation-cw modified getAccountType method to get type value correctly

/**
 * This enum class is created to manage course provider account type. 
 * The accountType attribute is used to identify whether the course provider is 
 * a featured course provider or an one-off course provider
 * 
 * @author jayani
 *
 */
public enum AccountType {
	
	
	FEATURED_COURSE_PROVIDER(1),
	ONE_OFF_COURSE_PROVIDER(2);
			
	private final int typeValue;

	AccountType(final int typeValue) {
		this.typeValue = typeValue;
	}

	/**
	 * @return the typeValue
	 */
	public int getTypeValue() {
		return typeValue;
	}	
	
	/**
	 * Returns the particular AccountType enum constant whose value for 
	 * typeValue field matches the argument passed. If multiple enum 
	 * constants of enum type AccountType qualify, then only the first
	 * will be returned. 
	 * 
	 * @param typeValue 
	 * An int indicating the value for typeValue member 
	 * that an AccountType enum constant must have for it to be returned.
	 * 
	 * @return AccountType;
	 * A constant of type AccountType whose value for 
	 * typeValue member must be equal to the argument passed for typeValue.
	 * IMPORTANT: If there are more than one constants with value for their 
	 * typeValue member equal to argument passed, then only the first 
	 * matching enum constant will be returned. 
	 */
	public static AccountType getAccountTypeByTypeValue(int typeValue) {
		
		for (AccountType accountType : AccountType.values()) {
			if (accountType.getTypeValue() == typeValue) {
				return accountType;
			}
		}
		return null;
	}
	
	/**
	 * Generic method for map the accountTypeValue. Able to call from whole campus application
	 * @author CW
	 * @param accountTypeValue selected account type Value string
	 * @return integer AccountType
	 * @throws Exception
	 */
	public static int getAccountType(String accountTypeValue){
		int AccountType=0; 	
		if (!Validator.isEmptyOrHavingSpace(accountTypeValue)) {
			if (accountTypeValue.equalsIgnoreCase("FEATURED_COURSE_PROVIDER")) {
				AccountType = FEATURED_COURSE_PROVIDER.getTypeValue();
			}
			if (accountTypeValue.equalsIgnoreCase("ONE_OFF_COURSE_PROVIDER")) {
				AccountType = ONE_OFF_COURSE_PROVIDER.getTypeValue();
			}
		}
		
		return AccountType;
	}
}
