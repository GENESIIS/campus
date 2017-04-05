package com.genesiis.campus.command;

//20161103 JH c7-higher-education-landing-page CmdListCategoryLandingPage.java created
//20161103 JH c7-higher-education-landing-page implement unimplemented methods
//20161103 JH c7-higher-education-landing-page refactor code
//20161104 JH c7-higher-education-landing-page code modification 
//20161115 JH c7-higher-education-landing-page change validation method for categoryId parameter , add method comments
//20161116 JH c7-higher-education-landing-page code review mx modifications 
//20161117 JH c7-higher-education-landing-page code review mx modifications 
//20161125 JH c7-higher-education-landing-page-MP QA modifications: load category logo using system config enum wip
//20161126 JH c7-higher-education-landing-page-MP QA modifications: load category logo using system config enum
//20161130 JH c7-higher-education-landing-page-MP code review modifications: error log statement modified, added documentation comments,
//												  removed unwanted statements
//20170216 PN CAM-137: modified execute() method to get and assign the values from static enum if the categoryDetails Collection is null or empty.
//20170302 PN CAM-137: CategoryList Enum imports changes into EducationCategory Enum class.
//20170403 PN CAM-137: changed the order of category image path values setting to the attributes.
//20170405 PN CAM-137: removed exception throw statement from the execute() method and logged the full error.

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.CategoryCourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Category;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.landing.CategoryCache;
import com.genesiis.campus.validation.EducationCategory;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

public class CmdListCategoryLandingPage implements ICommand {

	static Logger log = Logger
			.getLogger(CmdListCategoryLandingPage.class.getName());
	private IView landingPageData;

	public CmdListCategoryLandingPage(IView landingPageData) {
		this.landingPageData = landingPageData;
	}

	public CmdListCategoryLandingPage() {

	}

	/**
	 * execute method used to retrieve data related to a single category
	 * landing page. 
	 * @author JH
	 * @param helper
	 * @param view
	 * @return iview with selected category
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		ICrud categoryDAO = new CategoryDAO();
		ICrud categoryProgrammeDAO = new CategoryCourseProviderDAO();
		Collection<Collection<String>> categoryCollection = null;

		SystemMessage systemMessage = SystemMessage.UNKNOWN;

		Validator validator = new Validator();
		if (!validator.isEmpty( helper.getParameter("categoryId"))) {
			final String categoryId = helper.getParameter("categoryId");

			final Programme programme = new Programme();

			try {
				programme.setCategory(Integer.parseInt(categoryId));
				
				final Category category = new Category();

				category.setCode(Integer.parseInt(categoryId));

				Collection<Collection<String>> categoryDetails = CategoryCache.getInstance().getDefaultCategories(categoryId);
				
				if((categoryDetails == null) || (categoryDetails.size() == 0)){					
					categoryDetails = EducationCategory.getEnumAsCollection(categoryId);
				}
				view.setCollection(categoryDetails);
				
				final String contextDeployCategoryLogoPath = SystemConfig.CATEGORY_LOGO_PATH.getValue1();
				final String contextDeploySmallLogoPath = SystemConfig.PROVIDER_LOGO_PATH.getValue1();
				
				helper.setAttribute("categoryLogoPath", contextDeployCategoryLogoPath);
				helper.setAttribute("providerLogoPath", contextDeploySmallLogoPath);
				
				/**
				 * Here the attribute level in program is used to identify which
				 * query should be executed between two queries in CategoryCourseProvderDAO.
				 * There is no relationship between the program level and the selection 
				 * criteria. 
				 * 
				 * When level = 1, queries the database for course providers with 
				 * highest program stat.
				 * 
				 * When level =0, queries the database for random course providers
				 * who may or may not have program stat
				 */
				programme.setLevel(1);
				final Collection<Collection<String>> featuredCourseProviders = categoryProgrammeDAO.findById(programme);

				programme.setLevel(0);
				final Collection<Collection<String>> courseProviders = categoryProgrammeDAO.findById(programme);
				
				helper.setAttribute("featuredInstitutes", featuredCourseProviders);
				helper.setAttribute("institutes", courseProviders);

			} catch (SQLException sqle) {
				log.error("execute() SQLException : " + sqle);
				systemMessage = SystemMessage.ERROR;
			}catch (Exception exception) {
				log.error("execute() Exception : " + exception);
				systemMessage = SystemMessage.ERROR;
			}

		}

		return view;
	}
}