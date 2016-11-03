package com.genesiis.campus.command;

//20161029 PN c11-criteria-based-filter-search INIT the class and implemented execute() method.
//20161102 PN c11-criteria-based-filter-search modified execute() method by giving validation to 'categoryCode'

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;


import com.genesiis.campus.entity.DistrictDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

public class CmdListDistricts implements ICommand{
	static Logger log = Logger.getLogger(CmdListDistricts.class.getName());

	private IView districtData;

	public CmdListDistricts(IView districtData) {
		this.districtData = districtData;
	}

	public CmdListDistricts() {
		
	}

	/**
	 * @author pabodha
	 * @param helper
	 * @param iview
	 * @return iview with selected districts list
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException, Exception {
	
		
		ICrud districtDAO = new DistrictDAO();
		Collection<Collection<String>> districtCollection = null;
		String instituteCode = helper.getParameter("instituteCode");
		
		try {
			//If:the instituteCode is set
			if ((instituteCode != null) && ((!instituteCode.isEmpty()))) {
				districtCollection = districtDAO.findById(Integer.parseInt(instituteCode));
			
			//else:the instituteCode is not set at the beginning of the page loading
			} else {
				districtCollection = districtDAO.getAll();
			}
			
			if (districtCollection != null) {
				iview.setCollection(districtCollection);
			}
			
		} catch (SQLException sqle) {
			log.info("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.info("execute() : e" + e.toString());
			throw e;
		}
		return iview;
	}
}
