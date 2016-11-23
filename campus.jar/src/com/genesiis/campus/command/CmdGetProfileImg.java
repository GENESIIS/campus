package com.genesiis.campus.command;

//20161123 PN c27-upload-user-image: INIT CmdGetProfileImg.java class and implemented execute() method.

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;

public class CmdGetProfileImg implements ICommand {

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		String uploadPath = "";
		String proPicName = "";
		FileUtility utility = new FileUtility();
		ICrud sysconfigDAO = new SystemConfigDAO();

		Collection<Collection<String>> sysconfigCollection = sysconfigDAO.findById("USER_PIC_UPLOAD_PATH");
		for (Collection<String> collection : sysconfigCollection) {
			Object[] config = collection.toArray();
			uploadPath = (String) config[2];
		}

		int StudentCode = 1;String gender ="F"; // This needs to be assign from the session.
		String filepath = uploadPath + "/" + Integer.toString(StudentCode) + "/";
		boolean isFileExists = utility.isFileExists(filepath, Integer.toString(StudentCode));
		if(isFileExists){
			proPicName = "education/student/"+utility.getImageName();
		}else{
			if(gender.equals("F")){
				proPicName = "education/student/Female.jpg";
			}else if(gender.equals("M")){
				proPicName = "education/student/Male.jpg";
			}
		}
		helper.setAttribute("proPicName", proPicName);
		
		
		return null;
	}

}
