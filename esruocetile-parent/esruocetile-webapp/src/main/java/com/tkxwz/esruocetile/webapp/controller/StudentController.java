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
import com.tkxwz.esruocetile.webapp.entity.Student;
import com.tkxwz.esruocetile.webapp.service.StudentService;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:03:29
 */
@Controller("/student.do")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(params = "action=listStudent")
	public String listStudent(HttpServletRequest request, String currentPageNum) {
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.studentService.listStudent(page);
		request.setAttribute("page", page);
		return "/student/listStudent.jsp";
	}

	/**
	 * to add a Student
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:41:27
	 * @return the page to add Student
	 */

	@RequestMapping(params = "action=toAddStudent")
	public String toAddStudent() {
		return "/student/addStudent.jsp";
	}

	/**
	 * add a Student and then forward to the list page
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:48:53
	 * @return the page to list the Students
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(params = "action=addStudent")
	public String addStudent(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {
		Student student = new Student();
		BeanUtil.populate(student, request.getParameterMap());
		int result = this.studentService.addStudent(student);
		return "redirect:student.do?action=listStudent";
	}

	@RequestMapping(params = "action=deleteStudent")
	public String deleteStudent(HttpServletRequest request,
			HttpServletResponse response, String id) throws IOException {
		String[] ids = id.split(",");
		int result = this.studentService.batchDeleteStudent(ids);
		response.getWriter().write(" 成功删除 " + result + "条记录");
		return null;
	}

	@RequestMapping(params = "action=toUpdateStudent")
	public String toUpdateStudent(HttpServletRequest request, String id) {
		Map map = this.studentService.getStudentById(id);
		request.setAttribute("map", map);
		return "/student/updateStudent.jsp";
	}

	@RequestMapping(params = "action=updateStudent")
	public String updateStudent(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {
		Student student = new Student();
		BeanUtil.populate(student, request.getParameterMap());

		int result = this.studentService.updateStudent(student);
		return "redirect:student.do?action=listStudent";
	}

	@RequestMapping(params = "action=viewStudent")
	public String viewStudent(HttpServletRequest request, String id) {
		Map map = this.studentService.getStudentById(id);
		request.setAttribute("map", map);
		return "/student/viewStudent.jsp";
	}

	@RequestMapping(params = "action=searchStudent")
	public String searchStudent(HttpServletRequest request,
			String currentPageNum) throws IllegalAccessException,
			InvocationTargetException {
		Student student = new Student();
		BeanUtil.populate(student, request.getParameterMap());
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.studentService.searchStudent(page, student);
		request.setAttribute("page", page);
		return "/student/listStudent.jsp";
	}

}
