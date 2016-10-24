package com.genesiis.campus.util;

//20161024 DN c10-contacting-us-page created initial version

import java.io.IOException;

import org.apache.log4j.Logger;

import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.View;
import com.genesiis.campus.factory.FactoryProducer;
import com.genesiis.campus.factory.ICmdFactory;
import com.genesiis.campus.validation.Operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataHelper implements IDataHelper {

	Logger logger = Logger.getLogger(this.getClass());

	private static HttpServletRequest request;
	private static HttpServletResponse response;

	private String cco = "";
	private String commandChoice = "";

	public DataHelper(HttpServletRequest request) {
		this.request = request;
		this.response = response;
	}

	/**
	 * @author pabodha
	 * @return String
	 * @param request
	 * This will pass the CCO (Command Class Code) to the servlet
	 **/
	@Override
	public String getCommandCode() {
		return request.getParameter("CCO");
	}

	/**
	 * @author pabodha
	 * @return String This will be use to select jsp page
	 * **/
	@Override
	public String getResultPage(String cco) {
		String resultPage = "index.jsp";
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {		
		case BAD_OPERATION:
			resultPage = o.getPageURL();
			break;
		default:
			break;
		}
		return resultPage;
	}

	@Override
	public IView getResultView(String cco) throws Exception{
		IView result = new View();
		String sPath = request.getServletPath();
		try {
			final ICmdFactory factory = FactoryProducer.getFactory(sPath);
			final ICommand iCommand= factory.getCommand(cco);
			if(iCommand != null){
			result=iCommand.execute(this, result);
			}
		} catch (Exception e) {
			logger.info("getResultView() : "+ e.toString());
			throw e;
		}
 		return result;
	}

	@Override
	public String getParameter(String paramName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String Name, Object o) {
		// TODO Auto-generated method stub
		
	}

}