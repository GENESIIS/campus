package com.genesiis.campus.command;

//20161206 PN c26-add-student-details INIT CmdGetSkillDetails.java
//         PN c26-add-student-details implement execute() method.
//		   PN c26-add-student-details modified execute() method to get student's existing skill data.
//20161207 PN c26-add-student-details modified execute() method to get student's existing interest data and all interest data.
//20170117 PN CAM-28: added exception handling to the execute method.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InterestDAO;
import com.genesiis.campus.entity.SkillDAO;
import com.genesiis.campus.entity.StudentInterestDAO;
import com.genesiis.campus.entity.StudentSkillDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

public class CmdGetSkillDetails implements ICommand {
	static Logger log = Logger.getLogger(CmdGetSkillDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		int StudentCode = 1; // This needs to be assign from the session.

		ICrud skillDao = new SkillDAO();
		ICrud studentSkillDao = new StudentSkillDAO();
		ICrud interestDao = new InterestDAO();
		ICrud studentInterestDao = new StudentInterestDAO();
		Collection<Collection<String>> skillCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> stskillCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> interestCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> stinterestCollection = new ArrayList<Collection<String>>();

		try {
			skillCollection = skillDao.getAll();
			view.setCollection(skillCollection);

			stskillCollection = studentSkillDao.findById(StudentCode);
			helper.setAttribute("stskillCollection", stskillCollection);

			interestCollection = interestDao.getAll();
			helper.setAttribute("interestCollection", interestCollection);

			stinterestCollection = studentInterestDao.findById(StudentCode);
			helper.setAttribute("stinterestCollection", stinterestCollection);
		} catch (SQLException sqle) {
			log.error("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.error("execute() : e" + e.toString());
			throw e;
		}
		return view;
	}

}
