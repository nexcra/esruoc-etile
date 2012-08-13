package com.tkxwz.esruocetile.webapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.entity.TestBooking;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:12:25
 */
@Repository
public class TestBookingDao extends BaseDao<TestBooking> {

	public Page listTestBooking(Page page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,  ");
		sql.append("  		now() -t.booking_begin_time  begin_booking , ");
		sql.append("  		t.booking_end_time - now()   end_booking   ");
		sql.append(" from t_test_booking t ");
		sql.append(" order by  t.id desc ");
		return this.queryForPage(sql.toString(), page);
	}

	public int addTestBooking(TestBooking testBooking) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_test_booking  ");
		sql.append(" ( ");
		sql.append(" test_booking_name, ");
		sql.append(" campus, ");
		sql.append(" description, ");
		sql.append(" booking_begin_time, ");
		sql.append(" booking_end_time, ");
		sql.append(" max_booking_num, ");
		sql.append(" status, ");
		sql.append(" insert_time ");
		sql.append(" ) ");
		sql.append(" values ");
		sql.append(" ( ?, ?, ?, ?, ? ,? ,?,now()) ");
		Object[] values = { testBooking.getName(), testBooking.getCampus(),
				testBooking.getDescription(),
				testBooking.getBookingBeginTime(),
				testBooking.getBookingEndTime(),
				testBooking.getMaxBookingNum(), testBooking.getStatus() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER };
		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:13:11
	 * @param ids
	 */
	public int deleteTestBookingById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_test_booking ");
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
	public Map<String,Object> getTestBookingById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, ");
		sql.append("  		now() -t.booking_begin_time  begin_booking , ");
		sql.append("  		t.booking_end_time - now()   end_booking  ");
		sql.append(" from t_test_booking t where t.id = ? ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForMap(sql.toString(), values, valueTypes);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 23:25:24
	 * @param testBooking
	 * @return
	 */
	public int updateTestBooking(TestBooking testBooking) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_test_booking t");
		sql.append(" set t.test_booking_name = ? , ");
		sql.append(" t.campus= ? , ");
		sql.append(" t.description= ? , ");
		sql.append(" t.booking_begin_time= ? , ");
		sql.append(" t.booking_end_time= ? , ");
		sql.append(" t.max_booking_num= ? , ");
		sql.append(" t.status= ?  ");
		sql.append(" where t.id = ? ");
		Object[] values = { testBooking.getName(), testBooking.getCampus(),
				testBooking.getDescription(),
				testBooking.getBookingBeginTime(),
				testBooking.getBookingEndTime(),
				testBooking.getMaxBookingNum(), testBooking.getStatus(),
				testBooking.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER,
				Types.INTEGER };
		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 2012-8-4 下午12:57:36
	 * @param page
	 * @return
	 */
	public Page listTestBookingForStudent(Page page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, ");
		sql.append("  		now() -t.booking_begin_time  begin_booking , ");
		sql.append("  		t.booking_end_time - now()   end_booking  ");
		sql.append(" from t_test_booking t ");
		sql.append(" order by  t.id desc ");
		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * @author Po Kong
	 * @since 2012-8-8 下午9:18:45
	 * @param testBookingId
	 */
	public int increaseCurrentBookingNum(String testBookingId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_test_booking t");
		sql.append(" set   ");
		sql.append(" t.current_booking_num=  t.current_booking_num + 1 ");
		sql.append(" where t.id = ? ");
		Object[] values = { testBookingId };
		int[] valueTypes = { Types.INTEGER };
		return this.update(sql.toString(), values, valueTypes);

	}

	public int decreaseCurrentBookingNum(String testBookingId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_test_booking t");
		sql.append(" set   ");
		sql.append(" t.current_booking_num=  t.current_booking_num - 1 ");
		sql.append(" where t.id = ? ");
		Object[] values = { testBookingId };
		int[] valueTypes = { Types.INTEGER };
		return this.update(sql.toString(), values, valueTypes);

	}

}
