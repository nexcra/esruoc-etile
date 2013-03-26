package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.ExamClass;

/**
	 * 考试分类映射器内部类，用于返回范型的考试分类实体
	 * 
	 * @author Soyi Yao
	 */
	public class ExamClassMapper implements RowMapper<ExamClass> {

		public ExamClass mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExamClass examClass = new ExamClass();
			
			examClass.setId(rs.getString("id".toLowerCase())); 
examClass.setName(rs.getString("name".toLowerCase())); 
examClass.setOrderNumber(rs.getString("order_number".toLowerCase())); 
examClass.setTotalMark(rs.getString("total_mark".toLowerCase())); 
examClass.setRadioSubjectNum(rs.getString("radio_subject_num".toLowerCase())); 
examClass.setStatus(rs.getString("status".toLowerCase())); 
examClass.setRadioSubjectMark(rs.getString("radio_subject_mark".toLowerCase())); 
examClass.setCheckboxSubjectNumber(rs.getString("checkbox_subject_number".toLowerCase())); 
examClass.setCheckboxSubjectMark(rs.getString("checkbox_subject_mark".toLowerCase())); 
examClass.setJudgeSubjectNumber(rs.getString("judge_subject_number".toLowerCase())); 
examClass.setJudgeSubjectMark(rs.getString("judge_subject_mark".toLowerCase())); 
examClass.setBlankSubjectNumber(rs.getString("blank_subject_number".toLowerCase())); 
examClass.setBlankSubjectMark(rs.getString("blank_subject_mark".toLowerCase())); 
examClass.setCreateTime(rs.getString("create_time".toLowerCase())); 
examClass.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
examClass.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 

			return examClass;
		}
	}
