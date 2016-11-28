package com.genesiis.campus.command;

//20161125 CM c36-add-tutor-information INIT CmdCheckUsername.java
//20161125 CM c36-add-tutor-information Modified execute()method. 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;

public class CmdCheckUsername implements ICommand {
	static Logger log = Logger.getLogger(CmdCheckUsername.class.getName());

	/**
	 * @author Chathuri
	 * @param helepr
	 *            IDataHelper object of Object type view IView object of object
	 *            type
	 * @return View object to servlet
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		final TutorDAO tutorDAO = new TutorDAO();
		final Tutor tutor = new Tutor();
		String message = "";
		String usernm = null;
		Collection<Collection<String>> tutorCollection= new ArrayList<Collection<String>>();
		
		try {
			usernm = helper.getParameter("USERNAME");
			
			if (usernm != null){
				
					tutor.setUsername(usernm);
					tutorCollection = tutorDAO.findById(tutor);
			}
			
			
			
			if (tutorCollection.isEmpty()) {
				message = "1";
			} else {
				message = "0";
			}
			view.setCollection(tutorCollection);
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
		}
		return view;
	}

}
