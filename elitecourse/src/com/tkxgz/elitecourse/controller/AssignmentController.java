package com.tkxgz.elitecourse.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import com.tkxgz.elitecourse.core.constant.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tkxgz.elitecourse.bean.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxgz.elitecourse.bean.Assignment;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.AssignmentService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.bean.AssignmentClass;
import com.tkxgz.elitecourse.service.AssignmentClassService;
import com.tkxgz.elitecourse.bean.Classes;
import com.tkxgz.elitecourse.service.ClassesService;

/**
 * 作业Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/assignment.do")
public class AssignmentController {

	private static final String MODULE_NAME = "作业管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private AssignmentClassService assignmentClassService;

	@Autowired
	private ClassesService classesService;

	/**
	 * 默认action
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping
	public String defaultAction(HttpServletRequest request,
			String currentPageNum) {

		return this.listAssignment(request, currentPageNum);
	}

	/**
	 * 查询作业列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listAssignment")
	public String listAssignment(HttpServletRequest request,
			String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.assignmentService.listAssignment(page);

		request.setAttribute("page", page);

		List<AssignmentClass> assignmentClassList = this.assignmentClassService
				.listAllAssignmentClass();
		request.setAttribute("assignmentClassList", assignmentClassList);
		List<Classes> classesList = this.classesService.listAllClasses();
		request.setAttribute("classesList", classesList);

		return "/admin/assignment/listAssignment.jsp";
	}

	/**
	 * 转到新增作业页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddAssignment")
	public String toAddAssignment(HttpServletRequest request) {

		List<AssignmentClass> assignmentClassList = this.assignmentClassService
				.listAllAssignmentClass();
		request.setAttribute("assignmentClassList", assignmentClassList);
		List<Classes> classesList = this.classesService.listAllClasses();
		request.setAttribute("classesList", classesList);

		return "/admin/assignment/addAssignment.jsp";
	}

	/**
	 * 新增作业
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addAssignment")
	public String addAssignment(HttpServletRequest request) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/assignment.do?action=listAssignment";

		// 获取表单参数
		Assignment assignment = new Assignment();
		BeanUtil.populate(assignment, request.getParameterMap());

		int affectedRows = this.assignmentService.addAssignment(assignment);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddAssignment(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加作业");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 删除作业
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteAssignment")
	public String deleteAssignment(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.assignmentService.batchDeleteAssignment(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除作业");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改作业页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateAssignment")
	public String toUpdateAssignment(HttpServletRequest request, String id) {

		Map map = this.assignmentService.getAssignmentById(id);

		request.setAttribute("map", map);

		List<AssignmentClass> assignmentClassList = this.assignmentClassService
				.listAllAssignmentClass();
		request.setAttribute("assignmentClassList", assignmentClassList);
		List<Classes> classesList = this.classesService.listAllClasses();
		request.setAttribute("classesList", classesList);

		return "/admin/assignment/updateAssignment.jsp";
	}

	/**
	 * 修改作业
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @param name
	 * @param password
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(params = "action=updateAssignment")
	public String updateAssignment(HttpServletRequest request, String id)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/assignment.do?action=listAssignment";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Assignment assignment = this.assignmentService.findAssignmentById(id);
		BeanUtil.populate(assignment, request.getParameterMap());

		int affectedRows = this.assignmentService.updateAssignment(assignment);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddAssignment(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改作业");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看作业
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewAssignment")
	public String viewAssignment(HttpServletRequest request, String id) {

		Map map = this.assignmentService.getAssignmentById(id);

		request.setAttribute("map", map);

		return "/admin/assignment/viewAssignment.jsp";
	}

	/**
	 * 分页搜索作业列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchAssignment")
	public String searchAssignment(HttpServletRequest request,
			String currentPageNum) throws IllegalAccessException,
			InvocationTargetException {

		Assignment assignment = new Assignment();
		BeanUtil.populate(assignment, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.assignmentService.searchAssignment(page, assignment);

		List<AssignmentClass> assignmentClassList = this.assignmentClassService
				.listAllAssignmentClass();
		request.setAttribute("assignmentClassList", assignmentClassList);
		List<Classes> classesList = this.classesService.listAllClasses();
		request.setAttribute("classesList", classesList);

		request.setAttribute("page", page);
		request.setAttribute("bean", assignment);

		return "/admin/assignment/listAssignment.jsp";
	}

	/**
	 * 导出作业数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportAssignmentList")
	public String exportAssignmentList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要作业的作业列表
		Assignment assignment = new Assignment();
		BeanUtil.populate(assignment, request.getParameterMap());
		List<Assignment> excelContent = this.assignmentService
				.searchAssignmentForList(assignment);

		String[] columnNames = new String[] { "UserId", "UserName",
				"AssignmentClassId", "SubmitTime", "ClassesId", "Description",
				"Content", "Path", "Grade", "ReContent", "ReTime" };

		String titleName = "assignmentList.xls";// 不创建说明;
		String sheetName = "作业列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.assignmentService.exportAssignmentList(request, excelContent,
				columnNames, sheetName, outputStream);

		return null;
	}

}
