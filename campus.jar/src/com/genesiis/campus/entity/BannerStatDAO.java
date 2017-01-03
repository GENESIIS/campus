package com.genesiis.campus.entity;

//DJ 20161206 c52-report-banner-statistics-MP-dj created BannerStatDAO.java
//DJ 20161206 c52-report-banner-statistics-MP-dj Initiate findById() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.BannerStatSearchDTO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.UtilityHelper;

public class BannerStatDAO implements ICrud {
	
	static org.apache.log4j.Logger log = Logger.getLogger(BannerStatDAO.class.getName());

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
	public Collection<Collection<String>> findById(Object searchDTO)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet =null;
		Collection<Collection<String>> allBannerStatsList = new ArrayList<Collection<String>>();
		BannerStatSearchDTO bannerStatSearchDTO=new BannerStatSearchDTO();
		try {
			
			if (UtilityHelper.isNotEmptyObject(searchDTO)) {
				bannerStatSearchDTO = (BannerStatSearchDTO) searchDTO;
			} else {
				return allBannerStatsList;
			}
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
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return allBannerStatsList;
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
