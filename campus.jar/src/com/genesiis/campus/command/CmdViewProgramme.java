package com.genesiis.campus.command;

//20161025 CM c13-Display course details INIT CmdViewProgramme.java
//20161025 CM c13-Display course details Modified execute() method. 
//20161025 CM c13-Display course details Modified execute() method. Set ProgrammeView attribute.
//20161026 CM c13-Display course details Modified execute() method. Remove Module attribute.
//20161028 CM c13-Display course details Modified execute() method. Created  programmeView attribute.
//20161028 CM c13-Display course details Modified execute() method. Created  method comment.

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.IntakeDAO;
import com.genesiis.campus.entity.ModuleDAO;
import com.genesiis.campus.entity.ProgrammeDAO;
import com.genesiis.campus.entity.SemesterDAO;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdViewProgramme implements ICommand {

	static Logger log = Logger.getLogger(CmdViewProgramme.class.getName());
	private IView programmeData;

	public CmdViewProgramme() {
		// TODO Auto-generated constructor stub
	}

	public CmdViewProgramme(IView programmeData) {
		this.programmeData = programmeData;
	}

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
		try {
			final Programme programme = new Programme();
			ICrud programmeDAO = new ProgrammeDAO();
			ICrud semesterDAO = new SemesterDAO();
			ICrud intakeDAO = new IntakeDAO();
			ICrud moduleDAO = new ModuleDAO();

			int programmeId = Integer.parseInt(helper
					.getParameter("programmeCode"));

			programme.setCode(programmeId);

			Collection<Collection<String>> programmeDAOCollection = programmeDAO
					.findById(programme);

			Collection<Collection<String>> semesterDAOCollection = semesterDAO
					.findById(programme);

			Collection<Collection<String>> moduleDAOCollection = moduleDAO
					.findById(programme);
			view.setCollection(moduleDAOCollection);

			Collection<Collection<String>> intakeDAOCollection = intakeDAO
					.findById(programme);

			helper.setAttribute("semesterView", semesterDAOCollection);
			helper.setAttribute("programmeView", programmeDAOCollection);
			helper.setAttribute("intakeView", intakeDAOCollection);
		} catch (Exception e) {
			log.info("execute() : e" + e.toString());
			throw e;
		}

		return view;
	}

}
