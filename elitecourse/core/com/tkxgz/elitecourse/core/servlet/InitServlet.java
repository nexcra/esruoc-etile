package com.tkxgz.elitecourse.core.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.tkxgz.elitecourse.util.ApplicationUtils;

/**
 * @author Soyi Yao
 */
public class InitServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute(ApplicationUtils.TEMPLATE_NAME,
				"default");
	}
}
