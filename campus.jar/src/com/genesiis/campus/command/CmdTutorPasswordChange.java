package com.genesiis.campus.command;

//20170314 CW c147-tutor-reset-password-cw CmdTutorPasswordChange class created.

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SigningUpStudentDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

public class CmdTutorPasswordChange implements ICommand {
	static Logger log = Logger.getLogger(CmdTutorPasswordChange.class.getName());
	private Student data;
	private Collection<Collection<String>> dataCollection = null;

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "";
		String pageURL = "/index.jsp";
		/*try {
			String gsonData = helper.getParameter("jsonData");
			data = getStudentdetails(gsonData);

			ICrud passwordRest = new SigningUpStudentDAO();
			int result = passwordRest.update(data);
			if (result > 0) {
				message = SystemMessage.PASSWORD_SUCCESS.message();
				pageURL = "/index.jsp?showLogin=true";
			} else {
				message = SystemMessage.PASSWORD_UNSUCCESS.message();
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
		}*/

		helper.setAttribute("message", message);
		helper.setAttribute("pageURL", pageURL);
		return view;
	}
}

