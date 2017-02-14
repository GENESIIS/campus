package com.genesiis.campus.validation;

//20170105 CW c98-send-email-at-tutor-signup created the initial SystemMail.java version
//20170125 CW c125-un-formatted-email-sending-tutor-signup-add comments to the Enum - cw
//20170127 CW c126-formatting-un-formatted-email-tutor-signup-cw - add attributes to the Enum
//20170128 cw c126-formatting-un-formatted-email-tutor-signup-cw - modified attribute name
//20170128 cw c126-formatting-un-formatted-email-tutor-signup-cw - rename the enum name from SystemMail to SystemEmail.

/**
 * SystemMail enum handles the content of the email to be sent
 * at the time of tutor signup.
 * @author Chinthaka CW
 */
public enum SystemEmail {

	SEND_EMAIL_TUTOR_SIGNUP_BODY1("Registration Confirmation", "Dear Sir/Madam", "You have successfully registered with the campus.lk."),
	SEND_EMAIL_TUTOR_SIGNUP_BODY2("Registration Confirmation", "Dear Sir/Madam", "Your details are waiting for the administration approval. On admin approval you will receive an email, confirming your information with campus.lk."),
	SEND_EMAIL_TUTOR_SIGNUP_BODY3("Registration Confirmation", "Dear Sir/Madam", "If you didn't sign up for this account, or you are having trouble with your account, please contact us at support@campus.lk and we will be happy to help you."),
	SEND_EMAIL_TUTOR_SIGNUP_COMPLEMENTARY_CLOSE1("", "", "Campus.lk Support Team"),
	SEND_EMAIL_TUTOR_SIGNUP_COMPLEMENTARY_CLOSE2("", "", "http://www.campus.lk"),
	SEND_EMAIL_TUTOR_SIGNUP_COMPLEMENTARY_CLOSE3("", "", "Your user ID: ");
	
	private final String subject;
	private final String title;
	private final String mailBody;
	
	SystemEmail(final String subject, final String title, final String mailBody) {
		this.subject = subject;
		this.title = title;
		this.mailBody = mailBody;
	}	

	public String getSubject() {
		return subject;
	}

	public String getTitle() {
		return title;
	}

	public String getMailBody() {
		return mailBody;
	}	
}