package com.genesiis.campus.command;

/**
 * 20170221 PN CAM-48: INIT CmdGetcpImgDetails.java class and implementing execute() method to get cp images related config data.
 * 20170226 PN CAM-48: getImageDiskPath() implementation changed. modified execute method to get all the files in courseprovider's logo path and pass it into the JSP file as an array.
 */

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class handles the displaying of an image onChange even of cp logo type drop down.
 * @author Pabodha Narmadani
 *
 */
public class CmdGetcpImgDetails implements ICommand {
	static Logger log = Logger.getLogger(CmdGetcpImgDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		String[] listOfFiles;

		int courseProviderCode = 1; // This needs to be assign from the request later.
		ICrud sysConf = new SystemConfigDAO();
		Collection<Collection<String>> sysConfCollection = new ArrayList<Collection<String>>();
		try {
			sysConfCollection = sysConf.getAll();
			view.setCollection(sysConfCollection);
			// This code value given here can be any SYSTEMCONFIGCODE given for for CP images.
			String fileUploadedPath = getImageDiskPath(3, sysConfCollection);
			listOfFiles = FileUtility.getFileNames(fileUploadedPath + "/" + Integer.toString(courseProviderCode) + "/");
		} catch (SQLException sqle) {
			log.error("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.error("execute() : e" + e.toString());
			throw e;
		}
		helper.setAttribute("courseProviderCode", courseProviderCode);
		helper.setAttribute("listOfFiles", listOfFiles);
		return view;
	}

	/**
	 * This method is to get images uploaded path from the given
	 * [CAMPUS].[SYSTEMCONFIG] table data collection according to the given
	 * [SYSTEMCONFIGCODE] value and required field as index. (VALUE1, VALUE2,
	 * VALUE3)
	 * @param index
	 *            - value number
	 * @param picUploaDpath
	 * @return String - physical path to images uploaded folder.
	 * @throws SQLException
	 * @throws Exception
	 */
	private String getImageDiskPath(int index, Collection<Collection<String>> picUploaDpath)
			throws SQLException, Exception {
		String value = "";
		try {
			for (Collection<String> collection : picUploaDpath) {
				Object[] config = collection.toArray();
				value = (String) config[index];
			}
		} catch (Exception e) {
			log.error("getImageDiskPath(): Exception " + e.toString());
			throw e;
		}
		return value;
	}

}
