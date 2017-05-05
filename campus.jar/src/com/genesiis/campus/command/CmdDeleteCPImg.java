package com.genesiis.campus.command;
/**
 * 20170226 PN CAM-48: INIT CmdDeleteCPImg.java class and implementing execute() method to delete cp image from disk.modified execute method to get all the files in courseprovider's logo path and pass it into the JSP file as an array.
 * 20170419 PN CAM-48: removed unwanted logger statement.
 * 20170503 PN CAM-163: modified the execute method to perform image deletion according to the incoming parameters. 
 * 20170505 PN CAM-163: getImageUploadConfigs() method removed from the class.
 */
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * This class handles the cp image/logo deletion.
 * @author pabodha
 *
 */
public class CmdDeleteCPImg implements ICommand{
	static Logger log = Logger.getLogger(CmdDeleteCPImg.class.getName());
	

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// Variable declaration.
		FileUtility utility = new FileUtility();
		ICrud sysconfigDAO = new SystemConfigDAO();
		
		String fileDeleteError = "";
		String fileDeleteSuccess = "";
		String[] listOfFiles;

		String fileToDelete = "";
		String fileUploadedPath = "";
		
		Collection<Collection<String>> details = new ArrayList<Collection<String>>(); 
		try {
			Map<String, Object> formFielsd = helper.getFormFields();
			//Get form fields data from the request.
			
			if(!formFielsd.isEmpty()){
				fileToDelete = (String) formFielsd.get("delete_cp_img");
				fileUploadedPath = (String) formFielsd.get("fileUploadedPath");
			}
			String[] fileData = fileToDelete.split("/");
			//Check if the file get deleted.
			boolean isDeleted = utility.deleteFile(fileUploadedPath+"/"+fileData[0].toString()+"/",fileData[1].toString());
			if(isDeleted){
				fileDeleteSuccess = SystemMessage.FILEDELETED.message();
			}else{
				fileDeleteError = SystemMessage.FILEDELETEFAILED.message();
			}
			listOfFiles = FileUtility.getFileNames(fileUploadedPath+"/"+fileData[0].toString()+"/");	//File name list only.
			details = FileUtility.getFileDetails(fileUploadedPath+"/"+fileData[0].toString()+"/");		//All the details inside the folder.
		} catch (Exception e) {
			log.error("execute() : e" + e.toString());
			throw e;
		}
		helper.setAttribute("fileDeleteSuccess", fileDeleteSuccess);
		helper.setAttribute("fileDeleteError", fileDeleteError);
		helper.setAttribute("listOfFiles", listOfFiles);
		helper.setAttribute("cpImageData", details);
		return view;
	}
}
