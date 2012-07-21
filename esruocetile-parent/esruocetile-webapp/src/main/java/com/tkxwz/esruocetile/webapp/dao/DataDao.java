package com.tkxwz.esruocetile.webapp.dao;

import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.tkxwz.esruocetile.webapp.entity.Student;

/**
 * @author Po Kong 
 * @since 2012-7-5 下午8:11:43
 */
@Repository
public class DataDao extends BaseDao {

	public void addStudent(Student student) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_student(name,id_no,student_no)");
		sql.append(" values (?,?,?)");

		Object[] values = { student.getName(), student.getStudentNo(),student.getIdNo() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

		this.insert(sql.toString(), values, valueTypes);
	}
}
