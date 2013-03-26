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
import com.tkxgz.elitecourse.bean.Dict;
import com.tkxgz.elitecourse.mapper.DictMapper;
/**
 * 字典Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class DictBaseDao extends BaseDao<Dict> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询字典列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 字典分页数据
	 */
	public Page listDict(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_dict t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加字典
	 * 
	 * @author Soyi Yao
	 * @param dict
	 *        字典bean
	 * @return 影响行数
	 */
	public int addDict(Dict dict) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_dict (");
		   sql.append(" dict_code , "); 
  sql.append(" dict_name , "); 
  sql.append(" dict_desc , "); 
  sql.append(" dict_value , "); 
  sql.append(" status , "); 
  sql.append(" is_application_level  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { dict.getDictCode(), 
dict.getDictName(), 
dict.getDictDesc(), 
dict.getDictValue(), 
dict.getStatus(), 
dict.getIsApplicationLevel()};
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
	 * 根据字典id删除字典
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        字典id
	 * @return 影响行数
	 */
	public int deleteDictById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_dict ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据字典查询字典信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        字典id
	 * @return 字典Map
	 */
	public Map getDictById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_dict t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新字典
	 * 
	 * @author Soyi Yao
	 * @param dict
	 *        字典bean
	 * @return 影响行数
	 */
	public int updateDict(Dict dict) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_dict t");
		sql.append(" set   ");
		 sql.append(" t.dict_code = ? , "); 
  sql.append(" t.dict_name = ? , "); 
  sql.append(" t.dict_desc = ? , "); 
  sql.append(" t.dict_value = ? , "); 
  sql.append(" t.status = ? , "); 
  sql.append(" t.is_application_level = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { dict.getDictCode(), 
dict.getDictName(), 
dict.getDictDesc(), 
dict.getDictValue(), 
dict.getStatus(), 
dict.getIsApplicationLevel(), 
 dict.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
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
	 * 分页搜索字典列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param dict
	 *        字典bean
	 * @return 分页字典列表
	 */
	public Page searchDict(Page page, Dict dict) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_dict t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(dict.getDictCode())) {sql.append( "and t.dict_code like '%"+dict.getDictCode()+"%'");}if (StringUtils.isNotEmpty(dict.getDictName())) {sql.append( "and t.dict_name like '%"+dict.getDictName()+"%'");}if (StringUtils.isNotEmpty(dict.getDictDesc())) {sql.append( "and t.dict_desc like '%"+dict.getDictDesc()+"%'");}if (StringUtils.isNotEmpty(dict.getDictValue())) {sql.append( "and t.dict_value like '%"+dict.getDictValue()+"%'");}if (StringUtils.isNotEmpty(dict.getStatus())) {sql.append( "and t.status like '%"+dict.getStatus()+"%'");}if (StringUtils.isNotEmpty(dict.getIsApplicationLevel())) {sql.append( "and t.is_application_level like '%"+dict.getIsApplicationLevel()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索字典列表
	 * 
	 * @author Soyi Yao
	 * @param dict
	 *        Dict实体
	 * @return 字典列表
	 */
	public List<Dict> searchDictForList(Dict dict) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_dict t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(dict.getDictCode())) {sql.append( "and t.dict_code like '%"+dict.getDictCode()+"%'");}if (StringUtils.isNotEmpty(dict.getDictName())) {sql.append( "and t.dict_name like '%"+dict.getDictName()+"%'");}if (StringUtils.isNotEmpty(dict.getDictDesc())) {sql.append( "and t.dict_desc like '%"+dict.getDictDesc()+"%'");}if (StringUtils.isNotEmpty(dict.getDictValue())) {sql.append( "and t.dict_value like '%"+dict.getDictValue()+"%'");}if (StringUtils.isNotEmpty(dict.getStatus())) {sql.append( "and t.status like '%"+dict.getStatus()+"%'");}if (StringUtils.isNotEmpty(dict.getIsApplicationLevel())) {sql.append( "and t.is_application_level like '%"+dict.getIsApplicationLevel()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new DictMapper());
	}
	
	/**
	 * 根据字典id获取字典实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        字典id
	 * @return Dict实体
	 */
	public Dict findDictById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_dict t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Dict dict = this.queryForObject(sql.toString(), values, valueTypes,
				new DictMapper());
		
		logger.info(sql.toString());

		return dict;
	}


	/**
	 * 列出所有字典
	 * 
	 * @author Soyi Yao
	 * @return 字典列表
	 */
	public List<Dict> listAllDict() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_dict t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new DictMapper());
	}
	
    /**
	 * 获取t_dict表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_dict表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_dict  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
