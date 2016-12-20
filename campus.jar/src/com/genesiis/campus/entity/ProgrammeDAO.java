package com.genesiis.campus.entity;

//DJ 20161128 c51-report-courses-by-course-provider-MP-dj created ProgrammeDAO.java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

public class ProgrammeDAO implements ICrud {

	static org.apache.log4j.Logger log = Logger.getLogger(ProgrammeDAO.class
			.getName());

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
	 * Retrieve Programmes result set
	 * 
	 * @param ProgrammeDTO
	 * @author DJ
	 * @return Collection
	 */
	public Collection<Collection<String>> findById(Object programmeDTO)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		final Collection<Collection<String>> programmeList = new ArrayList<Collection<String>>();
		Programme programme = new Programme();

		try {
			if (UtilityHelper.isNotEmptyObject(programmeDTO)) {
				programme = (Programme) programmeDTO;
			} else {
				return programmeList;
			}
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT PROG.CODE AS PROGCODE, PROG.NAME AS PROGNAME , DESCRIPTION AS PROGDESCRIPTION, ");
			sb.append("PROGRAMMESTATUS AS PROSTATUS,DISPLAYSTARTDATE AS PROGSTARTDATE,EXPIRYDATE AS PROGEXPIRYDATE ");
			sb.append("FROM [CAMPUS].PROGRAMME PROG WHERE PROG.COURSEPROVIDER = ? ");
			if (programme.getDisplayStartDate() != null	&& programme.getDisplayStartDate().getTime() > 0) {
				sb.append("AND PROG.DISPLAYSTARTDATE >=  ");
				sb.append(new java.sql.Date(programme.getDisplayStartDate().getTime()));
			}
			if (programme.getDisplayStartDate() != null	&& programme.getDisplayStartDate().getTime() > 0) {
				sb.append("AND PROG.EXPIRYDATE <=  ");
				sb.append(new java.sql.Date(programme.getExpiryDate().getTime()));
			}
			sb.append(" AND PROG.PROGRAMMESTATUS=?  ");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, programme.getCourseProvider());
			//stmt.setDate(2, new java.sql.Date(programme.getDisplayStartDate().getTime()));
			//stmt.setDate(3, new java.sql.Date(programme.getExpiryDate().getTime()));
			stmt.setInt(2, programme.getProgrammeStatus());
			
			resultSet= stmt.executeQuery();			
			while (resultSet.next()) {
				final ArrayList<String> singleProgramme = new ArrayList<String>();
				singleProgramme.add(resultSet.getString("PROGCODE"));
				singleProgramme.add(resultSet.getString("PROGNAME"));				
				singleProgramme.add(resultSet.getString("PROGDESCRIPTION"));				
				singleProgramme.add(resultSet.getString("PROSTATUS"));				
				singleProgramme.add(resultSet.getString("PROGSTARTDATE"));				
				singleProgramme.add(resultSet.getString("PROGEXPIRYDATE"));				
				programmeList.add(singleProgramme);
			}

		} catch (SQLException sqlException) {
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return programmeList;
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

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
