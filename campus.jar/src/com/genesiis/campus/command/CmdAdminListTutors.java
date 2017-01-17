package com.genesiis.campus.command;

//20170117 JH c133-admin-list-tutors CmdAdminListTutors.java created

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdAdminListTutors implements ICommand{
	
	static Logger log = Logger.getLogger(CmdAdminListTutors.class.getName());

	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		ICrud tutorDAO = new TutorDAO();
		Collection<Collection<String>> tutorCollection = new ArrayList<Collection<String>>();

		try {
			 tutorCollection = tutorDAO.getAll();
				log.info(tutorCollection.size());
				view.setCollection(tutorCollection);
		} catch (Exception exception) {
			log.error("execute(IDataHelper, IView) : Exception"
					+ exception.toString());
			throw exception;
		}

		return view;
	}

}
