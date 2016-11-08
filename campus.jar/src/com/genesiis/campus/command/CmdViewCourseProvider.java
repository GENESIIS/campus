package com.genesiis.campus.command;

//20161108 CM c9-make-inquiry-for-institute INIT CmdViewCourseProvider.java

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProviderInquiry;
import com.genesiis.campus.util.IDataHelper;

public class CmdViewCourseProvider implements ICommand {

	static Logger log = Logger.getLogger(CmdViewCourseProvider.class.getName());

	private int corseProviderCode;

	/**
	 * @author Chathuri
	 * @param helepr
	 *            IDataHelper object of Object type view IView object of object
	 *            type
	 * @return View object to servlet
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "";

		try {
			final CourseProviderInquiry instituteInquiry = new CourseProviderInquiry();
			final CourseProviderDAO courseProviderDAO = new CourseProviderDAO();
			corseProviderCode = Integer.parseInt(helper
					.getParameter("courseProviderCode"));
			instituteInquiry.setCourseProvider(corseProviderCode);
			Collection<Collection<String>> courseProviderCollection = courseProviderDAO
					.findById(instituteInquiry);
			
			view.setCollection(courseProviderCollection);
			
		} catch (Exception exception) {
			log.error("execute() : " + exception);
			throw exception;
		}
		return view;
	}

}
