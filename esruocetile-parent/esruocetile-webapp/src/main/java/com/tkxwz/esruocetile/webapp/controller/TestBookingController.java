package com.tkxwz.esruocetile.webapp.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.BeanUtil;
import com.tkxwz.esruocetile.core.util.PageUtil;
import com.tkxwz.esruocetile.webapp.entity.TestBooking;
import com.tkxwz.esruocetile.webapp.service.TestBookingService;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:03:29
 */
@Controller("/testBooking.do")
public class TestBookingController {

	@Autowired
	private TestBookingService testBookingService;

	@RequestMapping(params = "action=listTestBooking")
	public String listTestBooking(HttpServletRequest request,
			String currentPageNum) {
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.testBookingService.listTestBooking(page);
		request.setAttribute("page", page);
		return "/testBooking/listTestBooking.jsp";
	}
	@RequestMapping(params = "action=listTestBookingForStudent")
	public String listTestBookingForStudent(HttpServletRequest request,
			String currentPageNum) {
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum),1000);
		this.testBookingService.listTestBookingForStudent(page);
		request.setAttribute("page", page);
		return "/front/testBooking/listTestBookingForStudent.jsp";
	}

	/**
	 * to add a TestBooking
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:41:27
	 * @return the page to add TestBooking
	 */

	@RequestMapping(params = "action=toAddTestBooking")
	public String toAddTestBooking() {
		return "/testBooking/addTestBooking.jsp";
	}

	/**
	 * add a TestBooking and then forward to the list page
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:48:53
	 * @return the page to list the TestBookings
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(params = "action=addTestBooking")
	public String addTestBooking(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {
		TestBooking testBooking = new TestBooking();
		BeanUtil.populate(testBooking, request.getParameterMap());
		int result = this.testBookingService.addTestBooking(testBooking);
		return "redirect:testBooking.do?action=listTestBooking";
	}

	@RequestMapping(params = "action=deleteTestBooking")
	public String deleteTestBooking(HttpServletRequest request,
			HttpServletResponse response, String id) throws IOException {
		String[] ids = id.split(",");
		int result = this.testBookingService.batchDeleteTestBooking(ids);
		response.getWriter().write(" 成功删除 " + result + "条记录");
		return null;
	}

	@RequestMapping(params = "action=toUpdateTestBooking")
	public String toUpdateTestBooking(HttpServletRequest request, String id) {
		Map map = this.testBookingService.getTestBookingById(id);
		request.setAttribute("map", map);
		return "/testBooking/updateTestBooking.jsp";
	}

	@RequestMapping(params = "action=updateTestBooking")
	public String updateTestBooking(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {
		TestBooking testBooking = new TestBooking();
		BeanUtil.populate(testBooking, request.getParameterMap());

		int result = this.testBookingService.updateTestBooking(testBooking);
		return "redirect:testBooking.do?action=listTestBooking";
	}

	@RequestMapping(params = "action=viewTestBooking")
	public String viewTestBooking(HttpServletRequest request, String id) {
		Map map = this.testBookingService.getTestBookingById(id);
		request.setAttribute("map", map);
		return "/testBooking/viewTestBooking.jsp";
	}
	
	@RequestMapping(params = "action=viewTestBookingForStudent")
	public String viewTestBookingForStudent(HttpServletRequest request, String id) {
		Map map = this.testBookingService.getTestBookingById(id);
		request.setAttribute("map", map);
		return "/front/testBooking/viewTestBookingForStudent.jsp";
	}

}
