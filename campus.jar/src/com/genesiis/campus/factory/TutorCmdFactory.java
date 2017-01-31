package com.genesiis.campus.factory;

//20170103 DN CAM:47 tutor: add tutor information : upload image created UPLOAD_EXISTING_PROFILE_PIC in  getCommand()
//20170106 DN CAM:47 UPLOAD_SUBMITED_TUTOR_IMAGE to the map and getCommand()

import com.genesiis.campus.command.CmdGetTutorProfileImg;
import com.genesiis.campus.command.CmdUploadTutorImage;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class TutorCmdFactory implements ICmdFactory {
	static {
	
		map.put(Operation.UPLOAD_EXISTING_TUTOR_PROFILE_PIC, new CmdGetTutorProfileImg());
		map.put(Operation.UPLOAD_SUBMITED_TUTOR_IMAGE, new CmdUploadTutorImage());
	}

	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case UPLOAD_EXISTING_TUTOR_PROFILE_PIC:
			command = map.get(o);
			break;
		case UPLOAD_SUBMITED_TUTOR_IMAGE:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;

	}

}