package com.tkxwz.esruocetile.webapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.dao.TestBookingDao;
import com.tkxwz.esruocetile.webapp.entity.TestBooking;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:14:39
 */
@Service
public class TestBookingService {

	@Autowired
	private TestBookingDao testBookingDao;

	public Page listTestBooking(Page page) {
		return this.testBookingDao.listTestBooking(page);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 12:51:56
	 * @param testBooking
	 */
	public int addTestBooking(TestBooking testBooking) {
		return this.testBookingDao.addTestBooking(testBooking);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:12:54
	 * @param ids
	 */
	public int deleteTestBookingById(String id) {
		return this.testBookingDao.deleteTestBookingById(id);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:14:08
	 * @param ids
	 */
	public int batchDeleteTestBooking(String[] ids) {
		int result = 0;
		for (String id : ids) {
			this.deleteTestBookingById(id);
			result++;
		}
		return result;

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:55:43
	 * @param id
	 */
	public Map getTestBookingById(String id) {
		return this.testBookingDao.getTestBookingById(id);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 23:25:01
	 * @param testBooking
	 */
	public int updateTestBooking(TestBooking testBooking) {
		return this.testBookingDao.updateTestBooking(testBooking);

	}

	 /**
	 * 
	 * 
	 * @author Po Kong
	 * @since 2012-8-4 下午12:56:49
	 * @param page
	 */
	public Page listTestBookingForStudent(Page page) {
		return this.testBookingDao.listTestBookingForStudent(page);
		
	}

	 /**
	 * 
	 * 
	 * @author Po Kong
	 * @since 2012-10-26 下午3:19:16
	 * @param name
	 * @return
	 */
	public boolean isTestBookingNameExist(String name) {
		int result = this.testBookingDao.checkTestBookingNameCount(name);
		return result > 0;
	}

	 /**
	 * 
	 * 
	 * @author Po Kong
	 * @since 2012-10-26 下午3:35:51
	 */
	public List<Map<String,Object>> listAllTestBooking() {
		return this.testBookingDao.listAllTestBooking();
		
	}

}
