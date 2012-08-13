package com.tkxwz.esruocetile.webapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.dao.StudentTestBookingDao;
import com.tkxwz.esruocetile.webapp.dao.TestBookingDao;
import com.tkxwz.esruocetile.webapp.entity.StudentTestBooking;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:14:39
 */
@Service
public class StudentTestBookingService {

	@Autowired
	private StudentTestBookingDao studentTestBookingDao;

	@Autowired
	private TestBookingDao testBookingDao;

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

	/**
	 * @author Po Kong
	 * @since 2012-8-4 下午1:45:34
	 * @param studentTestBooking
	 */
	public int addStudentTestBooking(String studentId, String testBookingId) {
		List<Map<String, Object>> list = this.studentTestBookingDao
				.getStudentTestBookingByStudentId(studentId);
		int result = 0;
		boolean noNeedToAdd = null != list && list.size() > 0;

		if (noNeedToAdd) {

			String oldTestBookingId = String.valueOf(list.get(0).get(
					"test_booking_id"));
			boolean needUpdate = !testBookingId.equals(oldTestBookingId);
			if (needUpdate) {
				result = this.studentTestBookingDao.updateStudentTestBooking(
						studentId, testBookingId);
				if (0 != result) { // update the testBooking record of the current_booking_num
					result = this.testBookingDao
							.decreaseCurrentBookingNum(oldTestBookingId);
					result = this.testBookingDao
							.increaseCurrentBookingNum(testBookingId);
				}
			}
		} else {
			result = this.studentTestBookingDao.addStudentTestBooking(
					studentId, testBookingId);
			if (0 != result) {// //update the testBooking record of the current_booking_num
				result = this.testBookingDao
						.increaseCurrentBookingNum(testBookingId);
			}
		}
		return result;
	}

	/**
	 * @author Po Kong
	 * @since 2012-8-4 下午3:54:02
	 * @param studentId
	 * @return
	 */
	public List<Map<String, Object>> getStudentTestBookingByStudentId(
			String studentId) {
		return this.studentTestBookingDao
				.getStudentTestBookingByStudentId(studentId);
	}

	/**
	 * @author Po Kong
	 * @since 2012-8-4 下午5:03:49
	 * @param studentId
	 */
	public int deleteStudentTestBooking(String studentId,
			String oldTestBookingId) {
		int result = 0;
		result = this.studentTestBookingDao

		.deleteStudentTestBookingById(studentId);
		if (0 != result) {
			result = this.testBookingDao
					.decreaseCurrentBookingNum(oldTestBookingId);
		}
		return result;

	}

	/**
	 * @author Po Kong
	 * @since 2012-9-13 下午9:42:45
	 * @param studentId
	 * @param testBookingId
	 * @return
	 */
	public int updateStudentTestBooking(String studentId, String testBookingId) {
		// TODO Auto-generated method stub
		return this.studentTestBookingDao.updateStudentTestBooking(studentId,
				testBookingId);
	}

	/**
	 * @author Po Kong
	 * @since 2012-9-13 下午9:42:49
	 * @param oldTestBookingId
	 * @return
	 */
	public int decreaseCurrentBookingNum(String oldTestBookingId) {

		return this.testBookingDao.decreaseCurrentBookingNum(oldTestBookingId);
	}

	/**
	 * @author Po Kong
	 * @since 2012-9-13 下午9:42:55
	 * @param testBookingId
	 * @return
	 */
	public int increaseCurrentBookingNum(String testBookingId) {

		return this.testBookingDao.increaseCurrentBookingNum(testBookingId);
	}

	/**
	 * @author Po Kong
	 * @since 2012-9-13 下午9:50:40
	 * @param oldTestBookingId
	 * @return
	 */
	public boolean isStopBooking(String oldTestBookingId) {
		Map<String, Object> map = this.testBookingDao
				.getTestBookingById(oldTestBookingId);
		boolean result = false;
		if (Double.valueOf((Double) map.get("end_booking")).intValue() < 0) {
			result = true;
		}

		return result;
	}
}
