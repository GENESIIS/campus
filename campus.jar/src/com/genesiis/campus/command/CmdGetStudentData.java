package com.genesiis.campus.command;
//20161124 PN c26-add-student-details: INIT CmdGetStudentData.java class.
//20161125 PN c26-add-student-details: implemented execute() method to load data to student education details
//20161125 PN c26-add-student-details: modified execute() method to load student personal details.
//20161208 PN c26-add-student-details: modified execute() method to load student skills and interest details.
//20161215 PN CAM-28 :modified execute() method to load award details collection and student Experience Collection with DB values.
//20170104 PN CAM-28: modified execute() method to load award details collection and student higher education details Collection with DB values.
//20170117 PN CAM-28: added exception handling to the execute method.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.AwardDAO;
import com.genesiis.campus.entity.Country2DAO;
import com.genesiis.campus.entity.HigherEducationDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InterestDAO;
import com.genesiis.campus.entity.MediumDAO;
import com.genesiis.campus.entity.ProfessionalExperienceDAO;
import com.genesiis.campus.entity.SchoolEducationDAO;
import com.genesiis.campus.entity.SchoolGradeDAO;
import com.genesiis.campus.entity.SkillDAO;
import com.genesiis.campus.entity.StdProfMajorDAO;
import com.genesiis.campus.entity.StudentDAO;
import com.genesiis.campus.entity.StudentInterestDAO;
import com.genesiis.campus.entity.StudentSkillDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import org.apache.log4j.Logger;

public class CmdGetStudentData implements ICommand {
	static Logger log = Logger.getLogger(CmdGetStudentData.class.getName());

	private IView educationData;

	public CmdGetStudentData(IView educationData) {
		this.educationData = educationData;
	}

	public CmdGetStudentData() {

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

		ICrud schoolEducationDao = new SchoolEducationDAO();
		ICrud majorDao = new StdProfMajorDAO();
		ICrud schoolGradeDao = new SchoolGradeDAO();
		ICrud mediumDao = new MediumDAO();
		ICrud country2Dao = new Country2DAO();
		ICrud studentDao = new StudentDAO();
		ICrud skillDao = new SkillDAO();
		ICrud studentSkillDao = new StudentSkillDAO();
		ICrud interestDao = new InterestDAO();
		ICrud studentInterestDao = new StudentInterestDAO();
		ICrud awardDao = new AwardDAO();
		ICrud expDao = new ProfessionalExperienceDAO();
		ICrud highereduDao = new HigherEducationDAO();

		Collection<Collection<String>> schoolEducationCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> majorCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> schoolGradeCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> mediumCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> country2Collection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> studentCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> skillCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> stskillCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> interestCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> stinterestCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> awardCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> stdExpCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> stdHighEduCollection = new ArrayList<Collection<String>>();

		try {
			schoolEducationCollection = schoolEducationDao.findById(StudentCode);
			view.setCollection(schoolEducationCollection);

			majorCollection = majorDao.getAll();
			helper.setAttribute("majorCollection", majorCollection);

			schoolGradeCollection = schoolGradeDao.getAll();
			helper.setAttribute("schoolGradeCollection", schoolGradeCollection);

			mediumCollection = mediumDao.getAll();
			helper.setAttribute("mediumCollection", mediumCollection);

			country2Collection = country2Dao.getAll();
			helper.setAttribute("country2Collection", country2Collection);

			Student student = new Student();
			student.setCode(StudentCode);
			studentCollection = studentDao.findById(student);
			helper.setAttribute("studentCollection", studentCollection);

			skillCollection = skillDao.getAll();
			helper.setAttribute("skillCollection", skillCollection);

			stskillCollection = studentSkillDao.findById(StudentCode);
			helper.setAttribute("stskillCollection", stskillCollection);

			interestCollection = interestDao.getAll();
			helper.setAttribute("interestCollection", interestCollection);

			stinterestCollection = studentInterestDao.findById(StudentCode);
			helper.setAttribute("stinterestCollection", stinterestCollection);

			awardCollection = awardDao.getAll();
			helper.setAttribute("awardCollection", awardCollection);

			stdExpCollection = expDao.findById(StudentCode);
			helper.setAttribute("stdExpCollection", stdExpCollection);

			stdHighEduCollection = highereduDao.findById(StudentCode);
			helper.setAttribute("stdHighEduCollection", stdHighEduCollection);
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
