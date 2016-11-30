package com.genesiis.campus.command;

//20161123 PN c27-upload-user-image: INIT CmdGetProfileImg.java class and implemented execute() method.
//20161124 PN c27-upload-user-image: added a collection to execute() method to set it into IView.
//20161130 PN c27-upload-user-image: modified execute() method by setting images to display on Image profile view according to a image existence.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import org.apache.log4j.Logger;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;

public class CmdGetProfileImg implements ICommand {
	static Logger log = Logger.getLogger(CmdGetProfileImg.class.getName());
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		//Variable declaration.
		String uploadPath = "";
		String proPicName = "";
		FileUtility utility = new FileUtility();
		ICrud sysconfigDAO = new SystemConfigDAO();

		try {
			//Set the image uploading path. Taken the path from SYSTEMCONFIG table.
			//This path has to take from the DB. The deployed education.war folder throws a npx 
			//when accessing it as education/student/ to check file existence on disk.
			Collection<Collection<String>> sysconfigCollection = sysconfigDAO.findById("USER_PIC_UPLOAD_PATH");
			for (Collection<String> collection : sysconfigCollection) {
				Object[] config = collection.toArray();
				uploadPath = (String) config[2];
			}

			int StudentCode = 1; // This needs to be assign from the session, in user sign up.
			String gender = "F"; // This needs to be assign from the session, in user sign up.
			
			//File path taken from the DB will post fix with student code folder and student code image name.
			String filepath = uploadPath + "/" + Integer.toString(StudentCode) + "/";
			//Check if the file exists,
			boolean isFileExists = utility.isFileExists(filepath, Integer.toString(StudentCode));
			if (isFileExists) {
				//If exists get the image from the path and sent the path to the JSP page.
				proPicName = "education/student/" + Integer.toString(StudentCode) + "/" + utility.getImageName();
			} else {
				//If not exists, get the default image according to the gender.
				if (gender.equals("F")) {
					proPicName = "education/student/Female.jpg";
				} else if (gender.equals("M")) {
					proPicName = "education/student/Male.jpg";
				}
			}
			
			//Set Profile picture details to sent them into the JSP page.
			final ArrayList<String> propicDetails = new ArrayList<String>();
			propicDetails.add(proPicName);

			final Collection<String> singleCollection = propicDetails;
			Collection<Collection<String>> collection = new ArrayList<Collection<String>>();
			collection.add(singleCollection);
			view.setCollection(collection);
		} catch (SQLException sqle) {
			log.info("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.info("execute() : e" + e.toString());
			throw e;
		}
		return view;
	}

}
