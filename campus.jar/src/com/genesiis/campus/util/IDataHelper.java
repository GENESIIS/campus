package com.genesiis.campus.util;

import com.genesiis.campus.entity.IView;

//20161024 DN c10-contacting-us-page created the initial version of the IDataHelper.java

public interface IDataHelper {
	public String getCommandCode();

	public String getResultPage(String cco);

	public IView getResultView(String cco) throws Exception;

	public String getParameter(String paramName);

	public void setAttribute(String Name, Object o);

}