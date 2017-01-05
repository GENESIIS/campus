package com.genesiis.campus.command;

//20170105 CW c98-send-email-at-tutor-signup-cw Created the CmdGenerateEmailTutorSinUp.java class to send email at tutor signup.

import java.sql.SQLException;
import java.util.ArrayList;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.RowStudentForJason;
import com.genesiis.campus.util.StudentSignUpEmailComposer;
import com.genesiis.campus.util.mail.IEmailComposer;
import com.genesiis.campus.validation.SystemMessage;

public class CmdGenerateEmailTutorSinUp implements ICommand {

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		try {
			IEmailComposer signUpEmailComposer = new StudentSignUpEmailComposer();
			int status;
//			String message = "";
//			String gsonData = helper.getParameter("jsonData");
//			this.setPartialStudent((RowStudentForJason) extractDumyObjectFrom(gsonData));
//			String recieversName = partialStudent.getFirstName().concat(" " + partialStudent.getLastName());
			
			String recieversName = helper.getParameter("firstname").concat(" " + helper.getParameter("lastname"));
			String sendersEmailAddress = helper.getParameter("email");
			String recieversEmailAddreses = helper.getParameter("email");
			
			
			signUpEmailComposer.setEnvironment(recieversName, sendersEmailAddress,
					signUpEmailComposer.composeSingleEmailList(recieversEmailAddreses),
					SystemMessage.STUDENT_ACCOUNT_CREATE_MAIL_SUBJECT.message(),
					SystemMessage.STUDENT_SUCCESSFULL_CREATTION.message());

			signUpEmailComposer.setGeneralEmail(signUpEmailComposer.formatEmailInstance(addSpecificContentToOriginalMailBody()));
			status = this.sendMail(signUpEmailComposer);
			helper.setAttribute("message", composeOutStatusMessageToClient(status));

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
		return view;
	}

}


/*

/**
 * setEnvironment() sets the required fields from the client inputs
 * @param recieversName
 * @param sedersEmailAddress
 * @param recieversEmailAddreses
 * @param mailSubject
 * @param emailBodyText
 * @param restoftheParameters it's a variable argument , it can be omitted as well.
 * if it's set it's users responsibility to set the fields and pass the
 * correct order of the parameters to be set.
 //
void setEnvironment(String recieversName,
		String sedersEmailAddress,
		ArrayList<String> recieversEmailAddreses, 
		String mailSubject,
		String emailBodyText,String ... restoftheParameters);
*/