package com.genesiis.campus.factory;

//20161025 PN c11-criteria-based-filter-search INIT PublicCmdFactory.java
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161121 PN c27-upload-user-image: added UPLOAD_USER_PROFILE attribute.

import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {	

	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		default:
			break;
		}
		return command;
	}

}
