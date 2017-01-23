package com.genesiis.campus.command;
//create the initial CmdUploadTutorImage.java with help of CmdUploadProfileImg.java from CAM:27
//20170109 DN c47-tutor-add-tutor-information-upload-image-dn added getTutorProfileImageUploadPath(), refactor execute() and add doc comments
//20170109 DN c47-tutor-add-tutor-information-upload-image-dn created isImageWithinSize(), asccessInerLoopSingleElement() created
//201701010 DN c47-tutor-add-tutor-information-upload-image-dn  isImageWithinSize()/getTutorProfileImageUploadPath()/isImageAccordanceWithSystemRequirement()
//20170116 DN c47-tutor-add-tutor-information-upload-image-dn  extractDumyObjectFrom() added 
//     coded wip
//20170120 DN c47-tutor-add-tutor-information-upload-image-dn  changed the isImageWithinSize()
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import com.genesiis.campus.validation.SystemMessage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;



// TODO: Auto-generated Javadoc
/**
 * CmdUploadTutorImage is responsible for uploading the image that 
 * is been uploaded from the client side to the location define by the SystemConfig enum class.
 *
 * @author dushantha DN
 */
public class CmdUploadTutorImage implements ICommand {
	static final Logger log = Logger.getLogger(CmdUploadTutorImage.class.getName());
	private int successCode=0;
	private String message = "";
	private FileUtility fileUtility = new FileUtility();
	private ArrayList<FileItem> files = new ArrayList<FileItem>();
	private String[] validExtensions = { "jpeg", "jpg", "png", "gif" };
	
	/* (non-Javadoc)
	 * @see com.genesiis.campus.command.ICommand#execute(com.genesiis.campus.util.IDataHelper, com.genesiis.campus.entity.IView)
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		// Variable declaration.
		JsonArray list = new JsonArray();
		Gson gson = new Gson();
		Connection con = null;
		// To store image file path
		String filePath = "";
		try{
			//get the image files from the browser and set field -files are set now
			files =getImageFileUploadedFromBrowser(helper);
			if((files.size()==0)|(files==null)){
				
				this.message = message +" "+systemMessage(-1)+"\n" +" Please Select a file to Upload."; // does not contain a file
				
			} else{
				
				getFileUtility().setFileItem(files.get(0)); //setting the file Item in the FileUtility
				con = ConnectionManager.getConnection();
				
				Integer tutorCodeFromSession = (Integer) helper.getSession(false).getAttribute("userid");
				int tutorCode = (tutorCodeFromSession!=null)?tutorCodeFromSession:1; //TUTOR CODE HAS TO BE OBTAINED FROM SESSION
				
				//get the tutor image upload path
				String tutorProfileImageUploadPath =getTutorProfileImageUploadPath(SystemConfig.TUTOR_PROFILE_IMAGE_ABSOLUTE_PATH,tutorCode,con);
				
				
				fileUtility.setUploadPath(tutorProfileImageUploadPath);
				
				if(!isImageAccordanceWithSystemRequirement(con,helper))
						return view;
				
				if(!isTheImageFileRenamed(fileUtility,tutorCode))
					return view ;
			}
		} catch(FileUploadException fle){
			log.error("execute():FileUploadException"+ fle.toString() );
			throw fle;			
		} catch(Exception exp) {
			log.error("execute(): Exception :"+ exp.toString());
			throw exp;
		}finally{
			DaoHelper.cleanup(con, null, null);
		}
		helper.setAttribute("message", message);
		view= new CmdGetTutorProfileImg().execute(helper, view); 
		return view;
	}
	
	
	
	
	
	
	private boolean isTheImageFileRenamed(FileUtility item, int tutorCode) throws Exception{
		boolean isTheImageFileRenamed = false;
		try{
			//String fileName = item.renameIntoOne(tutorCode);
			if(!(isTheImageFileRenamed=(item.renameIntoOne(tutorCode)!=""))){
				this.message = message +" "+systemMessage(-5)+"\n" ; 
			}
			
		} catch (Exception exp){
			log.error("isTheImageFileRenamed(): Exception "+exp.toString());
			throw exp;
		}
		return isTheImageFileRenamed;
	}
	
	/**
	 * getTutorProfileImageUploadPath provides the Physical location where the 
	 * image will be stored. If the table SYSTEMCONFIG doesn't have an entry 
	 * for the given "tutorImageProfilePath" in repository,then ,an empty string will be returned
	 * according to the agreed business logic path will be ending up with username_+ tutorcode
	 * e.g. C:/sdb/ctxdeploy/education.war/tutor/pro_image/username_1/
	 *
	 * @param tutorImageProfilePath SystemConfig type .
	 * Always this value must be in sync with the database [CAMPUS].[SYSTEMCONFIG]# column[SYSTEMCONFIGCODE].
	 * @param tutorCode :int code
	 * @param con the con
	 * @return String if the database table contains a due value , then it will be returned,
	 *  else an empty string will be returned
	 *  
	 *  Client of the function must check for empty string. If find so which means the database table is out of sync with the
	 *  passed SystemConfig tutorImageProfilePath.Hence appropriately the condition should be handled.
	 * @throws SQLException the SQL exception
	 * @throws Exception the exception
	 */
	private String getTutorProfileImageUploadPath(SystemConfig tutorImageProfilePath,int tutorCode,Connection con)throws SQLException,
	Exception{
		
		String tutorImagePath="";
		String[] systemConfigCode = {tutorImageProfilePath.toString()};
		//access the data base system configuration data related table and retrieve the path
		ICrud systemConfigDAO = new SystemConfigDAO();
		try {

			Collection<Collection<String>> turoUploadImageCollection = systemConfigDAO
					.findById(systemConfigCode, con);
			// the collection should contains only one collection encapsulated
			// one record from the systemconfig table
			tutorImagePath = (String) asccessInerLoopSingleElement(turoUploadImageCollection);
			tutorImagePath=(tutorImagePath!=null)?tutorImagePath+ "/" + "username_" + Integer.toString(tutorCode) + "/":"";
			return tutorImagePath;

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
	 * Checks if is image accordance with system requirement.
	 *
	 * @param con the con
	 * @param requestWrapper the request wrapper
	 * @return true, if is image accordance with system requirement
	 * @throws SQLException the SQL exception
	 * @throws Exception the exception
	 */
	private boolean isImageAccordanceWithSystemRequirement(Connection con,IDataHelper requestWrapper)throws SQLException,Exception{
		
		try{
			// check image size
			boolean isFilePassSizeRequirement=isImageWithinSize(SystemConfig.TUTOR_PROFILE_IMAGE_SIZE,con);
			// check image extension
			boolean isFilePassExtensionType = isImageHavingTheAcceptedExtension(files.get(0).getName(),validExtensions);
			
			//set the messages to be sent to the client side
			this.message =(!isFilePassSizeRequirement)?this.message + " "+systemMessage(-3):"";
			this.message =(!isFilePassExtensionType)?this.message + " "+systemMessage(-4):"";
			return (isFilePassSizeRequirement & isFilePassExtensionType) ;
			
		} catch(SQLException exp) {
			log.error("isImageAccordanceWithSystemRequirement(): SQLException"+exp.toString());
			throw exp;
			
		} catch(FileUploadException fle){
			log.error("isImageAccordanceWithSystemRequirement():FileUploadException"+ fle.toString() );
			throw fle;
			
		}	catch(Exception exp) {
			log.error("isImageAccordanceWithSystemRequirement(): SQLException"+exp.toString());
			throw exp;
		}
		
	}
	
	
	/**
	 * Checks if is image having the accepted extension types.
	 *@author dushantha DN
	 * @param fileName the file name
	 * @param extensions the extensions
	 * @return true, if is image having the accepted extension
	 */
	private boolean isImageHavingTheAcceptedExtension(String fileName, String extensions[]){
		boolean isImageHavingTheAcceptedExtension = false;
		if(this.getFileUtility().getFileItem()!=null){
			isImageHavingTheAcceptedExtension =	 getFileUtility().isValidImageFileType(fileName, extensions);
		}
		return isImageHavingTheAcceptedExtension;
	}
	
	
	/**
	 * Method confirms the image if exists is within the imposed image size.
	 * If the capasity of the image is accepted the method returns true else false
	 *
	 * @param tutorProfilePictureSize the tutor profile picture size
	 * @param con Connection to the database
	 * @param requestWrapper the request wrapper
	 * @return boolean if the image is accordance with the limit , if it's not or the image does not
	 * exist it returns a false value.
	 * @throws SQLException the SQL exception
	 * @throws FileUploadException the file upload exception
	 * @throws Exception the exception
	 */
	private boolean isImageWithinSize(SystemConfig tutorProfilePictureSize,Connection con)throws SQLException,FileUploadException,
	Exception{
		boolean isFilePassSizeRequirement=false;
		String[] tutorProfilePicturecapasity =  {tutorProfilePictureSize.toString()};
		try{
			long tutorDefaultImageSize=0;
			//get the image size from the database.
			ICrud systemConfigDAO = new SystemConfigDAO();
			Collection<Collection<String>> allovableImageSizeWrapper = systemConfigDAO.findById(tutorProfilePicturecapasity, con);
			
		
			Object JhonDoe = asccessInerLoopSingleElement(allovableImageSizeWrapper);
			tutorDefaultImageSize=(long)((JhonDoe!=null)?Long.parseLong((String)JhonDoe)*1024*1024:0.00);
			
			
			//### get the actual image file size which is uploaded to the server
			//since there is only one file been uploaded no need to iterate through a loop as FileUtility
			// doesn't have a Collection to store the FileItem instance, in a case where there is a necessity to upload many items,
			// internal data structure of FileUtility must be changed or custom class should be created to handle this logic
			// If there are more than one item in the collection then first item will be chosen.
			
			if((files.size()==0)|(files==null)){				
				isFilePassSizeRequirement= false;
			}else if(files.get(0).getSize()<=tutorDefaultImageSize){
				isFilePassSizeRequirement=true;
			}
		}  catch(SQLException exp) {
			log.error("isImageWithinSize(): SQLException"+exp.toString());
			throw exp;
		} catch(FileUploadException fle){
			log.error("isImageWithinSize():FileUploadException"+ fle.toString() );
			throw fle;
		}	catch(Exception exp) {
			log.error("isImageWithinSize(): SQLException"+exp.toString());
			throw exp;
		}
		
		return isFilePassSizeRequirement; 
	}

	
	/**
	 * Gets the image file uploaded from browser. The method returns an ArrayList<FileItem>
	 * In any case where there has no any files been transported from client , then an 
	 * ArrayList<FileItem> of zero elements will be returned or Null can be returned if the request is null,
	 * or Content type is not set.
	 * The user should check for the length 
	 * of the returned ArrayList<FileItem> / nullability and acts accordingly.
	 * @author dushantha DN
	 * @param requestWrapper the request wrapper IDataHelper
	 * @return the image file uploaded from browser ArrayList<FileItem>
	 * @throws FileUploadException the file upload exception
	 */
	private ArrayList<FileItem> getImageFileUploadedFromBrowser(IDataHelper requestWrapper) throws FileUploadException{
		try{
			ArrayList<FileItem> tutorImages = new ArrayList<FileItem>(); 
			
			tutorImages =requestWrapper.getFiles();
			return tutorImages;
			
		} catch(FileUploadException fle){
			log.error("getImageFileUploadedFromBrowser():FileUploadException"+ fle.toString() );
			throw fle;
			
		}
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
	
	
	/**
	 * System message.
	 *@author dushantha DN
	 * @param status the status
	 * @return the string
	 */
	private String systemMessage(int status){
		String message = SystemMessage.UNKNOWN.message();
		setSuccessCode(status);
		switch(status){		
		case 1:
			message = SystemMessage.SUCCESSFULLY_IMAGE_UPLOAD.message();
			break;
		case -1:
			message = SystemMessage.DOES_NOT_CONTAIN_FILE.message();
			break;
		case -2:
			message =SystemMessage.IMAGE_UPLOADING_FAIL.message();
			break;
		case -3:
			message =SystemMessage.EXCEED_LIMIT.message();
			break;
		case -4:
			message =SystemMessage.EXTENSION_MISSMATCH.message();
			break;	
		case -5:
			message =SystemMessage.IMAGE_RENAMING_FAIL.message();
			break;	
		default:			
			break;
		}
		
		return message;
		
	}

	/*
	 * Setters and getters
	 */
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the success code.
	 *
	 * @return the success code
	 */
	public int getSuccessCode() {
		return successCode;
	}

	/**
	 * Sets the success code.
	 *
	 * @param status the new success code
	 */
	private void setSuccessCode(int status) {
		this.successCode = status;
		
	}
	

	/**
	 * Gets the file utility.
	 *
	 * @return the file utility
	 */
	public FileUtility getFileUtility() {
		return fileUtility;
	}

	/**
	 * Sets the file utility.
	 *
	 * @param fileUtility the new file utility
	 */
	public void setFileUtility(FileUtility fileUtility) {
		this.fileUtility = fileUtility;
	}

	/**
	 * Gets the files.
	 *
	 * @return the files
	 */
	public ArrayList<FileItem> getFiles() {
		return files;
	}

	/**
	 * Sets the files.
	 *
	 * @param files the new files
	 */
	public void setFiles(ArrayList<FileItem> files) {
		this.files = files;
	}
	
}


