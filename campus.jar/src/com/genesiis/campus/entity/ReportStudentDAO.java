package com.genesiis.campus.entity;


//DJ 20161229 c53-report-registered-students-MP-dj created ReportStudentDAO.java

import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.DataHelper;
import com.genesiis.campus.validation.UtilityHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;



public class ReportStudentDAO  implements ICrud{
	
	static org.apache.log4j.Logger log = Logger.getLogger(ReportStudentDAO.class
			.getName());

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
	public Collection<Collection<String>> findById(Object studentDTO)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Student student=new Student();
		final Collection<Collection<String>> registeredStudentList = new ArrayList<Collection<String>>();
		 if(UtilityHelper.isNotEmptyObject(studentDTO)){
			  student=(Student)studentDTO;			 
		 }

		try {
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT STUDENT.CODE AS STUDENTCODE,  CONCAT(STUDENT.FIRSTNAME,' ' ,STUDENT.MIDDLENAME,' ' ,STUDENT.LASTNAME) AS STUDENTNAME, STUDENT.ISACTIVE AS STUDENTSTATUS, STUDENT.CRTON AS REGISTEREDDATE, ");
			sb.append("STUDENT.LASTLOGGEDINDATE AS LASTLOGGEDINDATE  FROM CAMPUS.STUDENT STUDENT WHERE STUDENT.ACCOUNTTYPE=?");
			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1,student.getAccountType());
			
			
			resultSet= stmt.executeQuery();			
			while (resultSet.next()) {
				final ArrayList<String> singleProvider = new ArrayList<String>();
				singleProvider.add(resultSet.getString("STUDENTCODE"));				
				singleProvider.add(resultSet.getString("STUDENTNAME"));	
				singleProvider.add(resultSet.getString("STUDENTSTATUS"));
				singleProvider.add(resultSet.getString("REGISTEREDDATE"));
				singleProvider.add(resultSet.getString("LASTLOGGEDINDATE"));
				registeredStudentList.add(singleProvider);
			}

		} catch (SQLException sqlException) {
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return registeredStudentList;
			
		
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
