package com.tkxgz.elitecourse.dao.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.ListUtil;
import com.tkxgz.elitecourse.bean.ExamClass;
import com.tkxgz.elitecourse.mapper.ExamClassMapper;
/**
 * 考试分类Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class ExamClassBaseDao extends BaseDao<ExamClass> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询考试分类列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 考试分类分页数据
	 */
	public Page listExamClass(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_exam_class t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加考试分类
	 * 
	 * @author Soyi Yao
	 * @param examClass
	 *        考试分类bean
	 * @return 影响行数
	 */
	public int addExamClass(ExamClass examClass) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_exam_class (");
		   sql.append(" name , "); 
  sql.append(" order_number , "); 
  sql.append(" total_mark , "); 
  sql.append(" radio_subject_num , "); 
  sql.append(" status , "); 
  sql.append(" radio_subject_mark , "); 
  sql.append(" checkbox_subject_number , "); 
  sql.append(" checkbox_subject_mark , "); 
  sql.append(" judge_subject_number , "); 
  sql.append(" judge_subject_mark , "); 
  sql.append(" blank_subject_number , "); 
  sql.append(" blank_subject_mark , "); 
  sql.append(" create_time , "); 
  sql.append(" create_user_id , "); 
  sql.append(" create_user_name  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" now() , "); 
  sql.append(" ? , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { examClass.getName(), 
examClass.getOrderNumber(), 
examClass.getTotalMark(), 
examClass.getRadioSubjectNum(), 
examClass.getStatus(), 
examClass.getRadioSubjectMark(), 
examClass.getCheckboxSubjectNumber(), 
examClass.getCheckboxSubjectMark(), 
examClass.getJudgeSubjectNumber(), 
examClass.getJudgeSubjectMark(), 
examClass.getBlankSubjectNumber(), 
examClass.getBlankSubjectMark(), 
examClass.getCreateUserId(), 
examClass.getCreateUserName()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据考试分类id删除考试分类
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        考试分类id
	 * @return 影响行数
	 */
	public int deleteExamClassById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_exam_class ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据考试分类查询考试分类信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        考试分类id
	 * @return 考试分类Map
	 */
	public Map getExamClassById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_exam_class t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新考试分类
	 * 
	 * @author Soyi Yao
	 * @param examClass
	 *        考试分类bean
	 * @return 影响行数
	 */
	public int updateExamClass(ExamClass examClass) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_exam_class t");
		sql.append(" set   ");
		 sql.append(" t.name = ? , "); 
  sql.append(" t.order_number = ? , "); 
  sql.append(" t.total_mark = ? , "); 
  sql.append(" t.radio_subject_num = ? , "); 
  sql.append(" t.status = ? , "); 
  sql.append(" t.radio_subject_mark = ? , "); 
  sql.append(" t.checkbox_subject_number = ? , "); 
  sql.append(" t.checkbox_subject_mark = ? , "); 
  sql.append(" t.judge_subject_number = ? , "); 
  sql.append(" t.judge_subject_mark = ? , "); 
  sql.append(" t.blank_subject_number = ? , "); 
  sql.append(" t.blank_subject_mark = ? , "); 
  sql.append(" t.create_time = ? , "); 
  sql.append(" t.create_user_id = ? , "); 
  sql.append(" t.create_user_name = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { examClass.getName(), 
examClass.getOrderNumber(), 
examClass.getTotalMark(), 
examClass.getRadioSubjectNum(), 
examClass.getStatus(), 
examClass.getRadioSubjectMark(), 
examClass.getCheckboxSubjectNumber(), 
examClass.getCheckboxSubjectMark(), 
examClass.getJudgeSubjectNumber(), 
examClass.getJudgeSubjectMark(), 
examClass.getBlankSubjectNumber(), 
examClass.getBlankSubjectMark(), 
examClass.getCreateTime(), 
examClass.getCreateUserId(), 
examClass.getCreateUserName(), 
 examClass.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.INTEGER };
		
		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}


	/**
	 * 分页搜索考试分类列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param examClass
	 *        考试分类bean
	 * @return 分页考试分类列表
	 */
	public Page searchExamClass(Page page, ExamClass examClass) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_exam_class t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(examClass.getName())) {sql.append( "and t.name like '%"+examClass.getName()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getOrderNumber()))&& (!"all".equalsIgnoreCase(examClass.getOrderNumber()))){		sql.append(" and t.order_number = ? ");	valuesList.add(Integer.valueOf(examClass.getOrderNumber()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getTotalMark()))&& (!"all".equalsIgnoreCase(examClass.getTotalMark()))){		sql.append(" and t.total_mark = ? ");	valuesList.add(Integer.valueOf(examClass.getTotalMark()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getRadioSubjectNum()))&& (!"all".equalsIgnoreCase(examClass.getRadioSubjectNum()))){		sql.append(" and t.radio_subject_num = ? ");	valuesList.add(Integer.valueOf(examClass.getRadioSubjectNum()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(examClass.getStatus())) {sql.append( "and t.status like '%"+examClass.getStatus()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getRadioSubjectMark()))&& (!"all".equalsIgnoreCase(examClass.getRadioSubjectMark()))){		sql.append(" and t.radio_subject_mark = ? ");	valuesList.add(Integer.valueOf(examClass.getRadioSubjectMark()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getCheckboxSubjectNumber()))&& (!"all".equalsIgnoreCase(examClass.getCheckboxSubjectNumber()))){		sql.append(" and t.checkbox_subject_number = ? ");	valuesList.add(Integer.valueOf(examClass.getCheckboxSubjectNumber()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getCheckboxSubjectMark()))&& (!"all".equalsIgnoreCase(examClass.getCheckboxSubjectMark()))){		sql.append(" and t.checkbox_subject_mark = ? ");	valuesList.add(Integer.valueOf(examClass.getCheckboxSubjectMark()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getJudgeSubjectNumber()))&& (!"all".equalsIgnoreCase(examClass.getJudgeSubjectNumber()))){		sql.append(" and t.judge_subject_number = ? ");	valuesList.add(Integer.valueOf(examClass.getJudgeSubjectNumber()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getJudgeSubjectMark()))&& (!"all".equalsIgnoreCase(examClass.getJudgeSubjectMark()))){		sql.append(" and t.judge_subject_mark = ? ");	valuesList.add(Integer.valueOf(examClass.getJudgeSubjectMark()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getBlankSubjectNumber()))&& (!"all".equalsIgnoreCase(examClass.getBlankSubjectNumber()))){		sql.append(" and t.blank_subject_number = ? ");	valuesList.add(Integer.valueOf(examClass.getBlankSubjectNumber()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getBlankSubjectMark()))&& (!"all".equalsIgnoreCase(examClass.getBlankSubjectMark()))){		sql.append(" and t.blank_subject_mark = ? ");	valuesList.add(Integer.valueOf(examClass.getBlankSubjectMark()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(examClass.getCreateTime())) {sql.append( "and t.create_time like '%"+examClass.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(examClass.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+examClass.getCreateUserName()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索考试分类列表
	 * 
	 * @author Soyi Yao
	 * @param examClass
	 *        ExamClass实体
	 * @return 考试分类列表
	 */
	public List<ExamClass> searchExamClassForList(ExamClass examClass) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_exam_class t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(examClass.getName())) {sql.append( "and t.name like '%"+examClass.getName()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getOrderNumber()))&& (!"all".equalsIgnoreCase(examClass.getOrderNumber()))){		sql.append(" and t.order_number = ? ");	valuesList.add(Integer.valueOf(examClass.getOrderNumber()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getTotalMark()))&& (!"all".equalsIgnoreCase(examClass.getTotalMark()))){		sql.append(" and t.total_mark = ? ");	valuesList.add(Integer.valueOf(examClass.getTotalMark()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getRadioSubjectNum()))&& (!"all".equalsIgnoreCase(examClass.getRadioSubjectNum()))){		sql.append(" and t.radio_subject_num = ? ");	valuesList.add(Integer.valueOf(examClass.getRadioSubjectNum()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(examClass.getStatus())) {sql.append( "and t.status like '%"+examClass.getStatus()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getRadioSubjectMark()))&& (!"all".equalsIgnoreCase(examClass.getRadioSubjectMark()))){		sql.append(" and t.radio_subject_mark = ? ");	valuesList.add(Integer.valueOf(examClass.getRadioSubjectMark()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getCheckboxSubjectNumber()))&& (!"all".equalsIgnoreCase(examClass.getCheckboxSubjectNumber()))){		sql.append(" and t.checkbox_subject_number = ? ");	valuesList.add(Integer.valueOf(examClass.getCheckboxSubjectNumber()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getCheckboxSubjectMark()))&& (!"all".equalsIgnoreCase(examClass.getCheckboxSubjectMark()))){		sql.append(" and t.checkbox_subject_mark = ? ");	valuesList.add(Integer.valueOf(examClass.getCheckboxSubjectMark()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getJudgeSubjectNumber()))&& (!"all".equalsIgnoreCase(examClass.getJudgeSubjectNumber()))){		sql.append(" and t.judge_subject_number = ? ");	valuesList.add(Integer.valueOf(examClass.getJudgeSubjectNumber()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getJudgeSubjectMark()))&& (!"all".equalsIgnoreCase(examClass.getJudgeSubjectMark()))){		sql.append(" and t.judge_subject_mark = ? ");	valuesList.add(Integer.valueOf(examClass.getJudgeSubjectMark()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getBlankSubjectNumber()))&& (!"all".equalsIgnoreCase(examClass.getBlankSubjectNumber()))){		sql.append(" and t.blank_subject_number = ? ");	valuesList.add(Integer.valueOf(examClass.getBlankSubjectNumber()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(examClass.getBlankSubjectMark()))&& (!"all".equalsIgnoreCase(examClass.getBlankSubjectMark()))){		sql.append(" and t.blank_subject_mark = ? ");	valuesList.add(Integer.valueOf(examClass.getBlankSubjectMark()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(examClass.getCreateTime())) {sql.append( "and t.create_time like '%"+examClass.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(examClass.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+examClass.getCreateUserName()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new ExamClassMapper());
	}
	
	/**
	 * 根据考试分类id获取考试分类实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        考试分类id
	 * @return ExamClass实体
	 */
	public ExamClass findExamClassById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_exam_class t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		ExamClass examClass = this.queryForObject(sql.toString(), values, valueTypes,
				new ExamClassMapper());
		
		logger.info(sql.toString());

		return examClass;
	}


	/**
	 * 列出所有考试分类
	 * 
	 * @author Soyi Yao
	 * @return 考试分类列表
	 */
	public List<ExamClass> listAllExamClass() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_exam_class t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new ExamClassMapper());
	}
	
    /**
	 * 获取t_exam_class表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_exam_class表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_exam_class  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
