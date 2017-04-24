package com.genesiis.campus.entity;

//20170228 JH c96-public-list-all-tutors PublicTutorDAO.java created
//20170302 JH c96-public-list-all-tutors getAll() method coding 
//20170308 JH c96-public-list-all-tutors getAll() query updated to get details with category, major and qualification 
//20170314 JH c96-public-list-all-tutors getAll() method changed to implement a stored procedure call, added method comments and removed unwanted imports
//20170320 JH c96-public-list-all-tutors fixed missing @Override annotation and method signature changes from IDCrud
//20170418 JH c135-public-display-tutor-profile findById() method coding wip
//20170424 JH c135-public-display-tutor-profile return result set data in findById() wip, fixed index out of bound error and fixed error 'too many arguments specified' 

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;

import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class PublicTutorDAO implements ICrud {

	static Logger log = Logger.getLogger(PublicTutorDAO.class.getName());

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
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {

		CallableStatement callableStatement = null;
		ResultSet rs = null;
		Connection conn = null;
		Collection<Collection<String>> tutorCollection = new ArrayList<Collection<String>>();

		try {
			conn = ConnectionManager.getConnection();
			String tutorCode = (String) code;

			callableStatement = conn.prepareCall("{call [CAMPUS].[select-tutor-public-profile-details](?,?,?,?)}");
			callableStatement.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			callableStatement.setInt(2, ApplicationStatus.ACTIVE.getStatusValue());
			callableStatement.setInt(3, ApplicationStatus.ACTIVE.getStatusValue());
			callableStatement.setInt(4, Integer.parseInt(tutorCode) );
			callableStatement.executeQuery();

			rs = callableStatement.executeQuery();

			while (rs.next()) {
				Collection<String> singleTutorList = new ArrayList<String>();

				singleTutorList.add(rs.getString("USERNAME"));
				singleTutorList.add(rs.getString("PASSWORD"));
				singleTutorList.add(rs.getString("FIRSTNAME"));
				singleTutorList.add(rs.getString("MIDDLENAME"));
				singleTutorList.add(rs.getString("LASTNAME"));
				singleTutorList.add(rs.getString("GENDER"));
				singleTutorList.add(rs.getString("EMAIL"));
				singleTutorList.add(rs.getString("LANDPHONECOUNTRYCODE"));
				singleTutorList.add(rs.getString("LANDPHONEAREACODE"));
				singleTutorList.add(rs.getString("LANDPHONENUMBER"));
				singleTutorList.add(rs.getString("MOBILEPHONENETWORKCODE"));
				singleTutorList.add(rs.getString("MOBILEPHONENUMBER"));
				singleTutorList.add(rs.getString("DESCRIPTION"));
				singleTutorList.add(rs.getString("EXPERIENCE"));
				singleTutorList.add(rs.getString("WEBLINK"));
				singleTutorList.add(rs.getString("FACEBOOKURL"));
				singleTutorList.add(rs.getString("TWITTERURL"));
				singleTutorList.add(rs.getString("MYSPACEURL"));
				singleTutorList.add(rs.getString("LINKEDINURL"));
				singleTutorList.add(rs.getString("INSTAGRAMURL"));
				singleTutorList.add(rs.getString("WHATSAPPNUMBER"));
				singleTutorList.add(rs.getString("VIBERNUMBER"));
				singleTutorList.add(rs.getString("ISAPPROVED"));
				singleTutorList.add(rs.getString("ADDRESS1"));			
				singleTutorList.add(rs.getString("ADDRESS2"));
				singleTutorList.add(rs.getString("ADDRESS3"));
				singleTutorList.add(rs.getString("CRTBY"));
				singleTutorList.add(rs.getString("MODBY"));
				singleTutorList.add(rs.getString("TUTORSTATUS"));
				singleTutorList.add(rs.getString("TOWN"));
				singleTutorList.add(rs.getString("COUNTRY"));
				singleTutorList.add(rs.getString("QUALIFICATIONCODE"));
				singleTutorList.add(rs.getString("QUALIFICATION"));
				singleTutorList.add(rs.getString("QDESCRIPTION"));
				singleTutorList.add(rs.getString("QLEVEL"));
				singleTutorList.add(rs.getString("TUTOREX"));
				singleTutorList.add(rs.getString("ORGANIZATION"));
				singleTutorList.add(rs.getString("INDUSTRY"));
				singleTutorList.add(rs.getString("JOBCATEGORY"));
				singleTutorList.add(rs.getString("COMMENCEDON"));
				singleTutorList.add(rs.getString("COMPLETIONON"));
				singleTutorList.add(rs.getString("MODULENAME"));
				singleTutorList.add(rs.getString("MODULECODE"));
				singleTutorList.add(rs.getString("MTUTOR"));
				singleTutorList.add(rs.getString("PROGRAMNAME"));
				singleTutorList.add(rs.getString("CPCODE"));
				singleTutorList.add(rs.getString("COURSEPROVIDRE"));
				singleTutorList.add(rs.getString("EMPTUTOR"));

				tutorCollection.add(singleTutorList);
			}

		} catch (SQLException SQLException) {
			log.error("SQL Exception : " + SQLException.toString());
			throw SQLException;

		} catch (Exception exception) {
			log.error("Exception : " + exception.toString());
			throw exception;

		} finally {
			DaoHelper.cleanup(conn, callableStatement, rs);
		}
		return tutorCollection;

	}

	/**
	 * getAll() method used to get tutors with their basic information to list
	 * for the public user. This includes tutor details related to TUTOR table
	 * as well as program MODULE table which has a foreign key relationship to
	 * the tutor table.
	 * 
	 * 
	 * @return collection of tutor basic details with category, major and
	 *         highest qualification details
	 * @author JH
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {

		final StringBuilder query = new StringBuilder();

		CallableStatement callableStatement = null;
		ResultSet rs = null;
		Connection conn = null;
		Collection<Collection<String>> tutorCollection = new ArrayList<Collection<String>>();

		try {
			conn = ConnectionManager.getConnection();

			/*
			 * callable statement public_list_all_tutors is created to avoid
			 * multiple database calls. Due to the Collection return type of the
			 * method it is impossible to return separate result sets which are
			 * related to tutor table and program module table. Therefore the
			 * following procedure call is created
			 */
			callableStatement = conn.prepareCall("{call [CAMPUS].[public_list_all_tutors](?)}");
			callableStatement.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			callableStatement.executeQuery();

			rs = callableStatement.executeQuery();

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
			DaoHelper.cleanup(conn, callableStatement, rs);
		}
		return tutorCollection;

	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
