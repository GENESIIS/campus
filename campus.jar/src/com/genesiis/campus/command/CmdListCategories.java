package com.genesiis.campus.command;

//20161028 PN c11-criteria-based-filter-search INIT the class and implemented execute() method.
//20161117 JH c7-higher-education-landing-page-MP code modifications : mx code review 
//20161125 JH c7-higher-education-landing-page-MP QA modifications: load category logo using system config enum
//20170103 PN CAM-137: modified execute() method by changing LPCategoryDAO instance into CategoryDAO instance.
//20170215 PN CAM-137: modified execute() method by removing CategoryDAO object. remove code block implemented to get institute list.
//20170216 PN CAM-137: modified execute() method to get and assign the values from static enum if the categoryCollection is null or empty.
//20170302 PN CAM-137: CategoryList Enum imports changes into EducationCategory Enum class.
//20170418 PN CAM-137: removed SQLException catch clause from the execute method, since SQLException is never thrown in body of corresponding try statement.

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.landing.CategoryCache;
import com.genesiis.campus.validation.EducationCategory;
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
	public IView execute(IDataHelper helper, IView iview) throws Exception {
		ICrud instituteDAO = new InstituteDAO();
		final String contextDeployCategoryLogoPath = SystemConfig.CATEGORY_LOGO_PATH.getValue1();
		
		try {
			// Get the only object available
			Collection<Collection<String>> categoryCollection = CategoryCache.getInstance().getDefaultCategories();
			
			//get and assign the values from static enum if the categoryCollection is null or empty.
			if((categoryCollection == null) || (categoryCollection.size() == 0)){
				categoryCollection = EducationCategory.getEnumAsCollection();
			}

			iview.setCollection(categoryCollection);
			helper.setAttribute("categoryLogoPath", contextDeployCategoryLogoPath);

		} catch (Exception e) {
			log.info("execute() : Exception " + e.toString());
			throw e;
		}
		return iview;
	}
}
