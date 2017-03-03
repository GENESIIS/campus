package com.genesiis.campus.factory;

//20161026 DN c10-contacting-us-page amended by inserting CONTACT_US_PUBLC entry to the map

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.genesiis.campus.command.CmdListCategories;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.util.DataHelper;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	HttpSession session;
	static {	
		
		map.put(Operation.LIST_CATEGORY_DATA, new CmdListCategories());

	}

	@Override
	public ICommand getCommand(String cco) {
		boolean status = sessionValidation(helper);
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		
		case LIST_CATEGORY_DATA:
			command = map.get(o);
			break;

		default:
			break;
		}
		return command;
	}
	//Session validation from backend side. 
	public boolean sessionValidation(){
		// HttpServletRequest request = ServletActionContext.getRequest();  
		IDataHelper helper = new DataHelper(request);
		session = helper.getRequest().getSession(false);
		String currentSessionUser = (String) session.getAttribute("currentSessionUser");
	
		if (currentSessionUser == null) {
			
		}else{
			
		}
		return false;
	}

}