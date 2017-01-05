package com.genesiis.campus.command;

//20161121 MM c25-student-login-create-dashboard-MP INIT CmdListStudentDashboardDetails.java
//20161121 MM c25-student-login-create-dashboard-MP Implemented execute() method
//20161122 MM c25-student-login-create-dashboard-MP-mm Fixed logger class import issue
//20161123 MM c25-student-login-create-dashboard-MP-mm Added code to search through the student
//			details collection fetched and separate professional experience details from it
//20161227 MM c25-student-create-dashboard-MP Removed unused code
//20170104 MM c25-student-dashboard-MP Added code to fetch student biographical info from DB
//20170104 MM c25-student-dashboard-MP Readied the code to get the student code from the 
//				session when session management is implemented.
//20170105 MM c25-student-dashboard-MP Added JavaDoc comment for execute(IDataHelper, IView) method

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentBasicBioDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CmdListStudentDashboardDetails  implements ICommand {
	
	static Logger Log = Logger.getLogger(CmdListStudentDashboardDetails.class.getName());

	/**
	 * Fetches student profile-related data from the DB to be displayed in the Student Dashboard.
	 * 
	 * @param An IDataHelper that wraps an HttpRequest typed object that contains the parameters sent by the client.  
	 * @param An IView that wraps a variable of type Collection<Collection<String>> that will be assigned profile-related content as a result of the execution of this method. 
	 * @return An IView that contains the student-profile related data collection.   
	 * 
	 * @author miyuru
	 *
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		
		List<String> msgList = new ArrayList<String>();
		
		try {
			String studentCodeStr = "1"; // Get this from the session
//			HttpSession session = helper.getSession(false);
//			studentCodeStr = (String) session.getAttribute("userCode");
			
			if (studentCodeStr != null && !studentCodeStr.isEmpty()) {
				if (Validator.isNumber(studentCodeStr)) {
					
					int studentCode = Integer.parseInt(studentCodeStr);
					
					Student student = new Student();
					student.setCode(studentCode);
					
					// Get profile information of Student represented by studentCode
					StudentBasicBioDAO studentDao = new StudentBasicBioDAO();
					Collection<Collection<String>> studentCollection = new ArrayList<Collection<String>>();	
					
					studentCollection = studentDao.findById(student);
															
					// Get student profile image path from SystemConfig enum
					String studentProfileImagePath = SystemConfig.STUDENT_PROFILE_IMAGE_PATH.getValue1();
					
					view.setCollection(studentCollection);
					helper.setAttribute("studentProfileImagePath", studentProfileImagePath);
				} else {
					msgList.add("The value provided for student parameter is invalid!");
				}// end of if (Validator.isNumber(studentCodeStr))	
			} else {
				msgList.add("No or empty value has been provided for student parameter!");
			}// end of if (studentCodeStr != null && !studentCodeStr.isEmpty())		
			
		} catch (NumberFormatException nfe) {
			Log.info("execute(IDataHelper, IView) : NumberFormatException " + nfe.toString());
			throw nfe;
		} catch (IllegalArgumentException iae) {
			Log.info("execute(IDataHelper, IView) : IllegalArgumentException " + iae.toString());
			throw iae;
		}  catch (Exception e) {
			Log.info("execute(IDataHelper, IView) : Exception " + e.toString());
			throw e;
		} finally {
			helper.setAttribute("messages", msgList);
		}
		
		return  view;
	}

}
