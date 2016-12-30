package com.genesiis.campus.entity;

//20161229 MM c25-student-create-dashboard-MP-mm INIT class and implemented findById(Object) 

import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class StudentActivitiyDAO implements ICrud {

	static Logger Log = Logger.getLogger(StudentActivitiyDAO.class.getName());

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
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {

		final Collection<Collection<String>> programmeDetailsCollectionList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Student student = (Student) code;
			
			int studentCode = student.getCode();

			// TODO convert this to a StringBuidler
			String query = "SELECT CODE, MODON, MODBY FROM [CAMPUS].[STUDENT] s "
					+ "UNION ALL SELECT CODE, MODON, MODBY FROM [CAMPUS].[APPLICATION] a WHERE CODE = ? "
					+ "UNION ALL SELECT CODE, MODON, MODBY FROM [CAMPUS].[HIGHEREDUCTAION] he WHERE CODE = ? "
					+ "UNION ALL SELECT CODE, MODON, MODBY FROM [CAMPUS].[SCHOOLEDUCATION] WHERE CODE = ? "
					+ "UNION ALL SELECT CODE, MODON, MODBY FROM [CAMPUS].[PROFESSIONALEXPERIENCE] WHERE CODE = ? "
					+ "UNION ALL SELECT CODE, MODON, MODBY FROM [CAMPUS].[INQUIRY] WHERE CODE = ? "
					+ "UNION ALL SELECT CODE, MODON, MODBY FROM [CAMPUS].[STUDENTCOURSEPROVIDERINQUIRY] WHERE CODE = ? "
					+ "UNION ALL SELECT CODE, MODON, MODBY FROM [CAMPUS].[STUDENTPROGRAMMEINQURY] WHERE CODE = ? "
					+ "UNION ALL SELECT CODE, MODON, MODBY FROM [CAMPUS].[PROFESSIONALEXPERIENCE] WHERE CODE = ? "
					+ "UNION ALL SELECT CODE, MODON, MODBY FROM [CAMPUS].[STUDENTINTEREST] WHERE CODE = ? "
					+ "UNION ALL SELECT CODE, MODON, MODBY FROM [CAMPUS].[STUDENTSKILL] WHERE CODE = ? ";
			
			String query2 = "SELECT * FROM"
					+ "(SELECT 'STUDENT' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, LASTLOGGEDINDATE, LASTLOGGEDINTIME, LASTLOGGEDINUSERAGENT, LASTLOGGEDINIPADDRESS, LASTLOGINAUTHENTICATEDBY, LASTLOGGEDOUTDATE, LASTLOGGEDOUTTIME, '' AS REFNUMBER, '' AS APPLIEDDATE, '' AS APPLIEDTIME, '' AS PROGRAMMENAME, '' AS COURSEPROVIDERNAME FROM [CAMPUS].[STUDENT] s WHERE CODE = 1"
					+ "UNION ALL"
					+ "SELECT 'HIGHEREDUCATION' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[HIGHERDUCATION] WHERE STUDENT = 1" 
					+ "UNION ALL" 
					+ "SELECT 'SCHOOLEDUCATION' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[SCHOOLEDUCATION] WHERE STUDENT = 1" 
					+ "UNION ALL" 
					+ "SELECT 'PROFESSIONALEXPERIENCE' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[PROFESSIONALEXPERIENCE] WHERE STUDENT = 1" 
					+ "UNION ALL" 
					+ "SELECT 'STUDENTINTEREST' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[STUDENTINTEREST] WHERE STUDENT = 1" 
					+ "UNION ALL" 
					+ "SELECT 'STUDENTSKILL' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[STUDENTSKILL] WHERE STUDENT = 1" 
					+ "UNION ALL" 
					+ "SELECT 'INQUIRY' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[INQUIRY] WHERE STUDENT = 1" 
					+ "UNION ALL" 
					+ "SELECT 'STUDENTCOURSEPROVIDERINQUIRY' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[STUDENTCOURSEPROVIDERINQUIRY] WHERE STUDENT = 1" 
					+ "UNION ALL" 
					+ "SELECT 'STUDENTPROGRAMINQUIRY' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[STUDENTPROGRAMINQUIRY] WHERE STUDENT = 1) as tempResult";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query2);
			ps.setInt(1, studentCode);
			ps.setInt(2, studentCode);
			ps.setInt(3, studentCode);
			ps.setInt(4, studentCode);
			ps.setInt(5, studentCode);
			ps.setInt(6, studentCode);
			ps.setInt(7, studentCode);
			ps.setInt(8, studentCode);
			ps.setInt(9, studentCode);
			ps.setInt(10, studentCode);
			ResultSet rs = ps.executeQuery();

			retrieveProgrammesFromResultSet(rs, programmeDetailsCollectionList);

		} catch (ClassCastException cce) {
			Log.info("findById(Object): ClassCastException: " + cce.toString());
			throw new IllegalArgumentException(
					"The argument passed is not of expected type (Student)!");
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
		return programmeDetailsCollectionList;
	}

	private void retrieveProgrammesFromResultSet(ResultSet rs,
			Collection<Collection<String>> activityList) throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleActivity = new ArrayList<String>();
			singleActivity.add(rs.getString("CODE")); // 0
			singleActivity.add(rs.getString("MODON")); // 1
			singleActivity.add(rs.getString("MODBY")); // 2
			final Collection<String> singleActivityCollection = singleActivity;
			activityList.add(singleActivityCollection);
		}
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

}
