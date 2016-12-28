package com.genesiis.campus.command;

//20161206 PN c26-add-student-details INIT CmdAddStudentSkillDetails.java. Implemented execute() method.
//20161207 PN c26-add-student-details: modified execute() method by adding status messages.

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentSkillDAO;
import com.genesiis.campus.entity.model.StudentSkill;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

public class CmdAddStudentSkillDetails implements ICommand {
	static Logger log = Logger.getLogger(CmdAddStudentSkillDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// This needs to be assign from the session.
		int StudentCode = 1;
		String[] oldStudentSkills = helper.getParameter("oldStudentSkills").split(",");
		String[] newStudentSkills = helper.getParameter("newStudentSkills").split(",");
		String message = "";

		ICrud skillDao = new StudentSkillDAO();
		Connection connection = ConnectionManager.getConnection();
		try {	
			// Commit false till the updations/additions successfully
			// completed.
			connection.setAutoCommit(false);
			
			if (oldStudentSkills.length > newStudentSkills.length) {
				log.info("Delete Diff.");
				List diff = Validator.subtract(Arrays.asList(oldStudentSkills), Arrays.asList(newStudentSkills));
				for (Object object : diff) {
					log.info("object " + object);
					StudentSkill skill = new StudentSkill();
					skill.setStudent(StudentCode);
					skill.setSkill(Integer.parseInt(object.toString()));
					skillDao.delete(skill, connection);
				}
			} else if (oldStudentSkills.length < newStudentSkills.length) {
				log.info("Add Diff.");
				List diff = Validator.subtract(Arrays.asList(newStudentSkills), Arrays.asList(oldStudentSkills));
				for (Object object : diff) {
					log.info("object " + object);
					StudentSkill skill = new StudentSkill();
					skill.setStudent(StudentCode);
					skill.setSkill(Integer.parseInt(object.toString()));
					skill.setCrtBy("USER");
					skillDao.add(skill, connection);
				}
			} else {
				List diff = Validator.subtract(Arrays.asList(oldStudentSkills), Arrays.asList(newStudentSkills));
				for (Object object : diff) {
					log.info("old object " + object);
					StudentSkill skill = new StudentSkill();
					skill.setStudent(StudentCode);
					skill.setSkill(Integer.parseInt(object.toString()));
					skillDao.delete(skill, connection);
				}

				List diff1 = Validator.subtract(Arrays.asList(newStudentSkills), Arrays.asList(oldStudentSkills));
				for (Object object : diff1) {
					log.info("new object " + object);
					StudentSkill skill = new StudentSkill();
					skill.setStudent(StudentCode);
					skill.setSkill(Integer.parseInt(object.toString()));
					skill.setCrtBy("USER");
					skillDao.add(skill, connection);
				}
			}	
			message = SystemMessage.SUCCESS.message();
			// Commit if all the updations/additions successfully completed.
			connection.commit();
			
			Collection<Collection<String>> studentSkillCollection = skillDao.findById(StudentCode);
			view.setCollection(studentSkillCollection);		
		} catch (SQLException sqle) {
			connection.rollback();
			message = SystemMessage.ERROR.message();
			log.error("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			connection.rollback();
			message = SystemMessage.ERROR.message();
			log.error("execute() : e" + e.toString());
			throw e;
		}finally{
			if(connection != null){
				connection.close();
			}
		}
		helper.setAttribute("skillChangesStatus", message);
		return view;
	}
}
