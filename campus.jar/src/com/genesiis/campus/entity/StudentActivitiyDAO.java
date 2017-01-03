package com.genesiis.campus.entity;

//20161229 MM c25-student-create-dashboard-MP INIT class and implemented findById(Object) 
//20170101 MM c25-student-create-dashboard-MP Modified query to fetch data from additional tables

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
			
			String query2 = "SELECT 'STUDENT' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, LASTLOGGEDINDATE, LASTLOGGEDINTIME, LASTLOGGEDINUSERAGENT, LASTLOGGEDINIPADDRESS, LASTLOGINAUTHENTICATEDBY, LASTLOGGEDOUTDATE, LASTLOGGEDOUTTIME, '' AS REFNUMBER, '' AS APPLIEDDATE, '' AS APPLIEDTIME, '' AS PROGRAMMENAME, '' AS COURSEPROVIDERNAME FROM [CAMPUS].[STUDENT] s WHERE CODE = 1 "
					+ "UNION ALL "
					+ "SELECT 'HIGHEREDUCATION' AS TABLEINDICATOR, '', MODON, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[HIGHERDUCATION] WHERE STUDENT = 1 GROUP BY MODON "
					+ "UNION ALL "
					+ "SELECT 'SCHOOLEDUCATION' AS TABLEINDICATOR, '', MODON, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[SCHOOLEDUCATION] WHERE STUDENT = 1 GROUP BY MODON "
					+ "UNION ALL "
					+ "SELECT 'PROFESSIONALEXPERIENCE' AS TABLEINDICATOR, '', MODON, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[PROFESSIONALEXPERIENCE] WHERE STUDENT = 1 GROUP BY MODON "
					+ "UNION ALL "
					+ "SELECT 'STUDENTINTEREST' AS TABLEINDICATOR, '', MODON, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[STUDENTINTEREST] WHERE STUDENT = 1 GROUP BY MODON "
					+ "UNION ALL "
					+ "SELECT 'STUDENTSKILL' AS TABLEINDICATOR, '', MODON, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[STUDENTSKILL] WHERE STUDENT = 1 GROUP BY MODON "
					+ "UNION ALL "
					+ "SELECT 'INQUIRY' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[INQUIRY] WHERE STUDENT = 1 "
					+ "UNION ALL  "
					+ "SELECT 'STUDENTCOURSEPROVIDERINQUIRY' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[STUDENTCOURSEPROVIDERINQUIRY] WHERE STUDENT = 1 "
					+ "UNION ALL "
					+ "SELECT 'STUDENTPROGRAMINQUIRY' AS TABLEINDICATOR, CODE, MODON, MODBY, CRTON, CRTBY, '', '', '', '', '', '', '', '', '', '', '', '' FROM [CAMPUS].[STUDENTPROGRAMINQUIRY] WHERE STUDENT = 1 "
					+ "UNION ALL "
					+ "SELECT 'APPLICATION' AS TABLEINDICATOR, a.CODE, a.MODON, a.MODBY, a.CRTON, a.CRTBY, '', '', '', '', '', '', '', a.REFNUMBER, a.APPLIEDDATE, a.APPLIEDTIME, p.NAME AS PROGRAMMENAME, cp.NAME AS COURSEPROVIDERNAME "
					+ "FROM [CAMPUS].[APPLICATION] a "
					+ "JOIN [CAMPUS].[INTAKE] i ON (i.CODE = a.INTAKE) "
					+ "JOIN [CAMPUS].[PROGRAMME] p ON (p.CODE = i.PROGRAMME) "
					+ "JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER) "
					+ "WHERE a.STUDENT = 1";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query2);
//			ps.setInt(1, studentCode);
//			ps.setInt(2, studentCode);
//			ps.setInt(3, studentCode);
//			ps.setInt(4, studentCode);
//			ps.setInt(5, studentCode);
//			ps.setInt(6, studentCode);
//			ps.setInt(7, studentCode);
//			ps.setInt(8, studentCode);
//			ps.setInt(9, studentCode);
//			ps.setInt(10, studentCode);
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
			singleActivity.add(rs.getString("CRTON")); // 3
			singleActivity.add(rs.getString("CRTBY")); // 4
			singleActivity.add(rs.getString("LASTLOGGEDINDATE")); // 5
			singleActivity.add(rs.getString("LASTLOGGEDINTIME")); // 6
			singleActivity.add(rs.getString("LASTLOGGEDINUSERAGENT")); // 7
			singleActivity.add(rs.getString("LASTLOGGEDINIPADDRESS")); // 8
			singleActivity.add(rs.getString("LASTLOGINAUTHENTICATEDBY")); // 9
			singleActivity.add(rs.getString("LASTLOGGEDOUTDATE")); // 10
			singleActivity.add(rs.getString("LASTLOGGEDOUTTIME")); // 11
			singleActivity.add(rs.getString("REFNUMBER")); // 12
			singleActivity.add(rs.getString("APPLIEDDATE")); // 13
			singleActivity.add(rs.getString("APPLIEDTIME")); // 14
			singleActivity.add(rs.getString("PROGRAMMENAME")); // 15
			singleActivity.add(rs.getString("COURSEPROVIDERNAME")); // 16
			singleActivity.add(rs.getString("TABLEINDICATOR")); // 17
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
