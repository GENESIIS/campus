package com.genesiis.campus.command;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;


public class CmdStudentLogout implements ICommand{

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		try {
			Student loggedStudent = new Student();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

			Date loginTime = new Date();

			java.util.Date utilDate = new java.util.Date();
			java.sql.Date loginDate = new java.sql.Date(utilDate.getTime());

			loggedStudent.setLastLoggedOutDate(loginDate.toString());
			loggedStudent.setLastLoggedOutTime(new Timestamp(loginTime.getTime())
					.toString());

			
			} catch (Exception e) {
			//	log.error("CmdStudentLogout():  Exception"
					//	+ e.toString());
				throw e;
			}
		
		return view;
	}

}
