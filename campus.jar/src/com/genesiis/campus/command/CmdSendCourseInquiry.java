package com.genesiis.campus.command;
//20161027 AS C8-inquiry-form-for-course CmdSendCourseInquiry class created.
import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import org.apache.log4j.Logger;

public class CmdSendCourseInquiry implements ICommand{

	static Logger log = Logger.getLogger(CmdSendCourseInquiry.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		//getting the admin relateddata e.g email address
		// composing the email and sending will be executed
		
		
		return null;
	}

	
}
