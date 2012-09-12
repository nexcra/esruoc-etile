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
		sql.append(" select t.id, t.column_name, t.column_type, t.show_on_nav, t.show_on_index_page, t.insert_time, t.update_time,  t.order_num ");
		sql.append(" from t_column t ");
		sql.append(" order by t.order_num desc, t.id desc ");
		return this.queryForPage(sql.toString(), page);
	}

	public int addColumn(Column column) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_column  ");
		sql.append(" ( column_name, column_type ,column_content,parent_id, description,order_num,  show_on_nav, insert_time ,update_time ) ");
		sql.append(" values ");
		sql.append(" ( ?, ?, ?, ?, ?,? ,? ,now(),now()) ");
		Object[] values = { column.getColumnName(), column.getColumnType(),
				column.getColumnContent(), column.getParentId(),
				column.getDescription(), column.getOrderNum(),
				column.getShowOnNav() };
		int[] valueTypes = { Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
				Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER };
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
		sql.append(" select id, column_name, column_type , parent_id, show_on_nav, show_on_index_page, column_content,description,order_num,insert_time ");
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
		sql.append("     t.parent_id = ? ,  ");
		sql.append("     t.description = ?,   ");
		sql.append("     t.order_num = ?,  ");
		sql.append("     t.show_on_nav = ?,  ");
		sql.append("     t.update_time = now()   ");
		sql.append(" where t.id = ? ");

		Object[] values = { column.getColumnName(), column.getColumnType(),
				column.getColumnContent(),column.getParentId(), column.getDescription(),
				column.getOrderNum(), column.getShowOnNav(), column.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.INTEGER, Types.VARCHAR,Types.INTEGER,
				Types.VARCHAR,  Types.INTEGER, Types.INTEGER, Types.VARCHAR };
		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 24 Jul 2012 22:22:30
	 * @return
	 */
	public List<Column> listAllColumnForArticle() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.column_name, t.column_type, t.parent_id  ");
		sql.append(" from t_column t ");
		sql.append(" where t.column_type = '2' ");
		sql.append(" order by t.order_num desc, t.id desc ");

		return this.queryForList(sql.toString(), new RowMapper<Column>() {

			public Column mapRow(ResultSet rs, int rowNum) throws SQLException {
				Column column = new Column();
				column.setId(rs.getInt("id"));
				column.setParentId(rs.getInt("parent_id"));
				column.setColumnName(rs.getString("column_name"));
				column.setColumnType(Integer.valueOf(rs
						.getString("column_Type")));
				return column;
			}

		});
	}

	public List<Column> listAllColumnForNav() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.column_name, t.column_type, t.parent_id  ");
		sql.append(" from t_column t ");
		sql.append(" where t.show_on_nav = '1' ");
		sql.append(" order by t.order_num desc, t.id desc ");

		return this.queryForList(sql.toString(), new RowMapper<Column>() {

			public Column mapRow(ResultSet rs, int rowNum) throws SQLException {
				Column column = new Column();
				column.setId(rs.getInt("id"));
				column.setParentId(rs.getInt("parent_id"));
				column.setColumnName(rs.getString("column_name"));
				column.setColumnType(Integer.valueOf(rs
						.getString("column_Type")));
				return column;
			}

		});
	}

	/**
	 * @author Po Kong
	 * @since 2012-8-4 下午6:24:24
	 * @param columnId
	 * @return
	 */
	public Page columnList(Page page, Integer columnId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.column_name ");
		sql.append(" from t_article a, t_column b ");
		sql.append(" where a.column_id = b.id   ");
		sql.append("  and  a.status = 1  ");
		sql.append("  and  a.column_id = ?  ");
		sql.append(" union ");
		sql.append(" select a.*,b.column_name  ");
		sql.append(" from t_article a, (select c.id,c.column_name  from t_column c where   c.parent_id = ?) b ");
		sql.append(" where a.column_id in ( b.id )    ");
		sql.append("  and  a.status = 1  ");

		Object[] values = { columnId, columnId };
		int[] valueTypes = { Types.INTEGER, Types.INTEGER };
		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * @author Po Kong
	 * @since 2012-8-15 下午10:24:27
	 * @param columnName
	 * @return
	 */
	public int checkUserCount(String columnName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) from t_column t");
		sql.append(" where t.column_name = ? ");
		Object[] values = { columnName };
		int[] valueTypes = { Types.VARCHAR };
		return this.queryForInt(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 2012-10-26 下午9:49:17
	 * @return
	 */
	public List<Column> listAllColumnForColumn() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.column_name, t.column_type,t.parent_id ");
		sql.append(" from t_column t ");
		sql.append(" where t.column_type = 2 ");

		sql.append(" order by t.order_num desc, t.id desc ");

		return this.queryForList(sql.toString(), new RowMapper<Column>() {

			public Column mapRow(ResultSet rs, int rowNum) throws SQLException {
				Column column = new Column();
				column.setId(rs.getInt("id"));
				column.setParentId(rs.getInt("parent_id"));
				column.setColumnName(rs.getString("column_name"));
				column.setColumnType(Integer.valueOf(rs
						.getString("column_Type")));
				return column;
			}

		});
	}

	/**
	 * @author Po Kong
	 * @since 2012-10-26 下午10:52:26
	 * @param string
	 * @return
	 */
	public List<Column> listColumnByColumnName(String columnName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.column_name, t.column_type,t.parent_id ");
		sql.append(" from t_column t ");
		sql.append(" where t.column_name = ? ");

		sql.append(" order by t.order_num desc, t.id desc ");
		Object[] values = { columnName };
		int[] valueTypes = { Types.VARCHAR };
		return this.queryForList(sql.toString(), values, valueTypes,
				new RowMapper<Column>() {

					public Column mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Column column = new Column();
						column.setId(rs.getInt("id"));
						column.setParentId(rs.getInt("parent_id"));
						column.setColumnName(rs.getString("column_name"));
						column.setColumnType(Integer.valueOf(rs
								.getString("column_Type")));
						return column;
					}

				});
	}

	/**
	 * @author Po Kong
	 * @since 2012-10-26 下午10:57:30
	 * @param string
	 * @return
	 */
	public int getColumnIdByColumnName(String columnName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id from t_column t");
		sql.append(" where t.column_name = ? ");
		Object[] values = { columnName };
		int[] valueTypes = { Types.VARCHAR };
		return this.queryForInt(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 2012-10-26 下午10:59:51
	 * @param id
	 * @return
	 */
	public List<Column> listColumnByParentId(int parentId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.column_name, t.column_type,t.parent_id ");
		sql.append(" from t_column t ");
		sql.append(" where t.parent_id = ? ");

		sql.append(" order by t.order_num desc, t.id desc ");
		Object[] values = { parentId };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForList(sql.toString(), values, valueTypes,
				new RowMapper<Column>() {

					public Column mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Column column = new Column();
						column.setId(rs.getInt("id"));
						column.setParentId(rs.getInt("parent_id"));
						column.setColumnName(rs.getString("column_name"));
						column.setColumnType(Integer.valueOf(rs
								.getString("column_Type")));
						return column;
					}

				});
	}

	 /**
	 * 
	 * 
	 * @author Po Kong
	 * @since 2012-9-5 上午12:00:57
	 * @param page
	 * @param columnName
	 * @return
	 */
	public Page columnListByColumnName(Page page, String columnName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.column_name ");
		sql.append(" from t_article a, t_column b ");
		sql.append(" where a.column_id = b.id   ");
		sql.append("  and  a.status = 1  ");
		sql.append("  and  b.column_name= ?  ");

		Object[] values = { columnName };
		int[] valueTypes = {  Types.VARCHAR };
		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
}
