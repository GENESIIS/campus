package com.genesiis.campus.validation;

import java.util.ArrayList;

public class Validator {

	public Object getDuration(int duration){
		ArrayList<String> programDuration = new ArrayList<String>();
		
		int years = duration / 365;
		duration = duration % 365;
		int month = duration /30;
		duration = duration % 30;
		
		programDuration.add(String.valueOf(years));
		programDuration.add(String.valueOf(month));
		programDuration.add(String.valueOf(duration));
		
		return programDuration;
	}
}
