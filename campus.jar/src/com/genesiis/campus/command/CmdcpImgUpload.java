package com.genesiis.campus.command;

/**
 * 20170221 PN CAM-48: INIT CmdcpImgUpload.java class and implementing execute() method to complete cp image uploading functionality.
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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CmdcpImgUpload implements ICommand {
	static Logger log = Logger.getLogger(CmdcpImgUpload.class.getName());
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// Variable declaration.
		JsonArray list = new JsonArray();
		Gson gson = new Gson();
		FileUtility utility = new FileUtility();
		ArrayList<FileItem> files = new ArrayList<FileItem>();

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
			// Set the image uploading path. Taken the path from SYSTEMCONFIG table.
			String uploadPath = getImageUploadConfigs(uploadPathConf,2);

			// get the number of bytes of the valid upload size. Taken the size from SYSTEMCONFIG table.
			String uploadSizeParam = getImageUploadConfigs(uploadPathConf,3);

			// Here it's only one file is coming from the Uploader. But it has
			// assigned to a ArrayList<FileItem> because of the reuseability of
			// helper.getFiles() method.
			files = (ArrayList<FileItem>) helper.getFiles();

			String war = uploadPath;
			utility.setUploadPath(uploadPath + "/" + Integer.toString(courseProviderCode) + "/");

			for (FileItem item : files) {
				utility.setFileItem(item);
				JsonObject response = new JsonObject();

				if (item.getSize() > 1) {
					fileUploadError = SystemMessage.FILE_SIZE_EXCEEDED.message();
				} else if ((item.getName().lastIndexOf(".") == -1)
						|| !utility.isValidImageFileType(item.getName(), validExtensions)) {
					fileUploadError = SystemMessage.INVALID_FILE_TYPE.message();
				} else {
					filePath = utility.remvoeOldAndUploadNew(uploadPathConf);
					response.addProperty("path", war + "/" + filePath);
					response.addProperty("name", utility.getNewName());
					fileUploadSuccess = SystemMessage.FILEUPLOADED.message();
				}
				response.addProperty("size", item.getSize());
				list.add(response);
			}
		} catch (SQLException sqle) {
			log.info("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (IOException ioe) {
			log.info("execute() : ioe - more than one users accessing the same image. Course Provider Code: " + courseProviderCode);
		} catch (NullPointerException npx) {
			log.info(
					"execute() : npx: ignore this NPX due to second POST request.");
		} catch (Exception e) {
			log.info("execute() : e" + e.toString());
			throw e;
		}
		return view;
	}
	
	/**
	 * 
	 * @param key
	 * @param index
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private String getImageUploadConfigs(String key, int index) throws SQLException, Exception {
		String uploadPath = "";
		try {
			ICrud sysconfigDAO = new SystemConfigDAO();
			Collection<Collection<String>> picUploaDpath = sysconfigDAO.findById(key);
			for (Collection<String> collection : picUploaDpath) {
				Object[] config = collection.toArray();
				uploadPath = (String) config[index];
			}
		} catch (SQLException sqle) {
			log.error("getImageUploadConfigs(): SQLException " + sqle.toString());
			throw sqle;

		} catch (Exception e) {
			log.error("getImageUploadConfigs(): Exception " + e.toString());
			throw e;
		}
		return uploadPath;
	}

}
