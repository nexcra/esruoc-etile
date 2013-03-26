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
import com.tkxgz.elitecourse.bean.Privilege;
import com.tkxgz.elitecourse.mapper.PrivilegeMapper;
/**
 * 权限Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class PrivilegeBaseDao extends BaseDao<Privilege> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询权限列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 权限分页数据
	 */
	public Page listPrivilege(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_privilege t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加权限
	 * 
	 * @author Soyi Yao
	 * @param privilege
	 *        权限bean
	 * @return 影响行数
	 */
	public int addPrivilege(Privilege privilege) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_privilege (");
		   sql.append(" privilege_code , "); 
  sql.append(" name , "); 
  sql.append(" status , "); 
  sql.append(" create_user_name , "); 
  sql.append(" create_user_id , "); 
  sql.append(" create_time , "); 
  sql.append(" remark  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" now() , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { privilege.getPrivilegeCode(), 
privilege.getName(), 
privilege.getStatus(), 
privilege.getCreateUserName(), 
privilege.getCreateUserId(), 
privilege.getRemark()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据权限id删除权限
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        权限id
	 * @return 影响行数
	 */
	public int deletePrivilegeById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_privilege ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据权限查询权限信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        权限id
	 * @return 权限Map
	 */
	public Map getPrivilegeById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_privilege t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新权限
	 * 
	 * @author Soyi Yao
	 * @param privilege
	 *        权限bean
	 * @return 影响行数
	 */
	public int updatePrivilege(Privilege privilege) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_privilege t");
		sql.append(" set   ");
		 sql.append(" t.privilege_code = ? , "); 
  sql.append(" t.name = ? , "); 
  sql.append(" t.status = ? , "); 
  sql.append(" t.create_user_name = ? , "); 
  sql.append(" t.create_user_id = ? , "); 
  sql.append(" t.create_time = ? , "); 
  sql.append(" t.remark = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { privilege.getPrivilegeCode(), 
privilege.getName(), 
privilege.getStatus(), 
privilege.getCreateUserName(), 
privilege.getCreateUserId(), 
privilege.getCreateTime(), 
privilege.getRemark(), 
 privilege.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
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
	 * 分页搜索权限列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param privilege
	 *        权限bean
	 * @return 分页权限列表
	 */
	public Page searchPrivilege(Page page, Privilege privilege) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_privilege t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(privilege.getPrivilegeCode())) {sql.append( "and t.privilege_code like '%"+privilege.getPrivilegeCode()+"%'");}if (StringUtils.isNotEmpty(privilege.getName())) {sql.append( "and t.name like '%"+privilege.getName()+"%'");}if (StringUtils.isNotEmpty(privilege.getStatus())) {sql.append( "and t.status like '%"+privilege.getStatus()+"%'");}if (StringUtils.isNotEmpty(privilege.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+privilege.getCreateUserName()+"%'");}if (StringUtils.isNotEmpty(privilege.getCreateTime())) {sql.append( "and t.create_time like '%"+privilege.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(privilege.getRemark())) {sql.append( "and t.remark like '%"+privilege.getRemark()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索权限列表
	 * 
	 * @author Soyi Yao
	 * @param privilege
	 *        Privilege实体
	 * @return 权限列表
	 */
	public List<Privilege> searchPrivilegeForList(Privilege privilege) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_privilege t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(privilege.getPrivilegeCode())) {sql.append( "and t.privilege_code like '%"+privilege.getPrivilegeCode()+"%'");}if (StringUtils.isNotEmpty(privilege.getName())) {sql.append( "and t.name like '%"+privilege.getName()+"%'");}if (StringUtils.isNotEmpty(privilege.getStatus())) {sql.append( "and t.status like '%"+privilege.getStatus()+"%'");}if (StringUtils.isNotEmpty(privilege.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+privilege.getCreateUserName()+"%'");}if (StringUtils.isNotEmpty(privilege.getCreateTime())) {sql.append( "and t.create_time like '%"+privilege.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(privilege.getRemark())) {sql.append( "and t.remark like '%"+privilege.getRemark()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new PrivilegeMapper());
	}
	
	/**
	 * 根据权限id获取权限实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        权限id
	 * @return Privilege实体
	 */
	public Privilege findPrivilegeById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_privilege t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Privilege privilege = this.queryForObject(sql.toString(), values, valueTypes,
				new PrivilegeMapper());
		
		logger.info(sql.toString());

		return privilege;
	}


	/**
	 * 列出所有权限
	 * 
	 * @author Soyi Yao
	 * @return 权限列表
	 */
	public List<Privilege> listAllPrivilege() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_privilege t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new PrivilegeMapper());
	}
	
    /**
	 * 获取t_privilege表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_privilege表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_privilege  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
