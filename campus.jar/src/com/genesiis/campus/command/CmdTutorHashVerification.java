package com.genesiis.campus.command;

//20170313 CW c148-tutor-verify-hashcode-reset-password-cw CmdTutorHashVerification class created

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * This class is used to validate the entered hash code with the hashcode in the tutor table
 * further it implements ICommand interface
 * @author chinthaka
 */
public class CmdTutorHashVerification implements ICommand {

	static Logger log = Logger.getLogger(CmdTutorHashVerification.class
			.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String pageURL = "/dist/partials/login/emailVerification.jsp";
		String result = "";
		/*String message = "";
		Student data;
		Collection<Collection<String>> dataCollection = null;
		try {
			String gsonData = helper.getParameter("jsonData");
			data = getStudentdetails(gsonData);
			StudentEmailVerificationDAO studentEmailvarification = new StudentEmailVerificationDAO();
			dataCollection = studentEmailvarification.verifyHashCode(data);

			for (Collection<String> collection : dataCollection) {
				Object[] array = collection.toArray();
				result = (String) array[0];
			}
			if (result == SystemMessage.VERIFICATION_CODEEXPIRED.message()) {
				message = result;
			}
			if (result == SystemMessage.INVALID_HASHCODE.message()) {
				message = result;
			} else {
				view.setCollection(dataCollection);
				pageURL = "/dist/partials/login/passwordReset.jsp";
				message = SystemMessage.VALIDHASHCODE.message();
			}
		} catch (SQLException sexp) {
			log.error("execute(): SQLException " + sexp.toString());
			throw sexp;
		} catch (IllegalArgumentException ilexp) {
			log.error("execute(): IllegalArgumentException" + ilexp.toString());
			throw ilexp;
		} catch (Exception exp) {
			log.error("execute():Exception " + exp.toString());
			throw exp;
		}

		helper.setAttribute("errorMessage", message);
		helper.setAttribute("pageURL", pageURL);
*/
		return view;
	}

	/**
	 * extract data from json object and assign to Student object object
	 * 
	 * @author anuradha
	 * @param gsonData
	 * @return Student object
	 */
/*
	private Student getStudentdetails(String gsonData) {
		Student student = (Student) extractFromJason(gsonData);
		return student;
	}

	public Object extractFromJason(String gsonData) {
		Gson gson = new Gson();
		String message = "";
		Student student = null;
		try {
			student = gson.fromJson(gsonData, Student.class);

		} catch (Exception e) {
			log.error("extractFromJason(): " + e.toString());
			throw e;
		}
		return student;
	}*/


}

/*package com.genesiis.campus.command;

//20170209 AS C22 forgot password, CmdHashCodeVerification command class created
//20170221 AS C22 execute() method body implemented a try-catch block
//20170223 AS C22 result,pageURL,message,data added inside try-block 
//20170224 AS C22 Changed imports oder. 

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentEmailVerificationDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

public class CmdHashCodeVerification implements ICommand {
	static Logger log = Logger.getLogger(CmdHashCodeVerification.class
			.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String pageURL = "/dist/partials/login/emailVerification.jsp";
		String result = "";
		String message = "";
		Student data;
		Collection<Collection<String>> dataCollection = null;
		try {
			String gsonData = helper.getParameter("jsonData");
			data = getStudentdetails(gsonData);
			StudentEmailVerificationDAO studentEmailvarification = new StudentEmailVerificationDAO();
			dataCollection = studentEmailvarification.verifyHashCode(data);

			for (Collection<String> collection : dataCollection) {
				Object[] array = collection.toArray();
				result = (String) array[0];
			}
			if (result == SystemMessage.VERIFICATION_CODEEXPIRED.message()) {
				message = result;
			}
			if (result == SystemMessage.INVALID_HASHCODE.message()) {
				message = result;
			} else {
				view.setCollection(dataCollection);
				pageURL = "/dist/partials/login/passwordReset.jsp";
				message = SystemMessage.VALIDHASHCODE.message();
			}
		} catch (SQLException sexp) {
			log.error("execute(): SQLException " + sexp.toString());
			throw sexp;
		} catch (IllegalArgumentException ilexp) {
			log.error("execute(): IllegalArgumentException" + ilexp.toString());
			throw ilexp;
		} catch (Exception exp) {
			log.error("execute():Exception " + exp.toString());
			throw exp;
		}

		helper.setAttribute("errorMessage", message);
		helper.setAttribute("pageURL", pageURL);

		return view;
	}

	*//**
	 * extract data from json object and assign to Student object object
	 * 
	 * @author anuradha
	 * @param gsonData
	 * @return Student object
	 *//*

	private Student getStudentdetails(String gsonData) {
		Student student = (Student) extractFromJason(gsonData);
		return student;
	}

	public Object extractFromJason(String gsonData) {
		Gson gson = new Gson();
		String message = "";
		Student student = null;
		try {
			student = gson.fromJson(gsonData, Student.class);

		} catch (Exception e) {
			log.error("extractFromJason(): " + e.toString());
			throw e;
		}
		return student;
	}

}*/

