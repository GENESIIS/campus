package com.genesiis.campus.command;

//20161103 JH c7-higher-education-landing-page CmdListCategoryLandingPage.java created
//20161103 JH c7-higher-education-landing-page implement unimplemented methods

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Category;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

public class CmdListCategoryLandingPage implements ICommand {

	static org.apache.log4j.Logger log = Logger
			.getLogger(CmdListCategoryProgramme.class.getName());
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
		Collection<Collection<String>> categoryCollection = null;

		SystemMessage systemMessage = SystemMessage.UNKNOWN;

		Validator validator = new Validator();
		if (!validator.isEmpty(helper)) {
			final String categoryId = helper.getParameter("categoryId");

			try {
				
				final Category category = new Category();	
				
				category.setCode(Integer.parseInt(categoryId));
				
				final Collection<Collection<String>> categoryDetails = categoryDAO.findById(category);
				helper.setAttribute("category", categoryDetails);
				view.setCollection(categoryDetails);

			} catch (Exception exception) {
				log.error("execute() : " + exception);
				systemMessage = SystemMessage.ERROR;
				throw exception;
			}

		}


		return view;
	}
}
