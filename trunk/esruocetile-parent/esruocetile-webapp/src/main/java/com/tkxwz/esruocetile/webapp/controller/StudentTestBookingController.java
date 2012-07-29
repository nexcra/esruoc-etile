package com.tkxwz.esruocetile.webapp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.BeanUtil;
import com.tkxwz.esruocetile.core.util.ExcelUtil;
import com.tkxwz.esruocetile.core.util.PageUtil;
import com.tkxwz.esruocetile.webapp.entity.StudentTestBooking;
import com.tkxwz.esruocetile.webapp.service.StudentTestBookingService;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:03:29
 */
@Controller("/studentTestBooking.do")
public class StudentTestBookingController {

	@Autowired
	private StudentTestBookingService studentTestBookingService;

	@RequestMapping(params = "action=listStudentTestBooking")
	public String listStudentTestBooking(HttpServletRequest request,
			String currentPageNum) {
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.studentTestBookingService.listStudentTestBooking(page);
		request.setAttribute("page", page);
		return "/studentTestBooking/listStudentTestBooking.jsp";
	}

	@RequestMapping(params = "action=deleteStudentTestBooking")
	public String deleteStudentTestBooking(HttpServletRequest request,
			HttpServletResponse response, String id) throws IOException {
		String[] ids = id.split(",");
		int result = this.studentTestBookingService
				.batchDeleteStudentTestBooking(ids);
		response.getWriter().write(" 成功删除 " + result + "条记录");
		return null;
	}

	@RequestMapping(params = "action=viewStudentTestBooking")
	public String viewStudentTestBooking(HttpServletRequest request, String id) {
		Map map = this.studentTestBookingService.getStudentTestBookingById(id);
		request.setAttribute("map", map);
		return "/studentTestBooking/viewStudentTestBooking.jsp";
	}

	@RequestMapping(params = "action=searchStudentTestBooking")
	public String searchStudentTestBooking(HttpServletRequest request,
			String currentPageNum) throws IllegalAccessException,
			InvocationTargetException {
		StudentTestBooking studentTestBooking = new StudentTestBooking();
		BeanUtil.populate(studentTestBooking, request.getParameterMap());
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.studentTestBookingService.searchStudentTestBooking(page,
				studentTestBooking);
		request.setAttribute("page", page);
		request.setAttribute("bean", studentTestBooking);
		return "/studentTestBooking/listStudentTestBooking.jsp";
	}

	@RequestMapping(params = "action=exportStudentTestBooking")
	public String exportStudentTestBooking(HttpServletRequest request,
			HttpServletResponse response, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException,
			IOException {
		StudentTestBooking studentTestBooking = new StudentTestBooking();
		BeanUtil.populate(studentTestBooking, request.getParameterMap());
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		List<Map<String, Object>> excelContent = this.studentTestBookingService
				.searchStudentTestBooking(studentTestBooking);
		request.setAttribute("page", page);
		request.setAttribute("bean", studentTestBooking);
		String[] columnNames = new String[] { "考试校区", "考试名称", "学院", "年级", "学号",
				"姓名", "性别", "民族代码", "民族", "出生日期", "身份证号", "专业名称", "行政班" };

		String titleName = "result.xls";//不创建说明;
		String sheetName = "考生预约考试结果";

		OutputStream fOut = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes("GB2312"), "ISO8859-1"));

		ExcelUtil.create(request, excelContent, columnNames, titleName,sheetName, fOut);

		return "/studentTestBooking/listStudentTestBooking.jsp";
	}
}
