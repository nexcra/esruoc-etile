package com.tkxgz.elitecourse.core.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

/**
 * @author Po Kong
 * @since 27 Jul 2012 23:40:43
 */
public class DateConverter implements Converter {

	private static String dateFormatStr = "yyyy/MM/dd";

	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
			dateFormatStr);

	private static String dateLongFormatStr = dateFormatStr + " HH:mm:ss";

	private static SimpleDateFormat dateTimeLongFormat = new SimpleDateFormat(
			dateLongFormatStr);

	public Object convert(Class arg0, Object arg1) {
		System.out.println(arg1.getClass().getName() + "=" + arg1.toString());
		String className = arg1.getClass().getName();
		// java.sql.Timestamp
		if ("java.sql.Timestamp".equalsIgnoreCase(className)) {
			try {
				SimpleDateFormat df = new SimpleDateFormat(dateFormatStr
						+ " HH:mm:ss");
				return df.parse(dateTimeLongFormat.format(arg1));
			} catch (Exception e) {
				try {
					SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);
					return df.parse(dateTimeFormat.format(arg1));
				} catch (ParseException ex) {
					e.printStackTrace();
					return null;
				}
			}
		} else {// java.util.Date，java.sql.Date
			String p = (String) arg1;
			if (p == null || p.trim().length() == 0) {
				return null;
			}
			try {
				SimpleDateFormat df = new SimpleDateFormat(dateFormatStr
						+ " HH:mm:ss");
				return df.parse(p.trim());
			} catch (Exception e) {
				try {
					SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);
					return df.parse(p.trim());
				} catch (ParseException ex) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	public static String formatDateTime(Object obj) {
		if (obj != null)
			return dateTimeFormat.format(obj);
		else
			return "";
	}

	public static String formatLongDateTime(Object obj) {
		if (obj != null)
			return dateTimeLongFormat.format(obj);
		else
			return "";
	}

}
