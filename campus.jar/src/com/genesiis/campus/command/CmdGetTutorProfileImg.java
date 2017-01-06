package com.genesiis.campus.command;
//20170103 DN c47-tutor-add-tutor-information-upload-image-dn create the CmdGetTutorProfileImg.java class
//20170105 DN c47-tutor-add-tutor-information-upload-image-dn introduced getExistingImageFilePathFromTheStorage()
//getProfilePictureIfExisitElseDefault()
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.ImageUtility;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;


/**
 * CmdGetTutorProfileImg handles the profile image which is currently
 * within the system When 
 * @author dushantha DN 
 *
 */
public class CmdGetTutorProfileImg implements ICommand {

	static final Logger log = Logger.getLogger(CmdGetTutorProfileImg.class.getName());
	private int successCode=0; // in future this can be used to deviate the error message
								// hence kept the variable at field level scope.
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		try{
			return getExistingImageFilePathFromTheStorage(helper,view);			
			
		} catch (SQLException sqle) {
			log.error("execute():SQLExceptiion :"+sqle.toString());
			throw sqle;
		} catch (Exception exe) {
			log.error("execute():Exception :"+exe.toString());
			throw exe;
		}
	}
	
	/**
	 * Method handles the providing of the image path and setting relevant helper attributes
	 * @param helper
	 * @param view
	 * @return IView
	 * @throws Exception
	 */
	
	private IView getExistingImageFilePathFromTheStorage(IDataHelper helper, IView view) throws Exception{
		
		//###########################################################################################
		// TUTOR CODE-GENDER HAS TO BE OBTAINED FROM SESSION - This section of code vulnerable to
		// change once the session parameters are set.	
		//###########################################################################################
		
		Integer tutorCodeFromSession = (Integer) helper.getSession(false).getAttribute("userid");
		int tutorCode = (tutorCodeFromSession!=null)?tutorCodeFromSession:1;
		String gender = (String) helper.getSession(false).getAttribute("gender");
		gender = (gender!=null||gender=="")?gender:"M";
		try{
			String profilePicture= getProfilePictureIfExisitElseDefault(tutorCode,gender);
			setSuccessCode(1); // there is no error
			helper.setAttribute("profilePicture", profilePicture);
			helper.setAttribute("message", "");
			helper.setAttribute("successCode", getSuccessCode());
			
		}catch (Exception exp){
			log.error("getExistingImageFilePathFromTheStorage(): Exception :"+exp.toString());
			throw exp;
		}
		return view;
	}

	/*
	 * method provides the image path. If the image exists ,then that path will be provided relative to war folder.
	 * If there is no profile picture found the default image path will be supplied based on
	 * the gender.
	 * 
	 * @author dushantha DN
	 * @param tutorCode Tutor unique identification number of int
	 * 
	 * @param gender this must be single letter selected from the domain {"M","F"} and 
	 * the selected value should be either "M" or "F" only. Other gender type such as  neuter
	 * has not been considered. If there is any situation where the neuter has to be consider, use the 
	 * female typ: "F".
	 * 
	 * @return String the image path that starts from the war folder  e.g.education/tutor/pro_image/username_1/imagename.jpg
	 * @throws Exception
	 */
	
	private String getProfilePictureIfExisitElseDefault(int tutorCode,String gender) throws Exception{
		try{
			String tutorWarFileImagePath = ImageUtility
					.getImageUploadPath(SystemConfig.TUTOR_PROFILE_IMAGE_PATH)+"/username_"+tutorCode;
			String tutorAbsoluteImagePath = ImageUtility
					.getImageUploadPath(SystemConfig.TUTOR_PROFILE_IMAGE_ABSOLUTE_PATH)+"/username_"+tutorCode+"/";
			String tutorWarDefaultFileImagePath = ImageUtility
					.getImageUploadPath(SystemConfig.TUTOR_DEFAULT_PROFILE_IMAGE_WAR_PATH);
			String tutorAbsoluteDefaultImagePath =ImageUtility
					.getImageUploadPath(SystemConfig.TUTOR_DEFAULT_PROFILE_IMAGE_ABSOLUTE_PATH);
			
			FileUtility utility = new FileUtility();
			String pictureName = "";
			if(utility.isFileExists(tutorAbsoluteImagePath, Integer.toString(tutorCode))){
				pictureName = tutorWarFileImagePath+"/"+utility.getImageName();
			}else{
				if(gender.equalsIgnoreCase("m")){
					pictureName=tutorWarDefaultFileImagePath+"/male_tutor.jpg";
				}else{
					pictureName=tutorWarDefaultFileImagePath+"/female_tutor.jpg";
				}
			}
			return pictureName;
		}catch (Exception exp){
			log.error("getProfilePictureIfExisitElseDefault(): Exception :"+exp.toString());
			throw exp;
		}
		
	}
	
	// getters and setters of the class
	
	public int getSuccessCode() {
		return successCode;
	}


	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}


	
	
	

}
