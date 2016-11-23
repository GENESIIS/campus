package com.genesiis.campus.command;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;

public class CmdCheckUsername implements ICommand {
	static Logger log = Logger.getLogger(CmdCheckUsername.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		final TutorDAO tutorDAO = new TutorDAO();
		final Tutor tutor = new Tutor();
		boolean message = false;
		try {
			tutor.setUsername(helper.getParameter("USERNAME"));
			
			
			Collection<Collection<String>> tutorCollection = tutorDAO
					.findById(tutor);
			
			if (tutorCollection.isEmpty()) {
				message = true;
			} else {
				message = false;
			}
			view.setCollection(tutorCollection);
		} catch (Exception exception) {
			log.error("execute() : " + exception);
			throw exception;
		} finally {
			helper.setAttribute("message", message);
		}
		return view;
	}

}
