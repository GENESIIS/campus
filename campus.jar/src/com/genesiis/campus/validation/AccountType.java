package com.genesiis.campus.validation;

//20161124 JH c7-higher-education-landing-page-MP AccountType.java created

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

}
