package com.genesiis.campus.command;

//20161029 PN c11-criteria-based-filter-search INIT the class and implemented execute() method.
//20161101 PN c11-criteria-based-filter-search modified execute() method parameter.

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.LevelDAO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdListLevels implements ICommand{
	static Logger log = Logger.getLogger(CmdListLevels.class.getName());

	private IView levelData;

	public CmdListLevels(IView levelData) {
		this.levelData = levelData;
	}

	public CmdListLevels() {
		
	}

	/**
	 * @author pabodha
	 * @param helper
	 * @param iview
	 * @return iview with selected levels list
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException, Exception {
	
		
		ICrud levelDAO = new LevelDAO();
		try {
			int categoryCode =  Integer.parseInt(helper.getParameter("categoryCode"));
			log.info("categoryCode "+categoryCode);
			Collection<Collection<String>> levelCollection = levelDAO.findById(categoryCode);
			iview.setCollection(levelCollection);
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
