package com.genesiis.campus.controller;
// 20161024 DN c10-contacting-us-page created the initial version of the Servlet Controller


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.View;
import com.genesiis.campus.factory.FactoryProducer;
import com.genesiis.campus.factory.ICmdFactory;
import com.genesiis.campus.util.DataHelper;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class CampusController
 * extract from XecoController.java
 * 
 * 
 */

public class CampusController extends HttpServlet {

	static Logger log = Logger.getLogger(CampusController.class.getName());
	private static final long serialVersionUID = 1L;

	
	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		process(request, response);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IDataHelper helper = null;
		IView result = null;
		String cco = "";
		try {
				helper = new DataHelper(request);
				cco = helper.getCommandCode();
				result = helper.getResultView(cco);

		} catch (Exception e) {
			log.error("process(): ", e);
		}

		request.setAttribute("result", result);
		log.error("CONTEXTPARAMETER:=====:"+request.getServletContext().getAttribute("pageSelector"));
		request.getRequestDispatcher(helper.getResultPage(cco)).forward(
				request, response);

	}

}