package com.genesiis.campus.factory;

import com.genesiis.campus.command.CmdAddSchoolEducationData;

//20161123 PN c27-upload-user-image: INIT StudentCmdFactory.java
//20161124 PN c26-add-student-details: added GET_SCHOOLEDUCATION_DATA, GET_SCHOOLEDUCATION_DATA, UPDATE_SCHOOLEDUCATION_DATA attributes

import com.genesiis.campus.command.CmdGetProfileImg;
import com.genesiis.campus.command.CmdGetSchoolEducationData;
import com.genesiis.campus.command.CmdUpdateSchoolEducationData;
import com.genesiis.campus.command.CmdUploadProfileImg;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class StudentCmdFactory implements ICmdFactory{
	private ICommand command = null;
	static {	
		map.put(Operation.UPLOAD_USER_PROFILE, new CmdUploadProfileImg());
		map.put(Operation.GET_USER_PROFILE, new CmdGetProfileImg());
		map.put(Operation.GET_SCHOOLEDUCATION_DATA, new CmdGetSchoolEducationData());
		map.put(Operation.UPDATE_SCHOOLEDUCATION_DATA, new CmdUpdateSchoolEducationData());
		map.put(Operation.ADD_SCHOOLEDUCATION_DATA, new CmdAddSchoolEducationData());
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case UPLOAD_USER_PROFILE:
			command = map.get(o);
			break;
		case GET_USER_PROFILE:
			command = map.get(o);
			break;
		case GET_SCHOOLEDUCATION_DATA:
			command = map.get(o);
			break;
		case UPDATE_SCHOOLEDUCATION_DATA:
			command = map.get(o);
			break;
		case ADD_SCHOOLEDUCATION_DATA:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}
}
