package com.genesiis.campus.command;

//20170313 CW c148-tutor-verify-hashcode-reset-password-cw CmdTutorHashVerification class created
//20170313 CW c148-tutor-verify-hashcode-reset-password-cw CmdTutorHashVerification class modified to validate hashcode properly & set messages correctly
//20170314 CW c148-tutor-verify-hashcode-reset-password-cw add comments to execute method
//20170317 CW c149-tutor-email-confirmation-for-password-change-cw modify variable declarations & execute method to improve code performance
//20170503 CW c149-tutor-email-confirmation-for-password-change-cw modified tutorEmailvarification variable name into tutorEmailVarification, 
					// add length validation into collectionArr array, Remove validation for the message variable for the VALIDHASHCODE message

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorEmailVerificationDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is used to validate the entered hash code with the hashcode in the tutor table
 * further it implements ICommand interface
 * @author chinthaka
 */
public class CmdTutorHashVerification implements ICommand {

	static Logger log = Logger.getLogger(CmdTutorHashVerification.class
			.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String pageURL = "/dist/partials/login/emailVerification.jsp";
		String message = "";
		
		try {
			
			Tutor tutor = new Tutor();
			tutor.setEmailAddress(helper.getParameter("email"));
			tutor.setHashCode(helper.getParameter("hashCode"));
			
			TutorEmailVerificationDAO tutorEmailVarification = new TutorEmailVerificationDAO();
			
			//checking the entered hashcode is matching with the generated hash code
			//if the hash code is a valid one get the details of the tutor 
			Collection<Collection<String>> dataCollection = tutorEmailVarification.verifyHashCode(tutor);
			
			if(dataCollection == null || dataCollection.isEmpty()){
				message = SystemMessage.INVALID_HASHCODE.message();
			}else{
				for (Collection<String> collection : dataCollection) {
					
					if(collection != null && !collection.isEmpty()){
						
						//Validating the hash code expiration details
						Object [] collectionArr = collection.toArray();
						
						if(collectionArr != null && collectionArr.length > 5 && collectionArr[5] != null && Integer.parseInt((String)collectionArr[5]) > 30){
							message = SystemMessage.VERIFICATION_CODEEXPIRED.message();
						} else {
							
							//entered hashcode is a valid one & tutor can redirect into the password reset page
							view.setCollection(dataCollection);
							pageURL = "/dist/partials/login/passwordReset.jsp";
							message = SystemMessage.VALIDHASHCODE.message();
						}
					}else {
						message = SystemMessage.INVALID_HASHCODE.message();
					}
				}
			}
		} catch (SQLException sexp) {
			log.error("execute(): SQLException " + sexp.toString());
			throw sexp;
		} catch (IllegalArgumentException ilexp) {
			log.error("execute(): IllegalArgumentException" + ilexp.toString());
			throw ilexp;
		} catch (Exception exp) {
			log.error("execute():Exception " + exp.toString());
			throw exp;
		}

		helper.setAttribute("errorMessage", message);
		helper.setAttribute("pageURL", pageURL);

		return view;
	}
}

