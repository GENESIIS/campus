package com.genesiis.campus.command;
//create the initial CmdUploadTutorImage.java with help of CmdUploadProfileImg.java from CAM:27
import java.sql.SQLException;
import java.util.ArrayList;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.ImageUtility;
import com.genesiis.campus.validation.SystemConfig;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.commons.fileupload.FileItem;

import org.apache.log4j.Logger;


/**
 * CmdUploadTutorImage is responsible for uploading the image that 
 * is been uploaded from the client side to the location define by the SystemConfig enum class
 * @author dushantha DN
 *
 */
public class CmdUploadTutorImage implements ICommand {
	static final Logger log = Logger.getLogger(CmdUploadTutorImage.class.getName());
	private int successCode=0;
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		// Variable declaration.
		JsonArray list = new JsonArray();
		Gson gson = new Gson();
		FileUtility fileUtility = new FileUtility();
		ArrayList<FileItem> files = new ArrayList<FileItem>();
		
		
		try{
			Integer tutorCodeFromSession = (Integer) helper.getSession(false).getAttribute("userid");
			int tutorCode = (tutorCodeFromSession!=null)?tutorCodeFromSession:1; //TUTOR CODE HAS TO BE OBTAINED FROM SESSION
			String tutorProfileImageUploadPath = new ImageUtility().getImageUploadPath(SystemConfig.TUTOR_PROFILE_IMAGE_PATH);
			fileUtility.setUploadPath(tutorProfileImageUploadPath + "/" + "username_"+ Integer.toString(tutorCode));
			
		} catch(Exception exp) {
			log.error("execute(): Exception :"+ exp.toString());
			throw exp;
		}
		
		return view;
	}

	
	
	
	
	
}


