package com.genesiis.campus.entity;


//DJ 20161229 c53-report-registered-students-MP-dj created ReportStudentDAO.java
//DJ 20170102 c53-report-registered-students-MP-dj Enhanced findById() with date range.
//DJ 20170104 c53-report-registered-students-MP-dj Enhanced findById() District filtering.

import com.genesiis.campus.entity.model.StudentSearchDTO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.DataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
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
	public Collection<Collection<String>> findById(Object studentSearchDTO)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		StudentSearchDTO student=new StudentSearchDTO();
		final Collection<Collection<String>> registeredStudentList = new ArrayList<Collection<String>>();
		 if(UtilityHelper.isNotEmptyObject(studentSearchDTO)){
			  student=(StudentSearchDTO)studentSearchDTO;			 
		 }

		try {
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT STUDENT.CODE AS STUDENTCODE,  CONCAT(STUDENT.FIRSTNAME,' ' ,STUDENT.MIDDLENAME,' ' ,STUDENT.LASTNAME) AS STUDENTNAME, STUDENT.ISACTIVE AS STUDENTSTATUS,");
			sb.append(" STUDENT.CRTON AS REGISTEREDDATE, STUDENT.LASTLOGGEDINDATE AS LASTLOGGEDINDATE,ISNULL(INTEREST.NAME, ' ') AS INTERESTNAME  FROM CAMPUS.STUDENT STUDENT ");
			sb.append(" INNER JOIN [CAMPUS].[TOWN] TOWN ON TOWN.CODE = STUDENT.TOWN ");
		    sb.append(" INNER JOIN [CAMPUS].[DISTRICT] DISTRICT ON DISTRICT.CODE = TOWN.DISTRICT  ");
		    sb.append(" LEFT JOIN [CAMPUS].[STUDENTINTEREST] STUDENTINTEREST ON STUDENTINTEREST.STUDENT=STUDENT.CODE ");
		    sb.append(" LEFT JOIN [CAMPUS].[INTEREST]  INTEREST ON STUDENTINTEREST.INTEREST=INTEREST.CODE ");
			sb.append(" WHERE 1=1 ");
			if (student.getAccountType() > 0) {
				sb.append("AND STUDENT.ACCOUNTTYPE= ");
				sb.append(student.getAccountType());
			}
			if (student.getStudentStatus() >= 0) {
				sb.append("AND	STUDENT.ISACTIVE= ");
				sb.append(student.getStudentStatus());
			}
			if (student.getDistrictCode() > 0) {
				sb.append("AND DISTRICT.CODE = ");
				sb.append(student.getDistrictCode());
			}
			
			if ((student.getFromDate() != null && student.getFromDate()	.getTime() > 0)	&& (student.getToDate() != null && student.getToDate().getTime() > 0)) {
				sb.append("AND STUDENT.CRTON BETWEEN ' ");
				sb.append(new java.sql.Date(student.getFromDate().getTime()));
				sb.append(" ' AND ' ");
				sb.append(new java.sql.Date(student.getToDate().getTime()));
				sb.append(" '  ");
			} else if (student.getFromDate() != null && student.getFromDate().getTime() > 0) {
				sb.append("AND STUDENT.CRTON >= ' ");
				sb.append(new java.sql.Date(student.getFromDate().getTime()));
				sb.append("'");
			} else if (student.getToDate() != null	&& student.getToDate().getTime() > 0) {
				sb.append("AND STUDENT.CRTON <= ' ");
				sb.append(new java.sql.Date(student.getToDate().getTime()));
				sb.append("'");			
			}
			
			stmt = conn.prepareStatement(sb.toString());			
			resultSet= stmt.executeQuery();			
			while (resultSet.next()) {
				final ArrayList<String> singleProvider = new ArrayList<String>();
				singleProvider.add(resultSet.getString("STUDENTCODE"));				
				singleProvider.add(resultSet.getString("STUDENTNAME"));	
				singleProvider.add(resultSet.getString("INTERESTNAME"));
				singleProvider.add(ApplicationStatus.getApplicationStatus(resultSet.getInt("STUDENTSTATUS")));
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
