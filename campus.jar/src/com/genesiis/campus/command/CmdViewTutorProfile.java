package com.genesiis.campus.command;

//20161220 CW c38-view-update-tutor-profile-cw INIT CmdViewTutorProfile.java
//20170207 CW c38-view-update-tutor-profile-cw execute() method

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

/**
 * this class used to view the tutor details for given tutor code 
 * further it implements ICommand interface
 * @author CW
 */
public class CmdViewTutorProfile implements ICommand {

	static Logger log = Logger.getLogger(CmdViewTutorProfile.class.getName());
	private String tutorCode = "0";
	private String message = "";

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		try {
			final TutorDAO tutorDAO = new TutorDAO();
			final Tutor tutor = new Tutor();
			Collection<Collection<String>> tutorViewCollection = new ArrayList<Collection<String>>();
			tutorCode = helper.getParameter("tutorCode");
			tutor.setCode(Integer.parseInt(tutorCode));
			
			if (Validator.isNotEmpty(tutorCode)){
				tutorViewCollection = tutorDAO.findById(tutor);
			}
			
			view.setCollection(tutorViewCollection);			
			
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);

		}
		return view;
	}

}
