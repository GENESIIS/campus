package com.genesiis.campus.command;

//20170427 DJ c145-add-enhanced-programme-MP-dj Initiate CmdHandleSemesterDetails.java
//20170428 DJ c145-add-enhanced-programme-MP-dj Initiate manageSemesterInsertion() method.

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.entity.model.ProgrammeDTO;
import com.genesiis.campus.entity.model.SemesterDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.FormValidator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The class {@code CmdHandleSemesterDetails} is a form of Command class.It is created for the purpose of handling commands of
 * semester details. 
 * @author dumani DJ
 *
*/
public class CmdHandleSemesterDetails implements ICommand{
	static Logger log = Logger.getLogger(CmdHandleSemesterDetails.class.getName());
	final ProgrammeICrud programmeDAO = new ProgrammeDAOImpl();

	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		String ccoString = helper.getParameter("CCO");
		try {
			if ("ADD_SEMESTER_DETAILS".equalsIgnoreCase(ccoString)) {
				manageSemesterInsertion(helper, iView);
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
	private void manageSemesterInsertion(IDataHelper helper, IView iView) throws Exception{
		//boolean isOkToSave = FormValidator.validateSemesterDetails(helper);
		//if (isOkToSave) {	}				
			final ArrayList<SemesterDTO> semesterList=new ArrayList<SemesterDTO>();
			for(int i=1; i<=3; i++){
				final SemesterDTO semesterDTO = new SemesterDTO();
				semesterDTO.setCode(i);
				semesterDTO.setName("name" +  i);
				semesterDTO.setYearNum(i);
				semesterDTO.setSemesterNum(i);
				semesterDTO.setDescription("Semester Description" + i);
				semesterDTO.setIsActive(1);
				semesterList.add(semesterDTO);
			}
			
			int successStatus = programmeDAO.addSemesterDetails(semesterList);
			if (successStatus > 0) {
				helper.setAttribute("successMessage", "success");
		
		}
		
	}

}
