
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

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.genesiis.campus.entity.CourseProviderPrefixDAO;
import com.genesiis.campus.entity.CourseProviderUsernameDAO;
import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.OneOffCourseProviderDAO;
import com.genesiis.campus.entity.UserTypeDAO;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.entity.model.CourseProviderTown;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.UserType;
import com.genesiis.campus.validation.Validator;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdAddFeaturedProvider implements ICommand{
	static Logger log = Logger.getLogger(CmdAddFeaturedProvider.class.getName());
	
	private IView courseProviderData;

	public CmdAddFeaturedProvider(IView courseProviderData) {
		this.courseProviderData = courseProviderData;
	}

	public CmdAddFeaturedProvider() {

	}
	
	/**
	 * execute method used to handle the request related to course provider 
	 * registration. 
	 * Details are validated and then username, email and prefix are validated for 
	 * for existence. Depending on the course provider type it will call between 
	 * featured course provider and one off course provider DAO classes.
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
		ICrud userTypeDAO = new UserTypeDAO();
		int userType = 0;
		Validator validator = new Validator();

		String systemMessage = null;

		try {

			int pStatus = 0;
			int generatedKey = 0;
			String userTypeString = UserType.FEATURED_COURSE_PROVIDER.getUserType();
			ArrayList<Collection<String>> userTypeList  = (ArrayList<Collection<String>>) userTypeDAO.findById(userTypeString);
			
			for (Collection<String> collection : userTypeList) {
				Object[] userTypeData = collection.toArray();
				String userTypeCode  = (String) userTypeData[0];
				userType = Integer.parseInt(userTypeCode);
			}
			//back end validation for required fields

			ArrayList<String> errorMessages = Validator.validateCourseProvider(helper);
			
			if(errorMessages.size()==0){
					
					//validate username
					ICrud usernameDAO = new CourseProviderUsernameDAO();
					String username = helper.getParameter("providerUsername");
					courseProviderAccount.setUsername(username);
					Collection<Collection<String>> usernameCollection = new ArrayList<Collection<String>>();

					//checks for the username
					usernameCollection = usernameDAO
							.findById(courseProviderAccount);
					if (usernameCollection.size() > 0) {
						systemMessage = SystemMessage.USERNAME_INVALID.message();
						helper.setAttribute("errorUsername", SystemMessage.USERNAME_INVALID.message());
			
					}else{
						
						//checks for the email address
						courseProviderAccount.setUsername("");
						courseProviderAccount.setEmail(helper.getParameter("providerEmail"));
					
						Collection<Collection<String>>	emailCollection = usernameDAO
								.findById(courseProviderAccount);
						if (emailCollection.size() > 0){
							systemMessage = SystemMessage.EMAIL_EXIST.message();
							helper.setAttribute("errorPrivateEmail", SystemMessage.EMAIL_EXIST.message());
						}else{
							
							courseProviderAccount.setUsername(username);
							
							//valdate prefix							
							String prefix = helper.getParameter("uniquePrefix");
			
								ICrud prefixDAO = new CourseProviderPrefixDAO();
								courseProvider.setUniquePrefix(prefix);
								Collection<Collection<String>> prefixCollection = prefixDAO
										.findById(courseProvider);
								if (prefixCollection.size() != 0) {
									helper.setAttribute("userMessage",SystemMessage.PREFIX_INVALID.message() );

								} else if (prefixCollection.size() == 0) {
									//helper.setAttribute("userMessage", SystemMessage.PREFIX_VALID.message());
																
						String expireDate = helper.getParameter("expirationDate");
						String countryCode = helper.getParameter("selectedCountry");
						String selectedTown = helper.getParameter("selectedTown");
						String courseProviderType = helper.getParameter("selectedProviderType");
						
						/**
						 * checks for head office code. if no head office code is provided 
						 * assign null
						 */
						if(validator.isEmptyString(helper.getParameter("headOffice"))){
							courseProvider.setHeadOffice(0);
						}else{
							courseProvider.setHeadOffice(Integer.parseInt("headOffice"));
						}
								
						int providerStatus = Integer.parseInt(helper.getParameter("providerStatus"));
						
						SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
						Date parsed = format.parse(expireDate);
						java.sql.Date sql = new java.sql.Date(parsed.getTime());
				        
						//set basic data
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
						courseProvider.setExpirationDate(sql);
						courseProvider.setMobilePhoneCountryCode(countryCode);
						//courseProvider.setHeadOffice(null);
						
						courseProvider.setCourseProviderType(Integer.parseInt(courseProviderType));
						courseProvider.setLandPhpneNo2(helper.getParameter("land2"));
						courseProvider.setFaxNo(helper.getParameter("fax"));
						courseProvider.setSpeciality(helper.getParameter("specialFeatures"));
						courseProvider.setExpirationDate(sql);
						courseProvider.setWeblink(helper.getParameter("webLink"));
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
						courseProvider.setCrtBy("admin");//to be update after the session is created
						courseProvider.setModBy("admin");//to be update after the session is created

						//set course provider town details
						courseProviderTown.setActive(true);
						courseProviderTown.setTown(Long.parseLong(selectedTown));
						courseProviderTown.setAddress1(helper.getParameter("address1"));
						courseProviderTown.setAddress2(helper.getParameter("address2"));
						courseProviderTown.setAddress3(helper.getParameter("address3"));
						
						courseProviderTown.setCrtBy("admin");
						courseProviderTown.setModBy("admin");
						
						Map map = new HashMap();
						map.put("provider", courseProvider);
						map.put("town" , courseProviderTown);
						
						int providerType = Integer.parseInt(helper.getParameter("courseProvider"));
						
						/**
						 * select the account type of the course provider. 
						 * and will call different DAO classes depending on the course provider
						 * type
						 */
						if(providerType == AccountType.FEATURED_COURSE_PROVIDER.getTypeValue()){
							courseProvider.setAccountType(AccountType.FEATURED_COURSE_PROVIDER.getTypeValue());
							courseProvider.setTutorRelated(false);
										
							String accountStatus = helper.getParameter("accountStatus");
							if(accountStatus.equalsIgnoreCase("1")){	
								courseProviderAccount.setActive(true);
							}
							if(accountStatus.equalsIgnoreCase("0")){	
								courseProviderAccount.setActive(false);
							}

							courseProviderAccount.setName(helper.getParameter("providerPrivateName"));
						//	courseProviderAccount.setUsername(helper.getParameter("providerUsername"));
							courseProviderAccount.setPassword(helper.getParameter("providerPassword"));
							courseProviderAccount.setName(helper.getParameter("accountDescription"));
							courseProviderAccount.setUserType(userType);
							courseProviderAccount.setCrtBy("admin");//to be update after the session is created
							courseProviderAccount.setModBy("admin");//to be update after the session is created
							
							ICrud courseProviderDAO = new FeaturedCourseProviderDAO();
							map.put("account", courseProviderAccount);
							generatedKey = courseProviderDAO.add(map);
							
						}else if(providerType == AccountType.ONE_OFF_COURSE_PROVIDER.getTypeValue()){
							courseProvider.setTutorRelated(false);
							courseProvider.setAdminAllowed(false);
							
							ICrud oneOffCourseProviderDAO = new OneOffCourseProviderDAO();
							courseProvider.setAccountType(AccountType.ONE_OFF_COURSE_PROVIDER.getTypeValue());
							generatedKey = oneOffCourseProviderDAO.add(map);
						}
						


						if (generatedKey >0) {
							systemMessage = SystemMessage.ADDED.message();
							helper.setAttribute("registerId", generatedKey);
						} else if (generatedKey ==0) {
							systemMessage = SystemMessage.NOTADDED.message();
						}

							helper.setAttribute("registerId", generatedKey);
								}
						

						}
						
					}
			}else{

				systemMessage = SystemMessage.MANDATORYFIELDREQUIRED.message();
	
			}
			
			
		} catch (Exception exception) {
			log.error("execute() : " + exception.toString());
			systemMessage = SystemMessage.ERROR.message();

			throw exception;
		} finally {
			if (systemMessage != null) {
				helper.setAttribute("userMessage", systemMessage);
			}
		}
		
		
		return view;
	}
	
}
