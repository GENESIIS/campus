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

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
	static org.apache.log4j.Logger log = Logger.getLogger(CmdAddFeaturedProvider.class.getName());
	
	private IView courseProviderData;

	public CmdAddFeaturedProvider(IView courseProviderData) {
		this.courseProviderData = courseProviderData;
	}

	public CmdAddFeaturedProvider() {

	}
	
	/**
	 * execute method used to handle the request related to featured course provider 
	 * registration. 
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

//			if (userTypeList.size() > 0) {
//				Iterator collectionIterator = userTypeList.iterator();
//				while (collectionIterator.hasNext()) {
//					ArrayList<String> singleList = (ArrayList<String>) collectionIterator
//							.next();
//					Iterator listIterator = singleList.iterator();
//					while(listIterator.hasNext()){
//						userType = listIterator.next();
//					}
//				}
//			}
//	
			//back end validation for required fields
			ArrayList<String> errorMessages = validateCourseProvider(helper);
			if(errorMessages.size()==0){
				log.info("no errors");
							
				String expireDate = helper.getParameter("expirationDate");
				String countryCode = helper.getParameter("selectedCountry");
				String selectedTown = helper.getParameter("selectedTown");
				String courseProviderType = helper.getParameter("providerType");
				
				/**
				 * checks for head office code. if no head office code is provided 
				 * assign null
				 */
				if(validator.isEmpty(helper.getParameter("headOffice"))){
					courseProvider.setHeadOffice(0);
				}
						
				String providerStatus = helper.getParameter("providerStatus");
				if(providerStatus.equalsIgnoreCase("active")){
					pStatus = ApplicationStatus.ACTIVE.getStatusValue();
				}
				if(providerStatus.equalsIgnoreCase("inactive")){
					pStatus = ApplicationStatus.INACTIVE.getStatusValue();
				}
				if(providerStatus.equalsIgnoreCase("pending") ){
					pStatus = ApplicationStatus.PENDING.getStatusValue();
				}
				
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
				courseProvider.setCourseProviderStatus(pStatus);
				courseProvider.setCrtBy("admin");//to be update after the session is created
				courseProvider.setModBy("admin");//to be update after the session is created

				//set course provider town details
				courseProviderTown.setActive(true);
				courseProviderTown.setTown(Integer.parseInt(selectedTown));
				courseProviderTown.setCrtBy("admin");
				courseProviderTown.setModBy("admin");
				
				Map map = new HashMap();
				map.put("provider", courseProvider);
				map.put("town" , courseProviderTown);
				
				String providerType = helper.getParameter("featured-oneoff");
				
				/**
				 * select the account type of the course provider. 
				 * and will call different DAO classes depending on the course provider
				 * type
				 */
				if(providerType.equalsIgnoreCase("featured")){
					courseProvider.setAccountType(AccountType.FEATURED_COURSE_PROVIDER.getTypeValue());
					courseProvider.setTutorRelated(false);
								
					String accountStatus = helper.getParameter("accountStatus");
					if(providerStatus.equalsIgnoreCase("active")){	
						courseProviderAccount.setActive(true);
					}
					if(providerStatus.equalsIgnoreCase("inactive")){	
						courseProviderAccount.setActive(false);
					}

					courseProviderAccount.setName(helper.getParameter("providerName"));
					courseProviderAccount.setEmail(helper.getParameter("providerEmail"));
					courseProviderAccount.setUsername(helper.getParameter("providerUsername"));
					courseProviderAccount.setPassword(helper.getParameter("providerPassword"));
					courseProviderAccount.setName(helper.getParameter("accountDescription"));
					courseProviderAccount.setUserType(5);
					courseProviderAccount.setCrtBy("admin");//to be update after the session is created
					courseProviderAccount.setModBy("admin");//to be update after the session is created
					
					ICrud courseProviderDAO = new FeaturedCourseProviderDAO();
					map.put("account", courseProviderAccount);
					generatedKey = courseProviderDAO.add(map);
					
				}else if(providerType.equalsIgnoreCase("one-off")){
					courseProvider.setTutorRelated(false);
					courseProvider.setAdminAllowed(false);
					
					ICrud oneOffCourseProviderDAO = new OneOffCourseProviderDAO();
					courseProvider.setAccountType(AccountType.ONE_OFF_COURSE_PROVIDER.getTypeValue());
					generatedKey = oneOffCourseProviderDAO.add(courseProvider);
				}
				


				if (generatedKey >0) {
					systemMessage = SystemMessage.ADDED.message();
				} else if (generatedKey ==0) {
					systemMessage = SystemMessage.NOTADDED.message();
				}

				helper.setAttribute("registerId", generatedKey);
				
			}else{
				log.info("errors");
				systemMessage = errorMessages.toString()+ SystemMessage.EMPTY_FIELD.message();
	
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
	
	
	public ArrayList<String> validateCourseProvider(IDataHelper helper){
		Validator validator = new Validator();
	//	boolean isValid = true;
		ArrayList<String> errorString = new ArrayList<String>();

		if(validator.isEmpty(helper.getParameter("featured-oneoff"))){
			errorString.add("Featured or One-off selection ");
		}if(validator.isEmpty(helper.getParameter("providerName"))){
			errorString.add("Provider Name ");
		}if(validator.isEmpty(helper.getParameter("shortName"))){
			errorString.add("Short Name ");
		}if(validator.isEmpty(helper.getParameter("uniquePrefix"))){
			errorString.add("Unique Prefix ");
		}if(validator.isEmpty(helper.getParameter("aboutMe"))){
			errorString.add("About Me ");
		}if(validator.isEmpty(helper.getParameter("specialFeatures"))){
			errorString.add("SpecialFeatures ");
		}if(validator.isEmpty(helper.getParameter("aboutMe"))){
			errorString.add("About Me ");
		}if(validator.isEmpty(helper.getParameter("inquiryMail"))){
			errorString.add("Inquiry Mail ");
		}if(validator.isEmpty(helper.getParameter("generalEmail"))){
			errorString.add("General Email ");
		}if(validator.isEmpty(helper.getParameter("expirationDate"))){
			errorString.add("Expiration Date ");
		}if(validator.isEmpty(helper.getParameter("selectedCountry"))){
			errorString.add("Country ");
		}if(validator.isEmpty(helper.getParameter("selectedTown"))){
			errorString.add("Town ");
		}if(validator.isEmpty(helper.getParameter("providerStatus"))){
			errorString.add("Course Provider status ");
		}if(validator.isEmpty(helper.getParameter("areaCode"))){
			errorString.add("Area Code ");
		}if(validator.isEmpty(helper.getParameter("land1"))){
			errorString.add("Land number 1 ");
		}if(validator.isEmpty(helper.getParameter("networkCode"))){
			errorString.add("Network code ");
		}if(validator.isEmpty(helper.getParameter("networkCode"))){
			errorString.add("Network code ");
		}if(validator.isEmpty(helper.getParameter("networkCode"))){
			errorString.add("Network code ");
		}if(validator.isEmpty(helper.getParameter("networkCode"))){
			errorString.add("Network code ");
		}if(validator.isEmpty(helper.getParameter("networkCode"))){
			errorString.add("Network code ");
		}if(validator.isEmpty(helper.getParameter("networkCode"))){
			errorString.add("Network code ");
		}
		
		return errorString;
	}

}
