package com.genesiis.campus.command;
//create the initial CmdUploadTutorImage.java with help of CmdUploadProfileImg.java from CAM:27
//20170109 DN c47-tutor-add-tutor-information-upload-image-dn added getTutorProfileImageUploadPath(), refactor execute() and add doc comments
//20170109 DN c47-tutor-add-tutor-information-upload-image-dn created isImageWithinSize(), asccessInerLoopSingleElement() created
//201701010 DN c47-tutor-add-tutor-information-upload-image-dn  isImageWithinSize()/getTutorProfileImageUploadPath()/isImageAccordanceWithSystemRequirement()
//20170116 DN c47-tutor-add-tutor-information-upload-image-dn  extractDumyObjectFrom() added 
//     coded wip
//20170120 DN c47-tutor-add-tutor-information-upload-image-dn  changed the isImageWithinSize()
//20170120 DN c47-tutor-add-tutor-information-upload-image-dn set the successCode,profilePicture,message attributes in execute() method
//20170130 DN c47-tutor-add-tutor-information-upload-image-dn  etTutorProfileImageUploadPath/getSystemConfigRepositoryValues
//asccessInerLoopSingleElement/isImageWithinSize methods has been shifted to ImageUtility.java
//20170131 DN c47-tutor-add-tutor-information-upload-image-dn remove hard coded message from execute() and placed it in SystemMessage.java
//				removed the log comments as per CREV instruction from execute().
//				systemMessage(int) has been shifted to ImageUtility.java class as a static method.

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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.Connection;

/**
 * CmdUploadTutorImage is responsible for uploading the image that 
 * is been uploaded from the client side to the location define by the SystemConfig enum class.
 *
 * @author dushantha DN
 */
public class CmdUploadTutorImage implements ICommand {
	
	/** The Constant log. */
	static final Logger log = Logger.getLogger(CmdUploadTutorImage.class.getName());
	
	/** The success code. */
	private int successCode=0;
	
	/** The message. */
	private String message = "";
	
	/** The file utility. */
	private FileUtility fileUtility = new FileUtility();
	
	/** The files. */
	private ArrayList<FileItem> files = new ArrayList<FileItem>();
	
	/** The valid extensions. */
	private String[] validExtensions = { "jpeg", "jpg", "png", "gif" };
	
	/** The war file path. */
	private String warFilePath = "education/";
	
	private ImageUtility imageUtility = new ImageUtility();
	
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
				
				this.message = message +" "+ImageUtility.systemMessage(-1); // does not contain a file
				this.setSuccessCode(-1);
			} else{
				
				getFileUtility().setFileItem(files.get(0)); //setting the file Item in the FileUtility
				con = ConnectionManager.getConnection();
				
				Integer tutorCodeFromSession = (Integer) helper.getSession(false).getAttribute("userid");
				int tutorCode = (tutorCodeFromSession!=null)?tutorCodeFromSession:1; //TUTOR CODE HAS TO BE OBTAINED FROM SESSION
				
				//get the tutor image upload Absolute path
				String tutorProfileImageUploadPath =imageUtility.getTutorProfileImageUploadPath(SystemConfig.TUTOR_PROFILE_IMAGE_ABSOLUTE_PATH,tutorCode,con);
				
				
				fileUtility.setUploadPath(tutorProfileImageUploadPath);
				
				if((!isImageAccordanceWithSystemRequirement(con,helper)) || (!isTheImageFileRenamed(fileUtility,tutorCode))){
					setResponseCridentials(helper);
							return view;
				}
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
		this.setMessage(ImageUtility.systemMessage(1));
		this.setSuccessCode(1);
		setResponseCridentials(helper);		
		return view;
	}
	
	/*
	 * @author dushantha DN
	 * setResponseCridentials sets the request attributes
	 * @param helper
	 */
	private void setResponseCridentials(IDataHelper helper){
		helper.setAttribute("successCode", getSuccessCode());
		helper.setAttribute("profilePicture", warFilePath);
		helper.setAttribute("message", message);
	}
	
	/*
	 * Checks if is the image file renamed.
	 * @author DN
	 * @param item the FileUtility
	 * @param tutorCode the tutor code int
	 * @return true, if is the image file renamed
	 * @throws Exception the exception
	 */
	private boolean isTheImageFileRenamed(FileUtility item, int tutorCode) throws Exception{
		boolean isTheImageFileRenamed = false;
		try{
			String fileName = item.renameIntoOne(tutorCode);
			String temwarFilePath =warFilePath;
			if(!(isTheImageFileRenamed=(fileName!=""))){
				this.message = message +" "+ImageUtility.systemMessage(-5)+"\n" ;
				this.setSuccessCode(-5);
			}else{
				warFilePath=temwarFilePath+fileName; // there is an issue
			}
		} catch (Exception exp){
			log.error("isTheImageFileRenamed(): Exception "+exp.toString());
			throw exp;
		}
		return isTheImageFileRenamed;
	}
	

	/*
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
			boolean isFilePassSizeRequirement=false;
			// check image size
			if((files.size()==0)|(files==null)){
				isFilePassSizeRequirement=false;
			} else{
				isFilePassSizeRequirement=imageUtility.isImageWithinSize(SystemConfig.TUTOR_PROFILE_IMAGE_SIZE,con,files.get(0));
			}
			 
			// check image extension
			boolean isFilePassExtensionType = isImageHavingTheAcceptedExtension(files.get(0).getName(),validExtensions);
			
			//set the messages to be sent to the client side
			this.message =(!isFilePassSizeRequirement)?this.message + " "+ImageUtility.systemMessage(-3):"";
			setSuccessCode(-3);
			this.message =(!isFilePassExtensionType)?this.message + " "+ImageUtility.systemMessage(-4):"";
			setSuccessCode(-4);
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
	
	
	/*
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

	
	/*
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

