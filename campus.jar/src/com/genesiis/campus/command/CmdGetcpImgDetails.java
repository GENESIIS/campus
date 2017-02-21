package com.genesiis.campus.command;

/**
 * 20170221 PN CAM-48: INIT CmdGetcpImgDetails.java class and implementing execute() method to get cp images related config data.
 */

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Pabodha Narmadani
 *
 */
public class CmdGetcpImgDetails implements ICommand {
	static Logger log = Logger.getLogger(CmdGetcpImgDetails.class.getName());
	

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		int courseProviderCode = 1; // This needs to be assign from the request later.
		ICrud sysConf = new SystemConfigDAO();
		Collection<Collection<String>> sysConfCollection = new ArrayList<Collection<String>>();
		try {
			sysConfCollection = sysConf.getAll();
			view.setCollection(sysConfCollection);
			helper.setAttribute("courseProviderCode", courseProviderCode);
		} catch (SQLException sqle) {
			log.info("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.info("execute() : e" + e.toString());
			throw e;
		}
		return view;
	}

}
