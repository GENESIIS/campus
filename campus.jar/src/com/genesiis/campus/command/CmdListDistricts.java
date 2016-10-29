package com.genesiis.campus.command;

//20161029 PN c11-criteria-based-filter-search INIT the class and implemented execute() method.

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
		try {
			Collection<Collection<String>> districtCollection = districtDAO.getAll();
			iview.setCollection(districtCollection);
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
