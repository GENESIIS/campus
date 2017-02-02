package com.genesiis.campus.command;

//20161123 PN c27-upload-user-image: INIT CmdGetProfileImg.java class and implemented execute() method.
//20161124 PN c27-upload-user-image: added a collection to execute() method to set it into IView.
//20161130 PN c27-upload-user-image: modified execute() method by setting images to display on Image profile view according to a image existence.
//20161221 PN CAM-27: modified execute() method by removing DB access method used to take image upload path from DB.

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CmdGetProfileImg implements ICommand {
	static Logger log = Logger.getLogger(CmdGetProfileImg.class.getName());
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		//Variable declaration.
		String proPicName = "";
		FileUtility utility = new FileUtility();

		try {
			int StudentCode = 1; // This needs to be assign from the session, in user sign up.
			String gender = "F"; // This needs to be assign from the session, in user sign up.
			
			//File path taken from the DB will post fix with student code folder and student code image name.
			String warfilepath = "education/student/" + Integer.toString(StudentCode) + "/";
			String absfilepath = "C:/sdb/ctxdeploy/education.war/student/"+ Integer.toString(StudentCode) + "/";
			//Check if the file exists,
			boolean isFileExists = utility.isFileExists(absfilepath, Integer.toString(StudentCode));
			if (isFileExists) {
				//If exists get the image from the path and sent the path to the JSP page.
				proPicName = warfilepath + "/" + utility.getImageName();
			} else {
				//If not exists, get the default image according to the gender.
				if (gender.equals("F")) {
					proPicName = "education/student/Female.jpg";
				} else if (gender.equals("M")) {
					proPicName = "education/student/Male.jpg";
				}
			}
			helper.setAttribute("proPicName", proPicName);
			
		} catch (Exception e) {
			log.info("execute() : e" + e.toString());
			throw e;
		}
		return view;
	}

}
