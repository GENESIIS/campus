package com.genesiis.campus.command;

//20170117 CW c36-add-tutor-information INIT CmdCheckEmail.java
//20170117 CW c36-add-tutor-information modified the comments in execute ()method
//20170126 CW c36-add-tutor-information modified the execute ()method return value.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorEmailDAO;
import com.genesiis.campus.entity.TutorUserNameDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Validator;
import org.apache.log4j.Logger;

public class CmdCheckEmail implements ICommand {
	static Logger log = Logger.getLogger(CmdCheckEmail.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		String message = "";
		boolean valid = false;
		
		Collection<Collection<String>> tutorCollection= new ArrayList<Collection<String>>();
		
		try {
			
			valid = Validator.isNotEmpty(helper.getParameter("email"));
			
			if (valid){
				final Tutor tutor = new Tutor();
				tutor.setEmailAddress(helper.getParameter("email"));
				tutorCollection = new TutorEmailDAO().findById(tutor);		
			}
			
			if (tutorCollection.isEmpty()) {
				message = "1"; // email does not exist
			} else {
				message = "0"; // email Already exists
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
