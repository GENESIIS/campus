package com.genesiis.campus.factory;

//20161122 JH c39-add-course-provider AdminCmdFactory.java command factory class created
//20161122 JH c39-add-course-provider implemented ICmdFactory
//20161122 JH c39-add-course-provider added new map for ADD_FEATURED_COURSE_PROVIDER
//20161201 JH c39-add-course-provider added new map for ADD_ONE_OFF_COURSE_PROVIDER
//20161208 JH c39-add-course-provider added new map for COURSE_PROVIDER_VALIDATION
//20161209 JH c39-add-course-provider COURSE_PROVIDER_VALIDATION map modified
//20161220 JH c39-add-course-provider added new map for LIST_PROVIDER_REGISTRATION_PAGE
//20161222 JH c39-add-course-provider added new map for LIST_COUESE_PROVIDER_TYPES


import com.genesiis.campus.command.CmdCourseProviderAccountValidate;
import com.genesiis.campus.command.CmdAddFeaturedProvider;
import com.genesiis.campus.command.CmdAddOneOffProvider;
import com.genesiis.campus.command.CmdListCategories;
import com.genesiis.campus.command.CmdListCategoryLandingPage;
import com.genesiis.campus.command.CmdListCourseProviderRegisterPage;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class AdminCmdFactory implements ICmdFactory{


	private ICommand command = null;
	static {	
		map.put(Operation.LIST_CATEGORY_LANDING_PAGE, new CmdListCategoryLandingPage());
		map.put(Operation.LIST_CATEGORY_DATA, new CmdListCategories());
		map.put(Operation.ADD_FEATURED_COURSE_PROVIDER, new CmdAddFeaturedProvider());
		map.put(Operation.COURSE_PROVIDER_VALIDATION, new CmdCourseProviderAccountValidate());
		map.put(Operation.LIST_PROVIDER_REGISTRATION_PAGE, new CmdListCourseProviderRegisterPage());
		map.put(Operation.DISPLAY_TOWN_DATA, new CmdListCourseProviderRegisterPage());
		map.put(Operation.LIST_COUESE_PROVIDER_TYPES, new CmdListCourseProviderRegisterPage());
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
		case COURSE_PROVIDER_VALIDATION:
			command = map.get(o);
			break;
		case LIST_PROVIDER_REGISTRATION_PAGE:
			command = map.get(o);
			break;
		case DISPLAY_TOWN_DATA:
			command = map.get(o);
			break;
		case LIST_COUESE_PROVIDER_TYPES:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
