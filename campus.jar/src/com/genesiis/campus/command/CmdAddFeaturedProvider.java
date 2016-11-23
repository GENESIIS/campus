package com.genesiis.campus.command;

//20161122 JH c39-add-course-provider CmdAddFeaturedProvider.java command class created
//20161122 JH c39-add-course-provider implemented ICommand class
//20161123 JH c39-add-course-provider execute method coding 

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.validation.SystemMessage;
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

		ICrud CourseProviderDAO = new CourseProviderDAO();
	//	Collection<Collection<String>> categoryCollection = null;
		
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		
		final CourseProvider courseProvider = new CourseProvider();
		
		try{
		//	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			log.info(">>>>>>>>>>>" +date.toString());
	

			courseProvider.setShortName(helper.getParameter(""));
			courseProvider.setName(helper.getParameter(""));
			courseProvider.setDescription(helper.getParameter(""));
			courseProvider.setGeneralEmail(helper.getParameter(""));
			courseProvider.setCourseInquiryEmail(helper.getParameter(""));
			courseProvider.setLandPhoneCountryCode(helper.getParameter(""));
			courseProvider.setLandPhoneAreaCode(helper.getParameter(""));
			courseProvider.setLandPhoneNo(helper.getParameter(""));
			courseProvider.setLandPhpneNo2(helper.getParameter(""));
			courseProvider.setFaxNo(helper.getParameter(""));
			courseProvider.setMobilePhoneCountryCode(helper.getParameter(""));
			courseProvider.setMobilePhoneNetworkCode(helper.getParameter(""));
			courseProvider.setMobilePhoneNumber(helper.getParameter(""));
			courseProvider.setHeaderImagePath(helper.getParameter(""));
			courseProvider.setLogoImagePath(helper.getParameter(""));
			courseProvider.setSpeciality(helper.getParameter(""));
			courseProvider.setWeblink(helper.getParameter(""));
			courseProvider.setFacebookURL(helper.getParameter(""));
			courseProvider.setTwitterURL(helper.getParameter(""));
			courseProvider.setMyspaceURL(helper.getParameter(""));
			courseProvider.setLinkedinURL(helper.getParameter(""));
			courseProvider.setInstagramURL(helper.getParameter(""));
			courseProvider.setViberNumber(helper.getParameter(""));
			courseProvider.setWhatsappNumber(helper.getParameter(""));
			courseProvider.setAddress1(helper.getParameter(""));
			courseProvider.setAddress2(helper.getParameter(""));
			courseProvider.setAddress3(helper.getParameter(""));
			//courseProvider.setExpirationDate(helper.getParameter(""));
		//	courseProvider.setTutorRelated(helper.getParameter(""));
			courseProvider.setName(helper.getParameter(""));
			courseProvider.setName(helper.getParameter(""));
			courseProvider.setName(helper.getParameter(""));
			courseProvider.setName(helper.getParameter(""));
			courseProvider.setName(helper.getParameter(""));
			
			
			int status = CourseProviderDAO.add(courseProvider);
			
			
			
		}catch(Exception exception){
			log.error("execute() : " + exception.toString());
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}
		
		
		return null;
	}

}
