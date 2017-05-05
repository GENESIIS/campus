package com.genesiis.campus.command;

//20170424 JH c135-public-display-tutor-profile INIT CmdDisplayPublicTutorProfile.java
//20170427 JH c135-public-display-tutor-profile created getTutorDetails() method to get tutor basic details from the tutor collection 
//20170502 JH c135-public-display-tutor-profile added doc comments 
//20170505 JH c135-public-display-tutor-profile changed the return type of getTutorDetails() to ArrayList<String>, changed tutor initialization  
//20170505 JH c162-public-display-tutor-full-profile getTutorDetails() method and execute() methods changed to get all required data for tutor profile

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.PublicTutorDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import sun.java2d.SunGraphicsEnvironment.TTFilter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * CmdDisplayPublicTutorProfile class used to get public tutor profile details. All the details required to display 
 * in the tutor profile for public will be listed here. In this stage it will return only the tutor basic details (contact 
 * details and social media details). 
 * @author JH
 *
 */
public class CmdDisplayPublicTutorProfile implements ICommand{
	
	static Logger log = Logger.getLogger(CmdDisplayPublicTutorProfile.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		final ICrud publicTutorDAO = new PublicTutorDAO();
		SystemMessage message = SystemMessage.UNKNOWN;
		ArrayList<ArrayList<String>> tutors = new ArrayList<ArrayList<String>>();
		ArrayList<String> tutor = new ArrayList<String>();
		
		try{
			if(Validator.validNumber(helper.getParameter("tutorCode"))){
				Collection<Collection<String>> tutorCollection = publicTutorDAO.findById(helper.getParameter("tutorCode"));
				
				
				if(tutorCollection.size() > 0){
					Map<String, ArrayList<ArrayList<String>>> result = getTutorDetails(tutorCollection);
					tutors = result.get("tutor");
					tutor = tutors.get(0);
				}
			}

		}catch (SQLException sqlException) {
			log.error("execute() SQLException : " + sqlException.toString());
			throw sqlException;
		} catch (Exception exception){
			log.error("execute() Exception : "+ exception.toString());
			throw exception;
		}finally{
			final String tutorImagePath = SystemConfig.TUTOR_PROFILE_IMAGE_PATH.getValue1();
			helper.setAttribute("userMessage", message.message());
			helper.setAttribute("tutorProfileImagePath", tutorImagePath);
			helper.setAttribute("tutor", tutor);
		}
		
		
		return view;
	}
	
	
	/**
	 * getTutorDetails() method used to separate different information requirements under different categories of the 
	 * same tutor. This method used to extract tutor employment, qualification, experience and etc details.
	 * 
	 * @param tutorCollection
	 * @return Map<String, ArrayList<ArrayList<String>>> map of tutor details
	 */
	public static Map<String, ArrayList<ArrayList<String>>> getTutorDetails(Collection<Collection<String>> tutorCollection){
		
		Iterator<Collection<String>> tutorIterator = tutorCollection.iterator();
		ArrayList<String> tutor = new ArrayList<String>();
		Map<String, ArrayList<ArrayList<String>>> result = new HashMap<String, ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> tutorsList = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> qualifications = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> experiences = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> modules = new ArrayList<ArrayList<String>>();
		
		
		int count = 0;
		
		while(tutorIterator.hasNext()){
			
			count++;
			ArrayList<String> tutorList = (ArrayList<String>) tutorIterator.next();
			
			if(count == 1){
				tutor.add(0, tutorList.get(0));
				tutor.add(1, tutorList.get(1));
				tutor.add(2, tutorList.get(2));
				tutor.add(3, tutorList.get(3));
				tutor.add(4, tutorList.get(4));
				tutor.add(5, tutorList.get(5));
				tutor.add(6, tutorList.get(6));
				tutor.add(7, tutorList.get(7));
				tutor.add(8, tutorList.get(8));
				tutor.add(9, tutorList.get(9));
				tutor.add(10, tutorList.get(10));
				tutor.add(11, tutorList.get(11));
				tutor.add(12, tutorList.get(12));
				tutor.add(13, tutorList.get(13));
				tutor.add(14, tutorList.get(14));
				tutor.add(15, tutorList.get(15));
				tutor.add(16, tutorList.get(16));
				tutor.add(17, tutorList.get(17));
				tutor.add(18, tutorList.get(18));
				tutor.add(19, tutorList.get(19));
				tutor.add(20, tutorList.get(20));
				tutor.add(21, tutorList.get(21));
				tutor.add(22, tutorList.get(22));
				tutor.add(23, tutorList.get(23));
				tutor.add(24, tutorList.get(24));
				tutor.add(25, tutorList.get(25));
				tutor.add(26, tutorList.get(26));
				tutor.add(27, tutorList.get(27));
				tutor.add(28, tutorList.get(28));
				tutor.add(29, tutorList.get(29));
				tutor.add(30, tutorList.get(30));
				tutor.add(31, tutorList.get(31));
				tutor.add(32, tutorList.get(32));
				tutor.add(33, tutorList.get(33));
				tutor.add(34, tutorList.get(34));
				tutor.add(35, tutorList.get(35));
				tutor.add(36, tutorList.get(36));
							
				tutorsList.add(tutor);
			}
			
			if(!Validator.isEmptyString(tutorList.get(37))){
				ArrayList<String> qualification = new ArrayList<String>();
				qualification.add(tutorList.get(37));
				qualification.add(tutorList.get(38));
				qualification.add(tutorList.get(39));
				qualification.add(tutorList.get(40));
			}
			
			
		}
		
		result.put("tutor", tutorsList);
		return result;
	}

}
