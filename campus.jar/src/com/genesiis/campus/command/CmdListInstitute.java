package com.genesiis.campus.command;

//20161101 PN c11-criteria-based-filter-search INIT the class and implemented execute() method.

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteDAO;
import com.genesiis.campus.util.IDataHelper;

public class CmdListInstitute implements ICommand{
	static Logger log = Logger.getLogger(CmdListInstitute.class.getName());

	private IView districtData;

	public CmdListInstitute(IView districtData) {
		this.districtData = districtData;
	}

	public CmdListInstitute() {
		
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
		
		ICrud instituteDAO = new InstituteDAO();
		String categoryCode = helper.getParameter("categoryCode");
		Collection<Collection<String>> instituteCollection = null;
		
		try {
			
			//If:the categoryCode is set
			if ((categoryCode != null) && ((!categoryCode.isEmpty()))) {
				instituteCollection = instituteDAO.findById(Integer.parseInt(categoryCode));
			
			//else:the categoryCode is not set at the beginning of the page loading
			} else {
				instituteCollection = instituteDAO.getAll();
			}

			if (instituteCollection != null) {
				iview.setCollection(instituteCollection);
			}
			
		} catch (SQLException sqle) {
			log.error("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.error("execute() : e" + e.toString());
			throw e;
		}
		return iview;
	}
}
