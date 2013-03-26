package com.tkxgz.elitecourse.core.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.beanutils.ConvertUtils;

import com.tkxgz.elitecourse.core.converter.SqlTimestampConverter;

/**
 * Servlet implementation class ConverterRegisterServlet
 */
/**
 * 转换java.sql.Timestamp
 * 
 * @author Soyi Yao
 */
public class ConverterRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		ConvertUtils.register(new SqlTimestampConverter(null),
				java.sql.Timestamp.class);
	}
}
