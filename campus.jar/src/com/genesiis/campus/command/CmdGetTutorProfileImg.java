package com.genesiis.campus.command;
//20170103 DN c47-tutor-add-tutor-information-upload-image-dn create the CmdGetTutorProfileImg.java class
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;


/**
 * CmdGetTutorProfileImg get the profile image which is currently
 * within the system 
 * @author dushantha DN 
 *
 */
public class CmdGetTutorProfileImg implements ICommand {

	static Logger log = Logger.getLogger(CmdGetTutorProfileImg.class.getName());
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		FileUtility utility = new FileUtility();
		Integer tutorCodeFromSession = (Integer) helper.getSession(false).getAttribute("userid");
		int tutorCode = (tutorCodeFromSession!=null)?tutorCodeFromSession:1; //TUTOR CODE HAS TO BE OBTAINED FROM SESSION
		// have to set the absolute path
		boolean isFileExists = utility.isFileExists("", Integer.toString(tutorCode));
		
		
		return view;
	}

}
