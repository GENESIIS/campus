package com.genesiis.campus.command;

//20161026 CM c9-make-inquiry-for-institute INIT CmdSendInstituteInquiry.java
//20161027 CM c9-make-inquiry-for-institute Modified execute() method
//20161027 CM c9-make-inquiry-for-institute Add method comment
//20161027 CM c9-make-inquiry-for-institute Validate request parameters
//20161031 CM c9-make-inquiry-for-institute Create sendEmail() method for send inquiry
//20161031 CM c9-make-inquiry-for-institute Modified execute() method
//20161031 CM c9-make-inquiry-for-institute Modified execute() method
//20161101 CM c9-make-inquiry-for-institute Modified execute() method
//20161102 CM c9-make-inquiry-for-institute Modified execute() method
//20161102 CM c9-make-inquiry-for-institute Implement reCAPTCHA.
//20161108 CM c9-make-inquiry-for-institute Removed unused loggers.
//20161108 CM c9-make-inquiry-for-institute Modified execute() method
//20161109 CM c9-make-inquiry-for-institute Removed createDatabaseConnection() method
//20161108 CM c9-make-inquiry-for-institute Modified composeSingleEmailList() method
//20161110 CM c9-make-inquiry-for-institute Modified execute() method

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.CourseProviderInquiryDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProviderInquiry;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.ReCaptchaManager;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.GeneralMail;
import com.genesiis.campus.util.mail.IEmail;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

public class CmdSendInstituteInquiry implements ICommand {

	static Logger log = Logger.getLogger(CmdSendInstituteInquiry.class
			.getName());

	private String fullname;
	private String sendersEmail;
	private String countryCode;
	private String areaCode;
	private String telNo;
	private String inquiryTitle;
	private String inquiry;
	private int studentCode;
	private int corseProviderCode;
	private ArrayList<String> recieversEmailAddreses;
	private EmailDispenser emailDispenser;
	private IEmail generalEmail;

	/**
	 * @author Chathuri
	 * @param helepr
	 *            IDataHelper object of Object type view IView object of object
	 *            type
	 * @return View object to servlet
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "";

		try {
			final CourseProviderInquiry instituteInquiry = new CourseProviderInquiry();

			final ReCaptchaManager reCaptchaManager = new ReCaptchaManager();
			boolean responseIsSuccess = reCaptchaManager
					.sendRequestToServer(helper);

			// Verify whether the input from Human or Robot
			if (responseIsSuccess) {
				// Input by Human
				String validateResult = Validator.validateInstituteInquiry(helper);

				if (validateResult.equalsIgnoreCase("True")) {
					setEnvironment(helper);

					instituteInquiry.setStudentName(fullname);
					instituteInquiry.setStudentEmail(sendersEmail);
					instituteInquiry.setTelephoneCountryCode(countryCode);
					instituteInquiry.setTelephoneAreaCode(areaCode);
					instituteInquiry.setTelNo(telNo);
					instituteInquiry.setInquiryTitle(inquiryTitle);
					instituteInquiry.setInquiryText(inquiry);
					instituteInquiry.setStudent(studentCode);
					instituteInquiry.setCourseProvider(corseProviderCode);
					instituteInquiry.setIsActive(1);

					final CourseProviderInquiryDAO inquiryDAO = new CourseProviderInquiryDAO();
					final CourseProviderDAO courseProviderDAO = new CourseProviderDAO();
					int result = inquiryDAO.add(instituteInquiry);
					if (result > 0) {
						Collection<Collection<String>> courseProviderEmail = courseProviderDAO
								.findById(instituteInquiry);

						recieversEmailAddreses = composeSingleEmailList(courseProviderEmail);
						generalEmail = formatEmailInstance();
						this.sendMail();
						message = SystemMessage.INQUIRYSENT.message();

					} else {
						message = SystemMessage.ERROR.message();
					}
				} else {
					message = validateResult;

				}
			} else {
				// Input by Robot
				message = SystemMessage.RECAPTCHAVERIFICATION.message();
			}

		} catch (Exception exception) {
			log.error("execute() : " + exception);
			throw exception;
		} finally {
			helper.setAttribute("message", message);

		}
		return view;
	}

	/*
	 * setEnvironment() method initializes all the instance variable
	 * 
	 * @author DN
	 * 
	 * @param helper IDataHelper
	 */
	private void setEnvironment(IDataHelper helper) {

		fullname = helper.getParameter("fullname");
		sendersEmail = helper.getParameter("email");
		countryCode = helper.getParameter("countryCode");
		areaCode = helper.getParameter("areaCode");
		telNo = helper.getParameter("telNum");
		inquiryTitle = helper.getParameter("inquiryTitle");
		inquiry = helper.getParameter("inquiry");
		studentCode = Integer.parseInt(helper.getParameter("studentCode"));
		corseProviderCode = Integer.parseInt(helper
				.getParameter("courseProviderCode"));
	}

	/*
	 * formatEmailInstance() creates an IEmail with email receiver and sender
	 * addresses, host ,SMTP host, subject, mailBody bounded
	 * 
	 * @author DN
	 * 
	 * @return IEmail formatted Email out put
	 */
	private IEmail formatEmailInstance() {
		addContentToOriginalMailBody(inquiry);
		IEmail generalEmail = new GeneralMail(recieversEmailAddreses,
				sendersEmail, inquiryTitle, inquiry);
		return generalEmail;

	}

	/*
	 * composeSingleEmailList() adds up all the email structured in Collection
	 * of Collections to a monolithic Collection<String>
	 * 
	 * @author DN
	 * 
	 * @param outer accepts Collection<Collection<String>
	 * 
	 * @return email list as a mono ArrayList<String>
	 */

	private ArrayList<String> composeSingleEmailList(
			Collection<Collection<String>> outer) {
		ArrayList<String> monoList = new ArrayList<String>();
		for (Collection<String> emailAddressList : outer) {
			Object ar[] = emailAddressList.toArray();
			String emailAddr = (String) ar[0];
			monoList.add(emailAddr);
		}
		return monoList;

	}

	/*
	 * sendMail() method intended to dispense the email
	 * 
	 * @author DN
	 * 
	 * @throws MessagingException in any case dispensing email fails
	 */

	private void sendMail() throws MessagingException {
		emailDispenser = new EmailDispenser(generalEmail);
		emailDispenser.emailDispense();
	}

	/*
	 * addContentToOriginalMailBody() formats the original details with users
	 * credentials e.g email, contact number, full name
	 * 
	 * @param originalMailBody String the original message that the user send to
	 * the SMPT mail server
	 */
	private void addContentToOriginalMailBody(String originalMailBody) {
		StringBuilder result = new StringBuilder();

		result.append(originalMailBody);
		result.append(System.getProperty("line.separator"));
		result.append("Inquiry From : ");
		result.append(System.getProperty("line.separator"));
		result.append(sendersEmail);
		this.inquiry = result.toString();

	}
}
