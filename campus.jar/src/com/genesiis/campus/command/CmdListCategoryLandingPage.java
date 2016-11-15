package com.genesiis.campus.command;

//20161103 JH c7-higher-education-landing-page CmdListCategoryLandingPage.java created
//20161103 JH c7-higher-education-landing-page implement unimplemented methods
//20161103 JH c7-higher-education-landing-page refactor code
//20161104 JH c7-higher-education-landing-page code modification 

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.CategoryCourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Category;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

public class CmdListCategoryLandingPage implements ICommand {

	static org.apache.log4j.Logger log = Logger
			.getLogger(CmdListCategoryLandingPage.class.getName());
	private IView landingPageData;

	public CmdListCategoryLandingPage(IView landingPageData) {
		this.landingPageData = landingPageData;
	}

	public CmdListCategoryLandingPage() {

	}

	/**
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
		if (!validator.isEmpty(helper)) {
			final String categoryId = helper.getParameter("categoryId");

			final Programme programme = new Programme();

			programme.setActive(true);
			programme.setProgrammeStatus(1);
			programme.setCategory(Integer.parseInt(categoryId));

			try {

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

				helper.setAttribute("featuredInstitutes",
						featuredCourseProviders);
				helper.setAttribute("institutes", courseProviders);
				
				String categoryIdentifierString = "";
				
				// The mapping of categoryCode to categoryIdentifierString is hard-coded as below.
				// But this is to be done by some mechanism that is to be agreed upon.
				switch (Integer.parseInt(categoryId)) {
					case 1: categoryIdentifierString = "PRE_EDUCATION"; break;
					case 2: categoryIdentifierString = "SCHOOL_EDUCATION"; break;
					case 3: categoryIdentifierString = "HIGHER_EDUCATION"; break;
					case 4: categoryIdentifierString = "CORPORATE_TRAINING"; break;
				}
				helper.setAttribute("categoryCode", categoryId);
				helper.setAttribute("categoryIdentifierString", categoryIdentifierString);
				

			} catch (Exception exception) {
				log.error("execute() : " + exception);
				systemMessage = SystemMessage.ERROR;
				throw exception;
			}

		}

		return view;
	}
}