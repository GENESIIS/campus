package com.genesiis.campus.command;

//20161121 PN c27-upload-user-image: INIT CmdUploadProfileImg.java class and implemented execute() method.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.genesiis.campus.entity.IView;
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
		String institute = "DEFX";
		try {

			Map<String, String[]> args = helper.getParameterMap();
			institute = ((String[]) args.get("IID"))[0];

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			files = (ArrayList<FileItem>) helper.getFiles();
			String uploadPath = "C:/sdb/ctxdeploy/career.war/images/uploads/institutes";//SystemConfiguration.getInstance().getConfigValueOne(SystemConfigCodes.INSTITUTE_UPLOAD_PATH.getValue());
			String war =        "C:/sdb/ctxdeploy/career.war/images/uploads/institutes";//SystemConfiguration.getInstance().getConfigValueTwo(SystemConfigCodes.INSTITUTE_UPLOAD_PATH.getValue());
			fu.setUploadPath(uploadPath + "/" + "PABODHA" + "/articles/");

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
