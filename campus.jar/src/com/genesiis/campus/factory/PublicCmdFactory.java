package com.genesiis.campus.factory;

//20161107 JH c7-higher-education-landing-page-mp added PublicCmdFactory.java

import com.genesiis.campus.command.CmdGetSearchData;
import com.genesiis.campus.command.CmdListCategories;
import com.genesiis.campus.command.CmdListDistricts;
import com.genesiis.campus.command.CmdListInstitute;
import com.genesiis.campus.command.CmdListLevels;
import com.genesiis.campus.command.CmdListMajors;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {	
	//	map.put(Operation.GET_SEARCH_DATA, new CmdGetSearchData());	

	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		

		default:
			break;
		}
		return command;
	}

}
