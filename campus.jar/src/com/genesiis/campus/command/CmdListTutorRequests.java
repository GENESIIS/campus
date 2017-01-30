package com.genesiis.campus.command;

//20170130 JH c134-admin-list-new-tutor-requests INIT CmdListTutorRequests.java

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.TutorRequestsDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemMessage;

public class CmdListTutorRequests implements ICommand{

	static Logger log = Logger.getLogger(CmdListTutorRequests.class.getName());

	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		ICrud tutorRequestsDAO = new TutorRequestsDAO();
		Collection<Collection<String>> tutorCollection = new ArrayList<Collection<String>>();
		SystemMessage systemMessage = SystemMessage.NO_DATA;

		try {
			 tutorCollection = tutorRequestsDAO.findById(ApplicationStatus.PENDING.getStatusValue());
			 
			 if(tutorCollection.size() >0){
					view.setCollection(tutorCollection);
			 }else{
				systemMessage = SystemMessage.NO_DATA; 
			 }

		}  catch (SQLException sqlException) {
			log.error("execute(IDataHelper, IView) : Exception"
					+ sqlException.toString());
			systemMessage = SystemMessage.ERROR;
			throw sqlException;
		}catch (Exception exception) {
			log.error("execute(IDataHelper, IView) : Exception"
					+ exception.toString());
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}finally{
			helper.setAttribute("userMessage", systemMessage.message());
		}

		return view;
	}
}
