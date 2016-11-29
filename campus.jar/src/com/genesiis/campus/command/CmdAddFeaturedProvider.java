package com.genesiis.campus.command;

//20161122 JH c39-add-course-provider CmdAddFeaturedProvider.java command class created
//20161122 JH c39-add-course-provider implemented ICommand class
//20161123 JH c39-add-course-provider execute method coding : retrieve request parameters
//20161129 JH c39-add-course-provider code refactor due to course provider DAO class rename, handle user messages

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
			Date date = new Date();
			log.info(">>>>>>>>>>>" + date.toString());
			String land1 = helper.getParameter("land1");
			String land2 = helper.getParameter("land2");
			String mobile = helper.getParameter("mobile");
			String fax = helper.getParameter("fax");
			String country = helper.getParameter("country");
			String town = helper.getParameter("town");
			String expireDate = helper.getParameter("expirationDate");
			String provider = helper.getParameter("featured-oneoff");

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
			date = sdf1.parse(expireDate);
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());  
			
			courseProvider.setShortName(helper.getParameter("shortName"));
			courseProvider.setName(helper.getParameter("providerName"));
			courseProvider.setDescription(helper.getParameter("aboutMe"));
			courseProvider.setGeneralEmail(helper.getParameter("generalEmail"));
			courseProvider.setCourseInquiryEmail(helper.getParameter("inquiryMail"));
//			courseProvider.setLandPhoneCountryCode(helper.getParameter(""));
//			courseProvider.setLandPhoneAreaCode(helper.getParameter(""));
//			courseProvider.setLandPhoneNo(helper.getParameter(""));
//			courseProvider.setLandPhpneNo2(helper.getParameter(""));
//			courseProvider.setFaxNo(helper.getParameter(""));
//			courseProvider.setMobilePhoneCountryCode(helper.getParameter(""));
//			courseProvider.setMobilePhoneNetworkCode(helper.getParameter(""));
//			courseProvider.setMobilePhoneNumber(helper.getParameter(""));
//			courseProvider.setHeaderImagePath(helper.getParameter(""));
//			courseProvider.setLogoImagePath(helper.getParameter(""));
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
			courseProvider.setExpirationDate(sqlStartDate);
//			courseProvider.setTutorRelated(helper.getParameter(""));
//			courseProvider.setCourseProviderType(Integer.parseInt(helper.getParameter("providerType")));
//			courseProvider.setCourseProviderStatus(Integer.parseInt(helper.getParameter("providerStatus")));
			courseProviderAccount.setName(helper.getParameter("providerName"));
			courseProviderAccount.setEmail(helper.getParameter("providerEmail"));
			courseProviderAccount.setUsername(helper.getParameter("providerUsername"));
			courseProviderAccount.setPassword(helper.getParameter("providerPassword"));
			courseProviderAccount.setName(helper.getParameter("accountDescription"));		
			
			HashMap map = new HashMap();
			map.put("provider", courseProvider);
			map.put("account", courseProviderAccount);

			int status = CourseProviderDAO.add(map);

			if (status == 1) {
				systemMessage = SystemMessage.ADDED.message();
			} else if (status == 0) {
				systemMessage = SystemMessage.NOTADDED.message();
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
