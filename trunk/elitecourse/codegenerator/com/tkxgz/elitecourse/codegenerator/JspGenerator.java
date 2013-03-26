package com.tkxgz.elitecourse.codegenerator;

import java.util.List;
import java.util.Map;

/**
 * 生成jsp页面
 * 
 * @author Soyi Yao
 */
public class JspGenerator {

	public void generateListJsp(String tableName, String tableDescription,
			boolean replaceable) throws Exception {
		String content = CodeGeneratorUtils
				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
						+ CodeGeneratorConstants.LIST_JSP_FILE);

		Map<String, String> replaceMap = CodeGeneratorUtils
				.getReplaceMap(tableName);
		String lowerBeanName = replaceMap.get("@lowerBeanName@");
		String upperBeanName = replaceMap.get("@upperBeanName@");

		content = CodeGeneratorUtils.replaceContent(replaceMap,
				tableDescription, content);
		String jspFileName = "list" + upperBeanName + ".jsp";
		String filePath = CodeGeneratorConstants.JSP_FILE_ROOT_FOLDER + "\\"
				+ lowerBeanName + "\\" + jspFileName;

		CodeGeneratorUtils.createFile(filePath, content, replaceable);

		System.out.println("---------生成" + jspFileName + "成功----------");
	}

	public void generateAddJsp(String tableName, String tableDescription,
			boolean replaceable) throws Exception {
		String content = CodeGeneratorUtils
				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
						+ CodeGeneratorConstants.ADD_JSP_FILE);

		Map<String, String> replaceMap = CodeGeneratorUtils
				.getReplaceMap(tableName);
		String lowerBeanName = replaceMap.get("@lowerBeanName@");
		String upperBeanName = replaceMap.get("@upperBeanName@");

		content = CodeGeneratorUtils.replaceContent(replaceMap,
				tableDescription, content);

		String jspFileName = "add" + upperBeanName + ".jsp";
		String filePath = CodeGeneratorConstants.JSP_FILE_ROOT_FOLDER + "\\"
				+ lowerBeanName + "\\" + jspFileName;

		CodeGeneratorUtils.createFile(filePath, content, replaceable);

		System.out.println("---------生成" + jspFileName + "成功----------");
	}

	public void generateUpdateJsp(String tableName, String tableDescription,
			boolean replaceable) throws Exception {
		String content = CodeGeneratorUtils
				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
						+ CodeGeneratorConstants.UPDATE_JSP_FILE);

		Map<String, String> replaceMap = CodeGeneratorUtils
				.getReplaceMap(tableName);

		String lowerBeanName = replaceMap.get("@lowerBeanName@");
		String upperBeanName = replaceMap.get("@upperBeanName@");

		content = CodeGeneratorUtils.replaceContent(replaceMap,
				tableDescription, content);

		String jspFileName = "update" + upperBeanName + ".jsp";
		String filePath = CodeGeneratorConstants.JSP_FILE_ROOT_FOLDER + "\\"
				+ lowerBeanName + "\\" + jspFileName;

		CodeGeneratorUtils.createFile(filePath, content, replaceable);

		System.out.println("---------生成" + jspFileName + "成功----------");
	}

	public void generateViewJsp(String tableName, String tableDescription,
			boolean replaceable) throws Exception {
		String content = CodeGeneratorUtils
				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
						+ CodeGeneratorConstants.VIEW_JSP_FILE);

		Map<String, String> replaceMap = CodeGeneratorUtils
				.getReplaceMap(tableName);
		String lowerBeanName = replaceMap.get("@lowerBeanName@");
		String upperBeanName = replaceMap.get("@upperBeanName@");

		content = CodeGeneratorUtils.replaceContent(replaceMap,
				tableDescription, content);

		String jspFileName = "view" + upperBeanName + ".jsp";
		String filePath = CodeGeneratorConstants.JSP_FILE_ROOT_FOLDER + "\\"
				+ lowerBeanName + "\\" + jspFileName;

		CodeGeneratorUtils.createFile(filePath, content, replaceable);

		System.out.println("---------生成" + jspFileName + "成功----------");
	}

	public static void generateMenuJsp(List<String> list, boolean replaceable)
			throws Exception {
		String content = CodeGeneratorUtils
				.readSource(CodeGeneratorConstants.TEMPLATE_ROOT_FOLDER
						+ CodeGeneratorConstants.MENU_JSP_FILE);
		String tableName = "";
		String tableDescription = "";
		String menuString = "";
		for (String table : list) {
			String str[] = table.split("@");
			tableName = str[0];
			tableDescription = str[1];
			Map<String, String> replaceMap = CodeGeneratorUtils
					.getReplaceMap(tableName);
			String lowerBeanName = replaceMap.get("@lowerBeanName@");
			String upperBeanName = replaceMap.get("@upperBeanName@");

			menuString += CodeGeneratorUtils.generateMenuString(lowerBeanName,
					upperBeanName, tableDescription);
		}

		// 去掉menu语句中最后一个多余的逗号
		if (menuString.length() > 0) {
			menuString = menuString.substring(0, menuString.lastIndexOf(",")); // 去掉后面的,
		}

		content = content.replace("@menus@", menuString);

		String jspFileName = "main.jsp";
		String filePath = CodeGeneratorConstants.MENU_FILE_ROOT_FOLDER + "\\"
				+ jspFileName;

		CodeGeneratorUtils.createFile(filePath, content, replaceable);

		System.out.println("---------生成" + jspFileName + "成功----------");
	}

	public static void generateCRUDPage(String tableName,
			String tableDescription, boolean replaceable) throws Exception {
		JspGenerator jspGenerator = new JspGenerator();

		jspGenerator.generateAddJsp(tableName, tableDescription, replaceable);
		jspGenerator
				.generateUpdateJsp(tableName, tableDescription, replaceable);
		jspGenerator.generateViewJsp(tableName, tableDescription, replaceable);
		jspGenerator.generateListJsp(tableName, tableDescription, replaceable);
	}
	
	public static void main(String[] args) throws Exception {
		JspGenerator jspGenerator = new JspGenerator();

		jspGenerator.generateCRUDPage("t_user", "用户", true);
	}

}
