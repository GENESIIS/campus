package com.genesiis.campus.validation;
//20161024 DN c10-contacting-us created the initial Operation.java version
//20161026 DN c10-contacting-us-page add CONTACT_US_PUBLC to the class

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {


	CONTACT_US_PUBLC("FBTSA","contactUs.jsp"),
	LIST_ALL_INSTITUTES("LAAI","contactUs.jsp"),
	BAD_OPERATION("BO", "commandNotFound.jsp");
			
	private final String commandString;
	private final String viewJspUrl;


	Operation(final String newValue, final String viewJspUrl) {
		this.commandString = newValue;
		this.viewJspUrl = viewJspUrl;

	}

	public String getCommandString() {
		return commandString;
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