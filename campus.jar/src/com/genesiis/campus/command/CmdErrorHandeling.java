package com.genesiis.campus.command;

//20170111 PN CAM-72 INIT CmdErrorHandeling class. Implementing execute() method WIP.

import java.sql.SQLException;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

public class CmdErrorHandeling implements ICommand {

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// Analyze the servlet exception

		Throwable throwable = (Throwable) helper.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) helper.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) helper.getAttribute("javax.servlet.error.servlet_name");
		if (servletName == null) {
			servletName = "Unknown";
		}
		String requestUri = (String) helper.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		if (statusCode != 500) {

		} else {

		}
		return null;
	}

}
