package com.genesiis.campus.entity;

//20161025 JH c7-list-higher-education-courses data access object class HigherEducationProgrammeDAO.java created
//20161025 JH c7-list-higher-education-courses implement unimplemented methods
//20161025 JH c7-list-higher-education-courses findById method modified
//20161027 JH c7-higher-education-landing-page findById method modified
//20161030 JH c7-higher-education-landing-page findById method modified : fix sql exception
//20161031 JH c7-higher-education-landing-page findById method modified : select filter 20 programmes randomly with towns
//20161101 JH c7-higher-education-landing-page query changes to get multiple towns for a program
//20161102 JH c7-higher-education-landing-page use validator class to get program duration
//20161102 JH c7-higher-education-landing-page query modified to remove expiration date constraint
//20161102 JH c7-higher-education-landing-page findById method modified due to ddl changes

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.Validator;

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
		PreparedStatement preparedStatement2 = null;

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

		
		String getAllQuery = "SELECT TOP 20 p.CODE , p.DURATION, p.NAME, p.DESCRIPTION, cp.LOGOIMAGEPATH, cp.CODE as PROVIDERCODE, cp.NAME as CPNAME, cp.SHORTNAME   "
				+ "FROM [CAMPUS].[PROGRAMME] p INNER JOIN [CAMPUS].[COURSEPROVIDER] cp ON "
				+ "(p.COURSEPROVIDER = cp.CODE AND p.CATEGORY = ? AND	p.PROGRAMMESTATUS = ?  AND COURSEPROVIDERSTATUS = ? ) ORDER BY NEWID()";

		String getTowns = "SELECT TOP 3 t.NAME FROM [CAMPUS].[TOWN] t INNER JOIN [CAMPUS].[PROGRAMMETOWN] pt on pt.TOWN = t.CODE AND pt.PROGRAMME = ? ";
		
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
				
				preparedStatement2 = conn.prepareStatement(getTowns);
				
				int programCode = Integer.parseInt(rs.getString("CODE"));
				preparedStatement2.setInt(1, programCode);
				
				final ResultSet rs2 = preparedStatement2.executeQuery();
								
				final ArrayList<String> singleProgrammeList = new ArrayList<String>();
				
				singleProgrammeList.add(rs.getString("CODE"));
				singleProgrammeList.add(rs.getString("LOGOIMAGEPATH"));
				singleProgrammeList.add(rs.getString("SHORTNAME"));
				singleProgrammeList.add(rs.getString("PROVIDERCODE"));
				singleProgrammeList.add(rs.getString("CPNAME"));
				singleProgrammeList.add(rs.getString("NAME"));
				singleProgrammeList.add(rs.getString("DESCRIPTION"));
			//	singleProgrammeList.add(rs.getString("DURATION"));
				
				String d = rs.getString("DURATION");
				Float du = Float.parseFloat(d);
				int duration = Math.round(du);
				
				Validator validator = new Validator();
				final ArrayList<String> programDuration = (ArrayList<String>) validator.getDuration(duration);
				
				String years = programDuration.get(0);
				String months = programDuration.get(1);
				String days = programDuration.get(2);
				
				singleProgrammeList.add(years);
				singleProgrammeList.add(months);
				singleProgrammeList.add(days);

				while(rs2.next()){
					singleProgrammeList.add(rs2.getString("NAME"));
				}
				

				final Collection<String> singleProgrammeCollection = singleProgrammeList;
				programmeCollection.add(singleProgrammeCollection);

			}

		} catch (SQLException exception) {
			log.error("findById(Object code) sql exception "
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
