package com.genesiis.campus.command;

//20161129 JH c39-add-course-provider CmdAddOneOffProvider.java entity class created
//20161129 JH c39-add-course-provider execute method coding wip
//20161130 JH c39-add-course-provider execute method coding wip

import java.sql.SQLException;
import java.util.Date;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.OneOffCourseProviderDAO;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

public class CmdAddOneOffProvider implements ICommand {

	static Logger log = Logger.getLogger(CmdAddOneOffProvider.class.getName());

	private IView courseProviderData;

	public CmdAddOneOffProvider(IView courseProviderData) {
		this.courseProviderData = courseProviderData;
	}

	public CmdAddOneOffProvider() {

	}

	/**
	 * execute method used to handle the request related to one-off course
	 * provider registration.
	 * 
	 * @author JH
	 * @param IDataHelper
	 * @param IView
	 * @return IView Objects
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		ICrud oneOffCourseProviderDAO = new OneOffCourseProviderDAO();

		final CourseProvider courseProvider = new CourseProvider();

		String systemMessage = null;

		try {
//			Date date = new Date();
//			log.info(">>>>>>>>>>>" + date.toString());
//
//			String town = helper.getParameter("town");
//			String expireDate = helper.getParameter("expirationDate");
//			String provider = helper.getParameter("featured-oneoff");
//
//			courseProvider.setShortName(helper.getParameter("shortName"));
//			courseProvider.setName(helper.getParameter("providerName"));
//			courseProvider.setDescription(helper.getParameter("aboutMe"));
//			courseProvider.setGeneralEmail(helper.getParameter("generalEmail"));
//			courseProvider.setCourseInquiryEmail(helper.getParameter("inquiryMail"));
//			courseProvider.setLandPhoneCountryCode(helper.getParameter("country"));
//			courseProvider.setLandPhoneAreaCode(helper.getParameter("areaCode"));
//			courseProvider.setLandPhoneNo(helper.getParameter("land1"));
//			courseProvider.setLandPhpneNo2(helper.getParameter("land2"));
//			courseProvider.setFaxNo(helper.getParameter("fax"));
//			courseProvider.setMobilePhoneCountryCode(helper.getParameter("country"));
//			courseProvider.setMobilePhoneNetworkCode(helper.getParameter("networkCode"));
//			courseProvider.setMobilePhoneNumber(helper.getParameter("mobile"));
//			courseProvider.setSpeciality(helper.getParameter("specialFeatures"));
//			courseProvider.setWeblink(helper.getParameter("webLink"));
//			courseProvider.setFacebookURL(helper.getParameter("facebook"));
//			courseProvider.setTwitterURL(helper.getParameter("twitter"));
//			courseProvider.setMyspaceURL(helper.getParameter("mySpace"));
//			courseProvider.setLinkedinURL(helper.getParameter("linkdedIn"));
//			courseProvider.setInstagramURL(helper.getParameter("instagram"));
//			courseProvider.setViberNumber(helper.getParameter("viber"));
//			courseProvider.setWhatsappNumber(helper.getParameter("whatsapp"));
//			courseProvider.setAddress1(helper.getParameter("address1"));
//			courseProvider.setAddress2(helper.getParameter("address2"));
//			courseProvider.setAddress3(helper.getParameter("address3"));
//			// courseProvider.setExpirationDate(helper.getParameter(""));
//			// courseProvider.setTutorRelated(helper.getParameter(""));
//			// courseProvider.setCourseProviderType(Integer.parseInt(helper.getParameter("providerType")));
//			// courseProvider.setCourseProviderStatus(Integer.parseInt(helper.getParameter("providerStatus")));
//
//
//			int status = oneOffCourseProviderDAO.add(courseProvider);
//
//			if (status == 1) {
//				systemMessage = SystemMessage.ADDED.message();
//			} else if (status == 0) {
//				systemMessage = SystemMessage.NOTADDED.message();
//			}
//			
			log.info("???????????? one off" );
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
