package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tkxgz.elitecourse.bean.User;

/**
 * 用户映射器内部类，用于返回范型的用户实体
 * 
 * @author Soyi Yao
 */
public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();

		user.setId(rs.getString("id".toLowerCase()));
		user.setName(rs.getString("name".toLowerCase()));
		user.setRealName(rs.getString("real_name".toLowerCase()));
		user.setPassword(rs.getString("password".toLowerCase()));
		user.setStatus(rs.getString("status".toLowerCase()));
		user.setGender(rs.getString("gender".toLowerCase()));
		user.setOrigin(rs.getString("origin".toLowerCase()));
		user.setBirthDate(rs.getString("birth_date".toLowerCase()));
		user.setGroupId(rs.getString("group_id".toLowerCase()));
		user.setEmail(rs.getString("email".toLowerCase()));
		user.setTelephone(rs.getString("telephone".toLowerCase()));
		user.setIsAdmin(rs.getString("is_admin".toLowerCase()));
		user.setIsLocked(rs.getString("is_locked".toLowerCase()));
		user.setAge(rs.getString("age".toLowerCase()));
		user.setRole(rs.getString("role".toLowerCase()));
		user.setClassesId(rs.getString("classes_id".toLowerCase()));
		user.setCreateUserName(rs.getString("create_user_name".toLowerCase()));
		user.setCreateUserId(rs.getString("create_user_id".toLowerCase()));
		user.setCreateTime(rs.getString("create_time".toLowerCase()));
		user.setUpdateUserName(rs.getString("update_user_name".toLowerCase()));
		user.setUpdateUserId(rs.getString("update_user_id".toLowerCase()));
		user.setUpdateTime(rs.getString("update_time".toLowerCase()));
		user.setRemark(rs.getString("remark".toLowerCase()));
		user.setStudentNo(rs.getString("student_no".toLowerCase()));

		return user;
	}
}
