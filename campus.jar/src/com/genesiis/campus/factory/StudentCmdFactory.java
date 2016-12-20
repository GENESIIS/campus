package com.genesiis.campus.factory;

//20161123 PN c27-upload-user-image: INIT StudentCmdFactory.java
//20161124 PN c26-add-student-details: added GET_SCHOOLEDUCATION_DATA, GET_SCHOOLEDUCATION_DATA, UPDATE_SCHOOLEDUCATION_DATA attributes
//20161129 PN c26-add-student-details: added ADD_PROFESSIONAL_EXP, UPDATE_PROFESSIONAL_EXP and DELETE_PROFESSIONAL_EXP attributes.
//20161205 PN c26-add-student-details: added GET_TOWN_DETAILS and ADD_STUDENT_DETAILS attributes.
//20161206 PN c26-add-student-details: added GET_SKILL_DETAILS , ADD_STUDENT_SKILL attributes.
//20161215 PN CAM-28: added ADD_HIGHER_EDUCATION attribute
//20161220 PN CAM-28: added DELETE_PROFESSIONAL_EXP attribute

import com.genesiis.campus.command.CmdGetStudentData;
import com.genesiis.campus.command.CmdGetSkillDetails;
import com.genesiis.campus.command.CmdGetTownDetails;
import com.genesiis.campus.command.CmdAddHigherEducationData;
import com.genesiis.campus.command.CmdAddProfessionalExpDetails;
import com.genesiis.campus.command.CmdAddSchoolEducationData;
import com.genesiis.campus.command.CmdAddStudentPersonlDetails;
import com.genesiis.campus.command.CmdAddStudentSkillDetails;
import com.genesiis.campus.command.CmdDeleteProfessionalExpDetails;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class StudentCmdFactory implements ICmdFactory{
	private ICommand command = null;
	static {	
		map.put(Operation.GET_SCHOOLEDUCATION_DATA, new CmdGetStudentData());
		map.put(Operation.ADD_PROFESSIONAL_EXP, new CmdAddProfessionalExpDetails());
		map.put(Operation.UPDATE_PROFESSIONAL_EXP, new CmdAddSchoolEducationData());
		map.put(Operation.ADD_SCHOOLEDUCATION_DATA, new CmdAddSchoolEducationData()); 
		map.put(Operation.GET_TOWN_DETAILS, new CmdGetTownDetails()); 
		map.put(Operation.ADD_STUDENT_DETAILS, new CmdAddStudentPersonlDetails()); 
		map.put(Operation.GET_SKILL_DETAILS, new CmdGetSkillDetails());
		map.put(Operation.ADD_STUDENT_SKILL, new CmdAddStudentSkillDetails());
		map.put(Operation.ADD_HIGHER_EDUCATION, new CmdAddHigherEducationData());
		map.put(Operation.DELETE_PROFESSIONAL_EXP, new CmdDeleteProfessionalExpDetails());
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
		case ADD_PROFESSIONAL_EXP:
			command = map.get(o);
			break;
		case UPDATE_PROFESSIONAL_EXP:
			command = map.get(o);
			break;
		case DELETE_PROFESSIONAL_EXP:
			command = map.get(o);
			break;
		case GET_TOWN_DETAILS:
			command = map.get(o);
			break;
		case ADD_STUDENT_DETAILS:
			command = map.get(o);
			break;
		case GET_SKILL_DETAILS:
			command = map.get(o);
			break;
		case ADD_STUDENT_SKILL:
			command = map.get(o);
			break;
		case ADD_HIGHER_EDUCATION:
			command = map.get(o);
			break;
		case DELETE_HIGHER_EDUCATION:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}
}
