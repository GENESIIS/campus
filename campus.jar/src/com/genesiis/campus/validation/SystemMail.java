package com.genesiis.campus.validation;

//20170105 CW c98-send-email-at-tutor-signup created the initial SystemMail.java version

public enum SystemMail {

	SEND_EMAIL_TUTOR_SIGNUP_BODY1("Registration Confirmation", "Dear Sir/Madam", "You have successfully registered with the campus.lk."),
	SEND_EMAIL_TUTOR_SIGNUP_BODY2("Registration Confirmation", "Dear Sir/Madam", "Your details are waiting for the administration approval. On admin approval you will receive an email, confirming your information with campus.lk."),
	SEND_EMAIL_TUTOR_SIGNUP_BODY3("Registration Confirmation", "Dear Sir/Madam", "If you didn't sign up for this account, or you are having trouble with your account, please contact us at support@campus.lk and we will be happy to help you.");
	
	private final String subject;
	private final String title;
	private final String mailBody;
	
	SystemMail(final String subject, final String title, final String mailBody) {
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