package com.genesiis.campus.entity.dao;

//20170421 DJ c54-report-course-stats-MP-dj Init:ProgrammeDAOImpl.java
//20170421 DJ c54-report-course-stats-MP-dj Implement method getLightProgrammes()

import com.genesiis.campus.command.CmdReportCourseStats;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.model.ProgrammeSearchDTO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**The class  {@code ProgrammeDAOImpl} is a form of DAO class.
 * The Interface {@code ProgrammeDAOImpl} has precise control over programme dao level manipulations. 
 *  @author dumani DJ   
 */
public class ProgrammeDAOImpl implements ProgrammeICrud{
	
	static Logger log = Logger.getLogger(ProgrammeDAOImpl.class.getName());

	@Override
	public Collection<Collection<String>> getLightProgrammes(ProgrammeSearchDTO programmeSearchDTO) throws SQLException,
			Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		final Collection<Collection<String>> programmeList = new ArrayList<Collection<String>>();
		try {			
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT PROG.CODE AS PROGCODE, PROG.NAME AS PROGNAME ");
			sb.append(" FROM [CAMPUS].PROGRAMME PROG  WHERE 1=1 ");
			if (programmeSearchDTO.getCourseProvider() > 0) {
				sb.append(" AND PROG.COURSEPROVIDER =  ");
				sb.append(programmeSearchDTO.getCourseProvider());
			}
			if (programmeSearchDTO.getProgrammeStatus() >= 0) {
				sb.append(" AND  PROG.PROGRAMMESTATUS =  ");
				sb.append(programmeSearchDTO.getProgrammeStatus());
			}
			
			stmt=conn.prepareStatement(sb.toString());
			resultSet=stmt.executeQuery();
			while (resultSet.next()) {
				final ArrayList<String> singleProgramme = new ArrayList<String>();
				singleProgramme.add(resultSet.getString("PROGCODE"));
				singleProgramme.add(resultSet.getString("PROGNAME"));	
			}
			}catch (SQLException sqlException) {
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

}
