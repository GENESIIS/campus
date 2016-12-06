package com.genesiis.campus.command;

//2016206 PN c26-add-student-details INIT CmdGetSkillDetails.java
//        PN c26-add-student-details implement execute() method.

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SkillDAO;
import com.genesiis.campus.util.IDataHelper;

public class CmdGetSkillDetails implements ICommand{

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		
		ICrud skillDao = new SkillDAO();
		
		Collection<Collection<String>> skillCollection = skillDao.getAll();
		view.setCollection(skillCollection);

		return view;
	}

}
