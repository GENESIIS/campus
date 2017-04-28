package com.genesiis.campus.command;

//20170425 CW c159-courseprovider-accept-tutor-request-cw INIT CmdApproveRejectRemoveTutors.java
//20170425 CW c159-courseprovider-accept-tutor-request-cw created approveList & rejcetList variables
//20170427 CW c159-courseprovider-accept-tutor-request-cw refactor the name of the class into CmdSaveEmploymentStatusCP
//20170428 CW c159-courseprovider-accept-tutor-request-cw modified execute method to update data, view message & view final tutor list

import com.genesiis.campus.entity.EmploymentDAO;
import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.model.Employment;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CmdSaveEmploymentStatusCP implements ICommand {

	static Logger log = Logger.getLogger(CmdSaveEmploymentStatusCP.class.getName());
	private String message = "";

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		String tablemessage = "";
		try {
			
			String maxSequence = helper.getParameter("maxSequence");
			String approveList = "";
			String rejcetList = "";
			int maxIndex = 0;
			boolean bothSelected = false;
			
			List<Employment> employmentCollection = new ArrayList<Employment>();

			if(Validator.isNotEmpty(maxSequence)){
				maxIndex = Integer.parseInt(maxSequence);				

				for(int i = 1; i <= maxIndex+1; i++){ // creating the comma separated list of employment codes needed to remove
					
					String list = "confirmationStatus" + i;
					String empCode = "employmentCode" + i;
					
					log.info(list + " " + helper.getParameter(list));
					log.info(empCode + " " + helper.getParameter(empCode));			
					
					String modUser = (String) helper.getSession(true).getAttribute("user");
					
					if(Validator.isNotEmpty(helper.getParameter(list)) && !helper.getParameter(list).equals("-99")){
						Employment emp = new Employment();
						
						emp.setConfirmationStatus(Integer.parseInt(helper.getParameter(list)));
						
						if(modUser == null){
							emp.setModby("");
						}else{
							emp.setModby(modUser);
						}
						//emp.setModon(modon);
						if(Validator.isNotEmpty(helper.getParameter(empCode))){
							emp.setCode(Integer.parseInt(helper.getParameter(empCode)));
						}
						
						employmentCollection.add(emp);
					}
				}
				
				EmploymentDAO employmentDao = new EmploymentDAO();
				int updatedCount = employmentDao.update(employmentCollection);
				if(updatedCount >0){
					tablemessage = SystemMessage.UPDATED.message();
				}
				
				Collection<String> cpCodeList = new ArrayList<String>();
				
				String cpCode = helper.getParameter("cpCode");
				if(cpCode == ""){
					cpCode =  helper.getParameter("courseprovidercode");
				}
				
				cpCodeList.add(cpCode);
				
				final Collection<Collection<String>> allcpCodeList = new ArrayList<Collection<String>>();
				allcpCodeList.add(cpCodeList);
				view.setCollection(allcpCodeList);	
				
				
				/*
				if(!bothSelected){
					
					for(int i = 1; i <= maxIndex+1; i++){ // creating the comma separated list of employment codes needed to remove
	
						if(Validator.isNotEmpty(helper.getParameter("isApprove"+i)) && helper.getParameter("isApprove"+i).equals("1")){
							if(approveList == ""){
								approveList = helper.getParameter("employmentCode"+i);
							}else{
								approveList = approveList + "," + helper.getParameter("employmentCode"+i);
							}			
						}
						
						if(Validator.isNotEmpty(helper.getParameter("isRemove"+i)) && helper.getParameter("isRemove"+i).equals("1")){
							if(rejcetList == ""){
								rejcetList = helper.getParameter("employmentCode"+i);
							}else{
								rejcetList = rejcetList + "," + helper.getParameter("employmentCode"+i);
							}			
						}
					}
					*/
		/*			if(deleteList != ""){
						final EmploymentDAO employment = new EmploymentDAO();
						status = employment.deleteMultiple(deleteList);
					}else{
						tablemessage = "Please select Employment Details to delete ...";				
					}
					
					if(status > 0){
						tablemessage = "Selected employers successfully removed ...";
					}
					
					String tutorCode = helper.getParameter("tutorCodeTable");		
					Collection<Collection<String>> allSelectedFeaturedCourseProviderList = FeaturedCourseProviderDAO.getTutorSelectedFCP(tutorCode);			
					
					if(allSelectedFeaturedCourseProviderList == null || allSelectedFeaturedCourseProviderList.isEmpty()){
						Collection<String> singleTutorEmploymentViewCollection = new ArrayList<String>();
						singleTutorEmploymentViewCollection.add(tutorCode);
						allSelectedFeaturedCourseProviderList.add(singleTutorEmploymentViewCollection);
					}
					
					view.setCollection(allSelectedFeaturedCourseProviderList);	
	
				}*/
			}
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("tablemessage", tablemessage);
		}
		return view;
	}
}
