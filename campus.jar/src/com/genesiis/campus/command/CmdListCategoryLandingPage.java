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

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.CategoryCourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Category;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

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
				
				programme.setActive(true);
				programme.setProgrammeStatus(1);
				programme.setCategory(Integer.parseInt(categoryId));
				
				final Category category = new Category();

				category.setCode(Integer.parseInt(categoryId));

				final Collection<Collection<String>> categoryDetails = categoryDAO
						.findById(category);
				view.setCollection(categoryDetails);

				programme.setLevel(1);// this level=1 is just to identify to get
										// course
				// providers with higher program stats
				final Collection<Collection<String>> featuredCourseProviders = categoryProgrammeDAO
						.findById(programme);

				// list 20 course providers randomly
				programme.setLevel(0);
				final Collection<Collection<String>> courseProviders = categoryProgrammeDAO
						.findById(programme);

				
				final String contextDeployCategoryLogoPath = SystemConfig.CATEGORY_LOGO_PATH.getValue1();
				final String contextDeploySmallLogoPath = SystemConfig.PROVIDER_LOGO_PATH.getValue1();
				
				helper.setAttribute("categoryLogoPath", contextDeployCategoryLogoPath);
				helper.setAttribute("providerLogoPath", contextDeploySmallLogoPath);
				
				helper.setAttribute("featuredInstitutes",
						featuredCourseProviders);
				helper.setAttribute("institutes", courseProviders);

			} catch (Exception exception) {
				log.error("execute() Exception : " + exception.toString());
				systemMessage = SystemMessage.ERROR;
				throw exception;
			}

		}

		return view;
	}
}