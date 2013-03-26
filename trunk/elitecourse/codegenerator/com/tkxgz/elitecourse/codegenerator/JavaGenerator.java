package com.tkxgz.elitecourse.codegenerator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 生成jsp页面
 * 
 * @author Soyi Yao
 */
public class JavaGenerator {

	private void generateBean(String tableName, String tableDescription,
			boolean replaceable) throws Exception {
		String packageName = "com.tkxgz.elitecourse.bean";

		Map<String, String> replaceMap = CodeGeneratorUtils
				.getReplaceMap(tableName);
		List<HashMap<String, String>> columns = CodeGeneratorUtils
				.getTableColumns(tableName);

		String upperBeanName = replaceMap.get("@upperBeanName@");

		StringBuffer content = new StringBuffer();
		content.append("package " + packageName + ";\n\n");

		content.append("import  java.sql.Timestamp ;" + "\n");
		content.append("import java.io.Serializable;" + "\n");

		content.append("/**\n");
		content.append("* " + upperBeanName + "类\n");
		content.append("* \n");
		content.append("* @author Soyi Yao\n");
		content.append("*/\n");
		content.append("public class " + upperBeanName
				+ " implements Serializable  {" + "\n\n");

		content.append("private static final long serialVersionUID = 1L;");

		// 生成类中的属性
		String lowerFieldName = "";
		String upperFieldName = "";

		for (HashMap<String, String> map : columns) {
			for (Iterator<Map.Entry<String, String>> it = map.entrySet()
					.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String typeString = entry.getKey();

				String columnJavaTypeName = typeString.split("@")[0];
				String columnSQLTypeName = typeString.split("@")[1];

				lowerFieldName = CodeGeneratorUtils
						.getFieldLowerNameFromTableName(entry.getValue());
				upperFieldName = CodeGeneratorUtils
						.getFieldUpperNameFromTableName(entry.getValue());

				content.append("\tprivate ");
				content.append(columnJavaTypeName + " " + lowerFieldName);
				content.append(";");
				content.append("\n");
				content.append("\n");
			}
		}

		// 生成类中的set get方法
		for (HashMap<String, String> map : columns) {
			for (Iterator<Map.Entry<String, String>> it = map.entrySet()
					.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				String typeString = entry.getKey();

				String columnJavaTypeName = typeString.split("@")[0];
				String columnSQLTypeName = typeString.split("@")[1];

				lowerFieldName = CodeGeneratorUtils
						.getFieldLowerNameFromTableName(entry.getValue());
				upperFieldName = CodeGeneratorUtils
						.getFieldUpperNameFromTableName(entry.getValue());

				content.append("\n");
				// get方法
				content.append("\tpublic " + columnJavaTypeName + " get"
						+ upperFieldName + "(){\n");
				content.append("\t\treturn " + lowerFieldName + ";\n");
				content.append("\t}\n");

				content.append("\n");

				// set
				content.append("\tpublic void set" + upperFieldName + "("
						+ columnJavaTypeName + " " + lowerFieldName + "){\n");
				content.append("\t\tthis." + lowerFieldName + " = "
						+ lowerFieldName + ";\n");
				content.append("\t}\n");
			}

		}
/*
		// 生成 hashCode()方法

		content.append(" @Override \n");
		content.append("         public  int  hashCode()  { \n");
		content.append("                 final  int  prime  =  31; \n");
		content.append("                 int  result  =  1; \n");
		for (HashMap<String, String> map : columns) {
			for (Iterator<Map.Entry<String, String>> it = map.entrySet()
					.iterator(); it.hasNext();) {

				Map.Entry<String, String> entry = it.next();
				String typeString = entry.getKey();

				String columnJavaTypeName = typeString.split("@")[0];
				String columnSQLTypeName = typeString.split("@")[1];

				lowerFieldName = CodeGeneratorUtils
						.getFieldLowerNameFromTableName(entry.getValue());
				upperFieldName = CodeGeneratorUtils
						.getFieldUpperNameFromTableName(entry.getValue());

				content.append("                 result  =  prime  *  result \n");
				content.append(" +  ((" + lowerFieldName
						+ "  ==  null)  ?  0  :  " + lowerFieldName
						+ ".hashCode()); \n");

			}

		}
		content.append(" return result;\n");
		content.append(" }\n");

		// 生成equals方法

		content.append(" @Override \n");
		content.append("         public  boolean  equals(Object  obj)  { \n");
		content.append("                 if  (this  ==  obj) ");
		content.append("                         return  true; ");
		content.append("                 if  (obj  ==  null) ");
		content.append("                         return  false; ");
		content.append("                 if  (getClass()  !=  obj.getClass()) ");
		content.append("                         return  false; ");
		content.append(" " + upperBeanName + "  other  =  (" + upperBeanName
				+ ")  obj; ");
		for (HashMap<String, String> map : columns) {
			for (Iterator<Map.Entry<String, String>> it = map.entrySet()
					.iterator(); it.hasNext();) {

				Map.Entry<String, String> entry = it.next();
				String typeString = entry.getKey();

				String columnJavaTypeName = typeString.split("@")[0];
				String columnSQLTypeName = typeString.split("@")[1];

				lowerFieldName = CodeGeneratorUtils
						.getFieldLowerNameFromTableName(entry.getValue());
				upperFieldName = CodeGeneratorUtils
						.getFieldUpperNameFromTableName(entry.getValue());

				content.append("                 if  (" + lowerFieldName
						+ "  ==  null)  { ");
				content.append("                         if  (other."
						+ lowerFieldName + "   !=  null) ");
				content.append("                                 return  false; ");
				content.append("                 }  else  if  (!"
						+ lowerFieldName + " .equals(other." + lowerFieldName
						+ " )) ");
				content.append("                         return  false; ");

			}

		}
		content.append("return true;");
		content.append("\n}");

		// 生成toString()方法

		content.append(" @Override \n");
		content.append("         public  String  toString()  { \n");
		content.append(" return \"" + upperBeanName + " [");
		int temp = 0;
		for (HashMap<String, String> map : columns) {
			if (temp > 0) {

				content.append("+\", ");
			}
			for (Iterator<Map.Entry<String, String>> it = map.entrySet()
					.iterator(); it.hasNext();) {
				temp++;

				Map.Entry<String, String> entry = it.next();
				String typeString = entry.getKey();

				String columnJavaTypeName = typeString.split("@")[0];
				String columnSQLTypeName = typeString.split("@")[1];

				lowerFieldName = CodeGeneratorUtils
						.getFieldLowerNameFromTableName(entry.getValue());
				upperFieldName = CodeGeneratorUtils
						.getFieldUpperNameFromTableName(entry.getValue());

				content.append("" + lowerFieldName + "=\"+" + lowerFieldName);
			}

		}

		content.append(" +\"]\";");

		content.append(" }\n");*/

		// 类结束
		content.append("\n}");
		String fileName = upperBeanName + ".java";
		String filePath = CodeGeneratorConstants.JAVA_FILE_ROOT_FOLDER + "\\"
				+ "bean" + "\\" + fileName;
		CodeGeneratorUtils
				.createFile(filePath, content.toString(), replaceable);

		System.out.println("---------生成" + fileName + "成功----------");
	}

	public void generateMapper(String tableName, String tableDescription,
			boolean replaceable) throws Exception {
		String content = CodeGeneratorUtils
				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
						+ CodeGeneratorConstants.MAPPER_FILE);

		Map<String, String> replaceMap = CodeGeneratorUtils
				.getReplaceMap(tableName);
		String upperBeanName = replaceMap.get("@upperBeanName@");

		content = CodeGeneratorUtils.replaceContent(replaceMap,
				tableDescription, content);
		String fileName = upperBeanName + "Mapper.java";
		String filePath = CodeGeneratorConstants.JAVA_FILE_ROOT_FOLDER + "\\"
				+ "mapper" + "\\" + fileName;

		CodeGeneratorUtils.createFile(filePath, content, replaceable);

		System.out.println("---------生成" + fileName + "成功----------");
	}

	private void generateBaseDao(String tableName, String tableDescription,
			boolean replaceable) throws Exception {
		String content = CodeGeneratorUtils
				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
						+ CodeGeneratorConstants.BASE_DAO_FILE);

		Map<String, String> replaceMap = CodeGeneratorUtils
				.getReplaceMap(tableName);
		String upperBeanName = replaceMap.get("@upperBeanName@");

		content = CodeGeneratorUtils.replaceContent(replaceMap,
				tableDescription, content);
		String fileName = upperBeanName + "BaseDao.java";
		String filePath = CodeGeneratorConstants.JAVA_FILE_ROOT_FOLDER + "\\"
				+ "dao" + "\\" + "base" + "\\" + fileName;

		CodeGeneratorUtils.createFile(filePath, content, replaceable);

		System.out.println("---------生成" + fileName + "成功----------");

	}

	public void generateDao(String tableName, String tableDescription,
			boolean replaceable) throws Exception {
		String content = CodeGeneratorUtils
				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
						+ CodeGeneratorConstants.DAO_FILE);

		Map<String, String> replaceMap = CodeGeneratorUtils
				.getReplaceMap(tableName);
		String upperBeanName = replaceMap.get("@upperBeanName@");

		content = CodeGeneratorUtils.replaceContent(replaceMap,
				tableDescription, content);
		String fileName = upperBeanName + "Dao.java";
		String filePath = CodeGeneratorConstants.JAVA_FILE_ROOT_FOLDER + "\\"
				+ "dao" + "\\" + fileName;

		CodeGeneratorUtils.createFile(filePath, content, true); // 不生成

		System.out.println("---------生成" + fileName + "成功----------");

		generateBaseDao(tableName, tableDescription, replaceable);
	}

	public void generateService(String tableName, String tableDescription,
			boolean replaceable) throws Exception {
		String content = CodeGeneratorUtils
				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
						+ CodeGeneratorConstants.SERVICE_FILE);

		Map<String, String> replaceMap = CodeGeneratorUtils
				.getReplaceMap(tableName);
		String upperBeanName = replaceMap.get("@upperBeanName@");

		content = CodeGeneratorUtils.replaceContent(replaceMap,
				tableDescription, content);
		String fileName = upperBeanName + "Service.java";
		String filePath = CodeGeneratorConstants.JAVA_FILE_ROOT_FOLDER + "\\"
				+ "service" + "\\" + fileName;

		CodeGeneratorUtils.createFile(filePath, content, replaceable);

		System.out.println("---------生成" + fileName + "成功----------");
	}

	public void generateController(String tableName, String tableDescription,
			boolean replaceable) throws Exception {
		String content = CodeGeneratorUtils
				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
						+ CodeGeneratorConstants.CONTROLLER_FILE);

		Map<String, String> replaceMap = CodeGeneratorUtils
				.getReplaceMap(tableName);
		String upperBeanName = replaceMap.get("@upperBeanName@");

		content = CodeGeneratorUtils.replaceContent(replaceMap,
				tableDescription, content);
		String fileName = upperBeanName + "Controller.java";
		String filePath = CodeGeneratorConstants.JAVA_FILE_ROOT_FOLDER + "\\"
				+ "controller" + "\\" + fileName;

		CodeGeneratorUtils.createFile(filePath, content, replaceable);

		System.out.println("---------生成" + fileName + "成功----------");
	}

	public static void generateAllJava(String tableName,
			String tableDescription, boolean replaceable) throws Exception {
		JavaGenerator javaGenerator = new JavaGenerator();
		javaGenerator.generateBean(tableName, tableDescription, replaceable);
		javaGenerator.generateMapper(tableName, tableDescription, replaceable);
		javaGenerator.generateDao(tableName, tableDescription, replaceable);
		javaGenerator.generateService(tableName, tableDescription, replaceable);
		javaGenerator.generateController(tableName, tableDescription,
				replaceable);
	}

	public static void main(String[] args) throws Exception {
		JavaGenerator javaGenerator = new JavaGenerator();
		javaGenerator.generateAllJava("t_column", "栏目", true);
		// javaGenerator.generateController("t_article", "etyn", true);
	}
}
