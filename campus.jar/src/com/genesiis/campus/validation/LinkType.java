package com.genesiis.campus.validation;
/*
 * 20170217 DN c131-admin-manage-banner-upload-banner-image-dn created the initial
 * 			enum class type
 */

/**
 * Enum LinkType 
 * maps the link type to an integer
 * @author dushantha DN
 *
 */
public enum LinkType {
	
	PAGE_LINK(0,"URL to seperate stand alone page,pdf,doc"),
	WEB_LINK(1,"URL of external web page"),
	MINI_WEB_LINK(2,"URL of mini web "),
	BAD_LINK(-1,"there is no valid link to prasent");
	
	LinkType(int mappingInt, String description){
		this.mappingInt =mappingInt;
		this.description = description;
	}

	/*
	 * Fields decleration
	 */
	private int mappingInt;
	private String description;
	
	
	/*
	 * Method implementation
	 */
	public int getMappingInt() {
		return mappingInt;
	}
	public void setMappingInt(int mappingInt) {
		this.mappingInt = mappingInt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
