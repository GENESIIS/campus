package com.genesiis.campus.command;

//20161122 JH c39-add-course-provider CmdAddFeaturedProvider.java command class created
//20161122 JH c39-add-course-provider implemented ICommand class
//20161123 JH c39-add-course-provider execute method coding : retrieve request parameters
//20161129 JH c39-add-course-provider code refactor due to course provider DAO class rename, handle user messages
//20161201 JH c39-add-course-provider added application enum to get course provider status

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.ApplicationStatus;
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

		ICrud CourseProviderDAO = new FeaturedCourseProviderDAO();

		final CourseProvider courseProvider = new CourseProvider();
		final CourseProviderAccount courseProviderAccount = new CourseProviderAccount();

		String systemMessage = null;

		try {

			int pStatus = 0;
			String expireDate = helper.getParameter("expirationDate");
			String providerStatus = helper.getParameter("providerStatus");
			if(providerStatus =="active"){
				pStatus = ApplicationStatus.ACTIVE.getStatusValue();
			}
			if(providerStatus =="inactive"){
				pStatus = ApplicationStatus.INACTIVE.getStatusValue();
			}
			if(providerStatus =="pending"){
				pStatus = ApplicationStatus.PENDING.getStatusValue();
			}
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	        Date parsed = format.parse(expireDate);
	        java.sql.Date sql = new java.sql.Date(parsed.getTime());
	        
			courseProvider.setShortName(helper.getParameter("shortName"));
			courseProvider.setName(helper.getParameter("providerName"));
			courseProvider.setDescription(helper.getParameter("aboutMe"));
			courseProvider.setCourseInquiryEmail(helper.getParameter("inquiryMail"));
			courseProvider.setLandPhoneCountryCode(helper.getParameter("country"));
			courseProvider.setLandPhoneAreaCode(helper.getParameter("areaCode"));
			courseProvider.setLandPhoneNo(helper.getParameter("land1"));
			courseProvider.setMobilePhoneCountryCode(helper.getParameter("country"));
			courseProvider.setMobilePhoneNetworkCode(helper.getParameter("networkCode"));
			courseProvider.setMobilePhoneNumber(helper.getParameter("mobile"));
			courseProvider.setExpirationDate(sql);
			
			courseProvider.setLandPhpneNo2(helper.getParameter("land2"));
			courseProvider.setFaxNo(helper.getParameter("fax"));
			courseProvider.setSpeciality(helper.getParameter("specialFeatures"));
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
			courseProvider.setCourseProviderType(AccountType.FEATURED_COURSE_PROVIDER.getTypeValue());
			courseProvider.setTutorRelated(false);
			courseProvider.setAdminAllowed(true);
			courseProvider.setCourseProviderStatus(pStatus);
			courseProviderAccount.setName(helper.getParameter("providerName"));
			courseProviderAccount.setEmail(helper.getParameter("providerEmail"));
			courseProviderAccount.setUsername(helper.getParameter("providerUsername"));
			courseProviderAccount.setPassword(helper.getParameter("providerPassword"));
			courseProviderAccount.setName(helper.getParameter("accountDescription"));		
			
			HashMap map = new HashMap();
			map.put("provider", courseProvider);
			map.put("account", courseProviderAccount);

			int status = CourseProviderDAO.add(map);

			if (status >0) {
				systemMessage = SystemMessage.ADDED.message();
			} else if (status ==0) {
				systemMessage = SystemMessage.NOTADDED.message();
			}
			
			log.info("???????????? featured" );
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
