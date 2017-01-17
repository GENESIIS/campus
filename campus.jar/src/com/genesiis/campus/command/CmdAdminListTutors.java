package com.genesiis.campus.command;

//20170117 JH c133-admin-list-tutors CmdAdminListTutors.java created
//20170117 JH c133-admin-list-tutors list tutors and exception handling 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

public class CmdAdminListTutors implements ICommand{
	
	static Logger log = Logger.getLogger(CmdAdminListTutors.class.getName());

	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		ICrud tutorDAO = new TutorDAO();
		Collection<Collection<String>> tutorCollection = new ArrayList<Collection<String>>();
		SystemMessage systemMessage = SystemMessage.NO_DATA;

		try {
			 tutorCollection = tutorDAO.getAll();
			 
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
