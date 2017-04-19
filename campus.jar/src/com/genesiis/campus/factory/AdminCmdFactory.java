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
//20170209 DN c131-admin-manage-banner-upload-banner-image-dn add UPLOAD_BANNER_SLOT_ON_BANNER_MANAGER_PAGE entry to the map &
//				getCommand() method.
//20170213 DN c131-admin-manage-banner-upload-banner-image-dn UPLOAD_BANNER_IMAGE_CREDENTIALS map entry added,changed the getCommand()
//            accordingly.
//20170216 DN c131-admin-manage-banner-upload-banner-image-dn BANNER_CREDENTIALS  map entry added,changed the getCommand()
//20170216 DN c131-admin-manage-banner-upload-banner-image-dn UPLOAD_BANNER_IMAGE_CREDENTIALS -->UPLOAD_BANNER_IMAGE_BY_ADMIN
//20170310 DN c81-admin-manage-banner-add-and-view-banner-dn ADMIN_DISPLAY_BANNERS has been added to map.
//20170313 DN c81-admin-manage-banner-add-and-view-banner-dn add the ADMIN_DISPLAY_BANNERS enum to switch structure.
//20170327 DN c83-admin-manage-banner-update-banner-info-dn add UPDATE_ONLY_THE_BANNER_RECORD  enum to switch structure.
//20170418/19 DN c86-admin-manage-banner-search-banner-dn. ACTIVATE_BANNER/DEACTIVATE_BANNER keys to the map and switch case has been added.
//				and associate the above entries to  new CmdAmendBannerState().

import com.genesiis.campus.command.CmdAdminBannerUpload;
import com.genesiis.campus.command.CmdAmendBannerState;
import com.genesiis.campus.command.CmdBannerPreRequisite;
import com.genesiis.campus.command.CmdListBanner;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

public class AdminCmdFactory implements ICmdFactory{

	private static final Logger log = Logger.getLogger(AdminCmdFactory.class.getName());

	private ICommand command = null;
	static {	
		
		map.put(Operation.DISPLAY_BANNER_MANAGER_ONLOAD_PAGE_DATA, new CmdBannerPreRequisite()); 
		map.put(Operation.UPLOAD_BANNER_SLOT_ON_BANNER_MANAGER_PAGE, new CmdBannerPreRequisite());
		map.put(Operation.UPLOAD_BANNER_IMAGE_TO_TEMP_FOLDER, new CmdAdminBannerUpload());
		map.put(Operation.UPLOAD_FULL_BANNER_CREDENTIALS,new CmdAdminBannerUpload());
		map.put(Operation.ADMIN_DISPLAY_BANNERS, new CmdListBanner());
		map.put(Operation.UPDATE_ONLY_THE_BANNER_RECORD, new CmdAdminBannerUpload());
		map.put(Operation.ACTIVATE_BANNER, new CmdAmendBannerState());
		map.put(Operation.DEACTIVATE_BANNER, new CmdAmendBannerState());
		
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case DISPLAY_BANNER_MANAGER_ONLOAD_PAGE_DATA:
			command = map.get(o);
			break;
		case UPLOAD_BANNER_SLOT_ON_BANNER_MANAGER_PAGE:
			command = map.get(o);
			break;
		case UPLOAD_BANNER_IMAGE_TO_TEMP_FOLDER:
			command = map.get(o);
			break;	
		case UPLOAD_FULL_BANNER_CREDENTIALS:
			command = map.get(o);
			break;
		case ADMIN_DISPLAY_BANNERS:
			command = map.get(o);
			break;
		case UPDATE_ONLY_THE_BANNER_RECORD:
			command = map.get(o);
			break;
		case ACTIVATE_BANNER:
			command = map.get(o);
			break;
		case DEACTIVATE_BANNER:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}