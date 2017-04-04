package com.genesiis.campus.command;

//20170329 CW c157-add-tutor-employment-details-cw INIT CmdRemoveSelectedEmploymentDetails.java
//20170330 CW c157-add-tutor-employment-details-cw modified execute method & add all the selected row tutor Codes & Course provider codes into allSelectedListToRemove collection
//20170330 CW c157-add-tutor-employment-details-cw modified execute method & create deleteList & pass it to deleteMultiple
//20170331 CW c157-add-tutor-employment-details-cw modified execute method & add validations & a message into the deleteMultiple method call
//20170403 CW c157-add-tutor-employment-details-cw modified execute method & add validations to empty allSelectedFeaturedCourseProviderList
//20170403 CW c157-add-tutor-employment-details-cw add validation messages to execute method 
//20170403 CW c157-add-tutor-employment-details-cw removed un used import statements & re-order Import Statements
//20170404 CW c157-add-tutor-employment-details-cw add code comments

import com.genesiis.campus.entity.EmploymentDAO;
import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * this class will remove all the tutor selected course provider details which were added by the tutor earlier, 
 * further it implements ICommand interface
 * @author CW
 */
public class CmdRemoveSelectedEmploymentDetails implements ICommand  {

	static Logger log = Logger.getLogger(CmdRemoveSelectedEmploymentDetails.class.getName());

	/**
	 * @author Chinthaka
	 * @param helepr IDataHelper object of Object type 
	 * @param view IView object of object type
	 * @return View object to servlet
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		String tablemessage = "";
		try {
			
			String sequence = helper.getParameter("maxSequence");
			String deleteList = "";
			int status = -1;
			
			if(Validator.isNotEmpty(sequence)){
				int maxIndex = Integer.parseInt(sequence);				
				
				final Collection<String> singleSelectedListToRemove = new ArrayList<String>();
				for(int i = 1; i <= maxIndex+1; i++){ // creating the comma separated list of employment codes needed to remove

					if(Validator.isNotEmpty(helper.getParameter("isSelected"+i)) && helper.getParameter("isSelected"+i).equals("1")){
						if(deleteList == ""){
							deleteList = helper.getParameter("employmentCode"+i);
						}else{
							deleteList = deleteList + "," + helper.getParameter("employmentCode"+i);
						}			
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
			Collection<Collection<String>> allSelectedFeaturedCourseProviderList = new ArrayList<Collection<String>>();			
			allSelectedFeaturedCourseProviderList = FeaturedCourseProviderDAO.getTutorSelectedFCP(tutorCode);			
			
			if(allSelectedFeaturedCourseProviderList == null || allSelectedFeaturedCourseProviderList.isEmpty()){
				Collection<String> singleTutorEmploymentViewCollection = new ArrayList<String>();
				singleTutorEmploymentViewCollection.add(tutorCode);
				allSelectedFeaturedCourseProviderList.add(singleTutorEmploymentViewCollection);
			}
			
			view.setCollection(allSelectedFeaturedCourseProviderList);	
			
			
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("tablemessage", tablemessage);
		}
		return view;
	}
}
