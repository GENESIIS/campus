package com.genesiis.campus.entity;

////20161027 AS C8-inquiry-form-for-course CourseInquiryDAO.java created 
//20161027 AS C8-inquiry-form-for-course add method modified
//20161031 AS C8-inquiry-form-for-course add method  query modified
//20161101 AS C8-inquiry-form-for-course add method  query modified
//20161102 AS C8-inquiry-form-for-course add method  query modified
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.model.StudentProgrammeInquiry;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

public class CourseInquiryDAO implements ICrud {

	static Logger log = Logger.getLogger(CourseInquiryDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {

		String query = "INSERT INTO CAMPUS.STUDENTPROGRAMINQUIRY ( NAME, EMAIL, TELEPHONECOUNTRYCODE, TELEPHONEAREACODE, TELEPHONENUM, INQUIRYTITLE, INQUIRYTEXT, INQUIRYDATE, INQUIRYTIME, STUDENT, PROGRAMME, ISACTIVE, CRTON, CRTBY) VALUES( ?, ?, ?, ?, ?, ?, ?, getdate(), getdate(), '1', '7', '1', getdate(), 'admin') ";
		Connection conn = null;
		PreparedStatement ps = null;
		StudentProgrammeInquiry spi = (StudentProgrammeInquiry) object;
		int status = -1;

		try {

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query.toString());
			ps.setString(1, spi.getStudentName());
			log.info(spi.getStudentName());
			ps.setString(2, spi.getStudentEmail());
			log.info(spi.getStudentEmail());
			ps.setString(3, spi.getTelephoneCountryCode());
			ps.setString(4, spi.getTelephoneAreaCode());
			ps.setString(5, spi.getTelephone());
			ps.setString(6, spi.getInquiryTitle());
			ps.setString(7, spi.getInquiry());

			// ps.setInt(10, spi.getStudent());
		//	 ps.setInt(8, spi.getProgramme());
			// ps.setString(12, "1");
			// ps.setString(14, "admin");
			status = ps.executeUpdate();

		} catch (SQLException exception) {
			log.error("add(): " + exception.toString());
			throw exception;
		} catch (Exception exception) {
			log.error("add(): " + exception.toString());
			throw exception;
		} finally {
			if (ps != null) {
				ps.close();
			}
			conn.close();
		}
		log.info("successfylly insert data Status = "+status);
		return status;
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
