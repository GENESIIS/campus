package com.genesiis.campus.command;

//20170202 AS C22 forgot password, CmdEmailVarification command class created
//20170221 AS C22 status attribute take out from try block and setAttribut() calls out of try block
//20170224 AS C22 Changed imports oder. 

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentEmailVerificationDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.scrypt.crypto.HashCodeBuilder;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.ResetPasswordInstructionEmailDispenser;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.IEmail;
import com.genesiis.campus.util.mail.IEmailComposer;
import com.genesiis.campus.validation.LoginValidator;
import com.genesiis.campus.validation.SystemMessage;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.mail.MessagingException;

public class CmdEmailVarification implements ICommand {
	static Logger log = Logger.getLogger(CmdEmailVarification.class.getName());
	private Student data;
	private Collection<Collection<String>> dataCollection = null;

	String securedHash = "";
	private String emailTitle = "Reset password instructions";
	private ArrayList<String> recieversEmailAddreses;
	private String sendersEmail = "dushantha@genesiis.com";
	private String emailBody = "Someone has requested a link to change your password. You can do this through the Link below.";

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		int status = -3;
		String message = SystemMessage.INVALID_EMAIL.message();
		String result = "";
		String firstName = "";
		String lastName = "";
		String email = "";
		String uname = "";
		String code = "";
		try {

			IEmailComposer resetPasswordEmailComposer = new ResetPasswordInstructionEmailDispenser();
			String gsonData = helper.getParameter("jsonData");
			data = getStudentdetails(gsonData);
			boolean validEmail = LoginValidator.validateEmail(data.getEmail());
			if (validEmail) {
				ICrud emailVarifyDAO = new StudentEmailVerificationDAO();
				dataCollection = emailVarifyDAO.findById(data);

				for (Collection<String> collection : dataCollection) {
					Object[] array = collection.toArray();
					if (array[0].equals(SystemMessage.INVALID_EMAIL.message())) {
						result = (String) array[0];
					} else {

						firstName = (String) array[0];
						lastName = (String) array[1];
						email = (String) array[2];
						uname = (String) array[3];
						code = (String) array[4];
					}
				}

				if (result.equalsIgnoreCase(SystemMessage.INVALID_EMAIL
						.message())) {
					message = SystemMessage.INVALID_EMAIL.message();

				} else {
					data.setFirstName(firstName);
					data.setLastName(lastName);
					data.setEmail(email);
					data.setCode(Integer.parseInt(code));

					recieversEmailAddreses = new ArrayList<String>();
					recieversEmailAddreses.add(email);
					HashCodeBuilder hashBuilder = new HashCodeBuilder();
					securedHash = hashBuilder.createHash(firstName, lastName);
					data.setHashCode(securedHash);

					int updateData = emailVarifyDAO.update(data);
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

	/**
	 * extract data from json object and assign to Student object object
	 * 
	 * @author anuradha
	 * @param gsonData
	 * @return Student object
	 */

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

	/*
	 * sendMail() method intended to dispense the email
	 * 
	 * @author DN
	 * 
	 * @return int -3 fail sending email 3 sent email successfully
	 * 
	 * @throws MessagingException in any case dispensing email fails
	 */
	private int sendMail(IEmailComposer emailComposer) {
		int MAIL_SENT_STATUS = 3;
		try {
			if (emailComposer.getGeneralEmail() == null) {
				Exception exp = new Exception("IEmail is not created ");
				log.error("sendMail(): Exception" + exp.toString());
				throw exp;
			}

			emailComposer.setEmailDispenser(new EmailDispenser(emailComposer
					.getGeneralEmail()));
			emailComposer.getEmailDispenser().emailDispense();
		} catch (IllegalArgumentException illearg) {
			log.error("sendMail():IllegalArgumentException "
					+ illearg.toString());
			MAIL_SENT_STATUS = -3;
		} catch (MessagingException msexp) {
			log.error("sendMail():MessagingException " + msexp.toString());
			MAIL_SENT_STATUS = -3;
		} catch (NullPointerException msexp) {
			log.error("sendMail():NullPointerException " + msexp.toString());
			MAIL_SENT_STATUS = -3;
		} catch (Exception exp) {
			MAIL_SENT_STATUS = -3;
		} finally {
			return MAIL_SENT_STATUS;
		}

	}

	/*
	 * addSpecificContentToOriginalMailBody() formats the original details with
	 * users credentials e.g email, contact number, full name
	 * 
	 * @param originalMailBody String the original message that the user send to
	 * the SMPT mail server
	 */
	private String addSpecificContentToOriginalMailBody() {
		StringBuilder result = new StringBuilder();
		result.append(System.getProperty("line.separator"));
		result.append(System.getProperty("line.separator"));

		result.append(System.getProperty("line.separator"));
		result.append(System.getProperty("line.separator"));

		result.append(System.getProperty("line.separator"));

		result.append(System.getProperty("line.separator"));
		result.append(System.getProperty("line.separator"));

		result.append(System.getProperty("line.separator"));
		result.append(System.getProperty("line.separator"));

		return result.toString();

	}

	/*
	 * systemMessage() handles the system Messages according to the state of the
	 * status passed in
	 * 
	 * @return String the message
	 * 
	 * @param status 3 request submitted successfully.
	 * 
	 * @param status -3 request submission fails.
	 */
	private String systemMessage(int status) {
		String message = SystemMessage.UNKNOWN.message();
		switch (status) {
		case 3:
			message = SystemMessage.MAIL_SUCCESS.message();
			break;
		case -3:
			message = SystemMessage.MAIL_UNSUCCESS.message();
			break;
		default:
			break;
		}
		return message;
	}
}
