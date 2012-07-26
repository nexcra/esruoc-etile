package com.tkxwz.esruocetile.webapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.entity.Column;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:12:25
 */
@Repository
public class ColumnDao extends BaseDao<Column> {

	public Page listColumn(Page page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.column_name, t.column_type, t.show_on_nav, t.show_on_index_page,  t.order_num ");
		sql.append(" from t_column t ");
		sql.append(" order by t.order_num desc, t.id desc ");
		return this.queryForPage(sql.toString(), page);
	}

	public int addColumn(Column column) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_column  ");
		sql.append(" ( column_name, column_type ,column_content,description,order_num,insert_time ) ");
		sql.append(" values ");
		sql.append(" ( ?, ?, ?, ?, ?  ,now()) ");
		Object[] values = { column.getColumnName(), column.getColumnType(),
				column.getColumnContent(), column.getDescription(),
				column.getOrderNum() };
		int[] valueTypes = { Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER };
		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:13:11
	 * @param ids
	 */
	public int deleteColumnById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_column ");
		sql.append(" where id = ?  ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:56:00
	 * @param i
	 */
	public Map getColumnById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select id, column_name, column_type ,column_content,description,order_num,insert_time ");
		sql.append(" from t_column t where t.id = ? ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForMap(sql.toString(), values, valueTypes);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 23:25:24
	 * @param column
	 * @return
	 */
	public int updateColumn(Column column) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_column t");
		sql.append(" set t.column_name = ? , ");
		sql.append("     t.column_type = ? ,   ");
		sql.append("     t.column_content = ? ,  ");
		sql.append("     t.description = ?,   ");
		sql.append("     t.order_num = ?   ");
		sql.append(" where t.id = ? ");

		Object[] values = { column.getColumnName(), column.getColumnType(),
				column.getColumnContent(), column.getDescription(),
				column.getOrderNum(), column.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER, Types.VARCHAR };
		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 24 Jul 2012 22:22:30
	 * @return
	 */
	public List<Column> listAllColumn() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.column_name ");
		sql.append(" from t_column t ");
		sql.append(" where t.column_type <> 1 ");
		sql.append(" order by t.order_num desc, t.id desc ");

		return this.queryForList(sql.toString(), new RowMapper<Column>() {

			public Column mapRow(ResultSet rs, int rowNum) throws SQLException {
				Column column = new Column();
				column.setId(rs.getInt("id"));
				column.setColumnName(rs.getString("column_name"));
				return column;
			}

		});
	}
}
