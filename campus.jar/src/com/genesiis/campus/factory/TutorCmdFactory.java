package com.genesiis.campus.factory;

//20161121 CM c36-add-tutor-information INIT TutorCmdFactory.java
//20161121 CM c36-add-tutor-information Modified getCommand() method. 
//20161122 CM c36-add-tutor-information Modified getCommand() method. 
//20161123 CM c36-add-tutor-information Modified getCommand() method. 
//20170116 CM c36-add-tutor-information add getCommand() add LOAD_TUTOR_WITH_DUMMY_DATA element. 
//20170116 CM c36-add-tutor-information removed getCommand() add LOAD_TUTOR_WITH_DUMMY_DATA element. 
//20170117 CM c36-add-tutor-information Modified getCommand() method. 
//20170116 CM c36-add-tutor-information removed CmdCheckUsername element.
//20170207 CW c38-view-update-tutor-profile removed CmdCheckUsername element.
//20170207 CW c38-view-update-tutor-profile removed ADD_TUTOR_PROFILE_DETAILS element
//20170227 CW c37-tutor-update-tutor-profile-cw add VIEW_TUTOR_UPDATE_TUTOR_DETAILS & TUTOR_UPDATE_TUTOR element
//20170303 CW c37-tutor-update-tutor-profile-cw add CHECK_PASSWORDS
//20170323 CW c157-add-tutor-employment-details-cw command LIST_COURSE_PROVIDERS_FOR_TUTORS added
//20170327 CW c157-add-tutor-employment-details-cw command ADD_EMPLOYMENT_DETAILS added
//20170327 CW c157-add-tutor-employment-details-cw command ADD_EMPLOYMENT added
//20170328 CW c157-add-tutor-employment-details-cw command LIST_SELECTED_COURSE_PROVIDERS_FOR_TUTORS added
//20170329 CW c157-add-tutor-employment-details-cw command REMOVE_SELECTED_EMPLOYMENT added
//20170420 CW c159-courseprovider-accept-tutor-request-cw command VIEW_TUTORS_FOR_CP added
//20170425 CW c159-courseprovider-accept-tutor-request-cw command APPROVE_REMOVE_EMPLOYMENT added

import com.genesiis.campus.command.CmdAddTutorEmploymentDetails;
import com.genesiis.campus.command.CmdApproveRejectRemoveTutors;
import com.genesiis.campus.command.CmdCheckEmail;
import com.genesiis.campus.command.CmdListCourseProvidersForTutors;
import com.genesiis.campus.command.CmdListTutorsForCourseProviders;
import com.genesiis.campus.command.CmdLoadCountry;
import com.genesiis.campus.command.CmdLoadTownDetails;
import com.genesiis.campus.command.CmdRemoveSelectedEmploymentDetails;
import com.genesiis.campus.command.CmdTutorUpdateTutorProfile;
import com.genesiis.campus.command.CmdValidatePasswords;
import com.genesiis.campus.command.CmdViewTutorEmploymentDetails;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.command.CmdViewTutorProfile;
import com.genesiis.campus.command.CmdViewTutorSelectedEmploymentDetails;

public class TutorCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.LIST_TOWN_DATA, new CmdLoadTownDetails());
		map.put(Operation.LIST_COUNTRY_DATA, new CmdLoadCountry());
		map.put(Operation.CHECK_EMAIL, new CmdCheckEmail());
		map.put(Operation.VIEW_TUTOR_UPDATE_TUTOR_DETAILS, new CmdViewTutorProfile());
		map.put(Operation.TUTOR_UPDATE_TUTOR, new CmdTutorUpdateTutorProfile());
		map.put(Operation.CHECK_PASSWORDS, new CmdValidatePasswords());
		map.put(Operation.LIST_COURSE_PROVIDERS_FOR_TUTORS, new CmdListCourseProvidersForTutors());
		map.put(Operation.ADD_EMPLOYMENT_DETAILS, new CmdAddTutorEmploymentDetails());		
		map.put(Operation.ADD_EMPLOYMENT, new CmdViewTutorEmploymentDetails());					
		map.put(Operation.LIST_SELECTED_COURSE_PROVIDERS_FOR_TUTORS, new CmdViewTutorSelectedEmploymentDetails());
		map.put(Operation.REMOVE_SELECTED_EMPLOYMENT, new CmdRemoveSelectedEmploymentDetails());		
		map.put(Operation.VIEW_TUTORS_FOR_CP, new CmdListTutorsForCourseProviders());				
		map.put(Operation.APPROVE_REMOVE_EMPLOYMENT, new CmdApproveRejectRemoveTutors());			
	}
	
	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case LIST_TOWN_DATA:
			command = map.get(o);
			break;
		case LIST_COUNTRY_DATA:
			command = map.get(o);
			break;
		case VIEW_TUTOR_DETAILS:
			command = map.get(o);
			break;
		case UPDATE_TUTOR:
			command = map.get(o);
			break;
		case CHECK_EMAIL:
			command = map.get(o);
			break;
		case CHECK_PASSWORDS:
			command = map.get(o);
			break;
		case VIEW_TUTOR_UPDATE_TUTOR_DETAILS:
			command = map.get(o);
			break;
		case TUTOR_UPDATE_TUTOR:
			command = map.get(o);
			break;
		case LIST_COURSE_PROVIDERS_FOR_TUTORS:
			command = map.get(o);
			break;
		case ADD_EMPLOYMENT_DETAILS:
			command = map.get(o);
			break;
		case ADD_EMPLOYMENT:
			command = map.get(o);
			break;
		case LIST_SELECTED_COURSE_PROVIDERS_FOR_TUTORS:
			command = map.get(o);
			break;
		case REMOVE_SELECTED_EMPLOYMENT:
			command = map.get(o);
			break;
		case VIEW_TUTORS_FOR_CP:
			command = map.get(o);
			break;
		case APPROVE_REMOVE_EMPLOYMENT:
			command = map.get(o);
			break;			
		default:
			break;
		}
		return command;

	}

}
