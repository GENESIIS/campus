package com.genesiis.campus.command;
//create the initial CmdUploadTutorImage.java with help of CmdUploadProfileImg.java from CAM:27
//20170109 DN c47-tutor-add-tutor-information-upload-image-dn added getTutorProfileImageUploadPath(), refactor execute() and add doc comments
//20170109 DN c47-tutor-add-tutor-information-upload-image-dn created isImageWithinSize(), asccessInerLoopSingleElement() created
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
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
		String fileUploadError = "";
		String fileUploadSuccess = "";
		Connection con = null;
		// To store image file path
		String filePath = "";
		// Valid file extensions to the user.
		String validExtensions[] = { "jpeg", "jpg", "png", "gif" };
		
		try{
			Integer tutorCodeFromSession = (Integer) helper.getSession(false).getAttribute("userid");
			int tutorCode = (tutorCodeFromSession!=null)?tutorCodeFromSession:1; //TUTOR CODE HAS TO BE OBTAINED FROM SESSION
			con = ConnectionManager.getConnection();
			//get the tutor image upload path
			String tutorProfileImageUploadPath =getTutorProfileImageUploadPath(SystemConfig.TUTOR_PROFILE_IMAGE_ABSOLUTE_PATH,tutorCode,con);
			//check if the image is within the allowed size
			// get the allowable image size
			
			//long uploadSizeLimit  = ImageUtility.getAlloawablePictureSize(SystemConfig.TUTOR_PROFILE_IMAGE_SIZE);
			
			
			
			
		} catch(Exception exp) {
			log.error("execute(): Exception :"+ exp.toString());
			throw exp;
		}finally{
			DaoHelper.cleanup(con, null, null);
		}
		
		return view;
	}

	/**
	 * getTutorProfileImageUploadPath provides the Physical location where the 
	 * image will be stored. If the table SYSTEMCONFIG doesn't have an entry 
	 * for the given "tutorImageProfilePath" in repository,then ,an empty string will be returned
	 * according to the agreed business logic path will be ending up with username_+ tutorcode
	 * e.g. C:/sdb/ctxdeploy/education.war/tutor/pro_image/username_1/
	 * @param tutorImageProfilePath SystemConfig type .
	 * Always this value must be in sync with the database [CAMPUS].[SYSTEMCONFIG]# column[SYSTEMCONFIGCODE].
	 * @param tutorCode :int code
	 * @return String if the database table contains a due value , then it will be returned,
	 *  else an empty string will be returned
	 *  
	 *  Client of the function must check for empty string. If find so which means the database table is out of sync with the
	 *  passed SystemConfig tutorImageProfilePath.Hence appropriately the condition should be handled.
	 */
	private String getTutorProfileImageUploadPath(SystemConfig tutorImageProfilePath,int tutorCode,Connection con)throws SQLException,
	Exception{
		
		String tutorDefaultImagePath="";
		String[] systemConfigCode = {tutorImageProfilePath.toString()};
		//access the data base system configuration data related table and retrieve the path
		ICrud systemConfigDAO = new SystemConfigDAO();
		try {

			Collection<Collection<String>> turoUploadImageCollection = systemConfigDAO
					.findById(systemConfigCode, con);
			// the collection should contains only one collection encapsulated
			// one record from the systemconfig table
			tutorDefaultImagePath = (String) asccessInerLoopSingleElement(turoUploadImageCollection);
			tutorDefaultImagePath=(tutorDefaultImagePath!=null)?tutorDefaultImagePath+ "/" + "username_" + Integer.toString(tutorCode) + "/":"";

			return tutorDefaultImagePath;

		} catch (SQLException exp) {
			log.error("getTutorProfileImageUploadPath(): SQLException"
					+ exp.toString());
			throw exp;

		} catch (Exception exp) {
			log.error("getTutorProfileImageUploadPath(): SQLException"
					+ exp.toString());
			throw exp;

		}
		
	}
	
	/**
	 * 
	 * 
	 * @param tutorProfilePictureSize
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private boolean isImageWithinSize(SystemConfig tutorProfilePictureSize,Connection con)throws SQLException,
	Exception{
		
		try{
			long tutorDefaultImageSize=0;
			//get the image size from the database.
			ICrud systemConfigDAO = new SystemConfigDAO();
			Collection<Collection<String>> allovableImageSizeWrapper = systemConfigDAO.findById(tutorProfilePictureSize, con);
		
			Object JhonDoe = asccessInerLoopSingleElement(allovableImageSizeWrapper);
			tutorDefaultImageSize=(long)((JhonDoe!=null)?Long.parseLong((String)JhonDoe)*1024*1024:0.00);
			
			//get the uploaded image and get the size and return if it within size
			
		}  catch(SQLException exp) {
			log.error("isImageWithinSize(): SQLException"+exp.toString());
			throw exp;
			
		}catch(Exception exp) {
			log.error("isImageWithinSize(): SQLException"+exp.toString());
			throw exp;
			
		}
		
		return false;
		
	}
	
	/**
	 * Method process accepts a Collection<Collection<String>> as a parameter and
	 * returns the inner collections' stored element as an object.
	 * 
	 * <b>NOTE<b> the precondition of the method is that the outer wrapper (Collection that 
	 * holds Collection) can only have one element which should NOT be NULL or EMPTY string a Collection of String.
	 * where the conditions are not met will result a null value to be returned.
	 *   
	 * @param wrapper Collection<Collection<String>> 
	 * @return Object
	 */
	private Object asccessInerLoopSingleElement(Collection<Collection<String>> wrapper){
		Object element=null;
		if(wrapper.size()==1){
			for(Collection<String> inner:wrapper){
				Object[] innerArray = inner.toArray();
				if((innerArray[0]!=null)&(innerArray[0]!="")){
					element= innerArray[0];
				}
			}	
		}
		return element;
	}
	
}


