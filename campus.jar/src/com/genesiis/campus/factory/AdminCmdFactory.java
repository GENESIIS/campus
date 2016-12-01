package com.genesiis.campus.factory;

//20161122 JH c39-add-course-provider AdminCmdFactory.java command factory class created
//20161122 JH c39-add-course-provider implemented ICmdFactory
//20161122 JH c39-add-course-provider added new map for ADD_FEATURED_COURSE_PROVIDER
//20161201 JH c39-add-course-provider added new map for ADD_ONE_OFF_COURSE_PROVIDER

import com.genesiis.campus.command.CmdAddFeaturedProvider;
import com.genesiis.campus.command.CmdAddOneOffProvider;
import com.genesiis.campus.command.CmdListCategories;
import com.genesiis.campus.command.CmdListCategoryLandingPage;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class AdminCmdFactory implements ICmdFactory{


	private ICommand command = null;
	static {	
		map.put(Operation.LIST_CATEGORY_LANDING_PAGE, new CmdListCategoryLandingPage());
		map.put(Operation.LIST_CATEGORY_DATA, new CmdListCategories());
		map.put(Operation.ADD_FEATURED_COURSE_PROVIDER, new CmdAddFeaturedProvider());
		map.put(Operation.ADD_ONE_OFF_COURSE_PROVIDER, new CmdAddOneOffProvider());

	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case ADD_FEATURED_COURSE_PROVIDER:
			command = map.get(o);
			break;
		case LIST_CATEGORY_DATA :
			command = map.get(o);
			break;
		case ADD_ONE_OFF_COURSE_PROVIDER:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
