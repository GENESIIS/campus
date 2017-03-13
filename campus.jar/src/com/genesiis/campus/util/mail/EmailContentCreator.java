package com.genesiis.campus.util.mail;

//20170128 CW c126-cw: Created in order to insert the email body composing functions inside an interface.
//20170307 CW c147-tutor-reset-password-cw interface copied from c37-tutor-update-tutor-profile-cw.

public interface EmailContentCreator {
	/**
	 * getEmailSubjectBody() manages to return the String
	 * @author chinthaka CW
	 * @return a String containing the Subject & title of the email
	 */
	public String getEmailSubjectBody();

	/**
	 * getEmailBody() manages to return the String
	 * @author chinthaka CW
	 * @return a String containing the body of the email
	 */
	public String getEmailBody();

	/**
	 * getEmailComplementaryClose() manages to return the String
	 * @author chinthaka CW
	 * @return a String Complementary Close of the email
	 */
	public String getEmailComplementaryClose(String username);
}