package com.genesiis.campus.entity.model;
/*
 * 20170207 DN c131-admin-manage-banner-upload-banner-image-dn created the initial AdvertiserRole interface stub
 */

public interface AdvertiserRole {

	
	public int getCode();
	
	public void setCode(int code);
	
	public String getName();
	
	public void setName(String name) ;
	
	public String getGeneralEmail() ;
	
	public void setGeneralEmail(String generalEmail);
	
	public String getLandPhoneCountryCode();
	
	public void setLandPhoneCountryCode(String landPhoneCountryCode);
	
	public String getLandPhoneAreaCode() ;
	
	public void setLandPhoneAreaCode(String landPhoneAreaCode) ;
	
	public String getLandPhoneNo() ;
	
	public void setLandPhoneNo(String landPhoneNo) ;
	
	public String getMobilePhoneCountryCode();
	
	public void setMobilePhoneCountryCode(String mobilePhoneCountryCode);
	
	public String getMobilePhoneNetworkCode() ;
	
	public void setMobilePhoneNetworkCode(String mobilePhoneNetworkCode) ;
	
	public String getMobilePhoneNumber();
	
	public void setMobilePhoneNumber(String mobilePhoneNumber) ;
	
	public String getDescription() ;
	
	public void setDescription(String description);
	
	public String getAddress1();
	
	public void setAddress1(String address1) ;
	
	public String getAddress2();
	
	public void setAddress2(String address2);
	
	public String getAddress3();
	
	public void setAddress3(String address3);
	
	public String getCrtBy();
	
	public void setCrtBy(String crtBy) ;
	
	public String getModBy() ;
	
	public void setModBy(String modBy) ;
	
}
