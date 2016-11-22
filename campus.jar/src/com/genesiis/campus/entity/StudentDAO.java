package com.genesiis.campus.entity;

//20161121 MM c25-student-login-create-dashboard-MP INIT StudentDAO.java
//20161121 MM c25-student-login-create-dashboard-MP INIT Implemented findById() 

import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class StudentDAO implements ICrud {

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

		final Collection<Collection<String>> studentList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Student student = (Student) code;
			int studentCode = student.getCode();
			
			String query = "SELECT * FROM [campus].[STUDENT] WHERE CODE = ?";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, studentCode);
			ps.setInt(2, 1);
			ResultSet rs = ps.executeQuery();
			
			retrieveStudentsFromResultSet(rs, studentList);

		} catch (ClassCastException cce) {
			Log.info("findById(Object): ClassCastException: " + cce.toString());
			throw new IllegalArgumentException("The argument passed is not of expected type (Programme)!");
		} catch (SQLException sqle) {
			Log.info("findById(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			Log.info("findById(Object): Exception: " + e.toString());
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return studentList;
	}
	
	private void retrieveStudentsFromResultSet(ResultSet rs, Collection<Collection<String>> studentList) throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleStudent = new ArrayList<String>();
			singleStudent.add(rs.getString("CODE")); //0
			singleStudent.add(rs.getString("NAME")); //1
			singleStudent.add(rs.getString("EMAIL")); //2
			singleStudent.add(rs.getString("IMAGE")); //3
			singleStudent.add(rs.getString("DESCRIPTION")); //4
			singleStudent.add(rs.getString("TOWNCODE")); //20
			singleStudent.add(rs.getString("TOWNNAME")); //21
			singleStudent.add(rs.getString("INDEXNO")); //22
			singleStudent.add(rs.getString("ADDRESS1")); //23
			singleStudent.add(rs.getString("ADDRESS2")); //24
			singleStudent.add(rs.getString("ADDRESS3")); //24
			final Collection<String> singleStudentCollection = singleStudent;
			studentList.add(singleStudentCollection);
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
