package com.teerawat.registration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.teerawat.registration.utils.DateUtil;
import com.teerawat.registration.utils.RegistrationBusinessUtil;
import com.teerawat.registration.utils.RegistrationBusinessUtil.SalaryClassify;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegistrationApplicationTests {
	
	@Mock
	private DateUtil dateUtil;
	
	@InjectMocks
	private RegistrationBusinessUtil registrationBusinessUtil;

	@Test
	public void whenGenerateReferenceCode() {
		Mockito.when(dateUtil.getCurrentDateInNormalFormat()).thenReturn("20180909");
		String testResult = registrationBusinessUtil.generateReferenceCode("0995970594");
		Assert.assertEquals("201809090594", testResult);
	}
	
	@Test
	public void whenRegisterWithSalaryMoreThan60K() {
		SalaryClassify testResult = registrationBusinessUtil.getClassifyBySalary(60000);
		Assert.assertEquals(SalaryClassify.Platinum, testResult);
	}
	
	@Test
	public void whenRegisterWithSalaryMoreThan50K() {
		SalaryClassify testResult = registrationBusinessUtil.getClassifyBySalary(50000);
		Assert.assertEquals(SalaryClassify.Gold, testResult);
	}
	
	@Test
	public void whenRegisterWithSalaryMoreThan40K() {
		SalaryClassify testResult = registrationBusinessUtil.getClassifyBySalary(40000);
		Assert.assertEquals(SalaryClassify.Gold, testResult);
	}
	
	@Test
	public void whenRegisterWithSalaryMoreThan30K() {
		SalaryClassify testResult = registrationBusinessUtil.getClassifyBySalary(30000);
		Assert.assertEquals(SalaryClassify.Gold, testResult);
	}
	
	@Test
	public void whenRegisterWithSalaryMoreThan20K() {
		SalaryClassify testResult = registrationBusinessUtil.getClassifyBySalary(20000);
		Assert.assertEquals(SalaryClassify.Silver, testResult);
	}

}
