package com.genesiis.campus.command;

//20170425 CW c159-courseprovider-accept-tutor-request-cw INIT CmdApproveRejectRemoveTutors.java
//20170425 CW c159-courseprovider-accept-tutor-request-cw created approveList & rejcetList variables

import com.genesiis.campus.entity.EmploymentDAO;
import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CmdApproveRejectRemoveTutors implements ICommand {

	static Logger log = Logger.getLogger(CmdApproveRejectRemoveTutors.class.getName());
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

			if(Validator.isNotEmpty(maxSequence)){
				maxIndex = Integer.parseInt(maxSequence);				

				for(int i = 1; i <= maxIndex+1; i++){ // creating the comma separated list of employment codes needed to remove
					if(Validator.isNotEmpty(helper.getParameter("isRemove"+i)) && Validator.isNotEmpty(helper.getParameter("isApprove"+i))){
						bothSelected = true;			
					}
				}
				
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
					
					if(deleteList != ""){
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
	
				}
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
