package com.genesiis.campus.command;

//20161121 MM c25-student-login-create-dashboard-MP INIT CmdListStudentDashboardDetails.java
//20161121 MM c25-student-login-create-dashboard-MP Implemented execute() method
//20161122 MM c25-student-login-create-dashboard-MP-mm Fixed logger class import issue
//20161123 MM c25-student-login-create-dashboard-MP-mm Added code to search through the student
//			details collection fetched and separate professional experience details from it
//20161227 MM c25-student-login-create-dashboard-MP-mm Removed unused code

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDashboardDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CmdListStudentDashboardDetails  implements ICommand {
	
	static Logger Log = Logger.getLogger(CmdListStudentDashboardDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		
		List<String> msgList = new ArrayList<String>();
		
		try {
			String studentCodeStr = helper.getParameter("student");
			if (studentCodeStr != null && !studentCodeStr.isEmpty()) {
				if (Validator.isNumber(studentCodeStr)) {
					
					int studentCode = Integer.parseInt(studentCodeStr);
					
					Student student = new Student();
					student.setCode(studentCode);
					
					// Get profile information of Student represented by studentCode
					StudentDashboardDAO studentDao = new StudentDashboardDAO();
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
