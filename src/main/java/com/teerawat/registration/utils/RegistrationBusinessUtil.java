package com.teerawat.registration.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationBusinessUtil {
	
	public enum SalaryClassify{
		Platinum("Platinum"),
		Gold("Gold"),
		Silver("Silver")
		;
		String value;
		SalaryClassify(String value) {this.value = value;}
	}
	
	@Autowired
	private DateUtil dateUtil;
	
	public String generateReferenceCode(String phone) {
		String subPhone = phone.substring(6, 10);
		return dateUtil.getCurrentDateInNamalFormat().concat(subPhone);
	}
	
	public SalaryClassify getClassifyBySalary(Integer salary) {
		if(salary > 50000) {
			return SalaryClassify.Platinum;
		} else if(salary > 30000){
			return SalaryClassify.Gold;
		} else {
			return SalaryClassify.Silver;
		}
	}

}
