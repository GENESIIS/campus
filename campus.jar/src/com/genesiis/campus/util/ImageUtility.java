package com.genesiis.campus.util;

import com.genesiis.campus.validation.PrevalentValidation;
import com.genesiis.campus.validation.SystemConfig;

import org.apache.log4j.Logger;

public class ImageUtility {
	static final Logger log = Logger.getLogger(ImageUtility.class.getName());
	
	
	/**
	 * getImageUploadPath: method provides the image upload path based on the
	 * SystemConfig enum value.
	 * @author dushantha DN
	 * @param imageUploadPath
	 * @return String :path which is the parent directory of the user profile picture resides 
	 * @throws Exception will be thrown if the SystemConfig enum has not defined such a value given by the parameter
	 */
	
	public String getImageUploadPath(SystemConfig imageUploadPath)throws Exception{
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
	 * if the SystemConfig enum has not defined an exception will be thrown
	 * @param pictureCategory SystemConfig enum defining the picture category e.g TUTOR_PROFILE_IMAGE_SIZE etc
	 * @return int :the value of the allowable image size in MB
	 * @throws Exception will be thrown if the SystemConfig enum has not defined such a value given by the parameter
	 */
	
	public long getAlloawablePictureSize(SystemConfig pictureCategory) throws Exception{
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
	 * isNullObject: method provides boolean value if the passed parameter is null or actually defined
	 * @param nullCheckingObjet
	 * @return true if the parameter is null else false
	 */
	public boolean isNullObject(Object nullCheckingObjet){
		boolean status = false;
		if (nullCheckingObjet == null) {
			status = true;
		} 
		return status;
	}
	
	
	

}
