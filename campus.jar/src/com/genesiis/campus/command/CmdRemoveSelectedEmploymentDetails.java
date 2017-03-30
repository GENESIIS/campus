package com.genesiis.campus.command;

//20170329 CW c157-add-tutor-employment-details-cw INIT CmdRemoveSelectedEmploymentDetails.java
//20170330 CW c157-add-tutor-employment-details-cw modified execute method & add all the selected row tutor Codes & Course provider codes into allSelectedListToRemove collection
//20170330 CW c157-add-tutor-employment-details-cw modified execute method & create deleteList & pass it to deleteMultiple

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.EmploymentDAO;
import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Validator;

/**
 * this class will remove all the tutor selected course provider details which were added by the tutor earlier, 
 * further it implements ICommand interface
 * @author CW
 */
public class CmdRemoveSelectedEmploymentDetails implements ICommand  {

	static Logger log = Logger.getLogger(CmdRemoveSelectedEmploymentDetails.class.getName());
	private String message = "";

	/**
	 * @author Chinthaka
	 * @param helepr IDataHelper object of Object type 
	 * @param view IView object of object type
	 * @return View object to servlet
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		try {
			
			final Collection<Collection<String>> allSelectedListToRemove = new ArrayList<Collection<String>>();
			
			String sequence = helper.getParameter("maxSequence");
			String deleteList = "";
			
			if(Validator.isNotEmpty(sequence)){
				int maxIndex = Integer.parseInt(sequence);				
				
				final Collection<String> singleSelectedListToRemove = new ArrayList<String>();
				for(int i = 1; i <= maxIndex+1; i++){

					if(Validator.isNotEmpty(helper.getParameter("isSelected"+i)) && helper.getParameter("isSelected"+i).equals("1")){
						if(deleteList == ""){
							deleteList = helper.getParameter("employmentCode"+i);
						}else{
							deleteList = deleteList + "," + helper.getParameter("employmentCode"+i);
						}			
					}
				}
			}
			
			final EmploymentDAO employment = new EmploymentDAO();
			employment.deleteMultiple(deleteList);

			Collection<Collection<String>> allSelectedFeaturedCourseProviderList = new ArrayList<Collection<String>>();			
			allSelectedFeaturedCourseProviderList = FeaturedCourseProviderDAO.getTutorSelectedFCP(helper.getParameter("tutorCode1"));			
			view.setCollection(allSelectedFeaturedCourseProviderList);			
			
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
		}
		return view;
	}
}
