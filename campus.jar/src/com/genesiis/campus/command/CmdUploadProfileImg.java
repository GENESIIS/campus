package com.genesiis.campus.command;

//20161121 PN c27-upload-user-image: INIT CmdUploadProfileImg.java class and implemented execute() method.
//20161122 PN c27-upload-user-image: modified execute() method to create the folder using studentCode to store image.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.entity.model.SystemConfiguration;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

public class CmdUploadProfileImg implements ICommand {
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		logger.info("executing...");
		JsonArray list = new JsonArray();
		Gson gson = new Gson();
		FileUtility fu = new FileUtility();
		ArrayList<FileItem> files = new ArrayList<FileItem>();
		
		int StudentCode = 1; //This needs to be assign from the session.

		try {

			files = (ArrayList<FileItem>) helper.getFiles();
			
			String uploadPath = "";
			ICrud sysconfigDAO = new SystemConfigDAO();
			Collection<Collection<String>> sysconfigCollection = sysconfigDAO.findById("USER_PIC_UPLOAD_PATH");
			for (Collection<String> collection : sysconfigCollection) {
				Object[] config = collection.toArray();
				uploadPath = (String) config[2];
			}
			
			String uploadPathuploadPath = uploadPath;
			String war = uploadPath;
			fu.setUploadPath(uploadPath + "/"+Integer.toString(StudentCode)+"/");

			for (FileItem item : files) {

				fu.setFileItem(item);
				boolean uploaded = fu.upload();
				String filePath = fu.rename();

				if (uploaded) {
					JsonObject response = new JsonObject();
					response.addProperty("path", war + "/" + filePath);
					response.addProperty("name", fu.getNewName());
					response.addProperty("size", item.getSize());
					list.add(response);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}

}
