package com.genesiis.campus.command;

/**
 * 20170221 PN CAM-48: INIT CmdUploadCPImg.java class and implementing execute() method to complete cp image uploading functionality.
 * 20170222 PN CAM-48: modifying execute() method by assigning values to courseProviderCode, uploadPathConf. implemented createFileName(String uploadPathConf, int courseProviderCode) method.
 * 20170222 PN CAM-48: implemented isValidFileSize() method and isValidFileType() method to validate uploaded image.
 * 20170223 PN CAM-48: implemented getFileUploadError() method implemented. modified execute method to fix incorrect validations in backend.
 */

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.imageio.ImageIO;

public class CmdUploadCPImg implements ICommand {
	static Logger log = Logger.getLogger(CmdUploadCPImg.class.getName());
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// Variable declaration.
		Gson gson = new Gson();
		FileUtility utility = new FileUtility();
		FileItem file = null;

		String fileUploadError = "";
		String fileUploadSuccess = "";
		// To store image file path
		String filePath = "";

		// Below values needs to be assign from the request later.
		int courseProviderCode = 1;
		String uploadPathConf = "";
				
		// Valid file extensions to the user.
		String validExtensions[] = { "jpeg", "jpg", "png", "gif" };
		
		try {
			Map<String, Object> formFielsd = helper.getFormFields();
			//Get form fields data from the request.
			
			if(!formFielsd.isEmpty()){
				courseProviderCode = Integer.parseInt((String) formFielsd.get("courseProviderCode"));
				uploadPathConf = (String) formFielsd.get("uploadPathConf");
			}
			// Here it's only one file is coming from the Uploader. But it has
			// assigned to a ArrayList<FileItem> because of the reuseability of
			// helper.getFiles() method.
			file =  (FileItem) formFielsd.get("cp_img");
			
			// Set the image uploading path. Taken the path from SYSTEMCONFIG table.
			String uploadPath = getImageUploadConfigs(uploadPathConf,2);

			// Take the dimensional values from SYSTEMCONFIG table.(Width, Height, Size)
			String uploadSizeParam = getImageUploadConfigs(uploadPathConf,4);
		
			String war = uploadPath;
			utility.setUploadPath(uploadPath + "/" + Integer.toString(courseProviderCode) + "/");

			utility.setFileItem(file);
			
			//Validate image and upload.
			fileUploadError = getFileUploadError(file, validExtensions, utility, uploadSizeParam);
			if (!fileUploadError.isEmpty() && fileUploadError != "") {
				fileUploadError = getFileUploadError(file, validExtensions, utility, uploadSizeParam);
			} else {
				filePath = utility.remvoeOldAndUploadNew(createFileName(uploadPathConf, courseProviderCode));
				fileUploadSuccess = SystemMessage.FILEUPLOADED.message();
			}
		} catch (SQLException sqle) {
			log.error("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (IOException ioe) {
			log.error("execute() : ioe - more than one users accessing the same image. Course Provider Code: " + courseProviderCode);
		} catch (NullPointerException npx) {
			log.error(
					"execute() : npx: ignore this NPX due to second POST request.");
		} catch (Exception e) {
			log.error("execute() : e" + e.toString());
			throw e;
		}
		return view;
	}
	
	/**
	 * This method is to get values from the [CAMPUS].[SYSTEMCONFIG] table where it passes the [SYSTEMCONFIGCODE] value and required field as index. (VALUE1, VALUE2, VALUE3)
	 * @param key - SYSTEMCONFIGCODE value.
	 * @param index - value number
	 * @return String -  physical path to upload the image.
	 * @throws SQLException
	 * @throws Exception
	 */
	private String getImageUploadConfigs(String key, int index) throws SQLException, Exception {
		String value = "";
		try {
			ICrud sysconfigDAO = new SystemConfigDAO();
			Collection<Collection<String>> picUploaDpath = sysconfigDAO.findById(key);
			for (Collection<String> collection : picUploaDpath) {
				Object[] config = collection.toArray();
				value = (String) config[index];
			}
		} catch (SQLException sqle) {
			log.error("getImageUploadConfigs(): SQLException " + sqle.toString());
			throw sqle;

		} catch (Exception e) {
			log.error("getImageUploadConfigs(): Exception " + e.toString());
			throw e;
		}
		return value;
	}

	/**
	 * Rename uploaded image according to the format given.
	 * @param uploadPathConf - type of the uploaded image (small logo, large logo etc)
	 * @param courseProviderCode - course provider ID.
	 * @return
	 */
	private String createFileName(String uploadPathConf, int courseProviderCode) {
		String subName = "";
		String newFileName = "";
		try {
			String[] uploadConfigs = uploadPathConf.split("_");
			subName = uploadConfigs[1].toLowerCase();
			newFileName = Integer.toString(courseProviderCode) + "_" + subName;
		} catch (Exception ex) {
			log.error("createFileName(): Exception " + ex.toString());
			throw ex;
		}
		return newFileName;
	}
	
	/**
	 * This method will validate the file extension.
	 * @param item - file
	 * @param validExtensions - uploaded possible file types array
	 * @param utility - FileUtility class
	 * @return true if file type is valid, else false.
	 */
	private boolean isValidFileType(FileItem item, String validExtensions[], FileUtility utility){
		if((item.getName().lastIndexOf(".") == -1)|| !utility.isValidImageFileType(item.getName(), validExtensions)){
			return false;
		}
		return true;
	}
	
	/**
	 * Method will validate file dimensions and size.
	 * @param item - uploaded file item.
	 * @param uploadSizeParam - valid file dimension details.
	 * @return error detailed message.
	 */
	private String isValidFileSize(FileItem item, String uploadSizeParam) throws IOException{
		String[] values = uploadSizeParam.split(",");
		long height = 0;
		long width = 0;
		long size = 0;
		String errorMsg = "";

		try {
			InputStream is = item.getInputStream();
			is = new BufferedInputStream(item.getInputStream());
			BufferedImage image = ImageIO.read(is);

			if (values.length == 3) {
				height = Long.parseLong(values[0].trim());
				width = Long.parseLong(values[1].trim());
				size = Long.parseLong(values[2].trim());
				//Convert into bytes.
				size = size * 1024 * 1024;
				
				if (item.getSize() > size) {
					errorMsg.concat("Size: " + size + " ");
				}
				if (image.getWidth() > width) {
					errorMsg.concat("Width: " + width + " ");
				}
				if (image.getHeight() > height) {
					errorMsg.concat("Height: " + height + " ");
				}
			}
		} catch (Exception ex) {
			log.error("isValidFileSize(): Exception " + ex.toString());
			throw ex;
		}
		return errorMsg;
	}
	
	/**
	 * Validate image and returns an error message for invalid images.
	 * @param item - file
	 * @param validExtensions - uploaded possible file types array
	 * @param utility - FileUtility class
	 * @param uploadSizeParam - valid file dimension details.
	 * @return String - error message;
	 */
	private String getFileUploadError(FileItem file, String validExtensions[], FileUtility utility, String uploadSizeParam) throws IOException{
		String fileUploadError = "";
		try {
			if (!isValidFileType(file, validExtensions, utility)) {
				fileUploadError = SystemMessage.INVALID_FILE_TYPE.message();
			} else if (!isValidFileSize(file, uploadSizeParam).isEmpty()) {
				fileUploadError = SystemMessage.FILE_SIZE_EXCEEDED.message() + " "
						+ isValidFileSize(file, uploadSizeParam);
			}
		} catch (IOException ioe) {
			log.error("getFileUploadError(): IOException " + ioe.toString());
			throw ioe;
		} catch (Exception ex) {
			log.error("getFileUploadError(): Exception " + ex.toString());
			throw ex;
		}
		return fileUploadError;
	}
}
