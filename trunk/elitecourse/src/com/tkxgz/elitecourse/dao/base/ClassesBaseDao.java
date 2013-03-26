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
import com.tkxgz.elitecourse.bean.Classes;
import com.tkxgz.elitecourse.mapper.ClassesMapper;

/**
 * 班级Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class ClassesBaseDao extends BaseDao<Classes> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询班级列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 班级分页数据
	 */
	public Page listClasses(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_classes t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加班级
	 * 
	 * @author Soyi Yao
	 * @param classes
	 *        班级bean
	 * @return 影响行数
	 */
	public int addClasses(Classes classes) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_classes (");
		sql.append(" name , ");
		sql.append(" order_number , ");
		sql.append(" remark , ");
		sql.append(" create_user_name , ");
		sql.append(" create_user_id , ");
		sql.append(" create_time  ");
		sql.append(" ) ");
		sql.append(" values ( ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" now()  ");

		sql.append(" ) ");
		Object[] values = { classes.getName(), classes.getOrderNumber(),
				classes.getRemark(), classes.getCreateUserName(),
				classes.getCreateUserId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据班级id删除班级
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        班级id
	 * @return 影响行数
	 */
	public int deleteClassesById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_classes ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据班级查询班级信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        班级id
	 * @return 班级Map
	 */
	public Map getClassesById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_classes t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新班级
	 * 
	 * @author Soyi Yao
	 * @param classes
	 *        班级bean
	 * @return 影响行数
	 */
	public int updateClasses(Classes classes) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_classes t");
		sql.append(" set   ");
		sql.append(" t.name = ? , ");
		sql.append(" t.order_number = ? , ");
		sql.append(" t.remark = ? , ");
		sql.append(" t.create_user_name = ? , ");
		sql.append(" t.create_user_id = ? , ");
		sql.append(" t.create_time = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { classes.getName(), classes.getOrderNumber(),
				classes.getRemark(), classes.getCreateUserName(),
				classes.getCreateUserId(), classes.getCreateTime(),
				classes.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };

		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * 分页搜索班级列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param classes
	 *        班级bean
	 * @return 分页班级列表
	 */
	public Page searchClasses(Page page, Classes classes) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_classes t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(classes.getName())) {
			sql.append("and t.name like '%" + classes.getName() + "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * 搜索班级列表
	 * 
	 * @author Soyi Yao
	 * @param classes
	 *        Classes实体
	 * @return 班级列表
	 */
	public List<Classes> searchClassesForList(Classes classes) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_classes t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(classes.getName())) {
			sql.append("and t.name like '%" + classes.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(classes.getOrderNumber()))
				&& (!"all".equalsIgnoreCase(classes.getOrderNumber()))) {
			sql.append(" and t.order_number = ? ");
			valuesList.add(Integer.valueOf(classes.getOrderNumber()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(classes.getRemark())) {
			sql.append("and t.remark like '%" + classes.getRemark() + "%'");
		}
		if (StringUtils.isNotEmpty(classes.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ classes.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(classes.getCreateTime())) {
			sql.append("and t.create_time like '%" + classes.getCreateTime()
					+ "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new ClassesMapper());
	}

	/**
	 * 根据班级id获取班级实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        班级id
	 * @return Classes实体
	 */
	public Classes findClassesById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_classes t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Classes classes = this.queryForObject(sql.toString(), values,
				valueTypes, new ClassesMapper());

		logger.info(sql.toString());

		return classes;
	}

	/**
	 * 列出所有班级
	 * 
	 * @author Soyi Yao
	 * @return 班级列表
	 */
	public List<Classes> listAllClasses() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_classes t  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new ClassesMapper());
	}

	/**
	 * 获取t_classes表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_classes表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_classes  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

}
