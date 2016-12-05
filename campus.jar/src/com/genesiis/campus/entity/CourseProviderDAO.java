package com.genesiis.campus.entity;

//20161031 CM c9-make-inquiry-for-institute INIT CourseProviderDAO.java.
//20161031 CM c9-make-inquiry-for-institute CourseProviderDAO.java modified findById() method.
//20161031 CM c9-make-inquiry-for-institute CourseProviderDAO.java modified findById() method.
//20161115 CM c9-make-inquiry-for-institute CourseProviderDAO.java modified findById() method(Code Review Modifications).
//20161201 AS c9-inquiry-form-for-institute-MP-cm  InquiryEmail variable name change to  inquiryEmail
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProviderInquiry;
import com.genesiis.campus.util.ConnectionManager;

public class CourseProviderDAO implements ICrud{
	static Logger log = Logger.getLogger(CourseProviderDAO.class.getName());
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
	
	/**
	 * Search Course provider details 
	 * 
	 * @author Chathuri
	 * @param Object
	 *            :  CourseProviderInquiry object of Object type
	 * @return Collection<Collection<String>> of Collection
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		final Collection<Collection<String>> inquiryEmail = new ArrayList<Collection<String>>();
		ArrayList<String> singleEmployeeList = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			CourseProviderInquiry courserProviderInquiry = (CourseProviderInquiry) code;
			conn = ConnectionManager.getConnection();
			String query="SELECT * FROM [CAMPUS].[COURSEPROVIDER] WHERE [CAMPUS].[COURSEPROVIDER].CODE = ?";
			preparedStatement = conn
					.prepareStatement(query);
			preparedStatement.setInt(1, courserProviderInquiry.getCourseProvider());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				singleEmployeeList = new ArrayList<String>();
				singleEmployeeList.add(rs.getString("GENERALEMAIL"));
				singleEmployeeList.add(rs.getString("NAME"));
				singleEmployeeList.add(rs.getString("CODE"));
				final Collection<String> singleEmployeeCollection = singleEmployeeList;
				inquiryEmail.add(singleEmployeeCollection);

			}
		} catch (SQLException SQLexception) {
			log.error("findById(Object code): SQLexception " + SQLexception.toString());
			throw SQLexception;
		} catch (Exception exception) {
			log.error("findById(Object code):  exception" + exception.toString());
			throw exception;
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
			

		}
		return inquiryEmail;
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
