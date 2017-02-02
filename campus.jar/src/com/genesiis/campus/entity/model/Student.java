package com.genesiis.campus.entity.model;

//20161121 MM c25-student-login-create-dashboard-MP-mm INIT - Initialised file
//20161122 MM c25-student-login-create-dashboard-MP-mm Added additional fields and setters and getters
//20161201 DN C18-student-signup-without-using-third-party-application-test-dn add private modifier to all fields as per CREVcomments
//20161209 DN C18-student-signup-without-using-third-party-application-test-dn add userTypeCode.
//20161121 MM c25-student-login-create-dashboard-MP-mm INIT - Initialised file
//20161122 MM c25-student-login-create-dashboard-MP-mm Added additional fields and setters and getters
//20161202 PN c27-upload-user-image: added private access modifier to the field attributes.

public class Student {
	private int code;
	private String username;
	private String password;
	private String indexNo;
	private String firstName;
	private String middleName;
	private String lastName;
	private java.sql.Date dateOfBirth;
	private int gender;
	private String email;
	private String type;
	private int userTypeCode;
	private String imagePath;
	private String landPhoneCountryCode;
	private String landPhoneAreaCode;
	private String landPhoneNo;
	private String mobilePhoneCountryCode;
	private String mobilePhoneNetworkCode;
	private String mobilePhoneNo;
	private String description;
	private String facebookUrl;
	private String twitterUrl;
	private String mySpaceUrl;
	private String linkedInUrl;
	private String instagramUrl;
	private String viberNumber;
	private String whatsAppNumber;
	private String address1;
	private String address2;
	private String address3;
	private String town;
	private String accountType;
	private String lastLoggedInUserAgent;
	private String lastLoggedInSessionid;
	private String lastLoggedInDate;
	private String lastLoggedInTime;
	private String lastLoggedInIpAddress;
	private String lastLoggedOutDate;
	private String lastLoggedOutTime;
	private String lastLoginAuthenticatedBy;
	private boolean isActive;
	private java.sql.Date crtOn;
	private String crtBy;
	private java.sql.Date modOn;
	private String modBy;
	
	public int getUserTypeCode() {
		return userTypeCode;
	}
	public void setUserTypeCode(int userType) {
		this.userTypeCode = userType;
	}
	
	public int getCode() {
		return code;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getIndexNo() {
		return indexNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public java.sql.Date getDateOfBirth() {
		return dateOfBirth;
	}
	public int getGender() {
		return gender;
	}
	public String getEmail() {
		return email;
	}
	public String getType() {
		return type;
	}
	public String getImagePath() {
		return imagePath;
	}
	public String getLandPhoneCountryCode() {
		return landPhoneCountryCode;
	}
	public String getLandPhoneAreaCode() {
		return landPhoneAreaCode;
	}
	public String getLandPhoneNo() {
		return landPhoneNo;
	}
	public String getMobilePhoneCountryCode() {
		return mobilePhoneCountryCode;
	}
	public String getMobilePhoneNetworkCode() {
		return mobilePhoneNetworkCode;
	}
	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}
	public String getDescription() {
		return description;
	}
	public String getFacebookUrl() {
		return facebookUrl;
	}
	public String getTwitterUrl() {
		return twitterUrl;
	}
	public String getMySpaceUrl() {
		return mySpaceUrl;
	}
	public String getLinkedInUrl() {
		return linkedInUrl;
	}
	public String getInstagramUrl() {
		return instagramUrl;
	}
	public String getViberNumber() {
		return viberNumber;
	}
	public String getWhatsAppNumber() {
		return whatsAppNumber;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getAddress3() {
		return address3;
	}
	public String getTown() {
		return town;
	}
	public String getAccountType() {
		return accountType;
	}
	public String getLastLoggedInUserAgent() {
		return lastLoggedInUserAgent;
	}
	public String getLastLoggedInSessionid() {
		return lastLoggedInSessionid;
	}
	public String getLastLoggedInDate() {
		return lastLoggedInDate;
	}
	public String getLastLoggedInTime() {
		return lastLoggedInTime;
	}
	public String getLastLoggedInIpAddress() {
		return lastLoggedInIpAddress;
	}
	public String getLastLoggedOutDate() {
		return lastLoggedOutDate;
	}
	public String getLastLoggedOutTime() {
		return lastLoggedOutTime;
	}
	public String getLastLoginAuthenticatedBy() {
		return lastLoginAuthenticatedBy;
	}
	public boolean isActive() {
		return isActive;
	}
	public java.sql.Date getCrtOn() {
		return crtOn;
	}
	public String getCrtBy() {
		return crtBy;
	}
	public java.sql.Date getModOn() {
		return modOn;
	}
	public String getModBy() {
		return modBy;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setDateOfBirth(java.sql.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public void setLandPhoneCountryCode(String landPhoneCountryCode) {
		this.landPhoneCountryCode = landPhoneCountryCode;
	}
	public void setLandPhoneAreaCode(String landPhoneAreaCode) {
		this.landPhoneAreaCode = landPhoneAreaCode;
	}
	public void setLandPhoneNo(String landPhoneNo) {
		this.landPhoneNo = landPhoneNo;
	}
	public void setMobilePhoneCountryCode(String mobilePhoneCountryCode) {
		this.mobilePhoneCountryCode = mobilePhoneCountryCode;
	}
	public void setMobilePhoneNetworkCode(String mobilePhoneNetworkCode) {
		this.mobilePhoneNetworkCode = mobilePhoneNetworkCode;
	}
	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}
	public void setMySpaceUrl(String mySpaceUrl) {
		this.mySpaceUrl = mySpaceUrl;
	}
	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}
	public void setInstagramUrl(String instagramUrl) {
		this.instagramUrl = instagramUrl;
	}
	public void setViberNumber(String viberNumber) {
		this.viberNumber = viberNumber;
	}
	public void setWhatsAppNumber(String whatsAppNumber) {
		this.whatsAppNumber = whatsAppNumber;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public void setLastLoggedInUserAgent(String lastLoggedInUserAgent) {
		this.lastLoggedInUserAgent = lastLoggedInUserAgent;
	}
	public void setLastLoggedInSessionid(String lastLoggedInSessionid) {
		this.lastLoggedInSessionid = lastLoggedInSessionid;
	}
	public void setLastLoggedInDate(String lastLoggedInDate) {
		this.lastLoggedInDate = lastLoggedInDate;
	}
	public void setLastLoggedInTime(String lastLoggedInTime) {
		this.lastLoggedInTime = lastLoggedInTime;
	}
	public void setLastLoggedInIpAddress(String lastLoggedInIpAddress) {
		this.lastLoggedInIpAddress = lastLoggedInIpAddress;
	}
	public void setLastLoggedOutDate(String lastLoggedOutDate) {
		this.lastLoggedOutDate = lastLoggedOutDate;
	}
	public void setLastLoggedOutTime(String lastLoggedOutTime) {
		this.lastLoggedOutTime = lastLoggedOutTime;
	}
	public void setLastLoginAuthenticatedBy(String lastLoginAuthenticatedBy) {
		this.lastLoginAuthenticatedBy = lastLoginAuthenticatedBy;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setCrtOn(java.sql.Date crtOn) {
		this.crtOn = crtOn;
	}
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	public void setModOn(java.sql.Date modOn) {
		this.modOn = modOn;
	}
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

}