package com.genesiis.campus.command;

//20170117 CW c36-add-tutor-information INIT CmdCheckEmail.java
//20170117 CW c36-add-tutor-information modified the comments in execute ()method
//20170126 CW c36-add-tutor-information modified the execute ()method return value.
//20170130 CW c36-add-tutor-information re-organise the import statements.
//20170215 CW c38-view-update-tutor-profile Add class comment 
//20170223 CW c36-add-tutor-information re-organise the import statements. 
//20170307 CW c147-tutor-reset-password-cw Class copied from c37-tutor-update-tutor-profile-cw.
//20170314 CW c148-tutor-verify-hashcode-reset-password-cw modified the declaration of the variable tutorEmail
//20170502 CW c149-tutor-email-confirmation-for-password-change-cw remove un wanted spaces from import statements

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorEmailDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * this class used to compare user given email address with the 
 * existing database e-mails used to create tutor accounts, 
 * further it implements ICommand interface
 * @author CW
 *
 */
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
				ICrud tutorEmail = new TutorEmailDAO();
				tutorCollection = tutorEmail.findById(tutor);		
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