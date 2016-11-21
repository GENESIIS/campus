package com.genesiis.campus.entity.model;

import java.sql.Date;

//20161121 CM c36-add-tutor-information INIT Tutor.java

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
	private String facebook;
	private String linkedIn;
	private String twitter;
	private String instagram;
	private String MySpace;
	private String whatsApp;
	private String viber; 
	private String email;
	private String imagePath;
	private String username;
	private String password;
	private int town;
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
	 * @return the facebook
	 */
	public String getFacebook() {
		return facebook;
	}
	/**
	 * @param facebook the facebook to set
	 */
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	/**
	 * @return the linkedIn
	 */
	public String getLinkedIn() {
		return linkedIn;
	}
	/**
	 * @param linkedIn the linkedIn to set
	 */
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	/**
	 * @return the twitter
	 */
	public String getTwitter() {
		return twitter;
	}
	/**
	 * @param twitter the twitter to set
	 */
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	/**
	 * @return the instagram
	 */
	public String getInstagram() {
		return instagram;
	}
	/**
	 * @param instagram the instagram to set
	 */
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
	/**
	 * @return the mySpace
	 */
	public String getMySpace() {
		return MySpace;
	}
	/**
	 * @param mySpace the mySpace to set
	 */
	public void setMySpace(String mySpace) {
		MySpace = mySpace;
	}
	/**
	 * @return the whatsApp
	 */
	public String getWhatsApp() {
		return whatsApp;
	}
	/**
	 * @param whatsApp the whatsApp to set
	 */
	public void setWhatsApp(String whatsApp) {
		this.whatsApp = whatsApp;
	}
	/**
	 * @return the viber
	 */
	public String getViber() {
		return viber;
	}
	/**
	 * @param viber the viber to set
	 */
	public void setViber(String viber) {
		this.viber = viber;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	public int getTown() {
		return town;
	}
	/**
	 * @param town the town to set
	 */
	public void setTown(int town) {
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
