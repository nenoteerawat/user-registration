package com.teerawat.registration.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	private static final String YYYYMMDD = "yyyyMMdd";
	public static final SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat(YYYYMMDD);
	
	/**
	 * 
	 * @return String date in format "yyyyMMdd"
	 */
	public String getCurrentDateInNamalFormat() {
		Date currentDate = new Date();
		return YYYYMMDD_FORMAT.format(currentDate);
	}
}
