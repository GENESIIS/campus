package com.genesiis.campus.command;

//20170117 JH c133-admin-list-tutors CmdAdminListTutors.java created
//20170117 JH c133-admin-list-tutors list tutors and exception handling 
//20170202 JH c134-admin-list-new-tutor-requests arranged imports according to the style guide document
//20170203 JH c133-admin-list-tutors arranged imports according to the style guide
//20170206 JH c133-admin-list-tutors added doc comments
//20170315 JH c134-admin-list-new-tutor-requests added doc comments
//20170317 JH c134-admin-list-new-tutor-requests get ApplicationStatus values to use in javascript for styling
//20170403 JH c134-admin-list-new-tutor-requests removed system message codes from catch blocks

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * CmdAdminListTutors class used to list all tutors for administration purposes.
 * This will include all tutor status values. 
 * @author JH
 *
 */

public class CmdAdminListTutors implements ICommand{
	
	static Logger log = Logger.getLogger(CmdAdminListTutors.class.getName());

	/** 
	 * @author JH
	 * @param helper
	 * @param view
	 * @return IView
	 * @author JH
	 * 
	 * execute method used to handle data retrieval related to 
	 * admin tutor listing function. All tutors belongs to any 
	 * status, need to be listed for this requirement. 
	 */
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		ICrud tutorDAO = new TutorDAO();
		Collection<Collection<String>> tutorCollection = new ArrayList<Collection<String>>();
		SystemMessage systemMessage = SystemMessage.NO_DATA;

		try {
			 tutorCollection = tutorDAO.getAll();
			 
			 //if result is empty send a user message
			 if(tutorCollection.size() >0){
					view.setCollection(tutorCollection);
			 }else{
				systemMessage = SystemMessage.NO_DATA; 
			 }

			 // return application status values
			 ApplicationStatus[] applicationStatus = ApplicationStatus.values();
			 Map<String, String> applicationStatusValue = new HashMap<String, String>();
			 
			 for( ApplicationStatus singleValue : applicationStatus){
				 applicationStatusValue.put(singleValue.toString(), String.valueOf(singleValue.getStatusValue()));
			 }
			 helper.setAttribute("applicationStatus", applicationStatusValue);
			 
		}  catch (SQLException sqlException) {
			log.error("execute(IDataHelper, IView) : Exception"
					+ sqlException.toString());
			throw sqlException;
		}catch (Exception exception) {
			log.error("execute(IDataHelper, IView) : Exception"
					+ exception.toString());
			throw exception;
		}finally{
			helper.setAttribute("userMessage", systemMessage.message());
		}

		return view;
	}

}
