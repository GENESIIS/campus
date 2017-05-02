package com.genesiis.campus.command;

//20170428 DJ c145-add-enhanced-programme-MP-dj Initiate CmdHandleModuleDetails.java
//20170428 DJ c145-add-enhanced-programme-MP-dj manageModuleInsertion()-modify method and command 'ADD_MODULE_DETAILS'.

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.entity.model.ModuleDTO;
import com.genesiis.campus.entity.model.SemesterDTO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * The class {@code CmdHandleModuleDetails} is a form of Command class.It is created for the purpose of handling commands of
 * module details. 
 * @author dumani DJ
 *
*/
public class CmdHandleModuleDetails implements ICommand {
	static Logger log = Logger.getLogger(CmdHandleModuleDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		String ccoString = helper.getParameter("CCO");
		try {
			if ("ADD_MODULE_DETAILS".equalsIgnoreCase(ccoString)) {
				manageModuleInsertion(helper, iView);
			}
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			throw exception;
		}
		return iView;
	}

	
	/** Manage form data(input values) for database insertion.
	 * @author DJ dumani
	 * @param helper 	  
	 * @param iView 
	 */	
	
	private void manageModuleInsertion(IDataHelper helper, IView iView) throws Exception{
		final ProgrammeICrud programmeDAO = new ProgrammeDAOImpl();
		// boolean isOkToSave = FormValidator.validateModuleDetails(helper);
		// if (isOkToSave) { }

		// TODO:moduleCode hard coded and ArrayList hard coded till front end
		// implementation.Holds by UI implementation.
		int moduleCode = 2;

		final ArrayList<ModuleDTO> moduleList = new ArrayList<ModuleDTO>();
		for (int i = 1; i <= 3; i++) {
			final ModuleDTO moduleDTO = new ModuleDTO();
			moduleDTO.setName("name" + i);
			moduleDTO.setInternalCodeModule("InternalCodeModule"+i);
			moduleDTO.setCreditValue(i);
			moduleDTO.setDescription("Semester Description" + i);
			moduleDTO.setCompulsoryStatus(1);
			moduleDTO.setTutoredBy(" ");
			moduleDTO.setIsActive(1);			
			moduleDTO.setCrtBy("DJ");
			moduleDTO.setCrtOn(new Date());
			moduleDTO.setSemester(moduleCode);
			moduleDTO.setTutorCode(3);
			moduleDTO.setIsTutorRelated(i);
			moduleList.add(moduleDTO);
		}

		int[] successCount = programmeDAO.addModuleDetails(moduleList);

		if (successCount.length > 0) {
			helper.setAttribute("successMessage", "success");
		}
	}

}
