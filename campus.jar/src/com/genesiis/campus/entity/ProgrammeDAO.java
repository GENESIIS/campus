package com.genesiis.campus.entity;

//DJ 20161128 c51-report-courses-by-course-provider-MP-dj created ProgrammeDAO.java
//DJ 20161221 c51-report-courses-by-course-provider-MP-dj Used ApplicationStatus.getApplicationStatus() in findById()

import com.genesiis.campus.entity.model.ProgrammeSearchDTO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The class {@code ProgrammeDAO} is a form of DAO class.It is  created for the purpose of retrieving and manipulating data from database, for 
 * courses by course provider report generation. 
 * @author dumani DJ
 *
*/
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
	 * Retrieve Programme details against course provider.Developer able to re use the query by adding conditions to the query builder by setting
	 * parameters to ProgrammeSearchDTO
	 * @param programmeDTO ProgrammeSearchDTO
	 * @author dumani DJ
	 * @return Collection od strings
	 */
	@Override
	public Collection<Collection<String>> findById(Object programmeDTO)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		final Collection<Collection<String>> programmeList = new ArrayList<Collection<String>>();
		ProgrammeSearchDTO programme = new ProgrammeSearchDTO();

		try {
			if (UtilityHelper.isNotEmptyObject(programmeDTO)) {
				programme = (ProgrammeSearchDTO) programmeDTO;
			} else {
				return programmeList;
			}
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT PROG.CODE AS PROGCODE, PROG.NAME AS PROGNAME ,  PROG.DESCRIPTION AS PROGDESCRIPTION ,PROV.NAME AS CPNAME , ");
			sb.append(" PROG.PROGRAMMESTATUS AS PROSTATUS, PROG.DISPLAYSTARTDATE AS PROGSTARTDATE, PROG.EXPIRYDATE AS PROGEXPIRYDATE ");
			sb.append(" FROM [CAMPUS].PROGRAMME PROG INNER JOIN  [CAMPUS].COURSEPROVIDER PROV ON PROG.COURSEPROVIDER=PROV.CODE WHERE 1=1 ");
			if (programme.getProviderStatus() > 0) {
				sb.append(" AND  PROV.COURSEPROVIDERSTATUS =  ");
				sb.append(programme.getProviderStatus());
			}
			if (programme.getCourseProvider() > 0) {
				sb.append(" AND PROG.COURSEPROVIDER =  ");
				sb.append(programme.getCourseProvider());
			}
			if (programme.getDisplayStartDate() != null	&& programme.getDisplayStartDate().getTime() > 0) {
				sb.append("AND PROG.DISPLAYSTARTDATE >= ' ");
				sb.append(new java.sql.Date(programme.getDisplayStartDate().getTime()));
				sb.append(" ' ");
			}
			if (programme.getExpiryDate() != null	&& programme.getExpiryDate().getTime() > 0) {
				sb.append("AND PROG.EXPIRYDATE <= '");
				sb.append(new java.sql.Date(programme.getExpiryDate().getTime()));
				sb.append(" ' ");
			}
			if (programme.getProgrammeStatus() > 0) {
				sb.append(" AND PROG.PROGRAMMESTATUS =  ");
				sb.append(programme.getProgrammeStatus());
			}

			stmt = conn.prepareStatement(sb.toString());
			resultSet= stmt.executeQuery();			
			while (resultSet.next()) {
				final ArrayList<String> singleProgramme = new ArrayList<String>();
				singleProgramme.add(resultSet.getString("PROGCODE"));
				singleProgramme.add(resultSet.getString("PROGNAME"));				
				singleProgramme.add(resultSet.getString("PROGDESCRIPTION"));				
				singleProgramme.add(resultSet.getString("CPNAME"));				
				singleProgramme.add(ApplicationStatus.getApplicationStatus(resultSet.getInt("PROSTATUS")));				
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
