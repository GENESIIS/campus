package com.genesiis.campus.command;

//20161028 PN c11-criteria-based-filter-search INIT the class and implemented execute() method.
//20161117 JH c7-higher-education-landing-page-MP code modifications : mx code review 
//20161125 JH c7-higher-education-landing-page-MP QA modifications: load category logo using system config enum
//20170103 PN CAM-137: modified execute() method by changing LPCategoryDAO instance into CategoryDAO instance.

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteDAO;
import com.genesiis.campus.util.CategoryCache;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

public class CmdListCategories implements ICommand {
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
		final String contextDeployCategoryLogoPath = SystemConfig.CATEGORY_LOGO_PATH.getValue1();
		// Get the only object available
		CategoryCache defaultCategories = CategoryCache.getInstance();
		try {
			Collection<Collection<String>> categoryCollection = defaultCategories.getDefaultCategories();
			iview.setCollection(categoryCollection);
			helper.setAttribute("categoryLogoPath", contextDeployCategoryLogoPath);

			// instituteCollection used in c11. But both issues are using the
			// same class.
			Collection<Collection<String>> instituteCollection = instituteDAO.getAll();
			helper.setAttribute("instituteCollection", instituteCollection);
		} catch (SQLException sqle) {
			log.info("execute() : SQL Exception " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.info("execute() : Exception " + e.toString());
			throw e;
		}
		return iview;
	}
}
