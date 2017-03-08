package com.genesiis.campus.entity;

//20170228 JH c96-public-list-all-tutors PublicTutorDAO.java created
//20170302 JH c96-public-list-all-tutors getAll() method coding 
//20170308 JH c96-public-list-all-tutors getAll() query updated to get details with category, major and qualification 

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class PublicTutorDAO implements ICrud {

	static Logger log = Logger.getLogger(PublicTutorDAO.class.getName());
	
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public Collection findById(Object code) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * getAll() method used to get tutors with their basic information to list for the public user.
	 * @return collection of tutor basic details with category and major details
	 * @author JH
	 */
	public Collection getAll() throws SQLException, Exception {
		final StringBuilder query = new StringBuilder();

		query.append("SELECT t.CODE, t.FIRSTNAME, t.MIDDLENAME, t.LASTNAME, t.EMAIL, t.LANDPHONECOUNTRYCODE, t.LANDPHONEAREACODE, t.LANDPHONENUMBER, t.MOBILEPHONENETWORKCODE, t.MOBILEPHONENUMBER,");
		query.append("tm.MAJOR AS MAJORCODE, m.NAME AS MAJORNAME, tc.CATEGORY AS CATEGORYCODE, c.NAME AS CATEGORYNAME, ");
		query.append("tq.LEVEL, n.CODE AS NVQ, l.NAME AS LEVELNAME, tq.name AS QUALIFICATION, tw.NAME AS TOWN, tw.CODE AS TOWNCODE FROM [CAMPUS].[TUTOR] t ");
		query.append("INNER JOIN [CAMPUS].[TOWN] tw ");
		query.append("ON tw.CODE = t.TOWN ");
		query.append("LEFT JOIN [CAMPUS].[TUTORMAJOR] tm "); 
		query.append("ON tm.TUTOR = t.CODE "); 
		query.append("LEFT JOIN [CAMPUS].[MAJOR] m ");
		query.append("ON tm.MAJOR = m.CODE ");
		query.append("LEFT JOIN [CAMPUS].[TUTORCATEGORY] tc ");
		query.append("ON t.CODE = tc.TUTOR ");
		query.append("LEFT JOIN [CAMPUS].[CATEGORY] c ");
		query.append("ON c.CODE = tc.CATEGORY ");

		query.append("LEFT JOIN ");

		query.append("[CAMPUS].[TUTORQUALIFICATION] tq ");
		query.append("ON t.CODE = tq.TUTOR ");
		query.append("LEFT JOIN [CAMPUS].[LEVEL] l ");
		query.append("ON tq.LEVEL = l.CODE ");
		query.append("LEFT JOIN [CAMPUS].[NVQ] n ");
		query.append("ON n.CODE = l.NVQ ");
		
		query.append("WHERE t.TUTORSTATUS = ?");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Connection conn = null;
		Collection<Collection<String>> tutorCollection = new ArrayList<Collection<String>>();

		try {
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query.toString());
			
			preparedStatement.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Collection<String> singleTutorList = new ArrayList<String>();
				singleTutorList.add(rs.getString("CODE"));
				singleTutorList.add(rs.getString("FIRSTNAME"));
				singleTutorList.add(rs.getString("MIDDLENAME"));
				singleTutorList.add(rs.getString("LASTNAME"));
				singleTutorList.add(rs.getString("EMAIL"));
				singleTutorList.add(rs.getString("LANDPHONECOUNTRYCODE"));
				singleTutorList.add(rs.getString("LANDPHONEAREACODE"));
				singleTutorList.add(rs.getString("LANDPHONENUMBER"));
				singleTutorList.add(rs.getString("MOBILEPHONENETWORKCODE"));
				singleTutorList.add(rs.getString("MOBILEPHONENUMBER"));
				singleTutorList.add(rs.getString("MAJORCODE"));
				singleTutorList.add(rs.getString("MAJORNAME"));
				singleTutorList.add(rs.getString("CATEGORYCODE"));
				singleTutorList.add(rs.getString("CATEGORYNAME"));
				singleTutorList.add(rs.getString("LEVEL"));
				singleTutorList.add(rs.getString("NVQ"));
				singleTutorList.add(rs.getString("LEVELNAME"));
				singleTutorList.add(rs.getString("QUALIFICATION"));
				singleTutorList.add(rs.getString("TOWN"));
				singleTutorList.add(rs.getString("TOWNCODE"));
				
				tutorCollection.add(singleTutorList);
			}

		} catch (SQLException SQLException) {
			log.error("SQL Exception : " + SQLException.toString());
			throw SQLException;

		} catch (Exception exception) {
			log.error("Exception : " + exception.toString());
			throw exception;

		} finally {
			DaoHelper.cleanup(conn, preparedStatement, rs);
		}
		return tutorCollection;
	}

	public int add(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
