package com.genesiis.campus.command;

//20161122 JH c39-add-course-provider CmdAddFeaturedProvider.java command class created
//20161122 JH c39-add-course-provider implemented ICommand class
//20161123 JH c39-add-course-provider execute method coding : retrieve request parameters
//20161129 JH c39-add-course-provider code refactor due to course provider DAO class rename, handle user messages
//20161201 JH c39-add-course-provider added application enum to get course provider status
//20161202 JH c39-add-course-provider fixed null point exception in execute method
//20161202 JH c39-add-course-provider code refactor to support one-off and featured provider registration
//20161206 JH c39-add-course-provider get accountStatus of the user account
//20161208 JH c39-add-course-provider code modified due to entity class method changes
//20161219 JH c39-add-course-provider code review modifications: use generics 
//20161229 JH c39-add-course-provider code modified to get course provider town data
//20170102 JH c39-add-course-provider back end code validation 
//20170126 JH c39-add-course-provider code re-factored to add contact number details of course provider account and town
//20170201 JH c39-add-course-provider arranged imports according to the style guide
//20170202 JH c39-add-course-provider combined username and the email validation methods together and removed database call 
//used to select course provider usertype
//20170202 JH c39-add-course-provider removed unwanted imports and code refactored
//20170203 JH c39-add-course-provider code changed to get the default course provider expriation date form the system config enum class
//20170221 JH c141-add-course-provider-issue-improvements modified to access validator class methods in static way, added doc comments
//20170301 JH c141-add-course-provider-issue-improvements remove expiration date which is retrieved from the front end, removed entity.setModBy() statements 
//20170404 JH c141-ui-integration-for-add-course-provider code modified to use AccountType enum class values to validate user type
//20170405 JH c141-ui-integration-for-add-course-provider fixed exception due to account status code selection
//20170406 JH c141-ui-integration-for-add-course-provider build contact number and web link hint messages
//20170407 JH c141-ui-integration-for-add-course-provider removed commented old expiration date implementation and added codes to get web link prefix
//20170417 JH c141-ui-integration-for-add-course-provider added "http://" as the webLinkPrefix value and build the last weblink
//20170502 JH c141-ui-integration-for-add-course-provider added checkUsername(IDataHelper, CourseProviderAccount, String)() method for username validation
//20170503 JH c141-ui-integration-for-add-course-provider use checkUsername() method to validate the email and the username, added method checkPrefix(),
//				added getExpirateionDate() to get the common expiration date

import com.genesiis.campus.entity.CourseProviderPrefixDAO;
import com.genesiis.campus.entity.CourseProviderUsernameDAO;
import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.OneOffCourseProviderDAO;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.entity.model.CourseProviderTown;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Validator;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * CmdAddFeaturedProvider used to add featured and one off course provider
 * @author JH
 *
 */
public class CmdAddFeaturedProvider implements ICommand {
	static Logger log = Logger
			.getLogger(CmdAddFeaturedProvider.class.getName());

	private IView courseProviderData;

	public CmdAddFeaturedProvider(IView courseProviderData) {
		this.courseProviderData = courseProviderData;
	}

	public CmdAddFeaturedProvider() {

	}

	/**
	 * execute method used to handle the request related to course provider
	 * registration. Details are validated and then username, email and prefix
	 * are validated for existence. Depending on the course provider type it
	 * will call between featured course provider and one off course provider
	 * DAO classes.
	 * 
	 * @author JH
	 * @param IDataHelper
	 * @param IView
	 * @return IView Objects
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		final CourseProvider courseProvider = new CourseProvider();
		final CourseProviderAccount courseProviderAccount = new CourseProviderAccount();
		final CourseProviderTown courseProviderTown = new CourseProviderTown();
		ICrud courseProviderDAO = new FeaturedCourseProviderDAO();
		int generatedKey = 0;

		String systemMessage = null;

		try {

			// back end validation for required fields

			ArrayList<String> errorMessages = Validator
					.validateCourseProvider(helper);

			if (errorMessages.size() == 0) {

				// validate username
				String username = helper.getParameter("providerUsername");
				String email = helper.getParameter("providerEmail");
				courseProviderAccount.setUsername(username);
				courseProviderAccount.setEmail(email);

				/**
				 * checks the username availability and email. If the email
				 */
				
				if ( !checkUsername(helper, courseProviderAccount, systemMessage)) { 
					/* username or the email is not valid */

				} else {
					/* valdate prefix */
					String prefix = helper.getParameter("uniquePrefix");
					courseProvider.setUniquePrefix(prefix);

					if (!checkPrefix(helper, courseProvider, systemMessage)) {
						/* prefix is not available */

					} else if (checkPrefix(helper, courseProvider, systemMessage)) {/* prefix is available */

						String countryCode = helper.getParameter("selectedCountry");
						String selectedTown = helper.getParameter("selectedTown");
						String courseProviderType = helper.getParameter("selectedProviderType");

						/**
						 * checks for head office code. if no head office code
						 * is provided, it means the course provider is a head office.
						 * Then assign value '0'. Else get course provider head 
						 * office town code from the helper. 
						 * 
						 * 
						 */
						if (Validator.isEmptyString(helper.getParameter("headOffice"))) {
							courseProvider.setHeadOffice(0);
						} else {
							courseProvider.setHeadOffice(Integer.parseInt(helper.getParameter("headOffice")));
						}

						int providerStatus = Integer.parseInt(helper.getParameter("providerStatus"));

						String webLinkPrefix = "http://";
						String webLink = helper.getParameter("webLink");
						String lastWebAddress = webLinkPrefix + webLink;
						
						// set basic data
						courseProvider.setUniquePrefix(helper.getParameter("uniquePrefix"));
						courseProvider.setShortName(helper.getParameter("shortName"));
						courseProvider.setName(helper.getParameter("providerName"));
						courseProvider.setDescription(helper.getParameter("aboutMe"));
						courseProvider.setCourseInquiryEmail(helper.getParameter("inquiryMail"));
						courseProvider.setLandPhoneCountryCode(countryCode);
						courseProvider.setLandPhoneAreaCode(helper.getParameter("areaCode"));
						courseProvider.setLandPhoneNo(helper.getParameter("land1"));
						courseProvider.setMobilePhoneNetworkCode(helper.getParameter("networkCode"));
						courseProvider.setMobilePhoneNumber(helper.getParameter("mobile"));
						courseProvider.setExpirationDate(getExpirationDate());
						courseProvider.setMobilePhoneCountryCode(countryCode);

						courseProvider.setCourseProviderType(Integer.parseInt(courseProviderType));
						courseProvider.setLandPhpneNo2(helper.getParameter("land2"));
						courseProvider.setFaxNo(helper.getParameter("fax"));
						courseProvider.setSpeciality(helper.getParameter("specialFeatures"));
						courseProvider.setWeblink(lastWebAddress);
						courseProvider.setFacebookURL(helper.getParameter("facebook"));
						courseProvider.setTwitterURL(helper.getParameter("twitter"));
						courseProvider.setMyspaceURL(helper.getParameter("mySpace"));
						courseProvider.setLinkedinURL(helper.getParameter("linkdedIn"));
						courseProvider.setInstagramURL(helper.getParameter("instagram"));
						courseProvider.setViberNumber(helper.getParameter("viber"));
						courseProvider.setWhatsappNumber(helper.getParameter("whatsapp"));
						courseProvider.setAddress1(helper.getParameter("address1"));
						courseProvider.setAddress2(helper.getParameter("address2"));
						courseProvider.setAddress3(helper.getParameter("address3"));
						courseProvider.setGeneralEmail(helper.getParameter("generalEmail"));
						courseProvider.setAdminAllowed(true);
						courseProvider.setCourseProviderStatus(providerStatus);
						courseProvider.setCrtBy("admin");// to be update after
															// the session is
															// created

						// set course provider town details
						courseProviderTown.setActive(true);
						courseProviderTown.setTown(Long.parseLong(selectedTown));
						courseProviderTown.setAddress1(helper.getParameter("address1"));
						courseProviderTown.setAddress2(helper.getParameter("address2"));
						courseProviderTown.setAddress3(helper.getParameter("address3"));
						String phoneNumber = courseProvider.getLandPhoneCountryCode()
								+ courseProvider.getLandPhoneAreaCode()
								+ courseProvider.getLandPhoneNo();
						courseProviderTown.setContactNumber(phoneNumber);
						courseProviderTown.setCrtBy("admin");

						Map map = new HashMap();
						map.put("provider", courseProvider);
						map.put("town", courseProviderTown);

						String providerType = helper.getParameter("courseProvider");

						/**
						 * selects the account type of the course provider and
						 * calls different DAO classes depending on the
						 * course provider type
						 */
						if (providerType.equals(AccountType.FEATURED_COURSE_PROVIDER.name())) {
							courseProvider.setAccountType(AccountType.FEATURED_COURSE_PROVIDER
											.getTypeValue());
							courseProvider.setTutorRelated(false);

							String accountStatus = helper
									.getParameter("accountStatus");
							
							//compare account status with enum class values
							if ( Integer.parseInt(accountStatus)  == ApplicationStatus.ACTIVE.getStatusValue()) {
								courseProviderAccount.setActive(true);
							}
							if (Integer.parseInt(accountStatus)  == ApplicationStatus.INACTIVE.getStatusValue()) {
								courseProviderAccount.setActive(false);
							}
							String contactNumber = countryCode + helper.getParameter("providerContactNumber");
							courseProviderAccount.setName(helper.getParameter("providerPrivateName"));
							courseProviderAccount.setPassword(helper.getParameter("providerPassword"));
							courseProviderAccount.setDescription(helper.getParameter("accountDescription"));
							courseProviderAccount.setContactNumber(contactNumber);
							// to be update after the session is created
							courseProviderAccount.setCrtBy("admin");

							map.put("account", courseProviderAccount);
							generatedKey = courseProviderDAO.add(map);

						} else if (providerType.equals(AccountType.ONE_OFF_COURSE_PROVIDER.name())) {
							courseProvider.setTutorRelated(false);
							courseProvider.setAdminAllowed(false);

							ICrud oneOffCourseProviderDAO = new OneOffCourseProviderDAO();
							courseProvider.setAccountType(AccountType.ONE_OFF_COURSE_PROVIDER.getTypeValue());
							generatedKey = oneOffCourseProviderDAO.add(map);
						}

						if (generatedKey > 0) {/* register success */
							systemMessage = SystemMessage.ADDED.message();
							helper.setAttribute("registerId", generatedKey);
						} else if (generatedKey == 0) {/* register failed */
							systemMessage = SystemMessage.NOTADDED.message();
						}
					}
				}
			} else {

				systemMessage = SystemMessage.MANDATORYFIELDREQUIRED.message();

			}

		} catch (Exception exception) {
			log.error("execute() : " + exception.toString());
			systemMessage = SystemMessage.ERROR.message();

			throw exception;
		} finally {
			helper.setAttribute("registerId", generatedKey);
			if (systemMessage != null) {
				helper.setAttribute("userMessage", systemMessage);
			}
		}

		return view;
	}

	
	
	/**
	 * username validation method used to validate the username and the course provider contact email 
	 * address
	 * @param helper
	 * @param courseProviderAccount
	 * @return true if valid, else return false
	 */
	public static boolean checkUsername(IDataHelper helper, CourseProviderAccount courseProviderAccount, String systemMessage)
	throws SQLException, Exception {
		// validate username
		ICrud usernameDAO = new CourseProviderUsernameDAO();
		Collection<Collection<String>> usernameCollection = new ArrayList<Collection<String>>();
		boolean isValid = true;

		/**
		 * checks the username availability and email. 
		 */
		usernameCollection = usernameDAO
				.findById(courseProviderAccount);
		if (usernameCollection.size() > 0) {
			
			isValid = false;
			String accountUsername = null;
			String accountEmail = null;

			if (usernameCollection.size() == 1) {

				for (Collection<String> accountCollection : usernameCollection) {
					Object[] accountData = accountCollection.toArray();
					accountUsername = (String) accountData[2];
					accountEmail = (String) accountData[4];
				}

				if((courseProviderAccount.getUsername().equalsIgnoreCase(accountUsername)) &&
						courseProviderAccount.getEmail().equalsIgnoreCase(accountEmail)){
					systemMessage = SystemMessage.USERNAME_INVALID
							.message()
							+ " and "
							+ SystemMessage.EMAIL_EXIST.message();
					helper.setAttribute("errorUsername",
							SystemMessage.USERNAME_INVALID.message());
					helper.setAttribute("errorPrivateEmail",
							SystemMessage.EMAIL_EXIST.message());
				}else if (courseProviderAccount.getUsername().equalsIgnoreCase(accountUsername)) {

					systemMessage = SystemMessage.USERNAME_INVALID.message();
					helper.setAttribute("errorUsername",
							SystemMessage.USERNAME_INVALID.message());
				} else if (courseProviderAccount.getEmail().equalsIgnoreCase(accountEmail)) {
					systemMessage = SystemMessage.EMAIL_EXIST.message();
					helper.setAttribute("errorPrivateEmail",
							SystemMessage.EMAIL_EXIST.message());
				}
		
			} else {
				systemMessage = SystemMessage.USERNAME_INVALID
						.message()
						+ " and "
						+ SystemMessage.EMAIL_EXIST.message();
				helper.setAttribute("errorUsername",
						SystemMessage.USERNAME_INVALID.message());
				helper.setAttribute("errorPrivateEmail",
						SystemMessage.EMAIL_EXIST.message());
			}
		
	}
		helper.setAttribute("userMessage", systemMessage);
		return isValid;
}
	
	/**
	 * checkPrefix() method validate the course provider prefix. If the prefix is not available it 
	 * will give an error message
	 * @param helper
	 * @param courseProvider
	 * @param systemMessage
	 * @return true if valid; else false
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean checkPrefix(IDataHelper helper, CourseProvider courseProvider, String systemMessage)
			throws SQLException, Exception{
		
		boolean isValid = true;
		ICrud prefixDAO = new CourseProviderPrefixDAO();
		Collection<Collection<String>> prefixCollection = prefixDAO
				.findById(courseProvider);
		
		if (prefixCollection.size() != 0) {
			helper.setAttribute("userMessage",
					SystemMessage.PREFIX_INVALID.message());
			isValid = false;

		}
		
		
		return isValid;
	}
	
	/**
	 * 		
	 * Course provider expiration date is related and will depend on their payments. 
	 * Until the payment module is implemented an expiration date from the 
	 * systemConfig enum will be used as the expiration date of the course provider.
	 * By using a one expiration date until the actual payment module implementation 
	 * will help to retrieve all previous records						 
	 * @return sql expiration date
	 * @author JH
	 */
	public static java.sql.Date getExpirationDate(){
		
		java.sql.Date sql = null;

		String expireDate = SystemConfig.COURSE_PROVIDER_EXPIRATION_DATE.getValue1();
		sql = java.sql.Date.valueOf(expireDate);
		
		return sql;
	}
}
