package com.genesiis.campus.entity;

//20161121 MM c25-student-login-create-dashboard-MP-mm INIT - Initialised file
//20161122 MM c25-student-login-create-dashboard-MP-mm Added code to retrieve more columns from the result set
//20161122 MM c25-student-login-create-dashboard-MP-mm Fixed logger class import issue

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

public class StudentDAO implements ICrud {

	static Logger Log = Logger.getLogger(StudentDAO.class.getName());

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

		final Collection<Collection<String>> studentDetailsCollectionList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Student student = (Student) code;
			int studentCode = student.getCode();

			String query = "SELECT * FROM [CAMPUS].[STUDENT] WHERE CODE = ?";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, studentCode);
			ResultSet rs = ps.executeQuery();

			retrieveStudentsFromResultSet(rs, studentDetailsCollectionList);

		} catch (ClassCastException cce) {
			Log.info("findById(Object): ClassCastException: " + cce.toString());
			throw new IllegalArgumentException(
					"The argument passed is not of expected type (Programme)!");
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
		return studentDetailsCollectionList;
	}

	private void retrieveStudentsFromResultSet(ResultSet rs,
			Collection<Collection<String>> studentList) throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleStudent = new ArrayList<String>();
			singleStudent.add(rs.getString("CODE")); // 0
			singleStudent.add(rs.getString("USERNAME")); // 1
			singleStudent.add(rs.getString("PASSWORD")); // 2
			singleStudent.add(rs.getString("INDEXNO")); // 3
			singleStudent.add(rs.getString("FIRSTNAME")); // 4
			singleStudent.add(rs.getString("MIDDLENAME")); // 5
			singleStudent.add(rs.getString("LASTNAME")); // 6
			singleStudent.add(rs.getString("DATEOFBIRTH")); // 7
			singleStudent.add(rs.getString("GENDER")); // 8
			singleStudent.add(rs.getString("EMAIL")); // 9
			singleStudent.add(rs.getString("TYPE")); // 10
			singleStudent.add(rs.getString("IMAGEPATH")); // 11
			singleStudent.add(rs.getString("LANDPHONECOUNTRYCODE")); // 12
			singleStudent.add(rs.getString("LANDPHONEAREACODE")); // 13
			singleStudent.add(rs.getString("LANDPHONENO")); // 14
			singleStudent.add(rs.getString("MOBILEPHONECOUNTRYCODE")); // 15
			singleStudent.add(rs.getString("MOBILEPHONEAREACODE")); // 16
			singleStudent.add(rs.getString("MOBILEPHONENO")); // 17
			singleStudent.add(rs.getString("DESCRIPTION")); // 18
			singleStudent.add(rs.getString("FACEBOOKURL")); // 19
			singleStudent.add(rs.getString("TWITTERURL")); // 20
			singleStudent.add(rs.getString("MYSPACEURL")); // 21
			singleStudent.add(rs.getString("LINKEDINURL")); // 22
			singleStudent.add(rs.getString("INSTAGRAMURL")); // 23
			singleStudent.add(rs.getString("VIBERNUMBER")); // 24
			singleStudent.add(rs.getString("WHATSAPPNUMBER")); // 24
			singleStudent.add(rs.getString("ADDRESS1")); // 24
			singleStudent.add(rs.getString("ADDRESS2")); // 24
			singleStudent.add(rs.getString("ADDRESS3")); // 24
			singleStudent.add(rs.getString("TOWN")); // 24
			singleStudent.add(rs.getString("ACCOUNTTYPE")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDINUSERAGENT")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDINSESSIONID")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDINDATE")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDINTIME")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDINIPADDRESS")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDOUTDATE")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDOUTTIME")); // 24
			singleStudent.add(rs.getString("LASTLOGINAUTHENTICATEDBY")); // 24
			singleStudent.add(rs.getString("ISACTIVE")); // 24
			singleStudent.add(rs.getString("CRTON")); // 24
			singleStudent.add(rs.getString("CRTBY")); // 24
			singleStudent.add(rs.getString("MODON")); // 24
			singleStudent.add(rs.getString("MODBY")); // 24
			final Collection<String> singleStudentCollection = singleStudent;
			studentList.add(singleStudentCollection);
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