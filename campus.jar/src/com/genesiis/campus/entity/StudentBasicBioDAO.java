package com.genesiis.campus.entity;

//20160104 MM c25-student-dashboard-MP-mm INIT - Initialised class and implemented findById(Object)
//20160104 MM c25-student-dashboard-MP-mm INIT - Modified query used to fetch student profile info 
//				(in findById(Object) to select the most recent school attended, the most recent 
//				higher education qualification and the most recent position held 

import com.genesiis.campus.command.CmdListStudentDashboardDetails;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class StudentBasicBioDAO implements ICrud {
	
	static Logger Log = Logger.getLogger(StudentBasicBioDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		
		final Collection<Collection<String>> studentDetailsCollectionList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Student student = (Student) code;
			
			int studentCode = student.getCode();

			// TODO convert this to a StringBuidler			
			String query = "SELECT s.FIRSTNAME, s.LASTNAME, s.DESCRIPTION, t.NAME AS TOWNNAME, he.INSTITUTE, he.AFFINSTITUTE, se.SCHOOL, "
					+ "c.NAME AS COUNTRYNAME, pe.ORGANIZATION, pe.DESIGNATION, s.CODE "
					+ "FROM [CAMPUS].[STUDENT] s "
					+ "JOIN [CAMPUS].[TOWN] t ON (s.CODE = 1 AND t.CODE = s.TOWN) "
					+ "JOIN [CAMPUS].[HIGHERDUCATION] he ON (s.CODE = he.STUDENT AND he.CODE = (SELECT TOP 1 CODE FROM [CAMPUS].[HIGHERDUCATION] ORDER BY COMPLETIONON DESC)) "
					+ "JOIN [CAMPUS].[SCHOOLEDUCATION] se ON (s.CODE = se.STUDENT AND se.CODE = (SELECT TOP 1 CODE FROM [CAMPUS].[SCHOOLEDUCATION] ORDER BY ACHIVEDON DESC)) "
					+ "JOIN [CAMPUS].[COUNTRY2] c ON (c.CODE = se.COUNTRY) "
					+ "JOIN [CAMPUS].[PROFESSIONALEXPERIENCE] pe ON (s.CODE = pe.STUDENT AND pe.CODE = (SELECT TOP 1 CODE FROM [CAMPUS].[PROFESSIONALEXPERIENCE] ORDER BY ACHIVEDON DESC))";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			retrieveStudentsFromResultSet(rs, studentDetailsCollectionList);

		} catch (ClassCastException cce) {
			Log.error("findById(Object): ClassCastException: " + cce.toString());
			throw new IllegalArgumentException(
					"The argument passed is not of expected type (Student)!");
		} catch (SQLException sqle) {
			Log.error("findById(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			Log.error("findById(Object): Exception: " + e.toString());
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return studentDetailsCollectionList;
	}

	private void retrieveStudentsFromResultSet(ResultSet rs,
			Collection<Collection<String>> studentCollection) throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleStudent = new ArrayList<String>();
			singleStudent.add(rs.getString("FIRSTNAME")); // 0
			singleStudent.add(rs.getString("LASTNAME")); // 1
			singleStudent.add(rs.getString("DESCRIPTION")); // 2
			singleStudent.add(rs.getString("TOWNNAME")); // 3
			singleStudent.add(rs.getString("INSTITUTE")); // 4
			singleStudent.add(rs.getString("AFFINSTITUTE")); // 5
			singleStudent.add(rs.getString("SCHOOL")); // 6
			singleStudent.add(rs.getString("COUNTRYNAME")); // 7
			singleStudent.add(rs.getString("ORGANIZATION")); // 8
			singleStudent.add(rs.getString("DESIGNATION")); // 9
			singleStudent.add(rs.getString("CODE")); // 10
			final Collection<String> singleStudentCollection = singleStudent;
			studentCollection.add(singleStudentCollection);
		}
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
