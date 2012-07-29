package com.tkxwz.esruocetile.webapp.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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

	public Page listStudentTestBooking(Page page) {
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
		sql.append(" delete from  t_studentTestBooking ");
		sql.append(" where id = ?  ");
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
		sql.append(" select t.* ");
		sql.append(" from t_studentTestBooking t where t.id = ? ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForMap(sql.toString(), values, valueTypes);

	}

	public Page searchStudentTestBooking(Page page,
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
				&& !"全部".equals(studentTestBooking.getGender())) {
			sql.append(" and a.gender = ? ");
			valuesList.add(studentTestBooking.getGender());
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
				&& !"全部".equals(studentTestBooking.getGender())) {
			sql.append(" and a.gender = ? ");
			valuesList.add(studentTestBooking.getGender());
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

		return queryForList(sql.toString(), values, valueTypes);
	}

}
