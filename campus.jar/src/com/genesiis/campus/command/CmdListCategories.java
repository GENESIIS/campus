package com.genesiis.campus.command;

//20161028 PN c11-criteria-based-filter-search INIT the class and implemented execute() method.
//20161109 PN c11-criteria-based-filter-search modified execute() to load more than one collection.

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.DistrictDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteDAO;
import com.genesiis.campus.entity.MajorDAO;
import com.genesiis.campus.util.IDataHelper;

public class CmdListCategories implements ICommand{
	static Logger log = Logger.getLogger(CmdListCategories.class.getName());

	private IView categoryData;

	public CmdListCategories(IView categoryData) {
		this.categoryData = categoryData;
	}

	public CmdListCategories() {
		
	}

	/**
	 * @author pabodha
	 * @param helper
	 * @param iview
	 * @return iview with selected Category list
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException, Exception {
	
		
		ICrud categoryDAO = new CategoryDAO();
		ICrud instituteDAO = new InstituteDAO();
		ICrud districtDAO = new DistrictDAO();
		ICrud majorDao = new MajorDAO();
		
		try {
			Collection<Collection<String>> categoryCollection = categoryDAO.getAll();
			iview.setCollection(categoryCollection);
			
			Collection<Collection<String>> instituteCollection = instituteDAO.getAll();
			helper.setAttribute("instituteCollection", instituteCollection);
			log.info("instituteCollection"+helper.getAttribute("instituteCollection"));
			
			Collection<Collection<String>> districtCollection = districtDAO.getAll();
			helper.setAttribute("districtCollection", districtCollection);
			log.info("districtCollection"+helper.getAttribute("districtCollection"));
			
			Collection<Collection<String>> majorCollection = majorDao.getAll();
			helper.setAttribute("majorCollection", majorCollection);
			log.info("majorCollection"+helper.getAttribute("majorCollection"));
			
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
