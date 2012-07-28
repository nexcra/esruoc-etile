package com.tkxwz.esruocetile.webapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.ListUtil;
import com.tkxwz.esruocetile.webapp.entity.Student;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:12:25
 */
@Repository
public class StudentDao extends BaseDao<Student> {

	public Page listStudent(Page page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_student t ");
		sql.append(" order by  t.id desc ");
		return this.queryForPage(sql.toString(), page);
	}

	public int addStudent(Student student) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_student  ");
		sql.append(" ( ");
		sql.append(" student_no, ");
		sql.append(" name, ");
		sql.append(" gender, ");
		sql.append(" nationality_code, ");
		sql.append(" nationality, ");
		sql.append(" date_of_birth, ");
		sql.append(" id_no, ");
		sql.append(" major, ");
		sql.append(" executive_class ");
		sql.append(" ) ");
		sql.append(" values ");
		sql.append(" ( ?, ?, ?, ?, ? ,? ,? ,?,? ) ");
		Object[] values = { student.getStudentNo(), student.getName(),
				student.getGender(), student.getNotionalityCode(),
				student.getNationality(), student.getDateOfBirth(),
				student.getIdNo(), student.getMajor(),
				student.getExecutiveClaas(),

		};
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR };
		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:13:11
	 * @param ids
	 */
	public int deleteStudentById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_student ");
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
	public Map getStudentById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_student t where t.id = ? ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForMap(sql.toString(), values, valueTypes);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 23:25:24
	 * @param student
	 * @return
	 */
	public int updateStudent(Student student) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_student t");
		sql.append(" set   ");
		sql.append(" student_no = ? , ");
		sql.append(" name = ? , ");
		sql.append(" gender = ? , ");
		sql.append(" nationality_code = ? , ");
		sql.append(" nationality = ? , ");
		sql.append(" date_of_birth = ? , ");
		sql.append(" id_no = ? , ");
		sql.append(" major = ? , ");
		sql.append(" executive_class  = ? ");
		sql.append(" where t.id = ? ");
		Object[] values = { student.getStudentNo(), student.getName(),
				student.getGender(), student.getNotionalityCode(),
				student.getNationality(), student.getDateOfBirth(),
				student.getIdNo(), student.getMajor(),
				student.getExecutiveClaas(), student.getId()

		};
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		return this.update(sql.toString(), values, valueTypes);
	}

	public Page searchStudent(Page page, Student student) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from t_student t ");
		sql.append(" where 1 = 1 ");
		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();
		if (StringUtils.isNotEmpty(student.getStudentNo())) {
			sql.append(" and t.student_no like '%" + student.getStudentNo()
					+ "%'");
		}

		if (StringUtils.isNotEmpty(student.getName())) {
			sql.append(" and t.name like  '%" + student.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(student.getGender())
				&& !"全部".equals(student.getGender())) {
			sql.append(" and t.gender = ? ");
			valuesList.add(student.getGender());
			valueTypesList.add(Types.VARCHAR);
		}
		if (StringUtils.isNotEmpty(student.getIdNo())) {
			sql.append(" and t.id_no like '%" + student.getIdNo() + "%'");
		}
		if (StringUtils.isNotEmpty(student.getMajor())) {
			sql.append(" and t.major like '%" + student.getMajor() + "%'");
		}
		if (StringUtils.isNotEmpty(student.getExecutiveClaas())) {
			sql.append(" and t.executive_class like '%"
					+ student.getExecutiveClaas() + "%'");
		}
		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		return queryForPage(sql.toString(), values, valueTypes, page);
	}

}
