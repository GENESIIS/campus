package com.genesiis.campus.validation;
//20161024 DN c10-contacting-us created the initial Operation.java version
//20161026 JH c7-list-higher-education-courses Operation class modified 

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {


	BAD_OPERATION("BO", "commandNotFound.jsp"),
	LIST_CATEGORY_LANDING_PAGE("LCLP", "higherEducation.jsp"),
	LIST_HIGHER_EDUCATION_PROGRAMMES("LHEP" , "higherEducation.jsp");
			
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