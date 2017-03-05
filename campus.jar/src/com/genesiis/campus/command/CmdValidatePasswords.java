package com.genesiis.campus.command;

//20170303 c37-tutor-update-tutor-profile-cw INIT CmdValidatePasswords.java
//20170305 c37-tutor-update-tutor-profile-cw modified execute method to validate password fields properly using Validator.isValidPassword method

import com.genesiis.campus.entity.IView;
//import com.genesiis.campus.entity.TutorEmailDAO;
//import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is used to validate password fields before update the password
 * further it implements ICommand interface
 * @author CW
 */
public class CmdValidatePasswords implements ICommand {
	static Logger log = Logger.getLogger(CmdValidatePasswords.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws Exception {

		String message = "";
		try {
				
			boolean valid = false;
			valid = Validator.isValidPassword(helper);
			
			if(valid){
				message = "TRUE";
			}else{
				message = "FALSE";
			}
			
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
		}
		return view;
	}

}
