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
import com.tkxgz.elitecourse.bean.Assignment;
import com.tkxgz.elitecourse.mapper.AssignmentMapper;
/**
 * 作业Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class AssignmentBaseDao extends BaseDao<Assignment> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询作业列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 作业分页数据
	 */
	public Page listAssignment(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_assignment t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加作业
	 * 
	 * @author Soyi Yao
	 * @param assignment
	 *        作业bean
	 * @return 影响行数
	 */
	public int addAssignment(Assignment assignment) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_assignment (");
		   sql.append(" user_id , "); 
  sql.append(" user_name , "); 
  sql.append(" assignment_class_id , "); 
  sql.append(" submit_time , "); 
  sql.append(" classes_id , "); 
  sql.append(" description , "); 
  sql.append(" content , "); 
  sql.append(" path , "); 
  sql.append(" grade , "); 
  sql.append(" re_content , "); 
  sql.append(" re_time  ");  
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
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { assignment.getUserId(), 
assignment.getUserName(), 
assignment.getAssignmentClassId(), 
assignment.getSubmitTime(), 
assignment.getClassesId(), 
assignment.getDescription(), 
assignment.getContent(), 
assignment.getPath(), 
assignment.getGrade(), 
assignment.getReContent(), 
assignment.getReTime()};
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
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据作业id删除作业
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        作业id
	 * @return 影响行数
	 */
	public int deleteAssignmentById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_assignment ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据作业查询作业信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        作业id
	 * @return 作业Map
	 */
	public Map getAssignmentById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_assignment t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新作业
	 * 
	 * @author Soyi Yao
	 * @param assignment
	 *        作业bean
	 * @return 影响行数
	 */
	public int updateAssignment(Assignment assignment) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_assignment t");
		sql.append(" set   ");
		 sql.append(" t.user_id = ? , "); 
  sql.append(" t.user_name = ? , "); 
  sql.append(" t.assignment_class_id = ? , "); 
  sql.append(" t.submit_time = ? , "); 
  sql.append(" t.classes_id = ? , "); 
  sql.append(" t.description = ? , "); 
  sql.append(" t.content = ? , "); 
  sql.append(" t.path = ? , "); 
  sql.append(" t.grade = ? , "); 
  sql.append(" t.re_content = ? , "); 
  sql.append(" t.re_time = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { assignment.getUserId(), 
assignment.getUserName(), 
assignment.getAssignmentClassId(), 
assignment.getSubmitTime(), 
assignment.getClassesId(), 
assignment.getDescription(), 
assignment.getContent(), 
assignment.getPath(), 
assignment.getGrade(), 
assignment.getReContent(), 
assignment.getReTime(), 
 assignment.getId()};
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
 Types.INTEGER };
		
		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}


	/**
	 * 分页搜索作业列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param assignment
	 *        作业bean
	 * @return 分页作业列表
	 */
	public Page searchAssignment(Page page, Assignment assignment) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_assignment t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

			if (StringUtils.isNotEmpty(String.valueOf(assignment.getUserId()))&& (!"all".equalsIgnoreCase(assignment.getUserId()))){		sql.append(" and t.user_id = ? ");	valuesList.add(Integer.valueOf(assignment.getUserId()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(assignment.getUserName())) {sql.append( "and t.user_name like '%"+assignment.getUserName()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(assignment.getAssignmentClassId()))&& (!"all".equalsIgnoreCase(assignment.getAssignmentClassId()))){		sql.append(" and t.assignment_class_id = ? ");	valuesList.add(Integer.valueOf(assignment.getAssignmentClassId()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(assignment.getSubmitTime())) {sql.append( "and t.submit_time like '%"+assignment.getSubmitTime()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(assignment.getClassesId()))&& (!"all".equalsIgnoreCase(assignment.getClassesId()))){		sql.append(" and t.classes_id = ? ");	valuesList.add(Integer.valueOf(assignment.getClassesId()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(assignment.getDescription())) {sql.append( "and t.description like '%"+assignment.getDescription()+"%'");}if (StringUtils.isNotEmpty(assignment.getContent())) {sql.append( "and t.content like '%"+assignment.getContent()+"%'");}if (StringUtils.isNotEmpty(assignment.getPath())) {sql.append( "and t.path like '%"+assignment.getPath()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(assignment.getGrade()))&& (!"all".equalsIgnoreCase(assignment.getGrade()))){		sql.append(" and t.grade = ? ");	valuesList.add(Integer.valueOf(assignment.getGrade()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(assignment.getReContent())) {sql.append( "and t.re_content like '%"+assignment.getReContent()+"%'");}if (StringUtils.isNotEmpty(assignment.getReTime())) {sql.append( "and t.re_time like '%"+assignment.getReTime()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索作业列表
	 * 
	 * @author Soyi Yao
	 * @param assignment
	 *        Assignment实体
	 * @return 作业列表
	 */
	public List<Assignment> searchAssignmentForList(Assignment assignment) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_assignment t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

			if (StringUtils.isNotEmpty(String.valueOf(assignment.getUserId()))&& (!"all".equalsIgnoreCase(assignment.getUserId()))){		sql.append(" and t.user_id = ? ");	valuesList.add(Integer.valueOf(assignment.getUserId()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(assignment.getUserName())) {sql.append( "and t.user_name like '%"+assignment.getUserName()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(assignment.getAssignmentClassId()))&& (!"all".equalsIgnoreCase(assignment.getAssignmentClassId()))){		sql.append(" and t.assignment_class_id = ? ");	valuesList.add(Integer.valueOf(assignment.getAssignmentClassId()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(assignment.getSubmitTime())) {sql.append( "and t.submit_time like '%"+assignment.getSubmitTime()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(assignment.getClassesId()))&& (!"all".equalsIgnoreCase(assignment.getClassesId()))){		sql.append(" and t.classes_id = ? ");	valuesList.add(Integer.valueOf(assignment.getClassesId()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(assignment.getDescription())) {sql.append( "and t.description like '%"+assignment.getDescription()+"%'");}if (StringUtils.isNotEmpty(assignment.getContent())) {sql.append( "and t.content like '%"+assignment.getContent()+"%'");}if (StringUtils.isNotEmpty(assignment.getPath())) {sql.append( "and t.path like '%"+assignment.getPath()+"%'");}	if (StringUtils.isNotEmpty(String.valueOf(assignment.getGrade()))&& (!"all".equalsIgnoreCase(assignment.getGrade()))){		sql.append(" and t.grade = ? ");	valuesList.add(Integer.valueOf(assignment.getGrade()));	valueTypesList.add(Types.INTEGER);}if (StringUtils.isNotEmpty(assignment.getReContent())) {sql.append( "and t.re_content like '%"+assignment.getReContent()+"%'");}if (StringUtils.isNotEmpty(assignment.getReTime())) {sql.append( "and t.re_time like '%"+assignment.getReTime()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new AssignmentMapper());
	}
	
	/**
	 * 根据作业id获取作业实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        作业id
	 * @return Assignment实体
	 */
	public Assignment findAssignmentById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_assignment t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Assignment assignment = this.queryForObject(sql.toString(), values, valueTypes,
				new AssignmentMapper());
		
		logger.info(sql.toString());

		return assignment;
	}


	/**
	 * 列出所有作业
	 * 
	 * @author Soyi Yao
	 * @return 作业列表
	 */
	public List<Assignment> listAllAssignment() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_assignment t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new AssignmentMapper());
	}
	
    /**
	 * 获取t_assignment表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_assignment表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_assignment  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
