package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.CourseIntroduction;

/**
	 * 课程简介映射器内部类，用于返回范型的课程简介实体
	 * 
	 * @author Soyi Yao
	 */
	public class CourseIntroductionMapper implements RowMapper<CourseIntroduction> {

		public CourseIntroduction mapRow(ResultSet rs, int rowNum) throws SQLException {
			CourseIntroduction courseIntroduction = new CourseIntroduction();
			
			courseIntroduction.setId(rs.getString("id".toLowerCase())); 
courseIntroduction.setCourseHostName(rs.getString("course_host_name".toLowerCase())); 
courseIntroduction.setIntroContent(rs.getString("intro_content".toLowerCase())); 
courseIntroduction.setPic(rs.getString("pic".toLowerCase())); 

			return courseIntroduction;
		}
	}
