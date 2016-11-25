package com.genesiis.campus.command;
//20161124 PN c26-add-student-details: INIT CmdGetSchoolEducationData.java class.
//20161125 PN c26-add-student-details: implemented execute() method to load data to student education details

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.Country2DAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.MajorDAO;
import com.genesiis.campus.entity.MediumDAO;
import com.genesiis.campus.entity.SchoolEducationDAO;
import com.genesiis.campus.entity.SchoolGradeDAO;
import com.genesiis.campus.util.IDataHelper;
import org.apache.log4j.Logger;

public class CmdGetSchoolEducationData implements ICommand {
	static Logger log = Logger.getLogger(CmdGetSchoolEducationData.class.getName());

	private IView educationData;

	public CmdGetSchoolEducationData(IView educationData) {
		this.educationData = educationData;
	}

	public CmdGetSchoolEducationData() {
		
	}

	/**
	 * @author pabodha
	 * @param helper
	 * @param iview
	 * @return iview with selected Category list
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// This needs to be assign from the session.
		int StudentCode = 1;
		
		SchoolEducationDAO schoolEducationDao = new SchoolEducationDAO();
		ICrud majorDao = new MajorDAO();
		ICrud schoolGradeDao = new SchoolGradeDAO();
		ICrud mediumDao = new MediumDAO();
		ICrud country2Dao = new Country2DAO();
		
		Collection<Collection<String>> schoolEducationCollection = schoolEducationDao.findById(StudentCode);
		view.setCollection(schoolEducationCollection);
		
		Collection<Collection<String>> majorCollection = majorDao.getAll();
		helper.setAttribute("majorCollection", majorCollection);

		Collection<Collection<String>> schoolGradeCollection = schoolGradeDao.getAll();
		helper.setAttribute("schoolGradeCollection", schoolGradeCollection);

		Collection<Collection<String>> mediumCollection = mediumDao.getAll();
		helper.setAttribute("mediumCollection", mediumCollection);

		Collection<Collection<String>> country2Collection = country2Dao.getAll();
		helper.setAttribute("country2Collection", country2Collection);

		return view;
	}

}
