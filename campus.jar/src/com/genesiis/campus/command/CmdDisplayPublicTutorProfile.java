package com.genesiis.campus.command;

//20170424 JH c135-public-display-tutor-profile INIT CmdDisplayPublicTutorProfile.java

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.PublicTutorDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

public class CmdDisplayPublicTutorProfile implements ICommand{
	
	static Logger log = Logger.getLogger(CmdDisplayPublicTutorProfile.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		final ICrud publicTutorDAO = new PublicTutorDAO();
		SystemMessage message = SystemMessage.UNKNOWN;
		
		try{
			if(Validator.validNumber(helper.getParameter("tutorCode"))){
				Collection<Collection<String>> tutorCollection = publicTutorDAO.findById(helper.getParameter("tutorCode"));
				
				
				if(tutorCollection.size() > 0){
				}
			}

		}catch (SQLException sqlException) {
			log.error("execute() SQLException : " + sqlException.toString());
			throw sqlException;
		} catch (Exception exception){
			log.error("execute() Exception : "+ exception.toString());
			throw exception;
		}finally{
			final String tutorImagePath = SystemConfig.TUTOR_PROFILE_IMAGE_PATH.getValue1();
			helper.setAttribute("userMessage", message.message());
			helper.setAttribute("tutorProfileImagePath", tutorImagePath);
		}
		
		
		return null;
	}

}
