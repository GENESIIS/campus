package com.genesiis.campus.entity.model;

// TODO: Auto-generated Javadoc
/*
 * 20170207 DN c131-admin-manage-banner-upload-banner-image-dn created the initial AdvertiserRole interface stub
 * 20170428 DN c88-admin-manage-advertiser-add-new-advertiser-dn added the java doc comments for the method signatures.
 * 20170428 DN c88-admin-manage-advertiser-add-new-advertiser-dn changed the return type and the signature of the methods
 * 				getTownCode() and setTownCode(String townCode)
 */				

/**
 * The Interface AdvertiserRole.
 */
public interface AdvertiserRole {

	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode();
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(int code);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) ;
	
	/**
	 * Gets the general email.
	 *
	 * @return the general email
	 */
	public String getGeneralEmail() ;
	
	/**
	 * Sets the general email.
	 *
	 * @param generalEmail the new general email
	 */
	public void setGeneralEmail(String generalEmail);
	
	/**
	 * Gets the land phone country code.
	 *
	 * @return the land phone country code
	 */
	public String getLandPhoneCountryCode();
	
	/**
	 * Sets the land phone country code.
	 *
	 * @param landPhoneCountryCode the new land phone country code
	 */
	public void setLandPhoneCountryCode(String landPhoneCountryCode);
	
	/**
	 * Gets the land phone area code.
	 *
	 * @return the land phone area code
	 */
	public String getLandPhoneAreaCode() ;
	
	/**
	 * Sets the land phone area code.
	 *
	 * @param landPhoneAreaCode the new land phone area code
	 */
	public void setLandPhoneAreaCode(String landPhoneAreaCode) ;
	
	/**
	 * Gets the land phone no.
	 *
	 * @return the land phone no
	 */
	public String getLandPhoneNo() ;
	
	/**
	 * Sets the land phone no.
	 *
	 * @param landPhoneNo the new land phone no
	 */
	public void setLandPhoneNo(String landPhoneNo) ;
	
	/**
	 * Gets the mobile phone country code.
	 *
	 * @return the mobile phone country code
	 */
	public String getMobilePhoneCountryCode();
	
	/**
	 * Sets the mobile phone country code.
	 *
	 * @param mobilePhoneCountryCode the new mobile phone country code
	 */
	public void setMobilePhoneCountryCode(String mobilePhoneCountryCode);
	
	/**
	 * Gets the mobile phone network code.
	 *
	 * @return the mobile phone network code
	 */
	public String getMobilePhoneNetworkCode() ;
	
	/**
	 * Sets the mobile phone network code.
	 *
	 * @param mobilePhoneNetworkCode the new mobile phone network code
	 */
	public void setMobilePhoneNetworkCode(String mobilePhoneNetworkCode) ;
	
	/**
	 * Gets the mobile phone number.
	 *
	 * @return the mobile phone number
	 */
	public String getMobilePhoneNumber();
	
	/**
	 * Sets the mobile phone number.
	 *
	 * @param mobilePhoneNumber the new mobile phone number
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) ;
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() ;
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description);
	
	/**
	 * Gets the address 1.
	 *
	 * @return the address 1
	 */
	public String getAddress1();
	
	/**
	 * Sets the address 1.
	 *
	 * @param address1 the new address 1
	 */
	public void setAddress1(String address1) ;
	
	/**
	 * Gets the address 2.
	 *
	 * @return the address 2
	 */
	public String getAddress2();
	
	/**
	 * Sets the address 2.
	 *
	 * @param address2 the new address 2
	 */
	public void setAddress2(String address2);
	
	/**
	 * Gets the address 3.
	 *
	 * @return the address 3
	 */
	public String getAddress3();
	
	/**
	 * Sets the address 3.
	 *
	 * @param address3 the new address 3
	 */
	public void setAddress3(String address3);

	
	/**
	 * Gets the active status.
	 *
	 * @return the active status
	 */
	public int getActiveStatus ();
	
	/**
	 * Sets the active status.
	 *
	 * @param status the new active status
	 */
	public void setActiveStatus(int status);
	
	/**
	 * Gets the town code.
	 *
	 * @return the town code
	 */
	public String getTownCode();
	
	/**
	 * Sets the town C ode.
	 *
	 * @param townCode the new town C ode
	 */
	void setTownCode(String townCode);
	
}
