package com.tkxwz.esruocetile.webapp.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.ListUtil;
import com.tkxwz.esruocetile.webapp.entity.StudentTestBooking;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:12:25
 */
@Repository
public class StudentTestBookingDao extends BaseDao<StudentTestBooking> {

	private static Log logger = LogFactory.getLog(StudentTestBookingDao.class);

	public Page listStudentTestBooking(Page page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" b.test_booking_name , ");
		sql.append(" b.campus , ");
		sql.append(" a.id, ");
		sql.append(" a.name, ");
		sql.append(" a.student_no, ");
		sql.append(" a.college, ");
		sql.append(" a.grade, ");
		sql.append(" a.gender, ");
		sql.append(" a.nationality_code, ");
		sql.append(" a.nationality, ");
		sql.append(" a.date_of_birth, ");
		sql.append(" a.id_no, ");
		sql.append(" a.major, ");
		sql.append(" a.executive_class ");
		sql.append(" from t_student a, t_test_booking b, t_student_test_booking c ");
		sql.append(" where a.id = c.student_id  ");
		sql.append(" and b.id = c.test_booking_id  ");
		sql.append(" order by  b.id desc ");
		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:13:11
	 * @param ids
	 */
	public int deleteStudentTestBookingById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_student_test_booking ");
		sql.append(" where  student_id = ?  ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:56:00
	 * @param i
	 */
	public Map getStudentTestBookingById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" b.test_booking_name , ");
		sql.append(" b.campus , ");
		sql.append(" a.id, ");
		sql.append(" a.name, ");
		sql.append(" a.student_no, ");
		sql.append(" a.college, ");
		sql.append(" a.grade, ");
		sql.append(" a.gender, ");
		sql.append(" a.nationality_code, ");
		sql.append(" a.nationality, ");
		sql.append(" a.date_of_birth, ");
		sql.append(" a.id_no, ");
		sql.append(" a.major, ");
		sql.append(" a.executive_class ");
		sql.append(" from t_student a, t_test_booking b, t_student_test_booking c ");
		sql.append(" where a.id = c.student_id  ");
		sql.append(" and b.id = c.test_booking_id  ");
		sql.append(" and a.id = ?  ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForMap(sql.toString(), values, valueTypes);

	}

	public Page searchStudentTestBooking(Page page,
			StudentTestBooking studentTestBooking) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" b.id , ");
		sql.append(" b.test_booking_name , ");
		sql.append(" b.campus , ");
		sql.append(" a.name, ");
		sql.append(" a.student_no, ");
		sql.append(" a.college, ");
		sql.append(" a.grade, ");
		sql.append(" a.gender, ");
		sql.append(" a.nationality_code, ");
		sql.append(" a.nationality, ");
		sql.append(" a.date_of_birth, ");
		sql.append(" a.id_no, ");
		sql.append(" a.major, ");
		sql.append(" a.executive_class ");
		sql.append(" from t_student a, t_test_booking b, t_student_test_booking c ");
		sql.append(" where a.id = c.student_id  ");
		sql.append(" and b.id = c.test_booking_id  ");
		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();
		if (StringUtils.isNotEmpty(studentTestBooking.getCampus())
				&& !"0".equals(studentTestBooking.getCampus())) {
			sql.append(" and b.campus like '%" + studentTestBooking.getCampus()
					+ "%'");
		}

		if (StringUtils.isNotEmpty(studentTestBooking.getTestBookingName())) {
			sql.append(" and b.test_booking_name like  '%"
					+ studentTestBooking.getTestBookingName() + "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getStudentNo())) {
			sql.append(" and a.student_no like '%"
					+ studentTestBooking.getStudentNo() + "%'");
		}

		if (StringUtils.isNotEmpty(studentTestBooking.getName())) {
			sql.append(" and a.name like  '%" + studentTestBooking.getName()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getGrade())) {
			sql.append(" and a.grade like  '%" + studentTestBooking.getGrade()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getCollege())) {
			sql.append(" and a.college like  '%"
					+ studentTestBooking.getCollege() + "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getGender())
				&& !"all".equals(studentTestBooking.getGender())) {
			sql.append(" and a.gender = ? ");
			if (studentTestBooking.getGender().equals("1")) {
				valuesList.add("男");
			} else {
				valuesList.add("女");
			}
			valueTypesList.add(Types.VARCHAR);
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getIdNo())) {
			sql.append(" and a.id_no like '%" + studentTestBooking.getIdNo()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getMajor())) {
			sql.append(" and a.major like '%" + studentTestBooking.getMajor()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getExecutiveClaas())) {
			sql.append(" and a.executive_class like '%"
					+ studentTestBooking.getExecutiveClaas() + "%'");
		}

		sql.append(" order by  b.id desc ");
		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		logger.debug("StudentTestBookingDao:searchStudentTestBooking(Page page,StudentTestBooking studentTestBooking):"
				+ sql.toString());
		return queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * @author Po Kong
	 * @since 29 Jul 2012 11:57:53
	 * @param studentTestBooking
	 * @return
	 */
	public List<Map<String, Object>> searchStudentTestBooking(
			StudentTestBooking studentTestBooking) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" b.test_booking_name , ");
		sql.append(" b.campus , ");
		sql.append(" a.name, ");
		sql.append(" a.student_no, ");
		sql.append(" a.college, ");
		sql.append(" a.grade, ");
		sql.append(" a.gender, ");
		sql.append(" a.nationality_code, ");
		sql.append(" a.nationality, ");
		sql.append(" a.date_of_birth, ");
		sql.append(" a.id_no, ");
		sql.append(" a.major, ");
		sql.append(" a.executive_class ");
		sql.append(" from t_student a, t_test_booking b, t_student_test_booking c ");
		sql.append(" where a.id = c.student_id  ");
		sql.append(" and b.id = c.test_booking_id  ");
		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();
		if (StringUtils.isNotEmpty(studentTestBooking.getCampus())
				&& !"0".equals(studentTestBooking.getCampus())) {
			sql.append(" and b.campus like '%" + studentTestBooking.getCampus()
					+ "%'");
		}

		if (StringUtils.isNotEmpty(studentTestBooking.getTestBookingName())) {
			sql.append(" and b.test_booking_name like  '%"
					+ studentTestBooking.getTestBookingName() + "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getStudentNo())) {
			sql.append(" and a.student_no like '%"
					+ studentTestBooking.getStudentNo() + "%'");
		}

		if (StringUtils.isNotEmpty(studentTestBooking.getName())) {
			sql.append(" and a.name like  '%" + studentTestBooking.getName()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getGrade())) {
			sql.append(" and a.grade like  '%" + studentTestBooking.getGrade()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getCollege())) {
			sql.append(" and a.college like  '%"
					+ studentTestBooking.getCollege() + "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getGender())
				&& !"all".equals(studentTestBooking.getGender())) {
			sql.append(" and a.gender = ? ");
			logger.debug("性别:" + studentTestBooking.getGender());
			if (studentTestBooking.getGender().equals("1")) {
				valuesList.add("男");
			} else {
				valuesList.add("女");
			}
			valueTypesList.add(Types.VARCHAR);
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getIdNo())) {
			sql.append(" and a.id_no like '%" + studentTestBooking.getIdNo()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getMajor())) {
			sql.append(" and a.major like '%" + studentTestBooking.getMajor()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(studentTestBooking.getExecutiveClaas())) {
			sql.append(" and a.executive_class like '%"
					+ studentTestBooking.getExecutiveClaas() + "%'");
		}

		sql.append(" order by  b.id desc ");
		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		logger.debug("StudentTestBookingDao:searchStudentTestBooking(StudentTestBooking studentTestBooking):"
				+ sql.toString());
		return queryForList(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 2012-8-4 下午1:46:16
	 * @param studentTestBooking
	 * @return
	 */
	public int addStudentTestBooking(String studentId, String testBookingId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into   t_student_test_booking(student_id, test_booking_id ) ");
		sql.append(" values  ( ? , ? ) ");
		Object[] values = { Integer.valueOf(studentId),
				Integer.valueOf(testBookingId) };
		int[] valueTypes = { Types.INTEGER, Types.INTEGER };
		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 2012-8-4 下午3:54:27
	 * @param studentId
	 * @return
	 */
	public List<Map<String, Object>> getStudentTestBookingByStudentId(
			String studentId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" b.id , ");
		sql.append(" b.test_booking_name , ");
		sql.append(" b.campus , ");
		sql.append(" a.id, ");
		sql.append(" a.name, ");
		sql.append(" a.student_no, ");
		sql.append(" a.college, ");
		sql.append(" a.grade, ");
		sql.append(" a.gender, ");
		sql.append(" a.nationality_code, ");
		sql.append(" a.nationality, ");
		sql.append(" a.date_of_birth, ");
		sql.append(" a.id_no, ");
		sql.append(" a.major, ");
		sql.append(" a.executive_class ");
		sql.append(" from t_student a, t_test_booking b, t_student_test_booking c ");
		sql.append(" where a.id = c.student_id  ");
		sql.append(" and b.id = c.test_booking_id  ");
		sql.append(" and a.id = ?  ");
		Object[] values = { Integer.parseInt(studentId) };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForList(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 2012-8-4 下午4:55:18
	 * @param studentId
	 * @return
	 */
	public int updateStudentTestBooking(String studentId, String testBookingId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_student_test_booking a set   test_booking_id = ?   ");
		sql.append(" where   a.student_id = ?  ");
		Object[] values = { Integer.valueOf(testBookingId),
				Integer.valueOf(studentId) };
		int[] valueTypes = { Types.INTEGER, Types.INTEGER };
		return this.update(sql.toString(), values, valueTypes);
	}

}
