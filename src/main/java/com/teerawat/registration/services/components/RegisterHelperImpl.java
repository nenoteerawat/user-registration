package com.teerawat.registration.services.components;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.teerawat.registration.services.components.functions.CurrentDate;
import com.teerawat.registration.services.components.functions.GenRefCode;
import com.teerawat.registration.services.components.functions.SalaryClassify;

@Component
public class RegisterHelperImpl implements RegisterHelper{
	
	@Override
	public String genRefCode(String phone) {
		CurrentDate curDate = () -> {
			String format = "yyyyMMdd";
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.format(new Date());
		};
		GenRefCode genRefCode = parameter -> {
			return curDate.currentDate().concat(parameter.substring(parameter.length()-4, parameter.length()));
		};
		return genRefCode.genRefcode(phone);
	}

	@Override
	public String classifyUser(Integer salary) {
		SalaryClassify salaryClassify = parameter -> {
			if (parameter < 15000) return "UnClassify";
			else if (parameter > 50000) return "Platinum";
			else if (parameter >= 30000) return "Gold";
			else return "Silver";
		};
		return salaryClassify.classify(salary);
	}
	
}
