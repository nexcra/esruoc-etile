package com.tkxgz.elitecourse.codegenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tkxgz.elitecourse.service.UserService;

/**
 * 代码生成工具类
 * 
 * @author Soyi Yao
 */
public class CodeGeneratorUtils {

	public static String menusString = "";// 每新增一个数据表， 则按纽

	/**
	 * 读取模板文件，返回文件内容
	 * 
	 * @author Soyi Yao
	 * @param filePath
	 *        文件路径
	 * @return 文件内容
	 */
	public static String readSource(String filePath) {
		StringBuilder content = new StringBuilder();

		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath)));

			String readLine = "";

			while ((readLine = bufferedReader.readLine()) != null) {
				content.append(readLine).append("\n");
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		return content.toString();
	}

	/**
	 * 根据文件路径及内容生成文件
	 * 
	 * @author Soyi Yao
	 * @param filePath
	 *        文件路径
	 * @param content
	 *        文件内容
	 * @param replaceable
	 *        是否替换
	 */
	public static void createFile(String filePath, String content,
			boolean replaceable) {

		File file = new File(filePath.substring(0, filePath.lastIndexOf("\\")));

		boolean createable = false;

		if (!file.exists()) {
			file.mkdirs();
			createable = true;
		}

		if (file.exists() && replaceable) {
			createable = true;
		}

		// 如果文件存在且可替换
		if (createable) {
			BufferedWriter bufferedWriter = null;

			try {
				bufferedWriter = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(filePath)));
				bufferedWriter.write(content);
				bufferedWriter.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bufferedWriter != null) {
					try {
						bufferedWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * 根据表名称获取bean的小写开头名称
	 * 比如，表名为t_user_info，去掉"t_"，返回userInfo
	 * 
	 * @author Soyi Yao
	 * @param tableName
	 * @return
	 */
	public static String getBeanLowerNameFromTableName(String tableName) {

		String[] str = tableName.toLowerCase()
				.replaceAll("^" + CodeGeneratorConstants.TABLE_PREFIX, "")
				.split("_");
		String result = str[0];

		for (int i = 1; i < str.length; i++) {
			str[i] = StringUtils.capitalize(str[i]);
			result += str[i];
		}

		return result;
	}

	/**
	 * 根据表字段名称获取字段的小写开头名称
	 * 比如，表名为 user_name， 返回userName
	 * 
	 * @author Soyi Yao
	 * @param fieldName
	 *        表名
	 * @return 小写开头的字段名
	 */
	public static String getFieldLowerNameFromTableName(String fieldName) {

		String[] str = fieldName.toLowerCase().split("_");
		String result = str[0];

		for (int i = 1; i < str.length; i++) {
			str[i] = StringUtils.capitalize(str[i]);
			result += str[i];
		}

		return result;
	}

	/**
	 * 根据表字段名称获取字段的大写开头名称
	 * 比如，表名为 user_name， 返回userName
	 * 
	 * @author Soyi Yao
	 * @param fieldName
	 *        表名
	 * @return 小写开头的字段名
	 */
	public static String getFieldUpperNameFromTableName(String fieldName) {

		String[] str = fieldName.toLowerCase().split("_");
		String result = StringUtils.capitalize(str[0]);

		for (int i = 1; i < str.length; i++) {
			str[i] = StringUtils.capitalize(str[i]);
			result += str[i];
		}

		return result;
	}

	/**
	 * 生成字段表单字符串
	 * 
	 * @author Soyi Yao
	 * @param sqlFieldName
	 *        字段名的小写开头形式
	 * @param fieldType
	 *        字段Java类型
	 * @return 表单字符串
	 */
	public static String generateFormFieldsStringForAddJsp(String sqlFieldName,
			String lowerFieldName, String fieldType) {
		String formFields = "";
		if (!sqlFieldName.equalsIgnoreCase("id")) {

			if ("datetime".equalsIgnoreCase(fieldType.trim().toLowerCase())
					&& !"create_time".equalsIgnoreCase(sqlFieldName
							.toLowerCase())
					&& !"update_time".equalsIgnoreCase(sqlFieldName
							.toLowerCase())) {
				formFields += "<tr>"
						+ "<td  class=\"fieldName\"  width=\"20%\">"
						+ lowerFieldName + ":</td> "
						+ " <td  class=\"fieldForm\"  width=\"80%\">";
				formFields += " <input  name=\""
						+ lowerFieldName
						+ "\" "
						+ " id=\""
						+ lowerFieldName
						+ "\"  type=\"text\"  class=\"inputText\" "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})\"  /> "
						+ "<img "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})\" "
						+ " src=\"../widget/My97DatePicker/skin/datePicker.gif\"  width=\"16\" "
						+ " height=\"22\"  align=\"absmiddle\"> ";
				formFields += "<span  class=\"asterisk\">*</span></td> "
						+ "</tr>";

			} else if ("date".equalsIgnoreCase(fieldType.trim().toLowerCase())
					&& !"create_time".equalsIgnoreCase(sqlFieldName
							.toLowerCase())
					&& !"update_time".equalsIgnoreCase(sqlFieldName
							.toLowerCase())) {
				formFields += "<tr>"
						+ "<td  class=\"fieldName\"  width=\"20%\">"
						+ lowerFieldName + ":</td> "
						+ " <td  class=\"fieldForm\"  width=\"80%\">";
				formFields += " <input  name=\""
						+ lowerFieldName
						+ "\" "
						+ " id=\""
						+ lowerFieldName
						+ "\"  type=\"text\"  class=\"inputText\" "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd'})\"  /> "
						+ "<img "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd'})\" "
						+ " src=\"../widget/My97DatePicker/skin/datePicker.gif\"  width=\"16\" "
						+ " height=\"22\"  align=\"absmiddle\"> ";
				formFields += "<span  class=\"asterisk\">*</span></td> "
						+ "</tr>";

			}

			else if ("content".equalsIgnoreCase(sqlFieldName.trim()
					.toLowerCase())) {
				formFields += "<tr>"
						+ "<td  class=\"fieldName\"  width=\"20%\">"
						+ lowerFieldName + ":</td> "
						+ " <td  class=\"fieldForm\"  width=\"80%\">";

				formFields += "<textarea name=\"content\" id=\"content\">";
				formFields += "</textarea>";
				formFields += "<span  class=\"asterisk\">*</span></td> "
						+ "</tr>";
			} else if (!"create_user_id".equalsIgnoreCase(sqlFieldName
					.toLowerCase())
					&& !"create_user_name".equalsIgnoreCase(sqlFieldName
							.toLowerCase())
					&& !"update_user_id".equalsIgnoreCase(sqlFieldName
							.toLowerCase())
					&& !"update_user_name".equalsIgnoreCase(sqlFieldName
							.toLowerCase())
					&& !"create_time".equalsIgnoreCase(sqlFieldName
							.toLowerCase())
					&& !"update_time".equalsIgnoreCase(sqlFieldName
							.toLowerCase())) {

				if (!sqlFieldName.equalsIgnoreCase("id")
						&& !"create_user_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"update_user_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"parent_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"re_user_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"full_path_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())) {

					String sqlFieldNameTemp = "";
					String referenceTableName = "";// 引用的外键表名
					int temp = sqlFieldName.length() - 3;
					if (sqlFieldName.lastIndexOf("_id") == temp && temp > 0) {
						sqlFieldNameTemp = sqlFieldName.replace("_id", "");
						referenceTableName = "t_" + sqlFieldNameTemp;

						if (StringUtils.isNotEmpty(referenceTableName)) {
							String referenceUpperBeanName = getBeanUpperNameFromTableName(referenceTableName);
							String referenceLowerBeanName = getBeanLowerNameFromTableName(referenceTableName);

							formFields += "<tr>"
									+ "<td  class=\"fieldName\"  width=\"20%\">"
									+ lowerFieldName
									+ ":</td> "
									+ " <td  class=\"fieldForm\"  width=\"80%\">";

							formFields += "<select name=\""
									+ referenceLowerBeanName + "Id\" id=\""
									+ referenceLowerBeanName + "Id\">";
							formFields += "<option value=\"\">请选择</option>";
							formFields += "<c:forEach var=\""
									+ referenceLowerBeanName
									+ "List\" items=\"${"
									+ referenceLowerBeanName + "List }\">";
							formFields += "<option value=\"${"
									+ referenceLowerBeanName + "List.id }\">${"
									+ referenceLowerBeanName
									+ "List.name }</option>";
							formFields += "</c:forEach>";
							formFields += "</select>";

							formFields += "<span  class=\"asterisk\">*</span></td> "
									+ "</tr>";

						}
					} else {
						formFields += "<tr>"
								+ "<td  class=\"fieldName\"  width=\"20%\">"
								+ lowerFieldName + ":</td> "
								+ " <td  class=\"fieldForm\"  width=\"80%\">";

						formFields += "<input  type=\"text\" " + "name=\""
								+ lowerFieldName + "\"  id=\"" + lowerFieldName
								+ "\"  />";
						formFields += "<span  class=\"asterisk\">*</span></td> "
								+ "</tr>";
					}
				} else {
					formFields += "<tr>"
							+ "<td  class=\"fieldName\"  width=\"20%\">"
							+ lowerFieldName + ":</td> "
							+ " <td  class=\"fieldForm\"  width=\"80%\">";

					formFields += "<input  type=\"text\" " + "name=\""
							+ lowerFieldName + "\"  id=\"" + lowerFieldName
							+ "\"  />";
					formFields += "<span  class=\"asterisk\">*</span></td> "
							+ "</tr>";
				}

			}

		}
		return formFields;
	}

	/**
	 * 生成字段表单字符串
	 * 
	 * @author Soyi Yao
	 * @param sqlFieldName
	 *        字段名的小写开头形式
	 * @param fieldType
	 *        字段Java类型
	 * @return 表单字符串
	 */
	public static String generateFormFieldsStringForUpdateJsp(
			String sqlFieldName, String lowerFieldName, String fieldType) {
		String formFields = "";
		if (!lowerFieldName.equalsIgnoreCase("id")
				&& !"create_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_time".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"update_time".equalsIgnoreCase(sqlFieldName.toLowerCase())) {

			if ("datetime".equalsIgnoreCase(fieldType.trim().toLowerCase())) {
				formFields += "<tr>"
						+ "<td  class=\"fieldName\"  width=\"20%\">"
						+ lowerFieldName + ":</td> "
						+ " <td  class=\"fieldForm\"  width=\"80%\">";

				formFields += " <input  name=\""
						+ lowerFieldName
						+ "\" "
						+ " id=\""
						+ lowerFieldName
						+ "\"  value=\""
						+ "<fmt:formatDate value=\'${map."
						+ sqlFieldName
						+ " }\'"
						+ " pattern=\'yyyy-MM-dd HH:mm:ss\'/>\""
						+ " type=\"text\"  class=\"inputText\" "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})\"  /> "
						+ "<img "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})\" "
						+ " src=\"../widget/My97DatePicker/skin/datePicker.gif\"  width=\"16\" "
						+ " height=\"22\"  align=\"absmiddle\"> ";
				formFields += "<span  class=\"asterisk\">*</span></td> "
						+ "</tr>";

			} else if ("date".equalsIgnoreCase(fieldType.trim().toLowerCase())) {
				formFields += "<tr>"
						+ "<td  class=\"fieldName\"  width=\"20%\">"
						+ lowerFieldName + ":</td> "
						+ " <td  class=\"fieldForm\"  width=\"80%\">";

				formFields += " <input  name=\""
						+ lowerFieldName
						+ "\" "
						+ " id=\""
						+ lowerFieldName
						+ "\"  value=\""
						+ "<fmt:formatDate value=\'${map."
						+ sqlFieldName
						+ " }\'"
						+ " pattern=\'yyyy-MM-dd\'/>\""
						+ " type=\"text\"  class=\"inputText\" "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd'})\"  /> "
						+ "<img "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd'})\" "
						+ " src=\"../widget/My97DatePicker/skin/datePicker.gif\"  width=\"16\" "
						+ " height=\"22\"  align=\"absmiddle\"> ";
				formFields += "<span  class=\"asterisk\">*</span></td> "
						+ "</tr>";

			} else if ("content".equalsIgnoreCase(sqlFieldName.trim()
					.toLowerCase())) {
				formFields += "<tr>"
						+ "<td  class=\"fieldName\"  width=\"20%\">"
						+ lowerFieldName + ":</td> "
						+ " <td  class=\"fieldForm\"  width=\"80%\">";

				formFields += "<textarea name=\"content\" id=\"content\">";

				formFields += "${map." + sqlFieldName + "}";
				formFields += "</textarea>";
				formFields += "<span  class=\"asterisk\">*</span></td> "
						+ "</tr>";
			} else if (!"create_user_id".equalsIgnoreCase(sqlFieldName
					.toLowerCase())
					&& !"create_user_name".equalsIgnoreCase(sqlFieldName
							.toLowerCase())
					&& !"update_user_id".equalsIgnoreCase(sqlFieldName
							.toLowerCase())
					&& !"update_user_name".equalsIgnoreCase(sqlFieldName
							.toLowerCase())
					&& !"create_time".equalsIgnoreCase(sqlFieldName
							.toLowerCase())
					&& !"update_time".equalsIgnoreCase(sqlFieldName
							.toLowerCase())) {

				if (!sqlFieldName.equalsIgnoreCase("id")
						&& !"create_user_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"update_user_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"parent_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"re_user_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"full_path_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())) {

					String sqlFieldNameTemp = "";
					String referenceTableName = "";// 引用的外键表名
					int temp = sqlFieldName.length() - 3;
					if (sqlFieldName.lastIndexOf("_id") == temp && temp > 0) {
						sqlFieldNameTemp = sqlFieldName.replace("_id", "");
						referenceTableName = "t_" + sqlFieldNameTemp;

						if (StringUtils.isNotEmpty(referenceTableName)) {
							String referenceUpperBeanName = getBeanUpperNameFromTableName(referenceTableName);
							String referenceLowerBeanName = getBeanLowerNameFromTableName(referenceTableName);

							formFields += "<tr>"
									+ "<td  class=\"fieldName\"  width=\"20%\">"
									+ lowerFieldName
									+ ":</td> "
									+ " <td  class=\"fieldForm\"  width=\"80%\">";

							formFields += "<select name=\""
									+ referenceLowerBeanName + "Id\" id=\""
									+ referenceLowerBeanName + "Id\">";
							formFields += "<option value=\"\">请选择</option>";
							formFields += "<c:forEach var=\""
									+ referenceLowerBeanName
									+ "List\" items=\"${"
									+ referenceLowerBeanName + "List }\">";
							formFields += "<option value=\"${"
									+ referenceLowerBeanName
									+ "List.id }\" "
									+ "<c:if test=\"${map."
									+ sqlFieldName
									+ " =="
									+ referenceLowerBeanName
									+ "List.id  }\"> selected=\"selected\"</c:if> "
									+ ">${" + referenceLowerBeanName
									+ "List.name }</option>";
							formFields += "</c:forEach>";
							formFields += "</select>";

							formFields += "<span  class=\"asterisk\">*</span></td> "
									+ "</tr>";

						}
					} else {
						formFields += "<tr>"
								+ "<td  class=\"fieldName\"  width=\"20%\">"
								+ lowerFieldName + ":</td> "
								+ " <td  class=\"fieldForm\"  width=\"80%\">";

						formFields += "<input  type=\"text\" " + "name=\""
								+ lowerFieldName + "\"  id=\"" + lowerFieldName
								+ "\"  value=\"${map." + sqlFieldName
								+ " }\" />";
						formFields += "<span  class=\"asterisk\">*</span></td> "
								+ "</tr>";
					}
				} else {
					formFields += "<tr>"
							+ "<td  class=\"fieldName\"  width=\"20%\">"
							+ lowerFieldName + ":</td> "
							+ " <td  class=\"fieldForm\"  width=\"80%\">";

					formFields += "<input  type=\"text\" " + "name=\""
							+ lowerFieldName + "\"  id=\"" + lowerFieldName
							+ "\" value=\"${map." + sqlFieldName + " }\"  />";
					formFields += "<span  class=\"asterisk\">*</span></td> "
							+ "</tr>";
				}
			}

		} else {
			// formFields += "<tr style='display:none'>"
			// + "<td  class=\"fieldName\"  width=\"20%\">"
			// + lowerFieldName + ":</td> "
			// + " <td  class=\"fieldForm\"  width=\"80%\">";
			//
			// formFields += "<input  type=\"hidden\" " + "name=\""
			// + lowerFieldName + "\"  id=\"" + lowerFieldName
			// + "\" value=\"${map." + sqlFieldName + " }\"  />";
			// formFields += "<span  class=\"asterisk\">*</span></td> " + "</tr>";
		}
		return formFields;
	}

	/**
	 * 生成查看详细字段表单字符串
	 * 
	 * @author Soyi Yao
	 * @param lowerFieldName
	 *        字段名(非转换下划线后的)
	 * @param fieldType
	 *        字段Java类型
	 * @return 表单字符串
	 */
	public static String generateFieldsForViewString(String sqlFieldName,
			String fieldType) {
		String formFields = "";
		if (!sqlFieldName.equalsIgnoreCase("id")
				&& !sqlFieldName.equalsIgnoreCase("create_user_id")
				&& !sqlFieldName.equalsIgnoreCase("update_user_id")

		) {
			if ("datetime".equalsIgnoreCase(fieldType.toLowerCase())) {
				formFields += " <tr> "
						+ "             <td  class=\"fieldName\"  width=\"20%\">"
						+ sqlFieldName
						+ ":</td> "
						+ "               <td  class=\"fieldForm\"  width=\"80%\">"
						+ "<fmt:formatDate value=\'${map." + sqlFieldName
						+ " }\'" + " pattern=\'yyyy-MM-dd HH:mm:ss\'/>"
						+ "</td> " + " </tr> ";
			} else if ("date".equalsIgnoreCase(fieldType.toLowerCase())) {
				formFields += " <tr> "
						+ "             <td  class=\"fieldName\"  width=\"20%\">"
						+ sqlFieldName
						+ ":</td> "
						+ "               <td  class=\"fieldForm\"  width=\"80%\">"
						+ "<fmt:formatDate value=\'${map." + sqlFieldName
						+ " }\'" + " pattern=\'yyyy-MM-dd\'/>" + "</td> "
						+ " </tr> ";
			} else {
				formFields += " <tr> "
						+ "             <td  class=\"fieldName\"  width=\"20%\">"
						+ sqlFieldName
						+ ":</td> "
						+ "               <td  class=\"fieldForm\"  width=\"80%\">${map."
						+ sqlFieldName + "  }</td> " + " </tr> ";
			}

		}
		return formFields;
	}

	/**
	 * 根据表名称获取bean的大写开头名称
	 * 比如，表名为t_user_info，去掉"t_"，返回UserInfo
	 * 
	 * @author Soyi Yao
	 * @param tableName
	 * @return
	 */
	public static String getBeanUpperNameFromTableName(String tableName) {

		String[] str = tableName.toLowerCase()
				.replaceAll("^" + CodeGeneratorConstants.TABLE_PREFIX, "")
				.split("_");
		String result = StringUtils.capitalize(str[0]);

		for (int i = 1; i < str.length; i++) {
			str[i] = StringUtils.capitalize(str[i]);
			result += str[i];
		}

		return result;
	}

	/**
	 * 返回以List包装的HashMap，表字段
	 * 
	 * @author Soyi Yao
	 * @param tableName
	 *        表名
	 * @return 以List包装的HashMap，表字段
	 * @throws Exception
	 *         异常
	 */
	public static List<HashMap<String, String>> getTableColumns(String tableName)
			throws Exception {
		ResultSetMetaData rsmd = CodeGeneratorDataBaseUtils
				.getTableResultSetMetaData(tableName);
		List<HashMap<String, String>> columns = new ArrayList<HashMap<String, String>>();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			String columnSQLTypeName = rsmd.getColumnTypeName(i);
			String columnSQLName = rsmd.getColumnName(i).toLowerCase();
			String columnJavaTypeName = getTypeName(columnSQLTypeName);// 转换数据库和java之间的数据类型
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(columnJavaTypeName + "@" + columnSQLTypeName, columnSQLName);
			columns.add(map);
		}

		return columns;
	}

	/**
	 * 用于转换数据库和java之间的数据类型
	 * 
	 * @param typeName
	 *        数据表字段类型名
	 * @return 转换后的Java类型名
	 */

	public static String getTypeName(String typeName) {
		String typeStr = "";
		if (typeName.toLowerCase().trim().equals("varchar")) {
			typeStr = "String";
		} else if (typeName.toLowerCase().trim().equals("int")) {
			typeStr = "String";
		} else if (typeName.toLowerCase().trim().equals("datetime")) {
			typeStr = "String";
		} else if (typeName.toLowerCase().trim().equals("date")) {
			typeStr = "String";
		} else {
			typeStr = "Object";
		}
		return typeStr;

	}

	/**
	 * 生成字段表单字符串
	 * 
	 * @author Soyi Yao
	 * @param lowerFieldName
	 *        字段名的小写开头形式
	 * @param fieldType
	 *        字段Java类型
	 * @return 表单字符串
	 */
	public static String generateFormFieldValidationRulesString(
			String sqlFieldName, String lowerFieldName, String fieldType) {
		String formFieldValidationRules = "";
		if (!lowerFieldName.equalsIgnoreCase("id")
				&& !"create_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_time".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"update_time".equalsIgnoreCase(sqlFieldName.toLowerCase())) {
			formFieldValidationRules += " , " + " " + lowerFieldName
					+ "  :  { " + "               required  :  true ";

			if ("int".equalsIgnoreCase(fieldType.toLowerCase())) {
				formFieldValidationRules += ",digits:true  ";
			}
			formFieldValidationRules += " } ";
		}
		return formFieldValidationRules;
	}

	/**
	 * 生成字段表单字符串
	 * 
	 * @author Soyi Yao
	 * @param lowerFieldName
	 *        字段名的小写开头形式
	 * @param fieldType
	 *        字段Java类型
	 * @return 表单字符串
	 */
	public static String generateListTableString(String sqlFieldName,
			String fieldType) {
		String listTableString = "";
		if (!sqlFieldName.equalsIgnoreCase("id")) {

			listTableString += " , " + " " + sqlFieldName + "  :  { "
					+ "               required  :  true " + " } ";

		}
		return listTableString;
	}

	/**
	 * 生成列表页头部字符串
	 * 
	 * @author Soyi Yao
	 * @param sqlFieldName
	 *        sql字段名
	 * @param type
	 *        字段类型
	 * @return
	 */
	public static String generateListHeadersForListString(String sqlFieldName,
			String type) {
		String listHeadersForListString = "";
		if (!sqlFieldName.equalsIgnoreCase("id")
				&& !"create_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_time".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"update_time".equalsIgnoreCase(sqlFieldName.toLowerCase())) {

			listHeadersForListString += "<th>" + sqlFieldName + "</th>";

		}
		return listHeadersForListString;
	}

	/**
	 * @author Soyi Yao
	 * @param sqlFieldName
	 * @param type
	 * @return
	 */
	public static String generateListRecordsForListString(String sqlFieldName,
			String type) {
		String listHeadersForListString = "";
		if (!sqlFieldName.equalsIgnoreCase("id")
				&& !"create_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_time".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"update_time".equalsIgnoreCase(sqlFieldName.toLowerCase())) {
			if (!"datetime".equalsIgnoreCase(type)) {
				listHeadersForListString += "<td>" + "${list." + sqlFieldName
						+ " }</td>";
			} else {
				listHeadersForListString += "<td>" +

				"<fmt:formatDate value=\"${list." + sqlFieldName + " }\""
						+ "pattern=\"yyyy-MM-dd HH:mm:ss\" />" + " </td>";

			}

		}
		return listHeadersForListString;
	}

	/**
	 * 要替死换内容的Map
	 * 
	 * @author Soyi Yao
	 * @param tableName
	 *        表名
	 * @return 需要替换的内容的Map
	 * @throws Exception
	 */
	public static Map<String, String> getReplaceMap(String tableName)
			throws Exception {

		Map<String, String> returnMap = new HashMap<String, String>();

		String lowerBeanName = CodeGeneratorUtils
				.getBeanLowerNameFromTableName(tableName);
		String upperBeanName = CodeGeneratorUtils
				.getBeanUpperNameFromTableName(tableName);
		String formFieldValidationRules = "";
		String lowerFieldName = "";
		String upperFieldName = "";
		String formFieldsForAddJsp = "";
		String fckeditorJavaScript = "";
		String fckeditorJavaScriptReplace = "";
		String formFieldsForUpdateJsp = "";
		String sqlFieldName = "";
		String fieldsForView = "";
		String listHeaders = "";
		String listRecords = "";
		String searchFormFields = "";
		String searchCriteria = "";
		String fieldsToInsert = "";
		String fieldsToInsertValue = "";
		String values = "";
		String valueTypes = "";
		String valuesForUpdate = "";
		String valueTypesForUpdate = "";
		String updateFields = "";
		String beanAllSettingMethod = "";
		String referenceServiceForController = "";
		String referenceListSetting = "";
		String referenceImport = "";
		String createUserInformationAdding = "";
		String userServiceForController = "";
		String createUserInformationUpdating = "";
		String newCellForContent = "";
		String columnNamesArray = "";
		String formActionCriteria = "";
		String userService = "";

		List<HashMap<String, String>> columns = CodeGeneratorUtils
				.getTableColumns(tableName);
		int count = 1;
		for (HashMap<String, String> map : columns) {
			for (Iterator<Map.Entry<String, String>> it = map.entrySet()
					.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String typeString = entry.getKey();

				String columnJavaTypeName = typeString.split("@")[0];
				String columnSQLTypeName = typeString.split("@")[1];
				String type = typeString.split("@")[1];

				lowerFieldName = CodeGeneratorUtils
						.getFieldLowerNameFromTableName(entry.getValue());
				upperFieldName = CodeGeneratorUtils
						.getFieldUpperNameFromTableName(entry.getValue());
				sqlFieldName = entry.getValue();

				formFieldsForAddJsp += CodeGeneratorUtils
						.generateFormFieldsStringForAddJsp(sqlFieldName,
								lowerFieldName, type);

				fckeditorJavaScriptReplace += CodeGeneratorUtils
						.generateFckeditorJavaScriptReplaceForAddUpdateJsp(
								sqlFieldName, lowerFieldName, type);
				fckeditorJavaScript += CodeGeneratorUtils
						.generateFckeditorJavaScriptForAddUpdateJsp(
								sqlFieldName, lowerFieldName, type);

				formFieldsForUpdateJsp += CodeGeneratorUtils
						.generateFormFieldsStringForUpdateJsp(sqlFieldName,
								lowerFieldName, type);

				formFieldValidationRules += CodeGeneratorUtils
						.generateFormFieldValidationRulesString(sqlFieldName,
								lowerFieldName, type);
				fieldsForView += CodeGeneratorUtils
						.generateFieldsForViewString(sqlFieldName, type);
				listHeaders += CodeGeneratorUtils
						.generateListHeadersForListString(sqlFieldName, type);
				listRecords += CodeGeneratorUtils
						.generateListRecordsForListString(sqlFieldName, type);
				if (!lowerFieldName.equalsIgnoreCase("id")
						&& !"create_user_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"create_user_name".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"update_user_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"update_user_name".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"create_time".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"update_time".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"content".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"description".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"remark".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"order_number".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"password".equalsIgnoreCase(sqlFieldName
								.toLowerCase())

				) {
					searchFormFields += CodeGeneratorUtils
							.generateSearchFormFieldsString(sqlFieldName,
									lowerFieldName, type, count++);
				}

				searchCriteria += CodeGeneratorUtils
						.generateSearchCriteriaString(lowerBeanName,
								sqlFieldName, lowerFieldName, upperFieldName,
								type);
				fieldsToInsert += CodeGeneratorUtils
						.generateFieldsToInsertString(lowerBeanName,
								sqlFieldName, lowerFieldName, upperFieldName,
								type);
				fieldsToInsertValue += CodeGeneratorUtils
						.generateFieldsToInsertValueString(lowerBeanName,
								sqlFieldName, lowerFieldName, upperFieldName,
								type);
				values += CodeGeneratorUtils.generateValuesString(
						lowerBeanName, sqlFieldName, lowerFieldName,
						upperFieldName, type);
				valueTypes += CodeGeneratorUtils.generateValueTypesString(
						lowerBeanName, sqlFieldName, lowerFieldName,
						upperFieldName, type);
				valuesForUpdate += CodeGeneratorUtils
						.generateValuesForUpdateString(lowerBeanName,
								sqlFieldName, lowerFieldName, upperFieldName,
								type);
				valueTypesForUpdate += CodeGeneratorUtils
						.generateValueTypesForUpdateString(lowerBeanName,
								sqlFieldName, lowerFieldName, upperFieldName,
								type);
				updateFields += CodeGeneratorUtils.generateUpdateFieldsString(
						lowerBeanName, sqlFieldName, lowerFieldName,
						upperFieldName, type);
				beanAllSettingMethod += CodeGeneratorUtils
						.generateBeanAllSettingMethodString(lowerBeanName,
								upperBeanName, upperFieldName, sqlFieldName);
				referenceServiceForController += CodeGeneratorUtils
						.generateReferenceServiceForControllerString(
								lowerBeanName, upperBeanName, upperFieldName,
								sqlFieldName);
				referenceListSetting += CodeGeneratorUtils
						.generateReferenceListSettingString(lowerBeanName,
								upperBeanName, upperFieldName, sqlFieldName);
				referenceImport += CodeGeneratorUtils
						.generateReferenceImportString(lowerBeanName,
								upperBeanName, upperFieldName, sqlFieldName);
				createUserInformationAdding += CodeGeneratorUtils
						.generateCreateUserInformationAddingString(
								lowerBeanName, upperBeanName, upperFieldName,
								sqlFieldName);
				createUserInformationUpdating += CodeGeneratorUtils
						.generateCreateUserInformationUpdatingingString(
								lowerBeanName, upperBeanName, upperFieldName,
								sqlFieldName);

				userServiceForController += CodeGeneratorUtils
						.generateUserServiceForControllerString(lowerBeanName,
								upperBeanName, upperFieldName, sqlFieldName);
				newCellForContent += CodeGeneratorUtils
						.generateNewCellForContentString(lowerBeanName,
								upperBeanName, upperFieldName, lowerFieldName,
								sqlFieldName);
				columnNamesArray += CodeGeneratorUtils
						.generateColumnNamesArrayString(lowerBeanName,
								upperBeanName, upperFieldName, lowerFieldName,
								sqlFieldName);
				formActionCriteria += CodeGeneratorUtils
						.generateFormActionCriteriaString(lowerBeanName,
								upperBeanName, upperFieldName, lowerFieldName,
								sqlFieldName);
				userService = CodeGeneratorUtils.generateUserServiceString(
						lowerBeanName, upperBeanName, upperFieldName,
						lowerFieldName, sqlFieldName);

			}
		}
		// 去掉insert语句中最后一个多余的逗号
		if (fieldsToInsert.length() > 0) {
			fieldsToInsert = fieldsToInsert.substring(0,
					fieldsToInsert.lastIndexOf(","))
					+ " \");"; // 去掉后面的,
		}
		// 去掉insert values语句中最后一个多余的逗号
		if (fieldsToInsertValue.length() > 0) {
			fieldsToInsertValue = fieldsToInsertValue.substring(0,
					fieldsToInsertValue.lastIndexOf(",")) + " \");"; // 去掉后面的,
		}
		// 去掉values语句中最后一个多余的逗号
		if (values.length() > 0) {
			values = values.substring(0, values.lastIndexOf(",")); // 去掉后面的,
		}
		// 去掉valueTypes语句中最后一个多余的逗号
		if (valueTypes.length() > 0) {
			valueTypes = valueTypes.substring(0, valueTypes.lastIndexOf(",")); // 去掉后面的,
		}
		// 去掉updateFields语句中最后一个多余的逗号
		if (updateFields.length() > 0) {
			updateFields = updateFields.substring(0,
					updateFields.lastIndexOf(","))
					+ " \");"; // 去掉后面的,
		}
		// 去掉columnNamesArray语句中最后一个多余的逗号
		if (columnNamesArray.length() > 0) {
			columnNamesArray = columnNamesArray.substring(0,
					columnNamesArray.lastIndexOf(",")); // 去掉后面的,
		}

		int temp = searchFormFields.length() - 5;
		if (searchFormFields.lastIndexOf("</tr>") != temp && temp > 0) {
			searchFormFields += "<td class=\"fieldName\" width=\"10%\"> </td>";
			searchFormFields += "<td class=\"fieldForm\" width=\"40%\"> </td>";
			searchFormFields += "</tr>";
		}

		returnMap.put("@lowerBeanName@", lowerBeanName);
		returnMap.put("@upperBeanName@", upperBeanName);
		returnMap.put("@formFieldsForAddJsp@", formFieldsForAddJsp);
		returnMap.put("@formFieldsForUpdateJsp@", formFieldsForUpdateJsp);
		returnMap.put("@formFieldValidationRules@", formFieldValidationRules);
		returnMap.put("@fieldsForView@", fieldsForView);
		returnMap.put("@listHeaders@", listHeaders);
		returnMap.put("@listRecords@", listRecords);
		returnMap.put("@searchFormFields@", searchFormFields);
		returnMap.put("@searchCriteria@", searchCriteria);
		returnMap.put("@fieldsToInsert@", fieldsToInsert);
		returnMap.put("@fieldsToInsertValue@", fieldsToInsertValue);
		returnMap.put("@values@", values);
		returnMap.put("@valueTypes@", valueTypes);
		returnMap.put("@valuesForUpdate@", valuesForUpdate);
		returnMap.put("@valueTypesForUpdate@", valueTypesForUpdate);
		returnMap.put("@updateFields@", updateFields);
		returnMap.put("@tableName@", tableName);
		returnMap.put("@fckeditorJavaScriptReplace@",
				fckeditorJavaScriptReplace);
		returnMap.put("@fckeditorJavaScript@", fckeditorJavaScript);
		returnMap.put("@beanAllSettingMethod@", beanAllSettingMethod);
		returnMap.put("@referenceServiceForController@",
				referenceServiceForController);
		returnMap.put("@referenceListSetting@", referenceListSetting);
		returnMap.put("@referenceImport@", referenceImport);
		returnMap.put("@createUserInformationAdding@",
				createUserInformationAdding);
		returnMap.put("@userServiceForController@", userServiceForController);
		returnMap.put("@createUserInformationUpdating@",
				createUserInformationUpdating);
		returnMap.put("@newCellForContent@", newCellForContent);
		returnMap.put("@columnNamesArray@", columnNamesArray);
		returnMap.put("@formActionCriteria@", formActionCriteria);
		returnMap.put("@userService@", userService);

		return returnMap;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param upperBeanName
	 * @param upperFieldName
	 * @param lowerFieldName
	 * @param sqlFieldName
	 * @return
	 */
	private static String generateUserServiceString(String lowerBeanName,
			String upperBeanName, String upperFieldName, String lowerFieldName,
			String sqlFieldName) {
		String userServiceString = "";
		if (!"User".equalsIgnoreCase(upperBeanName)) {
			userServiceString += "@Autowired\n";
			userServiceString += "private UserService userService;\n";
		}
		return userServiceString;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param upperBeanName
	 * @param upperFieldName
	 * @param lowerFieldName
	 * @param sqlFieldName
	 * @return
	 */
	private static String generateFormActionCriteriaString(
			String lowerBeanName, String upperBeanName, String upperFieldName,
			String lowerFieldName, String sqlFieldName) {
		String formActionCriteria = "";
		if (!lowerFieldName.equalsIgnoreCase("id")
				&& !"create_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_time".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"update_time".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"content".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"description".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"remark".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"order_number".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"password".equalsIgnoreCase(sqlFieldName.toLowerCase())

		) {

			formActionCriteria = " formAction  +=  \"&" + lowerFieldName
					+ "=\"" + "+$(\"#" + lowerFieldName + "\").val();\n ";

		}
		return formActionCriteria;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param upperBeanName
	 * @param upperFieldName
	 * @param lowerFieldName
	 * @param sqlFieldName
	 * @return
	 */
	private static String generateColumnNamesArrayString(String lowerBeanName,
			String upperBeanName, String upperFieldName, String lowerFieldName,
			String sqlFieldName) {
		String columnNamesArray = "";
		if (!sqlFieldName.equalsIgnoreCase("id"))

			columnNamesArray = "\"" + upperFieldName + "\" ,";

		return columnNamesArray;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param upperBeanName
	 * @param upperFieldName
	 * @param sqlFieldName
	 * @return
	 */
	private static String generateNewCellForContentString(String lowerBeanName,
			String upperBeanName, String upperFieldName, String lowerFieldName,
			String sqlFieldName) {
		String newCellForContent = "";
		if (!sqlFieldName.equalsIgnoreCase("id"))

			newCellForContent = "//  新建一列 \n"
					+ "\n cell  =  row.createCell(cellNo++); \n"
					+ "                         cell.setCellStyle(contentStyle); \n"
					+ "                         cellValue  =  (String)  excelContent.get(i).get"
					+ upperFieldName
					+ "();\n "
					+ "                         cell.setCellValue(cellValue);\n\n";

		return newCellForContent;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param upperBeanName
	 * @param upperFieldName
	 * @param sqlFieldName
	 * @return
	 */
	private static String generateCreateUserInformationUpdatingingString(
			String lowerBeanName, String upperBeanName, String upperFieldName,
			String sqlFieldName) {
		String createUserInformationUpdatinging = "";
		if ("update_user_id".equalsIgnoreCase(sqlFieldName.toLowerCase())

		) {

			// createUserInformationUpdatinging += "// 获取操作管理员信息\n";
			// createUserInformationUpdatinging +=
			// "	User admin = (User) request.getSession().getAttribute(\"admin\");\n";
			// createUserInformationUpdatinging +=
			// "	admin = this.userService.findUserById(admin.getId());\n\n";

			createUserInformationUpdatinging += "// 设置更新操作管理员\n";
			createUserInformationUpdatinging += lowerBeanName
					+ ".setUpdateUserId(admin.getId());";
			createUserInformationUpdatinging += lowerBeanName
					+ ".setUpdateUserName(admin.getName());";

		}
		return createUserInformationUpdatinging;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param upperBeanName
	 * @param upperFieldName
	 * @param sqlFieldName
	 * @return
	 */
	private static String generateUserServiceForControllerString(
			String lowerBeanName, String upperBeanName, String upperFieldName,
			String sqlFieldName) {
		String userServiceForController = "";
		if (!"User".equalsIgnoreCase(upperBeanName)) {
			if ("create_user_id".equalsIgnoreCase(sqlFieldName.toLowerCase())
					|| "udpate_user_id".equalsIgnoreCase(sqlFieldName
							.toLowerCase())

			) {

				userServiceForController += "	@Autowired\n";
				userServiceForController += "	private UserService userService;\n";

			}
		}
		return userServiceForController;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param upperBeanName
	 * @param upperFieldName
	 * @param sqlFieldName
	 * @return
	 */
	private static String generateCreateUserInformationAddingString(
			String lowerBeanName, String upperBeanName, String upperFieldName,
			String sqlFieldName) {
		String createUserInformationAdding = "";
		if ("create_user_id".equalsIgnoreCase(sqlFieldName.toLowerCase())

		) {

			// createUserInformationAdding += "// 获取操作管理员信息\n";
			// createUserInformationAdding +=
			// "	User admin = (User) request.getSession().getAttribute(\"admin\");\n";
			// createUserInformationAdding +=
			// "	admin = this.userService.findUserById(admin.getId());\n\n";

			createUserInformationAdding += "// 设置操作管理员\n";
			createUserInformationAdding += lowerBeanName
					+ ".setCreateUserId(admin.getId());";
			createUserInformationAdding += lowerBeanName
					+ ".setCreateUserName(admin.getName());";

		}
		return createUserInformationAdding;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param upperBeanName
	 * @param upperFieldName
	 * @param sqlFieldName
	 * @return
	 */
	private static String generateReferenceServiceForControllerString(
			String lowerBeanName, String upperBeanName, String upperFieldName,
			String sqlFieldName) {
		String referenceServiceForControllerString = "";
		String referenceTableName = getReferenceTable(sqlFieldName);
		if (StringUtils.isNotEmpty(referenceTableName)
				&& !"t_user".equalsIgnoreCase(referenceTableName)) {
			String referenceUpperBeanName = getBeanUpperNameFromTableName(referenceTableName);
			String referenceLowerBeanName = getBeanLowerNameFromTableName(referenceTableName);

			referenceServiceForControllerString += " @Autowired";
			referenceServiceForControllerString += " private "
					+ referenceUpperBeanName + "Service "
					+ referenceLowerBeanName + "Service;";

		}
		return referenceServiceForControllerString;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param upperBeanName
	 * @param upperFieldName
	 * @param sqlFieldName
	 * @return
	 */
	private static String getReferenceTable(String sqlFieldName) {
		String referenceTableName = "";
		if (!sqlFieldName.equalsIgnoreCase("id")
				&& !"create_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"parent_id".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"re_user_id".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"full_path_id".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"user_id".equalsIgnoreCase(sqlFieldName.toLowerCase())

		) {

			String sqlFieldNameTemp = "";
			referenceTableName = "";// 引用的外键表名
			int temp = sqlFieldName.length() - 3;
			if (sqlFieldName.lastIndexOf("_id") == temp && temp > 0) {
				sqlFieldNameTemp = sqlFieldName.replace("_id", "");
				referenceTableName = "t_" + sqlFieldNameTemp;
			}

		}
		return referenceTableName;
	}

	private static String generateReferenceListSettingString(
			String lowerBeanName, String upperBeanName, String upperFieldName,
			String sqlFieldName) {
		String referenceListSettingString = "";
		String referenceTableName = getReferenceTable(sqlFieldName);

		if (StringUtils.isNotEmpty(referenceTableName)) {
			String referenceUpperBeanName = getBeanUpperNameFromTableName(referenceTableName);
			String referenceLowerBeanName = getBeanLowerNameFromTableName(referenceTableName);

			referenceListSettingString += "List<" + referenceUpperBeanName
					+ "> " + referenceLowerBeanName + "List = this."
					+ referenceLowerBeanName + "Service.listAll"
					+ referenceUpperBeanName + "();";
			referenceListSettingString += "request.setAttribute(\""
					+ referenceLowerBeanName + "List\", "
					+ referenceLowerBeanName + "List);";

		}
		return referenceListSettingString;
	}

	private static String generateReferenceImportString(String lowerBeanName,
			String upperBeanName, String upperFieldName, String sqlFieldName) {
		String referenceListSettingString = "";
		String referenceTableName = getReferenceTable(sqlFieldName);

		if (StringUtils.isNotEmpty(referenceTableName)) {
			String referenceUpperBeanName = getBeanUpperNameFromTableName(referenceTableName);

			referenceListSettingString += "import com.tkxgz.elitecourse.bean."
					+ referenceUpperBeanName + ";";
			referenceListSettingString += "import com.tkxgz.elitecourse.service."
					+ referenceUpperBeanName + "Service;";

		}
		return referenceListSettingString;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param sqlFieldName
	 * @param sqlFieldName
	 * @param upperFieldName
	 * @param type
	 * @return
	 */
	private static String generateBeanAllSettingMethodString(
			String lowerBeanName, String upperBeanName, String UpperFieldName,
			String sqlFieldName) {
		String beanAllSettingMethodString = lowerBeanName + ".set"
				+ UpperFieldName + "(rs.getString(\"" + sqlFieldName
				+ "\".toLowerCase())); \n";

		return beanAllSettingMethodString;
	}

	/**
	 * @author Soyi Yao
	 * @param sqlFieldName
	 * @param lowerFieldName
	 * @param type
	 * @return
	 */
	private static String generateFckeditorJavaScriptForAddUpdateJsp(
			String sqlFieldName, String lowerFieldName, String type) {

		String fckEditorJavaScript = "";
		if (sqlFieldName.equalsIgnoreCase("content")) {

			fckEditorJavaScript = "<script type=\"text/javascript\" src=\"${ctx }/widget/ckeditor/ckeditor.js\"></script>";

		}
		return fckEditorJavaScript;
	}

	/**
	 * @author Soyi Yao
	 * @param sqlFieldName
	 * @param lowerFieldName
	 * @param type
	 * @return
	 */
	private static String generateFckeditorJavaScriptReplaceForAddUpdateJsp(
			String sqlFieldName, String lowerFieldName, String type) {
		String fckEditorJavaScriptReplace = "";
		if (sqlFieldName.equalsIgnoreCase("content")) {

			fckEditorJavaScriptReplace = " <script  type=\"text/javascript\"> "
					+ "                 CKEDITOR.replace('"
					+ sqlFieldName
					+ "',  { "
					+ "                         filebrowserUploadUrl  :  'ckeditor/uploader?Type=File', "
					+ "                         filebrowserImageUploadUrl  :  'ckeditor/uploader?Type=Image', "
					+ "                         filebrowserFlashUploadUrl  :  'ckeditor/uploader?Type=Flash' "
					+ "                 }); " + "         </script> ";

		}
		return fckEditorJavaScriptReplace;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param sqlFieldName
	 * @param lowerFieldName
	 * @param upperFieldName
	 * @param type
	 * @return
	 */
	private static String generateUpdateFieldsString(String lowerBeanName,
			String sqlFieldName, String lowerFieldName, String upperFieldName,
			String type) {
		String updateFields = "";
		if (!sqlFieldName.equalsIgnoreCase("id"))
			if (!sqlFieldName.equalsIgnoreCase("update_time")) {
				updateFields += " sql.append(\" t." + sqlFieldName
						+ " = ? , \"); \n ";
			} else {
				updateFields += " sql.append(\" t.update_time = now() , \"); \n ";

			}
		return updateFields;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param sqlFieldName
	 * @param lowerFieldName
	 * @param upperFieldName
	 * @param type
	 * @return
	 */
	private static String generateValuesString(String lowerBeanName,
			String sqlFieldName, String lowerFieldName, String upperFieldName,
			String type) {
		String valuesString = "";
		if (!sqlFieldName.equalsIgnoreCase("id")) {
			if (!sqlFieldName.equalsIgnoreCase("create_time")) {
				valuesString += lowerBeanName + ".get" + upperFieldName
						+ "(), \n";
			} else {

			}

		}
		return valuesString;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param sqlFieldName
	 * @param lowerFieldName
	 * @param upperFieldName
	 * @param type
	 * @return
	 */
	private static String generateValuesForUpdateString(String lowerBeanName,
			String sqlFieldName, String lowerFieldName, String upperFieldName,
			String type) {
		String valuesString = "";

		if (!sqlFieldName.equalsIgnoreCase("id")) {
			if (!sqlFieldName.equalsIgnoreCase("update_time")) {
				valuesString += lowerBeanName + ".get" + upperFieldName
						+ "(), \n";
			} else {

			}

		}
		return valuesString;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param sqlFieldName
	 * @param lowerFieldName
	 * @param upperFieldName
	 * @param type
	 * @return
	 */
	private static String generateValueTypesString(String lowerBeanName,
			String sqlFieldName, String lowerFieldName, String upperFieldName,
			String type) {
		String valueTypesString = "";
		if (!sqlFieldName.equalsIgnoreCase("id")) {
			if (!sqlFieldName.equalsIgnoreCase("create_time")) {
				valueTypesString += " Types.VARCHAR " + ", \n";
			} else {

			}

		}
		return valueTypesString;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param sqlFieldName
	 * @param lowerFieldName
	 * @param upperFieldName
	 * @param type
	 * @return
	 */
	private static String generateValueTypesForUpdateString(
			String lowerBeanName, String sqlFieldName, String lowerFieldName,
			String upperFieldName, String type) {
		String valueTypesString = "";
		if (!sqlFieldName.equalsIgnoreCase("id")
				&& !sqlFieldName.equalsIgnoreCase("update_time")) {
			valueTypesString += " Types.VARCHAR " + ", \n";
		}

		return valueTypesString;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param sqlFieldName
	 * @param lowerFieldName
	 * @param upperFieldName
	 * @param type
	 * @return
	 */
	private static String generateFieldsToInsertString(String lowerBeanName,
			String sqlFieldName, String lowerFieldName, String upperFieldName,
			String type) {
		String fieldsToInsertString = "";
		if (!sqlFieldName.equalsIgnoreCase("id")) {
			fieldsToInsertString += " sql.append(\" " + sqlFieldName
					+ " , \"); \n ";

		}
		return fieldsToInsertString;
	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param sqlFieldName
	 * @param lowerFieldName
	 * @param upperFieldName
	 * @param type
	 * @return
	 */
	private static String generateFieldsToInsertValueString(
			String lowerBeanName, String sqlFieldName, String lowerFieldName,
			String upperFieldName, String type) {
		String fieldsToInsertString = "";
		if (!sqlFieldName.equalsIgnoreCase("id")) {

			if (!sqlFieldName.equalsIgnoreCase("create_time")) {
				fieldsToInsertString += " sql.append(\" ? , \"); \n ";
			} else {
				fieldsToInsertString += " sql.append(\" now() , \"); \n ";

			}

		}
		return fieldsToInsertString;
	}

	/**
	 * @author Soyi Yao
	 * @param sqlFieldName
	 * @param lowerFieldName
	 * @param type
	 * @param i
	 * @return
	 */
	private static String generateSearchCriteriaString(String lowerBeanName,
			String sqlFieldName, String lowerFieldName, String upperFieldName,
			String type) {
		String listHeadersForListString = "";
		if (!sqlFieldName.equalsIgnoreCase("id")) {
			if ("String".equalsIgnoreCase(type)) {
				listHeadersForListString += "if (StringUtils.isNotEmpty("
						+ lowerBeanName + ".get" + upperFieldName + "())) {"
						+ "sql.append( \"and t." + sqlFieldName + " like '%\"+"
						+ lowerBeanName + ".get" + upperFieldName + "()"
						+ "+\"%'\");" + "}";
			} else if ("int".equalsIgnoreCase(type)) {
				if (!"create_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
						&& !"create_user_name".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"update_user_id".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"update_user_name".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"create_time".equalsIgnoreCase(sqlFieldName
								.toLowerCase())
						&& !"update_time".equalsIgnoreCase(sqlFieldName
								.toLowerCase())) {
					listHeadersForListString += "	if (StringUtils.isNotEmpty(String.valueOf("
							+ lowerBeanName + ".get" + upperFieldName + "()))";
					listHeadersForListString += "&& (!\"all\".equalsIgnoreCase("
							+ lowerBeanName
							+ ".get"
							+ upperFieldName
							+ "()))){";
					listHeadersForListString += "		sql.append(\" and t."
							+ sqlFieldName + " = ? \");";
					listHeadersForListString += "	valuesList.add(Integer.valueOf("
							+ lowerBeanName + ".get" + upperFieldName + "()));";
					listHeadersForListString += "	valueTypesList.add(Types.INTEGER);";
					listHeadersForListString += "}";
				}

			} else {
				listHeadersForListString += "if (StringUtils.isNotEmpty("
						+ lowerBeanName + ".get" + upperFieldName + "())) {"
						+ "sql.append( \"and t." + sqlFieldName + " like '%\"+"
						+ lowerBeanName + ".get" + upperFieldName + "()"
						+ "+\"%'\");" + "}";

			}

		}
		return listHeadersForListString;
	}

	/**
	 * 生成列表页搜索表单
	 * 
	 * @author Soyi Yao
	 * @param sqlFieldName
	 * @param type
	 * @param count
	 *        第几个，决定表格的一行
	 * @return
	 */

	public static int cells = 0;

	private static String generateSearchFormFieldsString(String sqlFieldName,
			String lowerFieldName, String type, int count/* 第几个 */) {
		String searchFormFieldsString = "";
		if (!lowerFieldName.equalsIgnoreCase("id")
				&& !"create_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_id".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"update_user_name".equalsIgnoreCase(sqlFieldName
						.toLowerCase())
				&& !"create_time".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"update_time".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"content".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"description".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"remark".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"order_number".equalsIgnoreCase(sqlFieldName.toLowerCase())
				&& !"password".equalsIgnoreCase(sqlFieldName.toLowerCase())

		) {

			if (count % 2 == 1) {// 偶数加<tr>
				searchFormFieldsString += "<tr>";
			}
			if ("datetime".equalsIgnoreCase(type)) {
				cells++;
				searchFormFieldsString += "<td  class=\"fieldName\"  width=\"10%\">"
						+ lowerFieldName
						+ ":</td> "
						+ "<td  class=\"fieldForm\"  width=\"40%\">"
						+ " <input  name=\""
						+ lowerFieldName
						+ "\" "
						+ " id=\""
						+ lowerFieldName
						+ "\"  type=\"text\"  class=\"inputText\" "
						+ " value=\"${bean."
						+ lowerFieldName
						+ " }\" "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})\"  /> "
						+ "<img "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})\" "
						+ " src=\"../widget/My97DatePicker/skin/datePicker.gif\"  width=\"16\" "
						+ " height=\"22\"  align=\"absmiddle\"> " + "</td>";

			} else if ("date".equalsIgnoreCase(type)) {
				cells++;
				searchFormFieldsString += "<td  class=\"fieldName\"  width=\"10%\">"
						+ lowerFieldName
						+ ":</td> "
						+ "<td  class=\"fieldForm\"  width=\"40%\">"
						+ " <input  name=\""
						+ lowerFieldName
						+ "\" "
						+ " id=\""
						+ lowerFieldName
						+ "\"  type=\"text\"  class=\"inputText\" "
						+ " value=\"${bean."
						+ lowerFieldName
						+ " }\" "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd'})\"  /> "
						+ "<img "
						+ " onclick=\"WdatePicker({el:'"
						+ lowerFieldName
						+ "',isShowClear:false,dateFmt:'yyyy-MM-dd'})\" "
						+ " src=\"../widget/My97DatePicker/skin/datePicker.gif\"  width=\"16\" "
						+ " height=\"22\"  align=\"absmiddle\"> " + "</td>";

			} else {
				String referenceTableName = getReferenceTable(sqlFieldName);

				if (StringUtils.isNotEmpty(referenceTableName)) {
					String referenceUpperBeanName = getBeanUpperNameFromTableName(referenceTableName);
					String referenceLowerBeanName = getBeanLowerNameFromTableName(referenceTableName);

					searchFormFieldsString += "<td  class=\"fieldName\"  width=\"10%\">"
							+ lowerFieldName
							+ ":</td> "
							+ "<td  class=\"fieldForm\"  width=\"40%\">";

					searchFormFieldsString += "<select name=\""
							+ referenceLowerBeanName + "Id\" id=\""
							+ referenceLowerBeanName + "Id\">";
					searchFormFieldsString += "<option value=\"all\" selected=\"selected\">全部</option>";

					searchFormFieldsString += "<c:forEach var=\""
							+ referenceLowerBeanName + "List\" items=\"${"
							+ referenceLowerBeanName + "List }\">";
					searchFormFieldsString += "<option "
							+ "<c:if test=\"${bean." + lowerFieldName + " =="
							+ referenceLowerBeanName
							+ "List.id  }\"> selected=\"selected\"</c:if>"
							+ "value=\"${" + referenceLowerBeanName
							+ "List.id }\">${" + referenceLowerBeanName
							+ "List.name }</option>";
					searchFormFieldsString += "</c:forEach>";
					searchFormFieldsString += "</select>";

					searchFormFieldsString += " </td>";

				} else {
					cells++;
					searchFormFieldsString += "<td  class=\"fieldName\"  width=\"10%\">"
							+ lowerFieldName
							+ ":</td> "
							+ "<td  class=\"fieldForm\"  width=\"40%\">"
							+ "<input  type=\"text\" "
							+ "        name=\""
							+ lowerFieldName
							+ "\"  id=\""
							+ lowerFieldName
							+ "\"  value=\"${bean."
							+ lowerFieldName
							+ "  }\"  /></td>";
				}

			}

			if (count % 2 == 0) {// 偶数加</tr>
				searchFormFieldsString += "</tr>";
			}

		}
		return searchFormFieldsString;
	}

	/**
	 * 替换模板内容公共方法
	 * 
	 * @author Soyi Yao
	 * @param content
	 * @throws Exception
	 */
	public static String replaceContent(Map<String, String> replaceMap,
			String tableDescription, String content) throws Exception {

		String lowerBeanName = replaceMap.get("@lowerBeanName@");
		String upperBeanName = replaceMap.get("@upperBeanName@");
		String formFieldValidationRules = replaceMap
				.get("@formFieldValidationRules@");
		String formFieldsForAddJsp = replaceMap.get("@formFieldsForAddJsp@");
		String formFieldsForUpdateJsp = replaceMap
				.get("@formFieldsForUpdateJsp@");
		String listHeaders = replaceMap.get("@listHeaders@");
		String listRecords = replaceMap.get("@listRecords@");
		String fieldsForView = replaceMap.get("@fieldsForView@");
		String searchFormFields = replaceMap.get("@searchFormFields@");
		String searchCriteria = replaceMap.get("@searchCriteria@");
		String fieldsToInsert = replaceMap.get("@fieldsToInsert@");
		String fieldsToInsertValue = replaceMap.get("@fieldsToInsertValue@");
		String values = replaceMap.get("@values@");
		String valueTypes = replaceMap.get("@valueTypes@");
		String valuesForUpdate = replaceMap.get("@valuesForUpdate@");
		String valueTypesForUpdate = replaceMap.get("@valueTypesForUpdate@");
		String updateFields = replaceMap.get("@updateFields@");
		String tableName = replaceMap.get("@tableName@");
		String fckeditorJavaScriptReplace = replaceMap
				.get("@fckeditorJavaScriptReplace@");
		String fckeditorJavaScript = replaceMap.get("@fckeditorJavaScript@");
		String beanAllSettingMethod = replaceMap.get("@beanAllSettingMethod@");
		String referenceServiceForController = replaceMap
				.get("@referenceServiceForController@");
		String referenceListSetting = replaceMap.get("@referenceListSetting@");
		String referenceImport = replaceMap.get("@referenceImport@");
		String createUserInformationAdding = replaceMap
				.get("@createUserInformationAdding@");
		String userServiceForController = replaceMap
				.get("@userServiceForController@");
		String createUserInformationUpdating = replaceMap
				.get("@createUserInformationUpdating@");
		String newCellForContent = replaceMap.get("@newCellForContent@");
		String columnNamesArray = replaceMap.get("@columnNamesArray@");
		String formActionCriteria = replaceMap.get("@formActionCriteria@");
		String userService = replaceMap.get("@userService@");

		content = content.replace("@lowerBeanName@", lowerBeanName);
		content = content.replace("@upperBeanName@", upperBeanName);
		content = content.replace("@formFieldValidationRules@",
				formFieldValidationRules);
		content = content.replace("@formFieldsForAddJsp@", formFieldsForAddJsp);
		content = content.replace("@formFieldsForUpdateJsp@",
				formFieldsForUpdateJsp);
		content = content.replace("@tableDescription@", tableDescription);
		content = content.replace("@listHeaders@", listHeaders);
		content = content.replace("@listRecords@", listRecords);
		content = content.replace("@fieldsForView@", fieldsForView);
		content = content.replace("@searchFormFields@", searchFormFields);
		content = content.replace("@searchCriteria@", searchCriteria);
		content = content.replace("@fieldsToInsert@", fieldsToInsert);
		content = content.replace("@fieldsToInsertValue@", fieldsToInsertValue);
		content = content.replace("@values@", values);
		content = content.replace("@valueTypes@", valueTypes);
		content = content.replace("@valuesForUpdate@", valuesForUpdate);
		content = content.replace("@valueTypesForUpdate@", valueTypesForUpdate);
		content = content.replace("@updateFields@", updateFields);
		content = content.replace("@tableName@", tableName);
		content = content.replace("@fckeditorJavaScriptReplace@",
				fckeditorJavaScriptReplace);
		content = content.replace("@fckeditorJavaScript@", fckeditorJavaScript);
		content = content.replace("@beanAllSettingMethod@",
				beanAllSettingMethod);
		content = content.replace("@referenceServiceForController@",
				referenceServiceForController);
		content = content.replace("@referenceListSetting@",
				referenceListSetting);
		content = content.replace("@referenceImport@", referenceImport);
		content = content.replace("@createUserInformationAdding@",
				createUserInformationAdding);
		content = content.replace("@userServiceForController@",
				userServiceForController);
		content = content.replace("@createUserInformationUpdating@",
				createUserInformationUpdating);
		content = content.replace("@newCellForContent@", newCellForContent);
		content = content.replace("@columnNamesArray@", columnNamesArray);
		content = content.replace("@formActionCriteria@", formActionCriteria);
		content = content.replace("@userService@", userService);

		return content;

	}

	/**
	 * @author Soyi Yao
	 * @param lowerBeanName
	 * @param upperBeanName
	 * @param tableDescription
	 * @return
	 */
	public static String generateMenuString(String lowerBeanName,
			String upperBeanName, String tableDescription) {
		String menuString = "{";
		menuString += " url : \"admin/" + lowerBeanName + ".do?action=list"
				+ upperBeanName + "\", ";
		menuString += " text : \"" + tableDescription + "管理\" ";
		menuString += "  } ,";

		return menuString;
	}
}
