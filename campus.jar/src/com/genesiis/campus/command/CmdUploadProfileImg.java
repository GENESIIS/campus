package com.genesiis.campus.command;

import java.io.IOException;

//20161121 PN c27-upload-user-image: INIT CmdUploadProfileImg.java class and implemented execute() method.
//20161122 PN c27-upload-user-image: modified execute() method to create the folder using studentCode to store image.
//20161124 PN c27-upload-user-image: modified execute() method. - modified exception handling, data setting into the IView
//									 errorMessage handling over validations, 
//20161130 PN c27-upload-user-image: modified filePath variable values.
//		   PN c27-upload-user-image: added more code comments to the execute() method.
//20161221 PN CAM-27: modified exception handling in execute() method by adding IOException and NullPointerException to the catch block.
//20161227 PN CAM-27: getUserProfilePicPath(), getPropicSize() and setProfileDetails() methods implemented. Changed execute method coding accordingly.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDAO;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.entity.model.StudentProfilePicture;
import com.genesiis.campus.entity.model.SystemConfiguration;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

public class CmdUploadProfileImg implements ICommand {
	static Logger log = Logger.getLogger(CmdUploadProfileImg.class.getName());

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

		// This needs to be assign from the session.
		int StudentCode = 1;

		// Valid file extensions to the user.
		String validExtensions[] = { "jpeg", "jpg", "png", "gif" };

		try {
			// Set the image uploading path. Taken the path from SYSTEMCONFIG
			// table.
			String uploadPath = getUserProfilePicPath("USER_PIC_UPLOAD_PATH");

			// get the number of bytes of the valid upload size. Taken the size
			// from SYSTEMCONFIG table.
			long uploadSizeLimit = getPropicSize("USER_PIC_MAX_SIZE");

			// Here it's only one file is coming from the Uploader. But it has
			// assigned to a ArrayList<FileItem> because of the reuseability of
			// helper.getFiles() method.
			files = (ArrayList<FileItem>) helper.getFiles();

			String war = uploadPath;
			utility.setUploadPath(uploadPath + "/" + Integer.toString(StudentCode) + "/");

			for (FileItem item : files) {
				utility.setFileItem(item);
				JsonObject response = new JsonObject();

				if (item.getSize() > uploadSizeLimit) {
					fileUploadError = SystemMessage.FILE_SIZE_EXCEEDED.message();
				} else if ((item.getName().lastIndexOf(".") == -1)
						|| !utility.isValidImageFileType(item.getName(), validExtensions)) {
					fileUploadError = SystemMessage.INVALID_FILE_TYPE.message();
				} else {
					filePath = utility.renameIntoOne(StudentCode);
					response.addProperty("path", war + "/" + filePath);
					response.addProperty("name", utility.getNewName());
					fileUploadSuccess = SystemMessage.FILEUPLOADED.message();
				}
				response.addProperty("size", item.getSize());
				list.add(response);
			}
			// Set profile picture details to sent them back to the JSP page.
			StudentProfilePicture proPic = new StudentProfilePicture();
			proPic.setFilePath(filePath);
			proPic.setFileUploadSuccess(fileUploadSuccess);
			proPic.setFileUploadError(fileUploadError);

			Collection<Collection<String>> collection = setProfileDetails(proPic);
			view.setCollection(collection);
		} catch (SQLException sqle) {
			log.info("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (IOException ioe) {
			log.info("execute() : ioe - more than one users accessing the same image. Student code: " + StudentCode);
		} catch (NullPointerException npx) {
			log.info(
					"execute() : npx: ignore this NPX due to second POST request in student-dashboard.jsp $(fileUpload()) method.");
		} catch (Exception e) {
			log.info("execute() : e" + e.toString());
			throw e;
		}
		return view;
	}

	/**
	 * getUserProfilePicPath()
	 * 
	 * @param key
	 * @return String value of user profile picture path;
	 */
	private String getUserProfilePicPath(String key) throws SQLException, Exception {
		String uploadPath = "";
		try {
			ICrud sysconfigDAO = new SystemConfigDAO();
			Collection<Collection<String>> picUploaDpath = sysconfigDAO.findById(key);
			for (Collection<String> collection : picUploaDpath) {
				Object[] config = collection.toArray();
				uploadPath = (String) config[2];
			}
		} catch (SQLException sqle) {
			log.error("getUserProfilePicPath(): SQLException " + sqle.toString());
			throw sqle;

		} catch (Exception e) {
			log.error("getUserProfilePicPath(): Exception " + e.toString());
			throw e;
		}
		return uploadPath;
	}

	/**
	 * getPropicSize()
	 * 
	 * @param key
	 * @return long value of the uploaded file size.
	 */
	private long getPropicSize(String key) throws SQLException, Exception {
		String uploadSize = "";
		long uploadSizeLimit = 0;
		try {
			ICrud sysconfigDAO = new SystemConfigDAO();
			Collection<Collection<String>> picUploadSize = sysconfigDAO.findById(key);
			for (Collection<String> collection : picUploadSize) {
				Object[] config = collection.toArray();
				uploadSize = (String) config[2];
			}
			uploadSizeLimit = Long.parseLong(uploadSize);
			uploadSizeLimit = uploadSizeLimit * 1024 * 1024;
		} catch (SQLException sqle) {
			log.error("getPropicSize(): SQLException " + sqle.toString());
			throw sqle;

		} catch (Exception e) {
			log.error("getPropicSize(): Exception " + e.toString());
			throw e;
		}
		return uploadSizeLimit;
	}

	/**
	 * setProfileDetails()
	 * 
	 * @param proPic
	 * @return Collection<Collection<String>> this method is to set student
	 *         profile picture details.
	 */
	private Collection<Collection<String>> setProfileDetails(StudentProfilePicture proPic) {
		final ArrayList<String> propicDetails = new ArrayList<String>();

		propicDetails.add(proPic.getFilePath());
		propicDetails.add(proPic.getFileUploadSuccess());
		propicDetails.add(proPic.getFileUploadError());
		propicDetails.add(proPic.getFileName());
		propicDetails.add(Long.toString(proPic.getFileSize()));		
		
		final Collection<String> singleCollection = propicDetails;
		Collection<Collection<String>> collection = new ArrayList<Collection<String>>();
		;
		collection.add(singleCollection);

		return collection;
	}

}