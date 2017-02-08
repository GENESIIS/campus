package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161108 DN, JH, DJ, AS, CM, PN, MM Added field responseType to enum.
//20161116 MM c2-integrate-google-banners Added LIST_GOOGLE_ADVERTS enum
//20161117 MM c2-integrate-google-banners Changed LIST_GOOGLE_ADVERTS to LOAD_BANNER_PAGE 
//20161217 MM c2-integrate-google-banners Removed LOAD_BANNER_PAGE constant (before 
//				merging with sprint branch) that was there for testing the banner 
//				displaying functionality 
//20170208 MM c111-display-banners-on-jsp-load - Added LIST_BANNERS enum

/**
* Operation class facilitate in binding the CCO
* 
*/
public enum Operation {

	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	ADD_BANNER_STAT("ADD_BANNER_STAT", ResponseType.JSON, ""),
	LIST_BANNERS("LIST_BANNERS", ResponseType.JSON, "");
			
	private final String commandString;
	private final ResponseType responseType;
	private final String viewJspUrl;


	Operation(final String newValue, final ResponseType responseType, 
			final String viewJspUrl) {
		this.commandString = newValue;
		this.responseType = responseType;
		this.viewJspUrl = viewJspUrl;
	}

	public String getCommandString() {
		return commandString;
	}

	public ResponseType getResponseType() {
		return responseType;
	}

	public String getPageURL() {
		return viewJspUrl;
	}


	public static Operation getOperation(String o) {
		for (Operation op : values()) {
			if (op.getCommandString().equalsIgnoreCase(o))
				return op;
		}
		return BAD_OPERATION;
	}
}