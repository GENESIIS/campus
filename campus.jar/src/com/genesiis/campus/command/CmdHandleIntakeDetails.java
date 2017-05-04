package com.genesiis.campus.command;

//20170502 DJ c145-add-enhanced-programme-MP-dj INT- CmdHandleIntakeDetails.java
//20170504 DJ c145-add-enhanced-programme-MP-dj INT- manageIntakeInsertion() method implementation.

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.entity.model.IntakeDTO;
import com.genesiis.campus.entity.model.ProgrammeDTO;
import com.genesiis.campus.entity.model.SemesterDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.FormValidator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


/**
 * The class {@code CmdHandleIntakeDetails} is a form of Command class.It is created for the purpose of handling commands of
 * intake details. 
 * @author dumani DJ
 *
*/
public class CmdHandleIntakeDetails implements ICommand{
	
	static Logger log = Logger.getLogger(CmdHandleIntakeDetails.class.getName());
	
	final ProgrammeICrud programmeDAO = new ProgrammeDAOImpl();

	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		String ccoString = helper.getParameter("CCO");
		try {
			if ("ADD_INTAKE_DETAILS".equalsIgnoreCase(ccoString)) {
				manageIntakeInsertion(helper, iView);
			}
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			throw exception;
		}
		return iView;
	}
	
	
	/**
	 * Populate Intakes insertion interface.
	 * @author DJ Dumani
	 * @param helper
	 * @param iView  	  
	 */	
	private void manageIntakeInsertion(IDataHelper helper, IView iView)throws Exception {
		//boolean isOkToSave = FormValidator.validateProgrammeDetails(helper);
		//if (isOkToSave) {
		//TODO:programmeCode hard coded and ArrayList hard coded till front end implementation.Holds by UI implementation.
				int programmeCode=2; 
				
				final ArrayList<IntakeDTO> intakeList=new ArrayList<IntakeDTO>();
				for(int i=1; i<=3; i++){
					final IntakeDTO intakeDTO = new IntakeDTO();				
					intakeDTO.setIntakeName("Intake name" +  i);					
					intakeDTO.setDescription("Intake Description" + i);
					intakeDTO.setOpeningDate(new Date());
					intakeDTO.setClosingDate(new Date());
					intakeDTO.setCommencementDate(new Date());
					intakeDTO.setProgrammeCode(programmeCode);
					intakeDTO.setFee(2000d);					
					intakeDTO.setIsActive(1);					
					intakeDTO.setCrtBy("DJ");
					intakeDTO.setCrtOn(new Date());
					intakeList.add(intakeDTO);
				}
				
				int[] successCount = programmeDAO.addIntakeDetails(intakeList);
				
				if (successCount.length > 0) {
					helper.setAttribute("successMessage", "success");
				}
		}

}
