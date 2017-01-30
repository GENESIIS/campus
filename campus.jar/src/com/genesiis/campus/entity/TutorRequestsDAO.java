package com.genesiis.campus.entity;

//20170130 JH c134-admin-list-new-tutor-requests INIT TutorRequestsDAO.java
//20170130 JH c134-admin-list-new-tutor-requests findById() method coding 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;

public class TutorRequestsDAO implements ICrud{
	static Logger log = Logger.getLogger(TutorRequestsDAO.class.getName());

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
		final String query = "SELECT TUTOR.CODE, USERNAME, FIRSTNAME, MIDDLENAME, LASTNAME, EMAIL, LANDPHONEAREACODE, LANDPHONENUMBER, MOBILEPHONENETWORKCODE, MOBILEPHONENUMBER, "
				+ " ISAPPROVED, ADDRESS1, ADDRESS2, ADDRESS3, TOWN.NAME as TOWNNAME, COUNTRY2.DIALCODE as DIALCODE, COUNTRY2.NAME as COUNTRY, TUTORSTATUS FROM [CAMPUS].[TUTOR] INNER JOIN "
				+ "[CAMPUS].TOWN ON TUTOR.TOWN = TOWN.CODE INNER JOIN [CAMPUS].[COUNTRY2] ON TUTOR.LANDPHONECOUNTRYCODE = COUNTRY2.CODE AND COUNTRY2.CODE NOT IN (-1) ORDER BY TUTOR.CODE DESC AND ISAPPROVED = ?";
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Connection conn = null;
		Collection<Collection<String>> tutorCollection = new ArrayList<Collection<String>>();
		
		try{
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			
			int status = (Integer) code;
			
			preparedStatement.setInt(1, status);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				Collection<String> singleTutorList = new ArrayList<String>();
				singleTutorList.add(rs.getString("CODE"));
				singleTutorList.add(rs.getString("FIRSTNAME"));
				singleTutorList.add(rs.getString("MIDDLENAME"));
				singleTutorList.add(rs.getString("LASTNAME"));
				singleTutorList.add(rs.getString("USERNAME"));
				singleTutorList.add(rs.getString("EMAIL"));
				singleTutorList.add(rs.getString("DIALCODE"));
				singleTutorList.add(rs.getString("LANDPHONEAREACODE"));
				singleTutorList.add(rs.getString("LANDPHONENUMBER"));
				singleTutorList.add(rs.getString("MOBILEPHONENETWORKCODE"));
				singleTutorList.add(rs.getString("MOBILEPHONENUMBER"));
				singleTutorList.add(rs.getString("ISAPPROVED"));
				singleTutorList.add(rs.getString("ADDRESS1"));
				singleTutorList.add(rs.getString("ADDRESS2"));
				singleTutorList.add(rs.getString("ADDRESS3"));
				singleTutorList.add(rs.getString("TOWNNAME"));
				singleTutorList.add(rs.getString("COUNTRY"));
				singleTutorList.add(rs.getString("TUTORSTATUS"));			
				tutorCollection.add(singleTutorList);
			}

			
			
		}catch(SQLException SQLException){
			log.error("SQL Exception : " + SQLException.toString());
			throw SQLException;
			
		}catch(Exception exception){
			log.error("Exception : " + exception.toString());
			throw exception;
			
		}finally{
			DaoHelper.cleanup(conn, preparedStatement, rs);
		}
		return tutorCollection;
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
