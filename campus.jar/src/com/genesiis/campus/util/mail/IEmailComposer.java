package com.genesiis.campus.util.mail;
//20161227 DN CAM 18: IEmailComposer.java interface is created in order to centerlise the reusable 
//            body composing functions.



import java.util.ArrayList;
import java.util.Collection;

public interface IEmailComposer {


/** formatEmailInstance() creates an IEmail with email receiver and sender addresses,
* host ,SMTP host, subject, mailBody bounded
 * @author DN
 * @param mailBodyForSpecificMail string type parameter thats to be appended
 * at the end of regular body content such as date,greeting, recipients name etc
 * @return IEmail formatted Email out put
 * @throws  IllegalArgumentException if the receivers email address is not set or emty string.
 */
IEmail formatEmailInstance(String mailBodyForSpecificMail) throws IllegalArgumentException;

/** composeSingleEmailList(Collection<Collection<String>> outer) adds up all the email structured in Collection
* of Collections to a monolithic Collection<String>
* @author DN
* @param outer accepts Collection<Collection<String>
* @return email list as a mono ArrayList<String>
*/
ArrayList<String> composeSingleEmailList(Collection<Collection<String>> outer);

/**
 * composeSingleEmailList(String anEmailAddress) adds up single email address
 * which is in string form to an ArrayList
 * @author dushantha DN
 * @param anEmailAddress
 * @return ArrayList<String>
 */
ArrayList<String> composeSingleEmailList(String anEmailAddress);


/**
 * addContentToOriginalMailBody() formats the original details with
 * users credentials e.g email, contact number, full name
 * @author dushantha DN
 * @param originalMailBody String the original message that the user send to
 * the SMPT mail server
 */
void addContentToOriginalMailBody(String originalMailBody);

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
 */
void setEnvironment(String recieversName,
		String sedersEmailAddress,
		ArrayList<String> recieversEmailAddreses, 
		String mailSubject,
		String emailBodyText,String ... restoftheParameters);

}
