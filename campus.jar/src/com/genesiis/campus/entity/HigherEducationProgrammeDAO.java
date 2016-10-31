package com.genesiis.campus.entity;

//20161025 JH c7-list-higher-education-courses data access object class HigherEducationProgrammeDAO.java created
//20161025 JH c7-list-higher-education-courses implement unimplemented methods
//20161025 JH c7-list-higher-education-courses findById method modified
//20161027 JH c7-higher-education-landing-page findById method modified
//20161030 JH c7-higher-education-landing-page findById method modified : fix sql exception
//20161031 JH c7-higher-education-landing-page findById method modified : select filter 20 programmes randomly with towns

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

		// String getAllQuery =
		// "SELECT TOP 20 p.CODE, p.NAME, p.EMAIL, cp.NAME AS PROVIDERNAME, p.DESCRIPTION, p.DURATION, cp.LOGOIMAGEPATH, p.COURSEPROVIDER, "
		// +
		// " t.CODE AS TOWNCODE, t.NAME AS TOWNNAME FROM [CAMPUS].[PROGRAMME] p JOIN [CAMPUS].[COURSEPROVIDER] cp ON (p.COURSEPROVIDER = cp.CODE AND p.CATEGORY = 1 AND"
		// +
		// " p.PROGRAMMESTATUS = 1  AND GETDATE() < p.EXPIRYDATE AND COURSEPROVIDERSTATUS = 1 AND GETDATE() < cp.EXPIRATIONDATE) "
		// +
		// "LEFT JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME AND pt.ISACTIVE = 1) LEFT JOIN [CAMPUS].[TOWN] t ON (pt.TOWN = t.CODE) ORDER BY NEWID()";

		
		
		String getAllQuery = "SELECT a.CODE ,a.DURATION, a.DESCRIPTION,a.LOGOIMAGEPATH, t.NAME, a.PROVIDERCODE, a.CPNAME, a.NAME as PNAME, a.UNIQUEPREFIX   FROM("
				+ "SELECT TOP 20 p.CODE , p.DURATION, p.NAME, p.DESCRIPTION, cp.LOGOIMAGEPATH, cp.CODE as PROVIDERCODE, cp.NAME as CPNAME, cp.UNIQUEPREFIX  "
				+ "FROM [CAMPUS].[PROGRAMME] p INNER JOIN [CAMPUS].[COURSEPROVIDER] cp ON "
				+ " (p.COURSEPROVIDER = cp.CODE AND p.CATEGORY = ? AND	p.PROGRAMMESTATUS = ?  AND GETDATE() < p.EXPIRYDATE AND COURSEPROVIDERSTATUS = ? AND GETDATE() < cp.EXPIRATIONDATE)  "
				+ "ORDER BY NEWID())a JOIN [CAMPUS].[PROGRAMMETOWN] pt on a.CODE= pt.PROGRAMME  LEFT JOIN [CAMPUS].[TOWN] t ON (pt.TOWN = t.CODE)";

		try {

			programme = (Programme) code;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(getAllQuery);

			int pCode = programme.getCategory();

			preparedStatement.setInt(1, programme.getCategory());
			preparedStatement.setInt(2, programme.getProgrammeStatus());
			preparedStatement.setInt(3, 1);

			final ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleProgrammeList = new ArrayList<String>();
				
				singleProgrammeList.add(rs.getString("CODE"));
				singleProgrammeList.add(rs.getString("LOGOIMAGEPATH"));
				singleProgrammeList.add(rs.getString("PROVIDERCODE"));
				singleProgrammeList.add(rs.getString("UNIQUEPREFIX"));
				singleProgrammeList.add(rs.getString("CPNAME"));
				singleProgrammeList.add(rs.getString("PNAME"));
				log.info(rs.getString("PNAME") +" >>>>>>>> " + rs.getString("NAME"));
				singleProgrammeList.add(rs.getString("DESCRIPTION"));
				singleProgrammeList.add(rs.getString("NAME"));
				singleProgrammeList.add(rs.getString("DURATION"));

								
				

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
