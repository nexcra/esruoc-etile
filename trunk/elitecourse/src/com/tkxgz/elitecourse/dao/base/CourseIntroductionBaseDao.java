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
import com.tkxgz.elitecourse.bean.CourseIntroduction;
import com.tkxgz.elitecourse.mapper.CourseIntroductionMapper;
/**
 * 课程简介Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class CourseIntroductionBaseDao extends BaseDao<CourseIntroduction> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询课程简介列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 课程简介分页数据
	 */
	public Page listCourseIntroduction(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_course_introduction t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加课程简介
	 * 
	 * @author Soyi Yao
	 * @param courseIntroduction
	 *        课程简介bean
	 * @return 影响行数
	 */
	public int addCourseIntroduction(CourseIntroduction courseIntroduction) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_course_introduction (");
		   sql.append(" course_host_name , "); 
  sql.append(" intro_content , "); 
  sql.append(" pic  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { courseIntroduction.getCourseHostName(), 
courseIntroduction.getIntroContent(), 
courseIntroduction.getPic()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据课程简介id删除课程简介
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        课程简介id
	 * @return 影响行数
	 */
	public int deleteCourseIntroductionById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_course_introduction ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据课程简介查询课程简介信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        课程简介id
	 * @return 课程简介Map
	 */
	public Map getCourseIntroductionById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_course_introduction t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新课程简介
	 * 
	 * @author Soyi Yao
	 * @param courseIntroduction
	 *        课程简介bean
	 * @return 影响行数
	 */
	public int updateCourseIntroduction(CourseIntroduction courseIntroduction) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_course_introduction t");
		sql.append(" set   ");
		 sql.append(" t.course_host_name = ? , "); 
  sql.append(" t.intro_content = ? , "); 
  sql.append(" t.pic = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { courseIntroduction.getCourseHostName(), 
courseIntroduction.getIntroContent(), 
courseIntroduction.getPic(), 
 courseIntroduction.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.INTEGER };
		
		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}


	/**
	 * 分页搜索课程简介列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param courseIntroduction
	 *        课程简介bean
	 * @return 分页课程简介列表
	 */
	public Page searchCourseIntroduction(Page page, CourseIntroduction courseIntroduction) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_course_introduction t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(courseIntroduction.getCourseHostName())) {sql.append( "and t.course_host_name like '%"+courseIntroduction.getCourseHostName()+"%'");}if (StringUtils.isNotEmpty(courseIntroduction.getIntroContent())) {sql.append( "and t.intro_content like '%"+courseIntroduction.getIntroContent()+"%'");}if (StringUtils.isNotEmpty(courseIntroduction.getPic())) {sql.append( "and t.pic like '%"+courseIntroduction.getPic()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索课程简介列表
	 * 
	 * @author Soyi Yao
	 * @param courseIntroduction
	 *        CourseIntroduction实体
	 * @return 课程简介列表
	 */
	public List<CourseIntroduction> searchCourseIntroductionForList(CourseIntroduction courseIntroduction) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_course_introduction t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(courseIntroduction.getCourseHostName())) {sql.append( "and t.course_host_name like '%"+courseIntroduction.getCourseHostName()+"%'");}if (StringUtils.isNotEmpty(courseIntroduction.getIntroContent())) {sql.append( "and t.intro_content like '%"+courseIntroduction.getIntroContent()+"%'");}if (StringUtils.isNotEmpty(courseIntroduction.getPic())) {sql.append( "and t.pic like '%"+courseIntroduction.getPic()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new CourseIntroductionMapper());
	}
	
	/**
	 * 根据课程简介id获取课程简介实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        课程简介id
	 * @return CourseIntroduction实体
	 */
	public CourseIntroduction findCourseIntroductionById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_course_introduction t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		CourseIntroduction courseIntroduction = this.queryForObject(sql.toString(), values, valueTypes,
				new CourseIntroductionMapper());
		
		logger.info(sql.toString());

		return courseIntroduction;
	}


	/**
	 * 列出所有课程简介
	 * 
	 * @author Soyi Yao
	 * @return 课程简介列表
	 */
	public List<CourseIntroduction> listAllCourseIntroduction() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_course_introduction t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new CourseIntroductionMapper());
	}
	
    /**
	 * 获取t_course_introduction表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_course_introduction表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_course_introduction  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
