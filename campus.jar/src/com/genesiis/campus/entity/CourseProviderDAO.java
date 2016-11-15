package com.genesiis.campus.entity;
//20161101 AS C8-inquiry-form-for-course CourseProviderDAO created.
//20161102 AS C8-inquiry-form-for-course findbyID method  query modified
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;



import com.genesiis.campus.entity.model.StudentProgrammeInquiry;
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
	 * Search Course provider institute inquiry email 
	 * 
	 * @author Chathuri
	 * @param Object
	 *            :  CourseProviderInquiry object of Object type
	 * @return Collection<Collection<String>> of Collection
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		final Collection<Collection<String>> InquiryEmail = new ArrayList<Collection<String>>();
		ArrayList<String> singleEmployeeList = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			StudentProgrammeInquiry studentProgrammeInquiry = (StudentProgrammeInquiry) code;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn
					.prepareStatement("SELECT COURSEPROVIDER.COURSEINQUIRYEMAIL FROM [CAMPUS].[COURSEPROVIDER] INNER JOIN [CAMPUS].[PROGRAMME] ON COURSEPROVIDER.CODE = PROGRAMME.COURSEPROVIDER  WHERE PROGRAMME.CODE = ?;");
			preparedStatement.setInt(1, studentProgrammeInquiry.getProgramme());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				singleEmployeeList = new ArrayList<String>();
				singleEmployeeList.add(rs.getString("COURSEINQUIRYEMAIL"));
				final Collection<String> singleEmployeeCollection = singleEmployeeList;
				InquiryEmail.add(singleEmployeeCollection);
				
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
		return InquiryEmail;
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