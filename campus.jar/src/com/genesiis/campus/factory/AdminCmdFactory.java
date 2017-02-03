package com.genesiis.campus.factory;

//20161122 JH c39-add-course-provider AdminCmdFactory.java command factory class created
//20161122 JH c39-add-course-provider implemented ICmdFactory
//20161122 JH c39-add-course-provider added new map for ADD_FEATURED_COURSE_PROVIDER
//20161201 JH c39-add-course-provider added new map for ADD_ONE_OFF_COURSE_PROVIDER
//20161208 JH c39-add-course-provider added new map for COURSE_PROVIDER_VALIDATION
//20161209 JH c39-add-course-provider COURSE_PROVIDER_VALIDATION map modified
//20161220 JH c39-add-course-provider added new map for LIST_PROVIDER_REGISTRATION_PAGE
//20161229 JH c39-add-course-provider added new map for LIST_COUESE_PROVIDER_TYPES
//20170103 JH c39-add-course-provider removed code related to issue c7
//20170203 DN c131-admin-manage-banner-upload-banner-image-dn add logger field/import statement to the class
//				enter new map entry related to the DISPLAY_BANNER_MANAGER_ONLOAD_PAGE_DATA

import com.genesiis.campus.command.CmdGetBannerPreRequisite;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

public class AdminCmdFactory implements ICmdFactory{

	private static final Logger log = Logger.getLogger(AdminCmdFactory.class.getName());

	private ICommand command = null;
	static {	
		
		map.put(Operation.DISPLAY_BANNER_MANAGER_ONLOAD_PAGE_DATA, new CmdGetBannerPreRequisite()); // should be changed to proper command class
		
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case DISPLAY_BANNER_MANAGER_ONLOAD_PAGE_DATA:
			command = map.get(o);
			break;
		
		default:
			break;
		}
		return command;
	}

}