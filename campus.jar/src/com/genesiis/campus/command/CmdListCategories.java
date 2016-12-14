package com.genesiis.campus.command;

//20161028 PN c11-criteria-based-filter-search INIT the class and implemented execute() method.

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteDAO;
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
		
		try {
			Collection<Collection<String>> categoryCollection = categoryDAO.getAll();
			iview.setCollection(categoryCollection);
			
			//instituteCollection used in c11. But both issues are using the same class. 
			Collection<Collection<String>> instituteCollection = instituteDAO.getAll();
			helper.setAttribute("instituteCollection", instituteCollection);
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
