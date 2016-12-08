package com.genesiis.campus.entity.model;

//20161026 JH c7-higher-education-landing-page CourseProvider.java entity class created
//20161026 JH c7-higher-education-landing-page create getters and setters
//20161122 JH c39-add-course-provider remove unused attributes and added new attributes
//20161128 JH c39-add-course-provider change userType getters and setters to headOffice

import java.sql.Date;

public class CourseProvider {

	private int code;
	private String uniquePrefix;
	private String shortName;
	private String name;
	private String description;
	private String generalEmail;
	private String courseInquiryEmail;
	private String landPhoneCountryCode;
	private String landPhoneAreaCode;
	private String landPhoneNo;
	private String landPhpneNo2;
	private String faxNo;
	private String mobilePhoneCountryCode;
	private String mobilePhoneNetworkCode;
	private String mobilePhoneNumber;
	private String speciality;
	private String weblink;
	private String facebookURL;
	private String twitterURL;
	private String myspaceURL;
	private String linkedinURL;
	private String instagramURL;
	private String viberNumber;
	private String whatsappNumber;
	private String address1;
	private String address2;
	private String address3;
	private String crtBy;
	private String modBy;

	private Date expirationDate;
	private Date crtOn;
	private Date modOn;

	private boolean isTutorRelated;
	private boolean isAdminAllowed;

	private int accountType;
	private int headOffice;
	private int courseProviderStatus;
	private int courseProviderType;
	private int principal;
	private int tutor;

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the uniquePrefix
	 */
	public String getUniquePrefix() {
		return uniquePrefix;
	}

	/**
	 * @param uniquePrefix
	 *            the uniquePrefix to set
	 */
	public void setUniquePrefix(String uniquePrefix) {
		this.uniquePrefix = uniquePrefix;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName
	 *            the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the generalEmail
	 */
	public String getGeneralEmail() {
		return generalEmail;
	}

	/**
	 * @param generalEmail
	 *            the generalEmail to set
	 */
	public void setGeneralEmail(String generalEmail) {
		this.generalEmail = generalEmail;
	}

	/**
	 * @return the courseInquiryEmail
	 */
	public String getCourseInquiryEmail() {
		return courseInquiryEmail;
	}

	/**
	 * @param courseInquiryEmail
	 *            the courseInquiryEmail to set
	 */
	public void setCourseInquiryEmail(String courseInquiryEmail) {
		this.courseInquiryEmail = courseInquiryEmail;
	}

	/**
	 * @return the landPhoneCountryCode
	 */
	public String getLandPhoneCountryCode() {
		return landPhoneCountryCode;
	}

	/**
	 * @param landPhoneCountryCode
	 *            the landPhoneCountryCode to set
	 */
	public void setLandPhoneCountryCode(String landPhoneCountryCode) {
		this.landPhoneCountryCode = landPhoneCountryCode;
	}

	/**
	 * @return the landPhoneAreaCode
	 */
	public String getLandPhoneAreaCode() {
		return landPhoneAreaCode;
	}

	/**
	 * @param landPhoneAreaCode
	 *            the landPhoneAreaCode to set
	 */
	public void setLandPhoneAreaCode(String landPhoneAreaCode) {
		this.landPhoneAreaCode = landPhoneAreaCode;
	}

	/**
	 * @return the landPhoneNo
	 */
	public String getLandPhoneNo() {
		return landPhoneNo;
	}

	/**
	 * @param landPhoneNo
	 *            the landPhoneNo to set
	 */
	public void setLandPhoneNo(String landPhoneNo) {
		this.landPhoneNo = landPhoneNo;
	}

	/**
	 * @return the landPhpneNo2
	 */
	public String getLandPhpneNo2() {
		return landPhpneNo2;
	}

	/**
	 * @param landPhpneNo2
	 *            the landPhpneNo2 to set
	 */
	public void setLandPhpneNo2(String landPhpneNo2) {
		this.landPhpneNo2 = landPhpneNo2;
	}

	/**
	 * @return the faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}

	/**
	 * @param faxNo
	 *            the faxNo to set
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * @return the mobilePhoneCountryCode
	 */
	public String getMobilePhoneCountryCode() {
		return mobilePhoneCountryCode;
	}

	/**
	 * @param mobilePhoneCountryCode
	 *            the mobilePhoneCountryCode to set
	 */
	public void setMobilePhoneCountryCode(String mobilePhoneCountryCode) {
		this.mobilePhoneCountryCode = mobilePhoneCountryCode;
	}

	/**
	 * @return the mobilePhoneNetworkCode
	 */
	public String getMobilePhoneNetworkCode() {
		return mobilePhoneNetworkCode;
	}

	/**
	 * @param mobilePhoneNetworkCode
	 *            the mobilePhoneNetworkCode to set
	 */
	public void setMobilePhoneNetworkCode(String mobilePhoneNetworkCode) {
		this.mobilePhoneNetworkCode = mobilePhoneNetworkCode;
	}

	/**
	 * @return the mobilePhoneNumber
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * @param mobilePhoneNumber
	 *            the mobilePhoneNumber to set
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * @return the speciality
	 */
	public String getSpeciality() {
		return speciality;
	}

	/**
	 * @param speciality
	 *            the speciality to set
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	/**
	 * @return the weblink
	 */
	public String getWeblink() {
		return weblink;
	}

	/**
	 * @param weblink
	 *            the weblink to set
	 */
	public void setWeblink(String weblink) {
		this.weblink = weblink;
	}

	/**
	 * @return the facebookURL
	 */
	public String getFacebookURL() {
		return facebookURL;
	}

	/**
	 * @param facebookURL
	 *            the facebookURL to set
	 */
	public void setFacebookURL(String facebookURL) {
		this.facebookURL = facebookURL;
	}

	/**
	 * @return the twitterURL
	 */
	public String getTwitterURL() {
		return twitterURL;
	}

	/**
	 * @param twitterURL
	 *            the twitterURL to set
	 */
	public void setTwitterURL(String twitterURL) {
		this.twitterURL = twitterURL;
	}

	/**
	 * @return the myspaceURL
	 */
	public String getMyspaceURL() {
		return myspaceURL;
	}

	/**
	 * @param myspaceURL
	 *            the myspaceURL to set
	 */
	public void setMyspaceURL(String myspaceURL) {
		this.myspaceURL = myspaceURL;
	}

	/**
	 * @return the linkedinURL
	 */
	public String getLinkedinURL() {
		return linkedinURL;
	}

	/**
	 * @param linkedinURL
	 *            the linkedinURL to set
	 */
	public void setLinkedinURL(String linkedinURL) {
		this.linkedinURL = linkedinURL;
	}

	/**
	 * @return the instagramURL
	 */
	public String getInstagramURL() {
		return instagramURL;
	}

	/**
	 * @param instagramURL
	 *            the instagramURL to set
	 */
	public void setInstagramURL(String instagramURL) {
		this.instagramURL = instagramURL;
	}

	/**
	 * @return the viberNumber
	 */
	public String getViberNumber() {
		return viberNumber;
	}

	/**
	 * @param viberNumber
	 *            the viberNumber to set
	 */
	public void setViberNumber(String viberNumber) {
		this.viberNumber = viberNumber;
	}

	/**
	 * @return the whatsappNumber
	 */
	public String getWhatsappNumber() {
		return whatsappNumber;
	}

	/**
	 * @param whatsappNumber
	 *            the whatsappNumber to set
	 */
	public void setWhatsappNumber(String whatsappNumber) {
		this.whatsappNumber = whatsappNumber;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * @param address3
	 *            the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	/**
	 * @return the crtBy
	 */
	public String getCrtBy() {
		return crtBy;
	}

	/**
	 * @param crtBy
	 *            the crtBy to set
	 */
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}

	/**
	 * @return the modBy
	 */
	public String getModBy() {
		return modBy;
	}

	/**
	 * @param modBy
	 *            the modBy to set
	 */
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the crtOn
	 */
	public Date getCrtOn() {
		return crtOn;
	}

	/**
	 * @param crtOn
	 *            the crtOn to set
	 */
	public void setCrtOn(Date crtOn) {
		this.crtOn = crtOn;
	}

	/**
	 * @return the modOn
	 */
	public Date getModOn) {
		return modOn;
	}

	/**
	 * @param modOn
	 *            the modOn to set
	 */
	public void setMonOn(Date modOn) {
		this.modOn = modOn;
	}

	/**
	 * @return the isTutorRelated
	 */
	public boolean isTutorRelated() {
		return isTutorRelated;
	}

	/**
	 * @param isTutorRelated
	 *            the isTutorRelated to set
	 */
	public void setTutorRelated(boolean isTutorRelated) {
		this.isTutorRelated = isTutorRelated;
	}

	/**
	 * @return the isAdminAllowed
	 */
	public boolean isAdminAllowed() {
		return isAdminAllowed;
	}

	/**
	 * @param isAdminAllowed
	 *            the isAdminAllowed to set
	 */
	public void setAdminAllowed(boolean isAdminAllowed) {
		this.isAdminAllowed = isAdminAllowed;
	}

	/**
	 * @return the accountType
	 */
	public int getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the courseProviderStatus
	 */
	public int getCourseProviderStatus() {
		return courseProviderStatus;
	}

	/**
	 * @param courseProviderStatus
	 *            the courseProviderStatus to set
	 */
	public void setCourseProviderStatus(int courseProviderStatus) {
		this.courseProviderStatus = courseProviderStatus;
	}

	/**
	 * @return the courseProviderType
	 */
	public int getCourseProviderType() {
		return courseProviderType;
	}

	/**
	 * @param courseProviderType
	 *            the courseProviderType to set
	 */
	public void setCourseProviderType(int courseProviderType) {
		this.courseProviderType = courseProviderType;
	}

	/**
	 * @return the headOffice
	 */
	public int getHeadOffice() {
		return headOffice;
	}

	/**
	 * @param headOffice
	 *            the userType to set
	 */
	public void setHeadOffice(int headOffice) {
		this.headOffice = headOffice;
	}

	/**
	 * @return the principal
	 */
	public int getPrincipal() {
		return principal;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(int principal) {
		this.principal = principal;
	}

	/**
	 * @return the tutor
	 */
	public int getTutor() {
		return tutor;
	}

	/**
	 * @param tutor
	 *            the tutor to set
	 */
	public void setTutor(int tutor) {
		this.tutor = tutor;
	}

}