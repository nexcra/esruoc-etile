package com.tkxgz.elitecourse.dao.base;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.tkxgz.elitecourse.core.page.Page;

/**
 * @author Soyi Yao
 */
public class BaseDao<T> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	/**
	 * 插入数据，返回影响行数,大于0表示成功
	 * 
	 * @param sql
	 *        要执行的sql语句
	 * @return 影响行数，大于0表示成功
	 */
	public int insert(String sql) {
		return this.getJdbcTemplate().update(sql);
	}

	/**
	 * 根据绑定变量及其绑定变量类型语句插入数据，返回影响行数,大于0表示成功
	 * 
	 * @param sql
	 *        要执行的包含绑定变量的sql语句
	 * @param values
	 *        绑定变量值数组
	 * @param valueTypes
	 *        绑定变量类型数组
	 * @return 影响行数
	 */
	public int insert(String sql, Object[] values, int[] valueTypes) {
		return this.getJdbcTemplate().update(sql, values, valueTypes);
	}

	/**
	 * 更新数据，返回影响行数,大于0表示成功
	 * 
	 * @since
	 * @param sql
	 *        要执行的sql语句
	 * @return 影响行数
	 */
	public int update(String sql) {
		return this.getJdbcTemplate().update(sql);

	}

	/**
	 * 根据绑定变量及其绑定变量类型语句更新数据，返回影响行数,大于0表示成功
	 * 
	 * @param sql
	 *        要执行的包含绑定变量的sql语句
	 * @param values
	 *        绑定变量值数组
	 * @param valueTypes
	 *        绑定变量类型数组
	 * @return 影响行数
	 */
	public int update(String sql, Object[] values, int[] valueTypes) {
		return this.getJdbcTemplate().update(sql, values, valueTypes);
	}

	/**
	 * 删除数据，返回影响行数,大于0表示成功
	 * 
	 * @since
	 * @param sql
	 *        要执行的sql语句
	 * @return 影响行数
	 */
	public int delete(String sql) {
		return this.getJdbcTemplate().update(sql);
	}

	/**
	 * 根据绑定变量及其绑定变量类型语句更新数据，返回影响行数,大于0表示成功
	 * 
	 * @param sql
	 *        要执行的包含绑定变量的sql语句
	 * @param values
	 *        绑定变量值数组
	 * @param valueTypes
	 *        绑定变量类型数组
	 * @return 影响行数
	 */
	public int delete(String sql, Object[] values, int[] valueTypes) {
		return this.getJdbcTemplate().update(sql, values, valueTypes);
	}

	/**
	 * 查询数据，返回格式为一个List，<br />
	 * 其中包含若干个以数据库字段名作为key,字段值作为value的Map,<br />
	 * 一行数据被封装成一个Map
	 * 
	 * @param sql
	 *        要执行的sql语句
	 * @return 包含每行一个Map的List
	 */
	public List<Map<String, Object>> queryForList(String sql) {
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 根据绑定变量及其绑定变量类型语句查询数据，返回格式为一个List，<br />
	 * 其中包含若干个以数据库字段名作为key,字段值作为value的Map,一行数据被封装成一个Map
	 * 
	 * @param sql
	 *        要执行的包含绑定变量的sql语句
	 * @param values
	 *        绑定变量值数组
	 * @param valueTypes
	 *        绑定变量类型数组
	 * @return 包含每行一个Map的List
	 */
	public List<Map<String, Object>> queryForList(String sql, Object[] values,
			int[] valueTypes) {

		return this.getJdbcTemplate().queryForList(sql, values, valueTypes);
	}

	/**
	 * 查询数据，返回格式为一个List，<br />
	 * List中的每一行数据被封装成rowMapper设定的bean对象类型
	 * 
	 * @param sql
	 *        要执行的sql语句
	 * @param rowMapper
	 *        行映射器
	 * @return 封装好的List，每一行映射成rowMapper设定的bean对象类型
	 */
	public List<T> queryForList(String sql, RowMapper<T> rowMapper) {
		return this.getJdbcTemplate().query(sql, rowMapper);
	}

	/**
	 * 根据绑定变量及其绑定变量类型语句查询数据，返回格式为一个List，<br />
	 * List中的每一行数据被封装成rowMapper设定的bean对象类型
	 * 
	 * @param sql
	 *        要执行的sql语句
	 * @param values
	 *        绑定变量值数组
	 * @param valueTypes
	 *        绑定变量类型数组
	 * @param rowMapper
	 *        行映射器
	 * @return 封装好的List，每一行映射成rowMapper设定的bean对象类型
	 */
	public List<T> queryForList(String sql, Object[] values, int[] valueTypes,
			RowMapper<T> rowMapper) {
		return this.getJdbcTemplate().query(sql, values, valueTypes, rowMapper);
	}

	public void queryForList(String sql, RowCallbackHandler rch) {
		this.getJdbcTemplate().query(sql, rch);
	}

	/**
	 * 查询数据，返回单一结果值<br />
	 * (<b><font color="red">注意，一定要保证只返回一行记录，否则出错</font></b>)，<br />
	 * 类型为Map，以字段名作为Map的key,字段值作为value<br />
	 * 可用于根据主键等唯一结果查询数据
	 * 
	 * @param sql
	 *        要执行的sql语句
	 * @return 以字段名作为key，字段值作为value的Map
	 */
	public Map<String, Object> queryForMap(String sql) {
		return this.getJdbcTemplate().queryForMap(sql);
	}

	/**
	 * 根据绑定变量及其绑定变量类型语句查询数据，返回单一结果值，<br />
	 * (<b><font color="red">注意，一定要保证只返回一行记录，否则出错</font></b>)<br />
	 * 类型为Map，以字段名作为Map的key,字段值作为value,<br />
	 * 可用于根据主键等唯一结果查询数据<br />
	 * 
	 * @param sql
	 *        要执行的包含绑定变量的sql语句
	 * @param values
	 *        绑定变量值数组
	 * @param valueTypes
	 *        绑定变量类型数组
	 * @return 以字段名作为key，字段值作为value的Map
	 */
	public Map<String, Object> queryForMap(String sql, Object[] values,
			int[] valueTypes) {
		return this.getJdbcTemplate().queryForMap(sql, values, valueTypes);
	}

	/**
	 * 查询数据，返回单一结果值<br />
	 * (<b><font color="red">注意，一定要保证只返回一个值，否则出错</font></b>)，<br />
	 * 类型为int，将查询出来的结果转换成int，类型转换不成功则抛异常<br />
	 * 可用于根据主键等唯一结果查询数据
	 * 
	 * @param sql
	 *        要执行的sql语句
	 * @return 整形值
	 */
	public int queryForInt(String sql) {
		return this.getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 根据绑定变量及其绑定变量类型语句查询数据，返回单一结果值<br />
	 * (<b><font color="red">注意，一定要保证只返回一个值，否则出错</font></b>)，<br />
	 * 类型为int，将查询出来的结果转换成int，类型转换不成功则抛异常<br />
	 * 可用于根据主键等唯一结果查询数据
	 * 
	 * @param sql
	 *        要执行的包含绑定变量的sql语句
	 * @param values
	 *        绑定变量值数组
	 * @param valueTypes
	 *        绑定变量类型数组
	 * @return 整形值
	 */
	public int queryForInt(String sql, Object[] values, int[] valueTypes) {
		return this.getJdbcTemplate().queryForInt(sql, values, valueTypes);
	}

	/**
	 * 查询数据，返回单一结果值<br />
	 * (<b><font color="red">注意，一定要保证只返回一个值，否则出错</font></b>)，<br />
	 * 类型为int，将查询出来的结果转换成int，类型转换不成功则抛异常<br />
	 * 可用于根据主键等唯一结果查询数据
	 * 
	 * @param sql
	 *        要执行的sql语句
	 * @return 长整形值
	 */
	public long queryForLong(String sql) {
		return this.getJdbcTemplate().queryForLong(sql);
	}

	/**
	 * 根据绑定变量及其绑定变量类型语句查询数据，返回单一结果值<br />
	 * (<b><font color="red">注意，一定要保证只返回一个值，否则出错</font></b>)，<br />
	 * 类型为long，将查询出来的结果转换成long，类型转换不成功则抛异常<br />
	 * 可用于根据主键等唯一结果查询数据
	 * 
	 * @param sql
	 *        要执行的包含绑定变量的sql语句
	 * @param values
	 *        绑定变量值数组
	 * @param valueTypes
	 *        绑定变量类型数组
	 * @return 长整形值
	 */
	public long queryForLong(String sql, Object[] values, int[] valueTypes) {
		return this.getJdbcTemplate().queryForLong(sql, values, valueTypes);
	}

	/**
	 * 根据绑定变量及其绑定变量类型语句查询数据，返回单一结果值<br />
	 * (<b><font color="red">注意，一定要保证只返回一个值，否则出错</font></b>)，<br />
	 * 类型为rowMapper设定的bean对象，将查询出来的结果转换成设定的bean对象，类型转换不成功则抛异常<br />
	 * 可用于根据主键等唯一结果查询数据
	 * 
	 * @param sql
	 *        要执行的sql语句
	 * @param rowMapper
	 *        行映射器
	 * @return 返回特定的bean类型
	 */
	public T queryForObject(String sql, RowMapper<T> rowMapper) {
		return this.getJdbcTemplate().queryForObject(sql, rowMapper);
	}

	/**
	 * 查询数据，返回单一结果值<br />
	 * (<b><font color="red">注意，一定要保证只返回一个值，否则出错</font></b>)，<br />
	 * 类型为rowMapper设定的bean对象类型，将查询出来的结果转换成设定的bean对象，类型转换不成功则抛异常<br />
	 * 可用于根据主键等唯一结果查询数据
	 * 
	 * @param sql
	 *        要执行的包含绑定变量的sql语句
	 * @param values
	 *        绑定变量值数组
	 * @param valueTypes
	 *        绑定变量类型数组
	 * @param rowMapper
	 *        行映射器
	 * @return 返回特定的bean对象类型
	 */
	public T queryForObject(String sql, Object[] values, int[] valueTypes,
			RowMapper<T> rowMapper) {
		return this.getJdbcTemplate().queryForObject(sql, values, valueTypes,
				rowMapper);

	}

	public List queryForPage(String sql, int pageSize, int startRecord) {
		List list = null;

		return list;

	}

	/**
	 * 得到分页sql
	 * 
	 * @param sql
	 *        要执行的sql
	 * @param startIndex
	 *        开始
	 * @param pageSize
	 *        每页显示记录数
	 * @return 分页sql
	 */
	private String getPageSql(String sql, int currentPageNum, int pageSize) {

		StringBuffer pageSql = new StringBuffer();
		int min = (currentPageNum - 1) * pageSize;// 查询起始行

		pageSql.append(" SELECT forPage.*  from ( ");
		pageSql.append(sql);
		pageSql.append(" ) forPage ");
		pageSql.append(" limit ");
		pageSql.append(min);
		pageSql.append(" , ");
		pageSql.append(pageSize);

		return pageSql.toString();
	}

	/**
	 * 得到记录总数
	 * 
	 * @param sql
	 *        要查询的sql
	 * @return 总记录数
	 */
	private int getTotalRecords(String sql) {
		String countSql = "select count(1) from (" + sql + ") forCount";
		return this.queryForInt(countSql);
	}

	/**
	 * 得到记录总数
	 * 
	 * @param sql
	 *        要查询的sql
	 * @param values
	 *        绑定变量值数组
	 * @param valueTypes
	 *        绑定变量类型数组
	 * @return 总记录数
	 */
	private int getTotalRecords(String sql, Object[] values, int[] valueTypes) {
		String countSql = "select count(1) from (" + sql + ") forCount";
		return this.queryForInt(countSql, values, valueTypes);
	}

	/**
	 * 分页查询数据，返回分页实体
	 * 
	 * @param sql
	 *        要查询的sql
	 * @param page
	 *        分页实体
	 * @return 分页实体
	 */

	@SuppressWarnings("rawtypes")
	public Page queryForPage(String sql, Page page) {
		int totalRecords = this.getTotalRecords(sql);
		int currentPageNum = page.getCurrentPageNum();
		page.setTotalRecords(totalRecords);
		int totalPages = page.getTotalPages();
		currentPageNum = currentPageNum > totalPages ? totalPages
				: currentPageNum;
		page.setCurrentPageNum(currentPageNum);
		String pageSql = this.getPageSql(sql, page.getCurrentPageNum(),
				page.getPageSize());
		List pagaDatas = this.queryForList(pageSql);

		// 设置分页相关值
		page.setPageDatas(pagaDatas);
		page.setTotalRecords(totalRecords);
		page.setTotalPages(totalPages);

		return page;
	}

	/**
	 * 分页查询数据，返回分页实体
	 * 
	 * @param sql
	 *        要查询的sql
	 * @param values
	 *        参数数组
	 * @param valueTypes
	 *        参数类型数组
	 * @param page
	 *        分页实体
	 * @return 分页实体
	 */
	@SuppressWarnings("rawtypes")
	public Page queryForPage(String sql, Object[] values, int[] valueTypes,
			Page page) {
		int totalRecords = this.getTotalRecords(sql, values, valueTypes);
		int currentPageNum = page.getCurrentPageNum();
		page.setTotalRecords(totalRecords);
		int totalPages = page.getTotalPages();
		currentPageNum = currentPageNum > totalPages ? totalPages
				: currentPageNum;
		page.setCurrentPageNum(currentPageNum);
		String pageSql = this.getPageSql(sql, page.getCurrentPageNum(),
				page.getPageSize());
		List pagaDatas = this.queryForList(pageSql, values, valueTypes);

		// 设置分页相关值
		page.setPageDatas(pagaDatas);
		page.setTotalRecords(totalRecords);
		page.setTotalPages(totalPages);

		return page;
	}

	/**
	 * * 取得下一个可用的整型序列值
	 * 
	 * @param sequenceName
	 *        序列名
	 * @return 整型序列值
	 */
	public int getNextIntSequence(String sequenceName) {
		String sql = "select " + sequenceName + ".nextval from dual";
		return this.queryForInt(sql);
	}

	/**
	 * 取得下一个可用的长整型序列值
	 * 
	 * @param sequenceName
	 *        序列名
	 * @return 长整型序列值
	 */
	public int getNextLongSequence(String sequenceName) {
		String sql = "select " + sequenceName + ".nextval from dual";
		return this.queryForInt(sql);
	}

	/**
	 * 执行数据定义操作，比如建表
	 * 
	 * @param sql
	 *        要执行的数据定义操作DDL语句
	 * @throws Exception
	 */
	public void ddl(String sql) {
		this.getJdbcTemplate().execute(sql);
	}

	/**
	 * 批量更新数据（增，删，改）
	 * 
	 * @param sql
	 *        要进行批量更新的sql语句数组
	 * @return 影响行数
	 */

	public int batchUpdate(String[] sql) {
		int[] batchResult = this.getJdbcTemplate().batchUpdate(sql);

		int affectRows = 0;
		for (int i = 0; i < batchResult.length; i++) {
			if (batchResult[i] > 0
					|| batchResult[i] == PreparedStatement.SUCCESS_NO_INFO)
				affectRows += 1;
		}

		return affectRows;
	}

	/**
	 * 批量更新数据（增，删，改）
	 * 
	 * @param sql
	 *        要进行批量更新的sql语句
	 * @param bpss
	 *        绑定变量参数
	 * @return 影响行数
	 */

	public int batchUpdate(String sql, BatchPreparedStatementSetter bpss) {
		int[] batchResult = this.getJdbcTemplate().batchUpdate(sql, bpss);

		int affectRows = 0;
		for (int i = 0; i < batchResult.length; i++) {
			if (batchResult[i] > 0
					|| batchResult[i] == PreparedStatement.SUCCESS_NO_INFO)
				affectRows += 1;
		}

		return affectRows;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}
}
