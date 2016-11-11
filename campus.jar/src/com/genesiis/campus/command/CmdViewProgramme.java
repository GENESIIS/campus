package com.genesiis.campus.command;

//20161025 CM c13-Display course details INIT CmdViewProgramme.java
//20161025 CM c13-Display course details Modified execute() method. 
//20161025 CM c13-Display course details Modified execute() method. Set ProgrammeView attribute.
//20161026 CM c13-Display course details Modified execute() method. Remove Module attribute.
//20161028 CM c13-Display course details Modified execute() method. Created  programmeView attribute.
//20161028 CM c13-Display course details Modified execute() method. Created  method comment.
//20161101 CM c13-Display course details Modified execute() method.
//20161101 CM c13-Display course details Modified execute() method.
//20161111 CM c13-Display course details Created calculateRating() method.

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.ClassTypeDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.IntakeDAO;
import com.genesiis.campus.entity.ModuleDAO;
import com.genesiis.campus.entity.ProgrammeDAO;
import com.genesiis.campus.entity.ProgrammeLocationDAO;
import com.genesiis.campus.entity.ProgrammeRatingDAO;
import com.genesiis.campus.entity.SemesterDAO;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;

public class CmdViewProgramme implements ICommand {

	static Logger log = Logger.getLogger(CmdViewProgramme.class.getName());
	private IView programmeData;

	public CmdViewProgramme() {
		// TODO Auto-generated constructor stub
	}

	public CmdViewProgramme(IView programmeData) {
		this.programmeData = programmeData;
	}

	/**
	 * @author Chathuri
	 * @param helepr
	 *            IDataHelper object of Object type view IView object of object
	 *            type
	 * @return View object to servlet
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		try {
			final Programme programme = new Programme();
			ICrud programmeDAO = new ProgrammeDAO();
			ICrud semesterDAO = new SemesterDAO();
			ICrud intakeDAO = new IntakeDAO();
			ICrud moduleDAO = new ModuleDAO();
			ICrud classTypeDAO = new ClassTypeDAO();
			ICrud locationDAO = new ProgrammeLocationDAO();
			ICrud programmeRatingDAO = new ProgrammeRatingDAO();
			int programmeId = Integer.parseInt(helper
					.getParameter("programmeCode"));

			programme.setCode(programmeId);

			Collection<Collection<String>> programmeDAOCollection = programmeDAO
					.findById(programme);
			Collection<Collection<String>> semesterDAOCollection = semesterDAO
					.findById(programme);

			Collection<Collection<String>> moduleDAOCollection = moduleDAO
					.findById(programme);
			view.setCollection(moduleDAOCollection);

			Collection<Collection<String>> intakeDAOCollection = intakeDAO
					.findById(programme);

			Collection<Collection<String>> classTypeDAOCollection = classTypeDAO
					.findById(programme);

			Collection<Collection<String>> locationDAOCollection = locationDAO
					.findById(programme);

			Collection<Collection<String>> programmeRatingCollection = programmeRatingDAO
					.findById(programme);

			double ratings=calculateRating(programmeRatingCollection);
			
			
			helper.setAttribute("semesterView", semesterDAOCollection);
			helper.setAttribute("programmeView", programmeDAOCollection);
			helper.setAttribute("intakeView", intakeDAOCollection);
			helper.setAttribute("classTypeView", classTypeDAOCollection);
			helper.setAttribute("locationView", locationDAOCollection);
			if(ratings==0.0){
				helper.setAttribute("ratings", "No Ratings");
			}else{
				helper.setAttribute("ratings", ratings+" /5");
			}
		
		} catch (Exception e) {
			log.error("execute() : e" + e.toString());
			throw e;
		}

		return view;
	}

	/**
	 * Calculate programme ratings
	 * @author Chathuri
	 * @param programmeRatingCollection
	 * @return
	 */
	public double calculateRating(Collection<Collection<String>> programmeRatingCollection) {
		double ratingValue=0;
		double ratingCount=0;
		double allRateCount=0;
		double singleRate=0;
		double rate=0;
		double totalRating=0;
		try {
				
				for (Collection<String> programmeRating : programmeRatingCollection) {
					Object ar[] = programmeRating.toArray();
					String ratingValueSt = (String) ar[0];
					String ratingCountSt = (String) ar[1];
					
					ratingValue=Double.parseDouble(ratingValueSt);
					ratingCount=Double.parseDouble(ratingCountSt);
					
					singleRate=ratingValue*ratingCount;
					
					rate+=singleRate;
					
					allRateCount+=ratingCount;
					
				}
				totalRating=rate/allRateCount;
				
			} catch (Exception e) {
				log.error("calculateRating() : e" + e.toString());
			}
			return totalRating;
	}
}
