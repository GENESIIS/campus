package com.genesiis.campus.command;

//20170228 JH c96-public-list-all-tutors INIT CmdPublicListTutors.java
//20170306 JH c96-public-list-all-tutors get tutor profile image path using SystemConfig enum, show error messages
//20170308 JH c96-public-list-all-tutors remove repeating records of the tutor from the tutorCollection 
//20170309 JH c96-public-list-all-tutors created seperateBasicData(Collection<Collection<String>>) to separate tutor basic details, category, major and qualification 
//				details from the initial DAO class result wip, removed commented lines
//20170310 JH c96-public-list-all-tutors separate tutor category details from the tutor basic data wip
//20170312 JH c96-public-list-all-tutors removed unwanted comments and remove repeating category records, major records from the tutor 
//			basic data, fixed concurrent modification exception

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.PublicTutorDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author JH
 *
 */
public class CmdPublicListTutors implements ICommand{
	
	static Logger log = Logger.getLogger(CmdPublicListTutors.class.getName());

	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		final ICrud publicTutorDAO = new PublicTutorDAO();
		SystemMessage message = SystemMessage.UNKNOWN;

		try {

			/*
			 * newTutorCollectio - does not contain tutor repeating records,
			 * contains only the basic details tutorArrayList - contains tutor
			 * repeating records with all details
			 */
			Collection<Collection<String>> newTutorCollection = new ArrayList<Collection<String>>();

			final Collection<Collection<String>> tutorCollection = publicTutorDAO
					.getAll();
			Map tutorMajorMap = new  HashMap<String, ArrayList<ArrayList<String>>>();
			Map tutorCategoryMap = new HashMap<String, ArrayList<ArrayList<String>>>();
			
			if (tutorCollection.size() == 0) {
				message = SystemMessage.NODATA;
			} else {
				//newTutorCollection = seperateBasicData(tutorCollection);
				Map returnData = new HashMap();
				returnData = seperateBasicData(tutorCollection);
				newTutorCollection = (Collection<Collection<String>>) returnData.get("tutorCollection");
				tutorCategoryMap = (Map) returnData.get("categoryData");
				tutorMajorMap = (Map) returnData.get("majaorData");
				
			}
			helper.setAttribute("majorMap", tutorMajorMap);
			helper.setAttribute("categoryMap", tutorCategoryMap);
			view.setCollection(newTutorCollection);

		} catch (SQLException sqlException) {
			message = SystemMessage.ERROR;
			log.error("execute() SQLException : " + sqlException.toString());
			throw sqlException;
		} catch (Exception exception){
			message = SystemMessage.ERROR;
			log.error("execute() Exception : "+ exception.toString());
			throw exception;
		}finally{
			final String tutorImagePath = SystemConfig.TUTOR_PROFILE_IMAGE_PATH.getValue1();
			helper.setAttribute("userMessage", message.toString());
			helper.setAttribute("tutorProfileImagePath", tutorImagePath);
		}
		
		
		return view;
	}

	
	/**
	 * seperateBasicData() used to remove repeating records and of the tutor while
	 * @return collection of tutor basic data 
	 * @param tutorCollection type of Collection<Collection<String>> 
	 * @author JH
	 */
	public static Map seperateBasicData(Collection<Collection<String>> tutorCollection){
		
		ArrayList<ArrayList<String>> newTutorList = new ArrayList<ArrayList<String>>();
		final ArrayList<Collection<String>> tutorArrayList = (ArrayList<Collection<String>>) tutorCollection;
		
				
			String code = "0";
			String firstName = null;
			String middleName = null;
			String lastName = null;
			String town = null;
			String countryCode = null;
			String areaCode = null;
			String landNumber = null;
			String networkCode = null;
			String mobileNumber = null;
			String email = null;
			String townCode = null;
			String qualification = null;
			String levelName = null;
			String categoryCode = null;
			String categoryName = null;
			String majorCode = null;
			String majorName = null;
			String nvq = null;

			// maps to store repeating category and major details
			Map categoryMap = new HashMap<String, ArrayList<ArrayList<String>>>();
			Map majorMap = new HashMap<String, ArrayList<ArrayList<String>>>();
			
			
			ArrayList<ArrayList<String>> categoryList =  null;
			ArrayList<ArrayList<String>> majorList = null;
			
			Iterator resultIterator = tutorArrayList.iterator();
			Iterator newTuorsIterator = newTutorList.iterator();
			
			//category list
			ArrayList<ArrayList<String>> x = null;
			
			// remove repeating tutor basic details
			while(resultIterator.hasNext()){
				
				ArrayList<String> singleList = (ArrayList<String>) resultIterator.next();
				
				if(code.equalsIgnoreCase(singleList.get(0))){
					
				}else{
					
					/*
					 * initially, tutor collection has duplicate category and major details
					 *  under the same tutor code. Therefore following method will compare
					 *  current category / major collection for the duplicate values and create a 
					 *  new category / major lists without duplicate records
					 */
					
					// to exclude the first iteration and empty category lists
					if(!code.equalsIgnoreCase("0") || (categoryList != null) ){
						
						ArrayList<ArrayList<String>> finalCategoryList = new ArrayList<ArrayList<String>>();
						
						Iterator categoryListIterator = categoryList.iterator(); // iterator for list with duplicates

					while (categoryListIterator.hasNext()) {
						
						int arraySize = finalCategoryList.size(); // array size
						boolean isNew = true;
						
						ArrayList<String> temporaryCategory = (ArrayList<String>) categoryListIterator.next();
						ArrayList<String> compareCateogry = null; // to compare categories
						
						if(finalCategoryList.contains(temporaryCategory)){
							// the category already exist
						}else{
							finalCategoryList.add(temporaryCategory);
						}
						
					}

					categoryMap.put(code, finalCategoryList);
					categoryList = null;
				}
					
					
					// to exclude the first iteration and empty major lists
					if(!code.equalsIgnoreCase("0") || (majorList != null) ){
						
						ArrayList<ArrayList<String>> finalMajorList = new ArrayList<ArrayList<String>>();
						
						Iterator majorListIterator = majorList.iterator(); // iterator for list with duplicates

					while (majorListIterator.hasNext()) {

						ArrayList<String> temporaryMjor = (ArrayList<String>) majorListIterator.next();
						ArrayList<String> compareMajor = null; // to compare categories
						
						if(finalMajorList.contains(temporaryMjor)){
							// the category already exist
						}else{
							finalMajorList.add(temporaryMjor);
						}
						
					}

					majorMap.put(code, finalMajorList);
					majorList = null;
				}
				}
				
				code = singleList.get(0);
				firstName = singleList.get(1);
				middleName = singleList.get(2);
				lastName = singleList.get(3);
				email = singleList.get(4);
				countryCode = singleList.get(5);
				areaCode = singleList.get(6);
				landNumber = singleList.get(7);
				networkCode = singleList.get(8);
				mobileNumber = singleList.get(9);
				town = singleList.get(18);
				townCode = singleList.get(19);
				
				// create a temporary tutor record 
				ArrayList<String> temporaryTutor = new ArrayList<String>();
				temporaryTutor.add(code);
				temporaryTutor.add(firstName);
				temporaryTutor.add(middleName);
				temporaryTutor.add(lastName);
				temporaryTutor.add(email);
				temporaryTutor.add(countryCode);
				temporaryTutor.add(areaCode);
				temporaryTutor.add(landNumber);
				temporaryTutor.add(networkCode);
				temporaryTutor.add(mobileNumber);
				temporaryTutor.add(town);
				temporaryTutor.add(townCode);
				
				
				// create a temporary category record
				ArrayList<String> temporaryCategory = new ArrayList<String>();
				
				if(singleList.get(12) != null){
					temporaryCategory.add(singleList.get(12));
					temporaryCategory.add(singleList.get(13));
				}
			
				ArrayList<String> temporaryMajor  = new ArrayList<String>();
				temporaryMajor.add(singleList.get(10));
				temporaryMajor.add(singleList.get(11));
				
				
				// if previous records are available: check whether the last
				// record matches the current temporary tutor record
				
				int count = newTutorList.size();	
				int index = 0;
						
				if(count != 0){
					index = count - 1;//get the last index of the collection					
				}else{
					index = count;
				}
	
				
				if (count> 0) {

					/*
					 *  The new temporary arrayList record need to compare against 
					 *  the newTutorCollection records before adding it. 
					 *  This is used to remove the repeating records of the 
					 *  tutor collection which is originally by the DAO class.
					 *  
					 *  The collection result set which is returned by the PublicTutorDAO class getAll() method 
					 *  is already ordered by the Tutor CODE. Therefore it is assumed that only
					 *  the last record of the tutor basic details collection is able to have a duplicate record.  
					 */
					
					ArrayList<String> compareArray = (ArrayList<String>) newTutorList.get(index);
					
					if (compareArray.equals(temporaryTutor)) {
						// the same tutor record is available, do nothing
						if(categoryList != null && temporaryCategory != null){
							categoryList.add(temporaryCategory);
						}
						if(majorList != null && temporaryMajor != null){
							majorList.add(temporaryMajor);
						}
						
					} else {
					// the tutor record does not available, insert the temporary
					// tutor record
					newTutorList.add(temporaryTutor);

					// add all category related records
					categoryList = new ArrayList<ArrayList<String>>();

					if (categoryList != null && temporaryCategory != null) {
						categoryList.add(temporaryCategory);
					}
					
					// add all major related records	
					majorList = new ArrayList<ArrayList<String>>();
					
					if(majorList != null && temporaryMajor != null){
						majorList.add(temporaryMajor);
					}
					}
					

				} else {// no previous tutor records are available
					newTutorList.add(temporaryTutor);
					
					categoryList = new 	ArrayList<ArrayList<String>>();
					majorList = new ArrayList<ArrayList<String>>();
					
					if(categoryList != null && temporaryCategory != null){
						categoryList.add(temporaryCategory);
					}
					
					if(majorList != null && temporaryMajor != null){
						majorList.add(temporaryMajor);
					}
				}

			}
			
			if(!categoryMap.isEmpty()){
				log.info("cateogry map " + categoryMap);
			}
			if(!majorMap.isEmpty()){
				log.info("major map " + majorMap);
			}

			
			Map returData = new HashMap();
			returData.put("tutorCollection", newTutorList);
			returData.put("categoryData", categoryMap);
			returData.put("majaorData", majorMap);
		return returData;
	}
}

