package com.genesiis.campus.command;

//20161206 PN c26-add-student-details INIT CmdGetSkillDetails.java
//         PN c26-add-student-details implement execute() method.
//		   PN c26-add-student-details modified execute() method to get student's existing skill data.
//20161207 PN c26-add-student-details modified execute() method to get student's existing interest data and all interest data..

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InterestDAO;
import com.genesiis.campus.entity.SkillDAO;
import com.genesiis.campus.entity.StudentInterestDAO;
import com.genesiis.campus.entity.StudentSkillDAO;
import com.genesiis.campus.util.IDataHelper;

public class CmdGetSkillDetails implements ICommand{

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		int StudentCode = 1; // This needs to be assign from the session.
		
		ICrud skillDao = new SkillDAO();
		ICrud studentSkillDao =  new StudentSkillDAO();
		ICrud interestDao = new InterestDAO();
		ICrud studentInterestDao =  new StudentInterestDAO();
		
		Collection<Collection<String>> skillCollection = skillDao.getAll();
		view.setCollection(skillCollection);
		
		Collection<Collection<String>> stskillCollection = studentSkillDao.findById(StudentCode);
		helper.setAttribute("stskillCollection", stskillCollection);
		
		Collection<Collection<String>> interestCollection = interestDao.getAll();
		helper.setAttribute("interestCollection", interestCollection);
		
		Collection<Collection<String>> stinterestCollection = studentInterestDao.findById(StudentCode);
		helper.setAttribute("stinterestCollection", stinterestCollection);
		
		return view;
	}

}
