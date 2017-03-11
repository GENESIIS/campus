package com.genesiis.campus.command;

import com.genesiis.campus.entity.ICrud;

//20170307 CW c147-tutor-reset-password-cw CmdTutorEmailVerification class created.
//20170311 CW c147-tutor-reset-password-cw modified execute method to get the data from the database for given email

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorEmailVerificationDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.mail.GenerateEmail;
import com.genesiis.campus.validation.LoginValidator;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CmdTutorEmailVerification implements ICommand {
	
	static Logger log = Logger.getLogger(CmdTutorEmailVerification.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		int status = -3;
		String message = null;//SystemMessage.INVALID_EMAIL.message();
		//String result = "";
		String firstName = "";
		String lastName = "";
		String email = "";
		String uname = "";
		String code = "";
		try {

			//IEmailComposer resetPasswordEmailComposer = new ResetPasswordInstructionEmailDispenser();
			
			GenerateEmail resetPasswordEmailComposer = new GenerateEmail();
			Tutor tutor = new Tutor();
			tutor.setEmailAddress(helper.getParameter("email"));
/*			String gsonData = helper.getParameter("jsonData");
			System.out.println("verifyemail = "+helper.getParameter("verifyemail"));
			data = getStudentdetails(gsonData);*/
			boolean validEmail = LoginValidator.validateEmail(tutor.getEmailAddress());
			if (validEmail) {
				ICrud emailVarifyDAO = new TutorEmailVerificationDAO();
				Collection<Collection<String>> emailCollection = new ArrayList<Collection<String>>();
				emailCollection = emailVarifyDAO.findById(tutor);

				for (Collection<String> collection : emailCollection) {
					if(collection != null && !collection.isEmpty()){
						Object[] array = collection.toArray();
						/*if (array[0].equals(SystemMessage.INVALID_EMAIL.message())) {
							result = (String) array[0];
						} else {*/	
							firstName = (String) array[0];
							lastName = (String) array[1];
							email = (String) array[2];
							uname = (String) array[3];
							code = (String) array[4];
					}else{
						message = SystemMessage.INVALID_EMAIL.message();
					}
				}

				if (message.isEmpty()){  // && message.equalsIgnoreCase(SystemMessage.INVALID_EMAIL.message())) {
			/*		message = SystemMessage.INVALID_EMAIL.message();
				} else {*/
					tutor.setFirstName(firstName);
					tutor.setLastName(lastName);
					tutor.setEmailAddress(email);
					tutor.setCode(Integer.parseInt(code));

					recieversEmailAddreses = new ArrayList<String>();
					recieversEmailAddreses.add(email);
					HashCodeBuilder hashBuilder = new HashCodeBuilder();
					securedHash = hashBuilder.createHash(firstName, lastName);
					tutor.setHashCode(securedHash);

					int updateData = emailVarifyDAO.update(tutor);
					if (updateData > 0) {
						message = SystemMessage.HASHCODES.message();
						resetPasswordEmailComposer.setEnvironment(firstName
								+ " " + lastName, sendersEmail,
								recieversEmailAddreses, emailTitle, emailBody
										+ " " + securedHash, securedHash);

						resetPasswordEmailComposer
								.setGeneralEmail(resetPasswordEmailComposer
										.formatEmailInstance(addSpecificContentToOriginalMailBody()));
						status = this.sendMail(resetPasswordEmailComposer);

					} else {
						message = SystemMessage.HASHCODEUNS.message();
					}

					message = systemMessage(status);

				}
			} else {
				message = SystemMessage.INVALID_EMAIL.message();
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
		helper.setAttribute("message", message);
		return view;
	}

}
