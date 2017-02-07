package com.genesiis.campus.entity.model;
/*
 * 20170207 DN c131-admin-manage-banner-upload-banner-image-dn created the initial class stub CourseAdvertiser.java
 */
import java.util.ArrayList;
import java.util.List;

/**
 * CourseAdvertiser.java maps the business requirement in representing 
 * the type that which advertises a course program(s) in the system.
 * @author dushantha DN
 *
 */

public class CourseAdvertiser implements AdvertiserRole {
	
	
	/*
	 * This class contains all the fields that are in BannerAdvertiser.java
	 * which has been inherited due to the methods came from interface 
	 * AdveriserRole.java. In future this class can be differentiated from
	 * BannerAdvertiser.java but for the time being both class got the identical 
	 * properties. But it is obvious that in the system there must be an entity
	 * or class to represent Course Advertiser.
	 */
	private int code;
	private String name;
	private String generalEmail;
	private String landPhoneCountryCode;
	private String landPhoneAreaCode;
	private String landPhoneNo;
	private String mobilePhoneCountryCode;
	private String mobilePhoneNetworkCode;
	private String mobilePhoneNumber;
	private String description;
	private String address1;
	private String address2;
	private String address3;
	private String crtBy;
	private String modBy;
	private List <AdvertiserRole> adverisers = new ArrayList<AdvertiserRole>(); 

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGeneralEmail() {
		return generalEmail;
	}
	public void setGeneralEmail(String generalEmail) {
		this.generalEmail = generalEmail;
	}
	public String getLandPhoneCountryCode() {
		return landPhoneCountryCode;
	}
	public void setLandPhoneCountryCode(String landPhoneCountryCode) {
		this.landPhoneCountryCode = landPhoneCountryCode;
	}
	public String getLandPhoneAreaCode() {
		return landPhoneAreaCode;
	}
	public void setLandPhoneAreaCode(String landPhoneAreaCode) {
		this.landPhoneAreaCode = landPhoneAreaCode;
	}
	public String getLandPhoneNo() {
		return landPhoneNo;
	}
	public void setLandPhoneNo(String landPhoneNo) {
		this.landPhoneNo = landPhoneNo;
	}
	public String getMobilePhoneCountryCode() {
		return mobilePhoneCountryCode;
	}
	public void setMobilePhoneCountryCode(String mobilePhoneCountryCode) {
		this.mobilePhoneCountryCode = mobilePhoneCountryCode;
	}
	public String getMobilePhoneNetworkCode() {
		return mobilePhoneNetworkCode;
	}
	public void setMobilePhoneNetworkCode(String mobilePhoneNetworkCode) {
		this.mobilePhoneNetworkCode = mobilePhoneNetworkCode;
	}
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getCrtBy() {
		return crtBy;
	}
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	public String getModBy() {
		return modBy;
	}
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
	public List<AdvertiserRole> getAdverisers() {
		return adverisers;
	}
	public void setAdverisers(List<AdvertiserRole> adverisers) {
		this.adverisers = adverisers;
	}
	
	
}
