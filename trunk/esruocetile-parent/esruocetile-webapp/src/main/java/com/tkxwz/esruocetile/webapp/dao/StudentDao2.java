package com.tkxwz.esruocetile.webapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.ListUtil;
import com.tkxwz.esruocetile.webapp.entity.Student;

/**
 * @author Po Kong
 * @since 2012-7-6 下午4:46:56
 */
@Repository
public class StudentDao2 extends BaseDao<Student> {

	public int addStudent(Student student) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_student (name, student_no ,id_no) ");
		sql.append(" values (? , ? , ?) ");
		Object[] values = { student.getName(), student.getStudentNo(),
				student.getIdNo() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		return this.insert(sql.toString(), values, valueTypes);
	}

	public int deleteStudentById(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from t_student t ");
		sql.append(" where t.id = ? ");
		Object[] values = { id };
		int[] valueTypes = { Types.INTEGER };
		return this.delete(sql.toString(), values, valueTypes);
	}

	public Student getStudentDetailById(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.student_no, t.id_no from t_student t ");
		sql.append(" where t.id = ? ");
		Object[] values = { id };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		return this.queryForObject(sql.toString(), values, valueTypes,
				new RowMapper<Student>() {

					public Student mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Student student = new Student();
						student.setId(rs.getInt("id"));
						student.setName(rs.getString("name"));
						student.setStudentNo(rs.getString("student_no"));
						student.setIdNo(rs.getString("id_no"));
						return student;
					}

				});
	}

	public int updateStudent(Student student) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_student t ");
		sql.append(" set t.name = ? ,");
		sql.append(" set t.student_no = ? ,");
		sql.append(" set t.id_no = ? ,");
		sql.append(" where t.id = ? ");
		Object[] values = { student.getName(), student.getStudentNo(),
				student.getIdNo(), student.getId() };
		int[] valueTypes = { Types.INTEGER };
		return this.update(sql.toString(), values, valueTypes);
	}

	public Page queryStudent(Page page, Student student) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.student_no, t.id_no from t_student t ");
		sql.append(" where 1 = 1 ");
		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();
		if (StringUtils.isNotEmpty(student.getName())) {
			sql.append(" and t.name like ? ");
			valuesList.add(student.getName());
			valueTypesList.add(Types.VARCHAR);
		}
		if (StringUtils.isNotEmpty(student.getStudentNo())) {
			sql.append(" and t.student_no like ? ");
			valuesList.add(student.getStudentNo());
			valueTypesList.add(Types.VARCHAR);
		}

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		return queryForPage(sql.toString(), values, valueTypes, page);
	}

}
