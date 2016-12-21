package com.genesiis.campus.entity.model;

//20161121 CM c36-add-tutor-information INIT Tutor.java

import java.sql.Date;

public class Tutor {
 
	private int code;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String experience;
	private String description;
	private String mobileCountryCode;
	private String mobileNetworkCode;
	private String mobileNumber;
	private String landCountryCode;
	private String landAreaCode;
	private String landNumber;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String webLink;
	private String facebookLink;
	private String linkedInLink;
	private String twitterNumber;
	private String instagramId;
	private String mySpaceId;
	private String whatsAppId;
	private String viberNumber; 
	private String emailAddress;
	private String username;
	private String password;
	private String town;
	private int usertype;
	private int isActive;
	private int isApproved;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the mobileCountryCode
	 */
	public String getMobileCountryCode() {
		return mobileCountryCode;
	}
	/**
	 * @param mobileCountryCode the mobileCountryCode to set
	 */
	public void setMobileCountryCode(String mobileCountryCode) {
		this.mobileCountryCode = mobileCountryCode;
	}
	/**
	 * @return the mobileNetworkCode
	 */
	public String getMobileNetworkCode() {
		return mobileNetworkCode;
	}
	/**
	 * @param mobileNetworkCode the mobileNetworkCode to set
	 */
	public void setMobileNetworkCode(String mobileNetworkCode) {
		this.mobileNetworkCode = mobileNetworkCode;
	}
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @return the landCountryCode
	 */
	public String getLandCountryCode() {
		return landCountryCode;
	}
	/**
	 * @param landCountryCode the landCountryCode to set
	 */
	public void setLandCountryCode(String landCountryCode) {
		this.landCountryCode = landCountryCode;
	}
	/**
	 * @return the landAreaCode
	 */
	public String getLandAreaCode() {
		return landAreaCode;
	}
	/**
	 * @param landAreaCode the landAreaCode to set
	 */
	public void setLandAreaCode(String landAreaCode) {
		this.landAreaCode = landAreaCode;
	}
	/**
	 * @return the landNumber
	 */
	public String getLandNumber() {
		return landNumber;
	}
	/**
	 * @param landNumber the landNumber to set
	 */
	public void setLandNumber(String landNumber) {
		this.landNumber = landNumber;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}
	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	/**
	 * @return the addressLine3
	 */
	public String getAddressLine3() {
		return addressLine3;
	}
	/**
	 * @param addressLine3 the addressLine3 to set
	 */
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	/**
	 * @return the webLink
	 */
	public String getWebLink() {
		return webLink;
	}
	/**
	 * @param webLink the webLink to set
	 */
	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}
	/**
	 * @return the facebookLink
	 */
	public String getFacebookLink() {
		return facebookLink;
	}
	/**
	 * @param facebookLink the facebookLink to set
	 */
	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}
	/**
	 * @return the linkedInLink
	 */
	public String getLinkedInLink() {
		return linkedInLink;
	}
	/**
	 * @param linkedInLink the linkedInLink to set
	 */
	public void setLinkedInLink(String linkedInLink) {
		this.linkedInLink = linkedInLink;
	}
	/**
	 * @return the twitterNumber
	 */
	public String getTwitterNumber() {
		return twitterNumber;
	}
	/**
	 * @param twitterNumber the twitterNumber to set
	 */
	public void setTwitterNumber(String twitterNumber) {
		this.twitterNumber = twitterNumber;
	}
	/**
	 * @return the instagramId
	 */
	public String getInstagramId() {
		return instagramId;
	}
	/**
	 * @param instagramId the instagramId to set
	 */
	public void setInstagramId(String instagramId) {
		this.instagramId = instagramId;
	}
	/**
	 * @return the mySpaceId
	 */
	public String getMySpaceId() {
		return mySpaceId;
	}
	/**
	 * @param mySpaceId the mySpaceId to set
	 */
	public void setMySpaceId(String mySpaceIdVal) {
		mySpaceId = mySpaceIdVal;
	}
	/**
	 * @return the whatsAppId
	 */
	public String getWhatsAppId() {
		return whatsAppId;
	}
	/**
	 * @param whatsAppId the whatsAppId to set
	 */
	public void setWhatsAppId(String whatsAppId) {
		this.whatsAppId = whatsAppId;
	}
	/**
	 * @return the viberNumber
	 */
	public String getViber() {
		return viberNumber;
	}
	/**
	 * @param viberNumber the viberNumber to set
	 */
	public void setViberNumber(String viberNumberVal) {
		this.viberNumber = viberNumberVal;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
		
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}
	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}
	/**
	 * @return the usertype
	 */
	public int getUsertype() {
		return usertype;
	}
	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	/**
	 * @return the isActive
	 */
	public int getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * @return the isApproved
	 */
	public int getIsApproved() {
		return isApproved;
	}
	/**
	 * @param isApproved the isApproved to set
	 */
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}
	/**
	 * @return the crtOn
	 */
	public Date getCrtOn() {
		return crtOn;
	}
	/**
	 * @param crtOn the crtOn to set
	 */
	public void setCrtOn(Date crtOn) {
		this.crtOn = crtOn;
	}
	/**
	 * @return the crtBy
	 */
	public String getCrtBy() {
		return crtBy;
	}
	/**
	 * @param crtBy the crtBy to set
	 */
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	/**
	 * @return the modOn
	 */
	public Date getModOn() {
		return modOn;
	}
	/**
	 * @param modOn the modOn to set
	 */
	public void setModOn(Date modOn) {
		this.modOn = modOn;
	}
	/**
	 * @return the modBy
	 */
	public String getModBy() {
		return modBy;
	}
	/**
	 * @param modBy the modBy to set
	 */
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
	
	
}
