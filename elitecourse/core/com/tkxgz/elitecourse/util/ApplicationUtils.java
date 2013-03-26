package com.tkxgz.elitecourse.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Soyi Yao
 */
public class ApplicationUtils {

	public static String TEMPLATE_NAME = "templateName";

	/**
	 * 得到当前设置的模板名
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 */
	public static String getCurrentTemplate(HttpServletRequest request) {
		String templateName = (String) request.getSession().getServletContext()
				.getAttribute(TEMPLATE_NAME);

		return templateName;

	}

	/**
	 * 设置使用的模板
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param templateName
	 */
	public static void setCurrentTemplate(HttpServletRequest request,
			String templateName) {

		request.getSession().getServletContext()
				.setAttribute(TEMPLATE_NAME, templateName);
	}
}
