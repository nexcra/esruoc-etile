package com.tkxwz.esruocetile.webapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.dao.StudentTestBookingDao;
import com.tkxwz.esruocetile.webapp.entity.StudentTestBooking;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:14:39
 */
@Service
public class StudentTestBookingService {

	@Autowired
	private StudentTestBookingDao studentTestBookingDao;

	public Page listStudentTestBooking(Page page) {
		return this.studentTestBookingDao.listStudentTestBooking(page);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:12:54
	 * @param ids
	 */
	public int deleteStudentTestBookingById(String id) {
		return this.studentTestBookingDao.deleteStudentTestBookingById(id);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:14:08
	 * @param ids
	 */
	public int batchDeleteStudentTestBooking(String[] ids) {
		int result = 0;
		for (String id : ids) {
			this.deleteStudentTestBookingById(id);
			result++;
		}
		return result;

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:55:43
	 * @param id
	 */
	public Map getStudentTestBookingById(String id) {
		return this.studentTestBookingDao.getStudentTestBookingById(id);

	}

	/**
	 * @author Po Kong
	 * @since 28 Jul 2012 23:01:41
	 * @param studentTestBooking
	 */
	public Page searchStudentTestBooking(Page page,
			StudentTestBooking studentTestBooking) {
		return this.studentTestBookingDao.searchStudentTestBooking(page,
				studentTestBooking);
	}

	public List<Map<String, Object>> searchStudentTestBooking(
			StudentTestBooking studentTestBooking) {
		return this.studentTestBookingDao
				.searchStudentTestBooking(studentTestBooking);
	}
}
