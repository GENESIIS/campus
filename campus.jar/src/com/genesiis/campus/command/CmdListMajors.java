package com.genesiis.campus.command;

//20161029 PN c11-criteria-based-filter-search INIT the class and implemented execute() method.
//20161102 PN c11-criteria-based-filter-search modified execute() method by giving validation to 'categoryCode'

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.MajorDAO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdListMajors implements ICommand {
	static Logger log = Logger.getLogger(CmdListMajors.class.getName());

	private IView majorData;

	public CmdListMajors(IView majorData) {
		this.majorData = majorData;
	}

	public CmdListMajors() {

	}

	/**
	 * @author pabodha
	 * @param helper
	 * @param iview
	 * @return iview with selected majors list
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException, Exception {
		
		ICrud majorDao = new MajorDAO();
		String categoryCode = helper.getParameter("categoryCode");
		Collection<Collection<String>> majorCollection = null;

		try {
			
			//If:the categoryCode is set
			if ((categoryCode != null) && ((!categoryCode.isEmpty()))) {
				majorCollection = majorDao.findById(Integer.parseInt(categoryCode));
			
			//else:the categoryCode is not set at the beginning of the page loading
			} else {
				majorCollection = majorDao.getAll();
			}
			iview.setCollection(majorCollection);
			
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
