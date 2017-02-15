package com.genesiis.campus.util;
//20170105 DN c47-tutor-add-tutor-information-upload-image-dn created the ImageUtility.java
//20170128 Dn c47-tutor-add-tutor-information-upload-image-dn refactor and added getTutorProfileImageUploadPath/getSystemConfigRepositoryValues
//asccessInerLoopSingleElement/isImageWithinSize methods has been created.
//20170131 DN c47-tutor-add-tutor-information-upload-image-dn shifted the systemMessage(int) method 
//              from CmdUploadTutorImage,java in order to encapsulate class responsibilities 
//20170216 DN getImageFileUploadedFromBrowser() has been created which is a copy of CmdUploadTutorImage#getImageFileUploadedFromBrowser() for 
//			  better modularity and re usability. implemented getImageTeporyUploadPath(SystemConfig usersProfileImagePath,String tempDirectory,Connection con) method


import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.validation.PrevalentValidation;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


/**
 * ImageUtility class responsible for supporting a define set of facilities for images
 * Such as getting the uploading path etc.
 *
 * @author dushantha DN
 */
public class ImageUtility {
	
	/** The Constant log. */
	static final Logger log = Logger.getLogger(ImageUtility.class.getName());
	
	
	/**
	 * getImageUploadPath: method provides the image upload path based on the
	 * SystemConfig enum value.
	 *
	 * @author dushantha DN
	 * @param imageUploadPath the image upload path
	 * @return String :path which is the parent directory of the user profile picture resides
	 * @throws Exception will be thrown if the SystemConfig enum has not defined such a value given by the parameter
	 */
	
	public static String getImageUploadPath(SystemConfig imageUploadPath)throws Exception{
		String imagePath = "";
		if(isNullObject(imageUploadPath)){
			try{
				throw new Exception();
			} catch(Exception exp){
				log.error("getImageUploadPath():Exception :"+exp.toString()); 
				throw exp;
			}
		}
		imagePath= imageUploadPath.getValue1();
		return imagePath;
	}
	
	/**
	 * getAlloawablePictureSize: method provides the allowable size of the image
	 * if the SystemConfig enum has not defined an exception will be thrown.
	 *
	 * @param pictureCategory SystemConfig enum defining the picture category e.g TUTOR_PROFILE_IMAGE_SIZE etc
	 * @return int :the value of the allowable image size in MB
	 * @throws Exception will be thrown if the SystemConfig enum has not defined such a value given by the parameter
	 */
	
	public static long getAlloawablePictureSize(SystemConfig pictureCategory) throws Exception{
		if(isNullObject(pictureCategory)){
			try{
				throw new Exception();
			} catch(Exception exp){
				log.error("getAlloawablePictureSize():Exception :"+exp.toString()); 
				throw exp;
			}
		}
		return Long.parseLong(pictureCategory.getValue1())*1024*1024 ;
	}
	
	/**
	 * isNullObject: method provides boolean value if the passed parameter is null or actually defined.
	 *
	 * @param nullCheckingObjet the null checking objet
	 * @return true if the parameter is null else false
	 */
	public static boolean isNullObject(Object nullCheckingObjet){
		boolean status = false;
		if (nullCheckingObjet == null) {
			status = true;
		} 
		return status;
	}	
	
	
	/*
	 * getTutorProfileImageUploadPath provides the Physical location where the 
	 * image will be stored. If the table SYSTEMCONFIG doesn't have an entry 
	 * for the given "tutorImageProfilePath" in repository,then ,an empty string will be returned
	 * according to the agreed business logic path will be ending up with username_+ tutorcode
	 * e.g. C:/sdb/ctxdeploy/education.war/tutor/pro_image/username_1/
	 *
	 * @param usersProfileImagePath SystemConfig type .
	 * Always this value must be in sync with the database [CAMPUS].[SYSTEMCONFIG]# column[SYSTEMCONFIGCODE].
	 * @param profileOwnersCode :int code
	 * @param con the con
	 * @return String if the database table contains a due value , then it will be returned,
	 *  else an empty string will be returned
	 *  
	 *  Client of the function must check for empty string. If find so which means the database table is out of sync with the
	 *  passed SystemConfig tutorImageProfilePath.Hence appropriately the condition should be handled.
	 * @throws SQLException the SQL exception
	 * @throws Exception the exception
	 */
	public String getTutorProfileImageUploadPath(SystemConfig usersProfileImagePath,int profileOwnersCode,Connection con)throws SQLException,
	Exception{
		
		String tutorImagePath="";
		String[] systemConfigCode = {usersProfileImagePath.toString()};
		//access the data base system configuration data related table and retrieve the path
		
		try {

			Collection<Collection<String>> turoUploadImageCollection = getSystemConfigRepositoryValues(systemConfigCode,con);
			// the collection should contains only one collection encapsulated
			// one record from the systemconfig table
			tutorImagePath = (String) asccessInerLoopSingleElement(turoUploadImageCollection);
			tutorImagePath=(tutorImagePath!=null)?tutorImagePath+ "/" + "username_" + Integer.toString(profileOwnersCode) + "/":"";
			return tutorImagePath;

		} catch (SQLException exp) {
			log.error("getTutorProfileImageUploadPath(): SQLException"
					+ exp.toString());
			throw exp;

		} catch (Exception exp) {
			log.error("getTutorProfileImageUploadPath(): Exception"
					+ exp.toString());
			throw exp;

		}
		
	}
	
	/**
	 * getImageTeporyUploadPath returns the temporary image path where the image
	 * is planned to store
	 * @param usersProfileImagePath
	 * @param tempDirectory
	 * @param con
	 * @return String temptutorImagePath
	 * @throws SQLException
	 * @throws Exception
	 */

	public String getImageTeporyUploadPath(SystemConfig usersProfileImagePath,String tempDirectory,Connection con)throws SQLException,
	Exception{
		try{
			String temptutorImagePath="";
			String[] systemConfigCode = {usersProfileImagePath.toString()};
			//access the data base system configuration data related table and retrieve the path
			Collection<Collection<String>> turoUploadImageCollection = getSystemConfigRepositoryValues(systemConfigCode,con);
			// the collection should contains only one collection encapsulated
			// one record from the systemconfig table
			temptutorImagePath = (String) asccessInerLoopSingleElement(turoUploadImageCollection);
			temptutorImagePath=(temptutorImagePath!=null)?temptutorImagePath+ "/" +tempDirectory +"/":"";
			return temptutorImagePath;
		
		} catch (SQLException exp) {
			log.error("getTutorProfileImageUploadPath(SystemConfig usersProfileImagePath,Connection con): SQLException"
					+ exp.toString());
			throw exp;
	
		} catch (Exception exp) {
			log.error("getTutorProfileImageUploadPath(SystemConfig usersProfileImagePath,Connection con): Exception"
					+ exp.toString());
			throw exp;
	
		}
		
	}
	
	
	/**
	 * getSystemConfigRepositoryValues extract repository records from [CAMPUS].[SYSTEMCONFIG]
	 * depend on the sysConfigArray which contains system configuration value that comes from the [SYSTEMCONFIGCODE] column  
	 *
	 * @param sysConfigArray type String[]
	 * @param con the con
	 * @return the system config repository values
	 * @throws SQLException the SQL exception
	 * @throws Exception the exception
	 */
	public Collection<Collection<String>> getSystemConfigRepositoryValues(String[] sysConfigArray,Connection con) throws SQLException,Exception {
		ICrud systemConfigDAO = new SystemConfigDAO();
		try{
			Collection<Collection<String>> turoUploadImageCollection = systemConfigDAO
					.findById(sysConfigArray, con);
			return turoUploadImageCollection;
		} catch(SQLException exp) {
			log.error("getSystemConfigRepositoryValues(): SQLException"
					+ exp.toString());
			throw exp;
		} catch (Exception exp) {
			log.error("getSystemConfigRepositoryValues(): Exception"
					+ exp.toString());
			throw exp;
		}
		
	}
	
	
	/*
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
	

	/*
	 * Method confirms the image if exists is within the imposed image size.
	 * If the capasity of the image is accepted the method returns true else false
	 *
	 * @param tutorProfilePictureSize the tutor profile picture size
	 * @param con Connection to the database
	 * @param fileItem the file item of which the size has to be measured and compared with the size constraint
	 * @return boolean if the image is accordance with the limit , if it's not or the image does not
	 * exist it returns a false value.
	 * @throws SQLException the SQL exception
	 * @throws FileUploadException the file upload exception
	 * @throws Exception the exception
	 */
	public boolean isImageWithinSize(SystemConfig tutorProfilePictureSize,Connection con,FileItem fileItem)throws SQLException,FileUploadException,
	Exception{
		boolean isFilePassSizeRequirement=false;
		String[] tutorProfilePicturecapasity =  {tutorProfilePictureSize.toString()};
		try{
			long tutorDefaultImageSize=0;
			//get the image size from the database.
			
			Collection<Collection<String>> allovableImageSizeWrapper =getSystemConfigRepositoryValues(tutorProfilePicturecapasity, con);
			
		
			Object JhonDoe = asccessInerLoopSingleElement(allovableImageSizeWrapper);
			tutorDefaultImageSize=(long)((JhonDoe!=null)?Long.parseLong((String)JhonDoe)*1024*1024:0.00);
			
			
			//### get the actual image file size which is uploaded to the server
			//since there is only one file been uploaded no need to iterate through a loop as FileUtility
			// doesn't have a Collection to store the FileItem instance, in a case where there is a necessity to upload many items,
			// internal data structure of FileUtility must be changed or custom class should be created to handle this logic
			// If there are more than one item in the collection then first item will be chosen.

			
			isFilePassSizeRequirement=(fileItem.getSize()<=tutorDefaultImageSize);
			
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
	 * System message.
	 *@author dushantha DN
	 * @param status the status int
	 * @return the string system message
	 */
	public static String systemMessage(int status){
		String message = SystemMessage.UNKNOWN.message();
		//setSuccessCode(status);
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
	public ArrayList<FileItem> getImageFileUploadedFromBrowser(IDataHelper requestWrapper) throws FileUploadException{
		try{
			ArrayList<FileItem> tutorImages = new ArrayList<FileItem>(); 
			
			tutorImages =requestWrapper.getFiles();
			return tutorImages;
			
		} catch(FileUploadException fle){
			log.error("getImageFileUploadedFromBrowser():FileUploadException"+ fle.toString() );
			throw fle;
			
		}
	}

}