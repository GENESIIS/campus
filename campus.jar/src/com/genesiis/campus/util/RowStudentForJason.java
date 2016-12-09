package com.genesiis.campus.util;
//20161129 DN C18-student-signup-without-using-third-party-application-test-dn created RowStudentForJason.java utility class

/*
 * RowStudentForJason  class maps the JSON data to raw Student object
 * till induce the brought in data from client side to proper  business model object
 * e.g. Student, 
 * @author dushantha DN
 *
 */
public class RowStudentForJason{
	String firstName ;
	String lastName  ;
	String gender	;
	String email;
	String mobilePhoneNo;
	String mobileCountryCode;
	String mobileNetworkCode;
	String town	;
	String usertypeCode;
	String userName	;
	String passWord	;
	String confirmPw;
	boolean isPolicyConfirm	;
	

	public String getUsertypeCode() {
		return usertypeCode;
	}
	public void setUsertypeCode(String usertypeCode) {
		this.usertypeCode = usertypeCode;
	}	
	public String getMobileCountryCode() {
		return mobileCountryCode;
	}
	public void setMobileCountryCode(String mobileCountryCode) {
		this.mobileCountryCode = mobileCountryCode;
	}
	public String getMobileNetworkCode() {
		return mobileNetworkCode;
	}
	public void setMobileNetworkCode(String mobileNetworkCode) {
		this.mobileNetworkCode = mobileNetworkCode;
	}
	public void setPolicyConfirm(boolean isPolicyConfirm) {
		this.isPolicyConfirm = isPolicyConfirm;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}
	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String pathway) {
		this.town = pathway;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getConfirmPw() {
		return confirmPw;
	}
	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}
	public boolean getIsPolicyConfirm() {
		return isPolicyConfirm;
	}
	public void setIsPolicyConfirm(boolean isPolicyConfirm) {
		this.isPolicyConfirm = isPolicyConfirm;
	}
}