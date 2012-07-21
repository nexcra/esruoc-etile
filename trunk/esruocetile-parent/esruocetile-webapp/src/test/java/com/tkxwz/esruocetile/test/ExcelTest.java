package com.tkxwz.esruocetile.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.tkxwz.esruocetile.webapp.service.DataService;

/**
 * @author Po Kong 
 * @since 2012-7-5 上午10:32:55
 */
public class ExcelTest {

	DataService service = new DataService();

	@Test
	public void readExcelData() throws Exception {
	System.out.println(	RandomStringUtils.randomAlphanumeric(10));
	}
}
