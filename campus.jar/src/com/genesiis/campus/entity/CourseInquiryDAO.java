package com.genesiis.campus.entity;
////20161027 AS C8-inquiry-form-for-course CourseInquiryDAO.java created 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.model.StudentProgrammeInquiry;

public class CourseInquiryDAO implements ICrud{

	@Override
	public int add(Object object) throws SQLException, Exception {
	
		String query = "INSERT INTO [campus.STUDENTPROGRAMINQUIRY] ( NAME, EMAIL, TELEPHONECOUNTRYCODE, TELEPHONEAREACODE, TELEPHONENUMBER, INQUARYTITLE, INQUARY ) VALUES (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		StudentProgrammeInquiry spi = (StudentProgrammeInquiry) object;
		int status = 0;
		
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
		// TODO Auto-generated method stub
		return null;
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
