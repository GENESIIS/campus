package com.genesiis.campus.command;

import com.genesiis.campus.entity.ICrud;

/**
 * 201702261 PN CAM-48: INIT CmdDeleteCPImg.java class and implementing execute() method to delete cp image from disk.modified execute method to get all the files in courseprovider's logo path and pass it into the JSP file as an array.
 */

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public class CmdDeleteCPImg implements ICommand{
	static Logger log = Logger.getLogger(CmdDeleteCPImg.class.getName());
	

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// Variable declaration.
		FileUtility utility = new FileUtility();
		ICrud sysconfigDAO = new SystemConfigDAO();
		
		String fileDeleteError = "";
		String fileDeleteSuccess = "";
		// To store image file path
		String filePath = "";
		String[] listOfFiles;

		// Below values needs to be assign from the request later.
		int courseProviderCode = 1;
		String uploadPathConf = "";
		String fileToDelete = "";
		
		try {
			helper.getParameter("delete_cp_img");
			
			Map<String, Object> formFielsd = helper.getFormFields();
			//Get form fields data from the request.
			
			if(!formFielsd.isEmpty()){
				courseProviderCode = Integer.parseInt((String) formFielsd.get("courseProviderCode"));
				uploadPathConf = (String) formFielsd.get("uploadPathConf");
				fileToDelete = (String) formFielsd.get("delete_cp_img");
			}
			log.info("fileToDelete"+fileToDelete);
			Collection<Collection<String>> picUploaDpath = sysconfigDAO.findById(uploadPathConf);

			// Set the image uploading path. Taken the path from SYSTEMCONFIG table.
			String deletePath = getImageUploadConfigs(2, picUploaDpath);
			deletePath = deletePath + "/" + Integer.toString(courseProviderCode) + "/";
			
			//Check if the file get deleted.
			boolean isDeleted = utility.deleteFile(deletePath,fileToDelete);
			if(isDeleted){
				fileDeleteSuccess = SystemMessage.FILEDELETED.message();
			}else{
				fileDeleteError = SystemMessage.FILEDELETEFAILED.message();
			}
			// This code value given here can be any SYSTEMCONFIGCODE given for for CP images.
			listOfFiles = FileUtility.getFileNames(deletePath);
		} catch (Exception e) {
			log.error("execute() : e" + e.toString());
			throw e;
		}
		helper.setAttribute("fileDeleteSuccess", fileDeleteSuccess);
		helper.setAttribute("fileDeleteError", fileDeleteError);
		helper.setAttribute("listOfFiles", listOfFiles);
		return view;
	}
	
	/**
	 * This method is to get values from the [CAMPUS].[SYSTEMCONFIG] table where it passes the [SYSTEMCONFIGCODE] value and required field as index. (VALUE1, VALUE2, VALUE3)
	 * @param index - value number
	 * @return String -  physical path to upload the image.
	 * @throws SQLException
	 * @throws Exception
	 */
	private String getImageUploadConfigs(int index, Collection<Collection<String>> picUploaDpath) throws SQLException, Exception {
		String value = "";
		try {
			for (Collection<String> collection : picUploaDpath) {
				Object[] config = collection.toArray();
				value = (String) config[index];
			}
		} catch (Exception e) {
			log.error("getImageUploadConfigs(): Exception " + e.toString());
			throw e;
		}
		return value;
	}

}
