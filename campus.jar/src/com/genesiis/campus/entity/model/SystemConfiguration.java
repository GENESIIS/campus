package com.genesiis.campus.entity.model;

//20161122 PN c27-upload-user-image: INIT SystemConfiguration.java class

import java.sql.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;
import com.genesiis.campus.validation.SystemMessage;

public class SystemConfiguration {
	SystemMessage returnMessage;
	private String systemConfiguration;
	private String value1,value2,value3,description;
	int sortKey,code;
	String isActive;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	
	public SystemMessage getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(SystemMessage returnMessage) {
		this.returnMessage = returnMessage;
	}
	public String getSystemConfiguration() {
		return systemConfiguration;
	}
	public void setSystemConfiguration(String systemConfiguration) {
		this.systemConfiguration = systemConfiguration;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSortKey() {
		return sortKey;
	}
	public void setSortKey(int sortKey) {
		this.sortKey = sortKey;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getCrtOn() {
		return crtOn;
	}
	public void setCrtOn(Date crtOn) {
		this.crtOn = crtOn;
	}
	public String getCrtBy() {
		return crtBy;
	}
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	public Date getModOn() {
		return modOn;
	}
	public void setModOn(Date modOn) {
		this.modOn = modOn;
	}
	public String getModBy() {
		return modBy;
	}
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
	
	
	
}
