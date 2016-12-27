//20161227 MM c25-student-login-create-dashboard-MP-mm Created class and implemented execute(IDataHelper, IView) method

package com.genesiis.campus.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDashboardDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.Validator;

/**
 * Returns the list of recommended programmes for the student indicated by the request parameter 
 * named "student" that is sent by the client 
 * 
 * @param IDataHelper Wraps an HTTPServletRequest object that contains the parameters sent by the client. 
 * @param IView Wraps a Collection<Collection<String>> that the collection of programmes selected for the student is assigned to.
 * @returns IView Contains the list of programmes fetched from the DB in Collection<Collection<String>> typed field within it. 
 * 
 * @author Miyuru
 *
 */
public class CmdListStudentRecommendedProgrammes implements ICommand {

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		List<String> msgList = new ArrayList<String>();
		
		try {
			String studentCodeStr = helper.getParameter("student");
			if (studentCodeStr != null && !studentCodeStr.isEmpty()) {
				if (Validator.isNumber(studentCodeStr)) {
					
					int studentCode = Integer.parseInt(studentCodeStr);
					
					Student student = new Student();
					student.setCode(studentCode);
					
					// Get profile information of Student represented by studentCode
					StudentDashboardDAO studentDashboardDao = new StudentDashboardDAO();
					Collection<Collection<String>> studentCollection = new ArrayList<Collection<String>>();
					
					studentCollection = studentDashboardDao.findById(student);					
											
					view.setCollection(studentCollection);
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
