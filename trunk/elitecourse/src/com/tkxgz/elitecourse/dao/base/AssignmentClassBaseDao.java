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
import com.tkxgz.elitecourse.bean.AssignmentClass;
import com.tkxgz.elitecourse.mapper.AssignmentClassMapper;

/**
 * 作业分类Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class AssignmentClassBaseDao extends BaseDao<AssignmentClass> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询作业分类列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 作业分类分页数据
	 */
	public Page listAssignmentClass(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_assignment_class t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加作业分类
	 * 
	 * @author Soyi Yao
	 * @param assignmentClass
	 *        作业分类bean
	 * @return 影响行数
	 */
	public int addAssignmentClass(AssignmentClass assignmentClass) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_assignment_class (");
		sql.append(" name , ");
		sql.append(" description , ");
		sql.append(" order_number , ");
		sql.append(" classes_id , ");
		sql.append(" classes_name , ");
		sql.append(" create_user_id , ");
		sql.append(" start_time , ");
		sql.append(" end_time , ");
		sql.append(" create_user_name , ");
		sql.append(" create_time  ");
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
		sql.append(" now()  ");

		sql.append(" ) ");
		Object[] values = { assignmentClass.getName(),
				assignmentClass.getDescription(),
				assignmentClass.getOrderNumber(),
				assignmentClass.getClassesId(),
				assignmentClass.getClassesName(),
				assignmentClass.getCreateUserId(),
				assignmentClass.getStartTime(), assignmentClass.getEndTime(),
				assignmentClass.getCreateUserName() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据作业分类id删除作业分类
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        作业分类id
	 * @return 影响行数
	 */
	public int deleteAssignmentClassById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_assignment_class ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据作业分类查询作业分类信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        作业分类id
	 * @return 作业分类Map
	 */
	public Map getAssignmentClassById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_assignment_class t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新作业分类
	 * 
	 * @author Soyi Yao
	 * @param assignmentClass
	 *        作业分类bean
	 * @return 影响行数
	 */
	public int updateAssignmentClass(AssignmentClass assignmentClass) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_assignment_class t");
		sql.append(" set   ");
		sql.append(" t.name = ? , ");
		sql.append(" t.description = ? , ");
		sql.append(" t.order_number = ? , ");
		sql.append(" t.classes_id = ? , ");
		sql.append(" t.classes_name = ? , ");
		sql.append(" t.create_user_id = ? , ");
		sql.append(" t.start_time = ? , ");
		sql.append(" t.end_time = ? , ");
		sql.append(" t.create_user_name = ? , ");
		sql.append(" t.create_time = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { assignmentClass.getName(),
				assignmentClass.getDescription(),
				assignmentClass.getOrderNumber(),
				assignmentClass.getClassesId(),
				assignmentClass.getClassesName(),
				assignmentClass.getCreateUserId(),
				assignmentClass.getStartTime(), assignmentClass.getEndTime(),
				assignmentClass.getCreateUserName(),
				assignmentClass.getCreateTime(), assignmentClass.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };

		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * 分页搜索作业分类列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param assignmentClass
	 *        作业分类bean
	 * @return 分页作业分类列表
	 */
	public Page searchAssignmentClass(Page page, AssignmentClass assignmentClass) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_assignment_class t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(assignmentClass.getName())) {
			sql.append("and t.name like '%" + assignmentClass.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(assignmentClass.getDescription())) {
			sql.append("and t.description like '%"
					+ assignmentClass.getDescription() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(assignmentClass
				.getClassesId()))
				&& (!"all".equalsIgnoreCase(assignmentClass.getClassesId()))) {
			sql.append(" and t.classes_id = ? ");
			valuesList.add(Integer.valueOf(assignmentClass.getClassesId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(assignmentClass.getClassesName())) {
			sql.append("and t.classes_name like '%"
					+ assignmentClass.getClassesName() + "%'");
		}
		if (StringUtils.isNotEmpty(assignmentClass.getStartTime())) {
			sql.append("and t.start_time like '%"
					+ assignmentClass.getStartTime() + "%'");
		}
		if (StringUtils.isNotEmpty(assignmentClass.getEndTime())) {
			sql.append("and t.end_time like '%" + assignmentClass.getEndTime()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(assignmentClass.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ assignmentClass.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(assignmentClass.getCreateTime())) {
			sql.append("and t.create_time like '%"
					+ assignmentClass.getCreateTime() + "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * 搜索作业分类列表
	 * 
	 * @author Soyi Yao
	 * @param assignmentClass
	 *        AssignmentClass实体
	 * @return 作业分类列表
	 */
	public List<AssignmentClass> searchAssignmentClassForList(
			AssignmentClass assignmentClass) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_assignment_class t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(assignmentClass.getName())) {
			sql.append("and t.name like '%" + assignmentClass.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(assignmentClass.getDescription())) {
			sql.append("and t.description like '%"
					+ assignmentClass.getDescription() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(assignmentClass
				.getOrderNumber()))
				&& (!"all".equalsIgnoreCase(assignmentClass.getOrderNumber()))) {
			sql.append(" and t.order_number = ? ");
			valuesList.add(Integer.valueOf(assignmentClass.getOrderNumber()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(String.valueOf(assignmentClass
				.getClassesId()))
				&& (!"all".equalsIgnoreCase(assignmentClass.getClassesId()))) {
			sql.append(" and t.classes_id = ? ");
			valuesList.add(Integer.valueOf(assignmentClass.getClassesId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(assignmentClass.getClassesName())) {
			sql.append("and t.classes_name like '%"
					+ assignmentClass.getClassesName() + "%'");
		}
		if (StringUtils.isNotEmpty(assignmentClass.getStartTime())) {
			sql.append("and t.start_time like '%"
					+ assignmentClass.getStartTime() + "%'");
		}
		if (StringUtils.isNotEmpty(assignmentClass.getEndTime())) {
			sql.append("and t.end_time like '%" + assignmentClass.getEndTime()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(assignmentClass.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ assignmentClass.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(assignmentClass.getCreateTime())) {
			sql.append("and t.create_time like '%"
					+ assignmentClass.getCreateTime() + "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new AssignmentClassMapper());
	}

	/**
	 * 根据作业分类id获取作业分类实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        作业分类id
	 * @return AssignmentClass实体
	 */
	public AssignmentClass findAssignmentClassById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_assignment_class t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		AssignmentClass assignmentClass = this.queryForObject(sql.toString(),
				values, valueTypes, new AssignmentClassMapper());

		logger.info(sql.toString());

		return assignmentClass;
	}

	/**
	 * 列出所有作业分类
	 * 
	 * @author Soyi Yao
	 * @return 作业分类列表
	 */
	public List<AssignmentClass> listAllAssignmentClass() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_assignment_class t  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new AssignmentClassMapper());
	}

	/**
	 * 获取t_assignment_class表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_assignment_class表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_assignment_class  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

}
