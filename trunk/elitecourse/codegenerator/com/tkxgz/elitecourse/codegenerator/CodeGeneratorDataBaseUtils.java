package com.tkxgz.elitecourse.codegenerator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Soyi Yao
 */
public class CodeGeneratorDataBaseUtils {

	public static ResultSet rs = null;

	public static ResultSetMetaData rsmd = null;

	/**
	 * 获取数据库连接
	 * 
	 * @author Soyi Yao
	 * @return Connection 数据库连接
	 */
	public static Connection getConnection() {

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();// 初始化驱动

			String url = CodeGeneratorConstants.DB_URL;
			String user = CodeGeneratorConstants.DB_USER;
			String password = CodeGeneratorConstants.DB_PASSWORD;
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return conn;

	}

	/**
	 * 根据给定表名获取表的MetaData
	 * 
	 * @author Soyi Yao
	 * @param tableName
	 *        表名
	 * @return 给定表名的ResultSetMetaData
	 * @throws SQLException
	 *         SQL异常
	 */
	public static ResultSetMetaData getTableResultSetMetaData(String tableName)
			throws SQLException {
		Statement stmt = CodeGeneratorDataBaseUtils.getConnection()
				.createStatement();
		ResultSet rs = stmt.executeQuery("select * from " + tableName);
		ResultSetMetaData rsmd = rs.getMetaData();
		return rsmd;
	}

	/**
	 * 数据库MetaData
	 * 
	 * @author Soyi Yao
	 * @param tableName
	 *        表名
	 * @return 给定表名的ResultSetMetaData
	 * @throws SQLException
	 *         SQL异常
	 */
	public static DatabaseMetaData getDatabaseMetaData() throws SQLException {
		DatabaseMetaData databaseMetaData = CodeGeneratorDataBaseUtils
				.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, "elite_course", "%",
				null);
		while(rs.next()) {
			
			System.out.println(rs.getString(3));
		}
		return databaseMetaData;
	}
	
	public static void main(String[] args) {
		try {
			CodeGeneratorDataBaseUtils.getDatabaseMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
