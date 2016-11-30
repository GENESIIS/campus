package com.genesiis.campus.command;

//20161121 PN c27-upload-user-image: INIT CmdUploadProfileImg.java class and implemented execute() method.
//20161122 PN c27-upload-user-image: modified execute() method to create the folder using studentCode to store image.
//20161124 PN c27-upload-user-image: modified execute() method. - modified exception handling, data setting into the IView
//									 errorMessage handling over validations, 
//20161130 PN c27-upload-user-image: modified filePath variable values.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDAO;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.entity.model.Student;
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
		JsonArray list = new JsonArray();
		Gson gson = new Gson();
		FileUtility utility = new FileUtility();
		ArrayList<FileItem> files = new ArrayList<FileItem>();
		ICrud sysconfigDAO = new SystemConfigDAO();
		String fileUploadError = "";
		String fileUploadSuccess = "";
		// This needs to be assign from the session.
		int StudentCode = 1;

		// Valid file extensions to the user.
		String validExtensions[] = { "jpeg", "jpg", "png", "gif" };

		try {
			// Set the image uploading path
			String uploadPath = "";
			Collection<Collection<String>> picUploaDpath = sysconfigDAO.findById("USER_PIC_UPLOAD_PATH");
			for (Collection<String> collection : picUploaDpath) {
				Object[] config = collection.toArray();
				uploadPath = (String) config[2];
			}

			// get the number of bytes of the valid upload size
			String uploadSize = "";
			long uploadSizeLimit = 0;

			Collection<Collection<String>> picUploadSize = sysconfigDAO.findById("USER_PIC_MAX_SIZE");
			for (Collection<String> collection : picUploadSize) {
				Object[] config = collection.toArray();
				uploadSize = (String) config[2];
			}
			uploadSizeLimit = Long.parseLong(uploadSize);
			uploadSizeLimit = uploadSizeLimit * 1024 * 1024;

			// Here it's only one file is coming from the Uploader. But it has 
			// assigned to a ArrayList<FileItem> because of the reuseability of
			// helper.getFiles() method.
			files = (ArrayList<FileItem>) helper.getFiles();

			String war = uploadPath;
			utility.setUploadPath(uploadPath + "/" + Integer.toString(StudentCode) + "/");

			//To store image file path
			String filePath = "";
			for (FileItem item : files) {
				utility.setFileItem(item);
				JsonObject response = new JsonObject();
				
				if (item.getSize() > uploadSizeLimit) {
					fileUploadError = SystemMessage.FILE_SIZE_EXCEEDED.message();
				} else if ((item.getName().lastIndexOf(".") == -1)
						|| !utility.isValidImageFileType(item.getName(), validExtensions)) {
					fileUploadError = SystemMessage.INVALID_FILE_TYPE.message();
				}else{
					filePath = utility.renameIntoOne(StudentCode);
					response.addProperty("path", war + "/" + filePath);
					response.addProperty("name", utility.getNewName());
					fileUploadSuccess = SystemMessage.FILEUPLOADED.message();
				} 
				response.addProperty("size", item.getSize());
				list.add(response);
			}
			final ArrayList<String> propicDetails = new ArrayList<String>();
			propicDetails.add(filePath);
			propicDetails.add(fileUploadSuccess);
			propicDetails.add(fileUploadError);
			
			final Collection<String> singleCollection = propicDetails;
			Collection<Collection<String>> collection = new ArrayList<Collection<String>>();;
			collection.add(singleCollection);
			view.setCollection(collection);

		} catch (Exception e) {
			//logging the exception and throw, gives a 'SyntaxError: Unexpected end of JSON input'.
			//So that StackTrace has printed in here.
			log.info("execute() : e" + e.toString());
			throw e;
//			e.printStackTrace();
		}

		return view;
	}

}