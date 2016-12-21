package com.genesiis.campus.command;

//20161125 CM c36-add-tutor-information INIT CmdCheckUsername.java
//20161125 CM c36-add-tutor-information Modified execute()method. 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.TutorUserName;
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
public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {		
		
		String message = "";
		
		Collection<Collection<String>> tutorCollection= new ArrayList<Collection<String>>();
		
		try {
			String username = helper.getParameter("USERNAME");
			
			if (username != null){
				final Tutor tutor = new Tutor();
				tutor.setUsername(username);
				tutorCollection = new TutorUserName().findById(tutor);
			}			
			
			if (tutorCollection.isEmpty()) {
				message = "1"; // user name does not exist
			} else {
				message = "0"; // user name Already exists
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
