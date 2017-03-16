package com.genesiis.campus.command;

//20170314 CW c148-tutor-verify-hashcode-reset-password-cw CmdTutorPasswordChange class created.
//20170314 CW c148-tutor-verify-hashcode-reset-password-cw comment changed & modified execute method to encrypt & update the password
//20170314 CW c148-tutor-verify-hashcode-reset-password-cw add comments to execute method

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SigningUpTutorDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

public class CmdTutorPasswordChange implements ICommand {
	static Logger log = Logger.getLogger(CmdTutorPasswordChange.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "";
		String pageURL = "/index.jsp";
		try {
			Tutor tutor = new Tutor();
			
			if(helper.getParameter("code") != null){
				tutor.setCode(Integer.parseInt(helper.getParameter("code")));
			}			
			
			//password encryption 
			Encryptable passwordEncryptor = new TripleDesEncryptor(helper.getParameter("password").trim());
			String encryptPassword = passwordEncryptor.encryptSensitiveDataToString().trim();
			tutor.setPassword(encryptPassword);
			
			//updating the tutor table with the new password
			ICrud passwordRest = new SigningUpTutorDAO();
			int result = passwordRest.update(tutor);
			if (result > 0) {
				// if password updated successfully showing the login screen to login
				message = SystemMessage.PASSWORD_SUCCESS.message();
				pageURL = "/index.jsp?showLogin=true";
			} else {
				message = SystemMessage.PASSWORD_UNSUCCESS.message();
			}
		} catch (SQLException sqle) {
			log.error("execute(): SQLException " + sqle.toString());
			throw sqle;
		} catch (IllegalArgumentException ilexp) {
			log.error("execute(): IllegalArgumentException" + ilexp.toString());
			throw ilexp;
		} catch (Exception exp) {
			log.error("execute():Exception " + exp.toString());
			throw exp;
		}

		helper.setAttribute("message", message);
		helper.setAttribute("pageURL", pageURL);
		return view;
	}
}

