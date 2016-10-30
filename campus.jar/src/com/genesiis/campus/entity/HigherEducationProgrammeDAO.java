package com.genesiis.campus.entity;

//20161025 JH c7-list-higher-education-courses data access object class HigherEducationProgrammeDAO.java created
//20161025 JH c7-list-higher-education-courses implement unimplemented methods
//20161025 JH c7-list-higher-education-courses findById method modified
//20161027 JH c7-higher-education-landing-page findById method modified
//20161030 JH c7-higher-education-landing-page findById method modified : fix sql exception

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;

public class HigherEducationProgrammeDAO implements ICrud {

	static org.apache.log4j.Logger log = Logger
			.getLogger(HigherEducationProgrammeDAO.class.getName());

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
	 * findById method is used to get all programmes under the higher education
	 * category.
	 * 
	 * @param code
	 * @return string collection of programmes
	 * @author JH
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		Programme programme = null;
		String returnMessage = "";
		final Collection<Collection<String>> programmeCollection = new ArrayList<Collection<String>>();

		String getAllQuery = "SELECT TOP 20 * FROM [CAMPUS].[PROGRAMME ] WHERE CATEGORY = ?"
				+ " and PROGRAMMESTATUS = ? and DISPLAYSTARTDATE <= GETDATE()  and EXPIRYDATE >= GETDATE() "
				+ "ORDER BY NEWID()";

		try {

			programme = (Programme) code;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(getAllQuery);

			int pCode = programme.getCategory();

			preparedStatement.setInt(1, programme.getCategory());
			preparedStatement.setInt(2, programme.getProgrammeStatus());


			final ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				final ArrayList<String> singleProgrammeList = new ArrayList<String>();

				log.info(rs.getString("CODE"));
				singleProgrammeList.add(rs.getString("CODE"));
				singleProgrammeList.add(rs.getString("NAME"));
				singleProgrammeList.add(rs.getString("EMAIL"));
				singleProgrammeList.add(rs.getString("IMAGE"));
				singleProgrammeList.add(rs.getString("DESCRIPTION"));
				singleProgrammeList.add(rs.getString("DURATION"));
				singleProgrammeList.add(rs.getString("ENTRYREQUIREMENTS"));
				singleProgrammeList.add(rs.getString("COUNSELORNAME"));
				singleProgrammeList.add(rs.getString("COUNSELORPHONE"));
				singleProgrammeList.add(rs.getString("DISPLAYSTARTDATE"));
				singleProgrammeList.add(rs.getString("EXPIRYDATE"));
				singleProgrammeList.add(rs.getString("PROGRAMMESTATUS"));
				singleProgrammeList.add(rs.getString("COURSEPROVIDER"));
				singleProgrammeList.add(rs.getString("MAJOR"));
				singleProgrammeList.add(rs.getString("CATEGORY"));
				singleProgrammeList.add(rs.getString("LEVEL"));
				singleProgrammeList.add(rs.getString("CLASSTYPE"));
				singleProgrammeList.add(rs.getString("CRTON"));
				singleProgrammeList.add(rs.getString("CRTBY"));
				singleProgrammeList.add(rs.getString("MODON"));
				singleProgrammeList.add(rs.getString("MODBY"));

				final Collection<String> singleProgrammeCollection = singleProgrammeList;
				programmeCollection.add(singleProgrammeCollection);

			}

		} catch (SQLException exception) {
			log.error("findById(Object code) sql exception"
					+ exception.toString());
			throw exception;

		} catch (Exception exception) {
			log.error("findById(Object code) " + exception.toString());
			throw exception;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			conn.close();

		}
		return programmeCollection;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {

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
