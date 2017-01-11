package com.genesiis.campus.entity.dao;

//20170111 DJ c52-report-banner-statistics-MP-dj Initiated AdminReportDAOImpl.java
//20170111 DJ c52-report-banner-statistics-MP-dj Implemented getBannerStatisticReport() method.

import com.genesiis.campus.entity.AdminReportICrud;
import com.genesiis.campus.entity.model.BannerStatSearchDTO;
import com.genesiis.campus.entity.model.StudentSearchDTO;
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

public class AdminReportDAOImpl implements AdminReportICrud{
	
	static Logger log = Logger.getLogger(AdminReportDAOImpl.class.getName());

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

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Retrieve banner statistic information for banner statistic report generation.
	 * @param bannerStatSearchDTO BannerStatSearchDTO DTO entity
	 * @author DJ
	 * @return Collection
	 */
	@Override
	public Collection<Collection<String>> getBannerStatisticReport(BannerStatSearchDTO bannerStatSearchDTO) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet =null;
		Collection<Collection<String>> allBannerStatsList = new ArrayList<Collection<String>>();		
		try {			
			conn=ConnectionManager.getConnection();			
			final StringBuilder sb = new StringBuilder(" SELECT BANNER.CODE AS BANNERCODE, BANNERSTAT.VIEWDATE,  COUNT(BANNERSTAT.CODE) AS BANNERHITCOUNT FROM [CAMPUS].PAGE PAGE ");
			sb.append("INNER JOIN [CAMPUS].PAGESLOT PAGESLOT ON PAGE.CODE=PAGESLOT.PAGE ");
			sb.append("INNER JOIN [CAMPUS].BANNER BANNER  ON PAGESLOT.CODE= BANNER.PAGESLOT ");
			sb.append("INNER JOIN [CAMPUS].BANNERSTAT BANNERSTAT ON BANNER.CODE=BANNERSTAT.BANNER  WHERE 1=1 ");
			if (bannerStatSearchDTO.getPageCode() > 0) {
				sb.append(" AND  PAGE.CODE= ");
				sb.append(bannerStatSearchDTO.getPageCode());
			}
			if (bannerStatSearchDTO.getPageSlotCode() > 0) {
				sb.append(" AND PAGESLOT.CODE= ");
				sb.append(bannerStatSearchDTO.getPageSlotCode());
			}
			if (bannerStatSearchDTO.getBannerProviderCode() > 0) {
				sb.append(" AND BANNER.ADVERTISER= ");
				sb.append(bannerStatSearchDTO.getBannerProviderCode());
			}
			if ((bannerStatSearchDTO.getFromDate() != null && bannerStatSearchDTO.getFromDate()	.getTime() > 0)	&& (bannerStatSearchDTO.getToDate() != null && bannerStatSearchDTO.getToDate().getTime() > 0)) {
				sb.append("AND BANNERSTAT.VIEWDATE BETWEEN ' ");
				sb.append(new java.sql.Date(bannerStatSearchDTO.getFromDate().getTime()));
				sb.append(" ' AND ' ");
				sb.append(new java.sql.Date(bannerStatSearchDTO.getToDate().getTime()));
				sb.append(" '  ");
			} else 	if (bannerStatSearchDTO.getFromDate() != null && bannerStatSearchDTO.getFromDate().getTime() > 0) {
				sb.append("AND BANNERSTAT.VIEWDATE >= ' ");
				sb.append(new java.sql.Date(bannerStatSearchDTO.getFromDate().getTime()));
				sb.append("'");
			} else if (bannerStatSearchDTO.getToDate() != null	&& bannerStatSearchDTO.getToDate().getTime() > 0) {
				sb.append("AND BANNERSTAT.VIEWDATE <= ' ");
				sb.append(new java.sql.Date(bannerStatSearchDTO.getToDate().getTime()));
				sb.append("'");			
			}
			sb.append(" GROUP BY BANNER.CODE,BANNERSTAT.VIEWDATE ");

			stmt = conn.prepareStatement(sb.toString());

			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				final ArrayList<String> bannerList = new ArrayList<String>();
				bannerList.add(resultSet.getString("BANNERCODE"));
				bannerList.add(resultSet.getString("VIEWDATE"));
				bannerList.add(resultSet.getString("BANNERHITCOUNT"));
				allBannerStatsList.add(bannerList);
			}			
			
		} catch (SQLException sqlException) {
			log.info("getBannerStatisticReport() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getBannerStatisticReport() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return allBannerStatsList;
	}
	
	/**
	 * Method returns student details.Student table joins with town,district,student interest and interest tables.Cann be reuse populating particular values to 
	 * StudentSearchDTO and by building query.
	 * @param studentSearchDTO StudentSearchDTO
	 * @author dumani DJ
	 * @return Collection of strings
	 */
	@Override
	public Collection<Collection<String>> getRegisteredStudentReport(
			StudentSearchDTO searchDTO) throws SQLException, Exception {
		
		return null;
	}

}
