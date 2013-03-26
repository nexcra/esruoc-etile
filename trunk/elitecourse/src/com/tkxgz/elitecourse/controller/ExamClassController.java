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

import com.tkxgz.elitecourse.bean.ExamClass;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.ExamClassService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;



/**
 * 考试分类Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/examClass.do")
public class ExamClassController {

	private static final String MODULE_NAME = "考试分类管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private ExamClassService examClassService;
	
	@Autowired
private UserService userService;


	

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

		return this.listExamClass(request, currentPageNum);
	}

	/**
	 * 查询考试分类列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listExamClass")
	public String listExamClass(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.examClassService.listExamClass(page);

		request.setAttribute("page", page);
		
		

		return "/admin/examClass/listExamClass.jsp";
	}

	/**
	 * 转到新增考试分类页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddExamClass")
	public String toAddExamClass(HttpServletRequest request) {
		
		
		
		return "/admin/examClass/addExamClass.jsp";
	}

	/**
	 * 新增考试分类
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addExamClass")
	public String addExamClass(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/examClass.do?action=listExamClass";

		// 获取表单参数
		ExamClass examClass = new ExamClass();
		BeanUtil.populate(examClass, request.getParameterMap());
		
		
		// 设置操作管理员
examClass.setCreateUserId(admin.getId());examClass.setCreateUserName(admin.getName());

		int affectedRows = this.examClassService.addExamClass(examClass);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddExamClass(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加考试分类");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除考试分类
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteExamClass")
	public String deleteExamClass(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.examClassService.batchDeleteExamClass(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除考试分类");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改考试分类页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateExamClass")
	public String toUpdateExamClass(HttpServletRequest request, String id) {

		Map map = this.examClassService.getExamClassById(id);

		request.setAttribute("map", map);
		
		

		return "/admin/examClass/updateExamClass.jsp";
	}

	/**
	 * 修改考试分类
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
	@RequestMapping(params = "action=updateExamClass")
	public String updateExamClass(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/examClass.do?action=listExamClass";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		ExamClass examClass = this.examClassService.findExamClassById(id);
		BeanUtil.populate(examClass, request.getParameterMap());

		
		
		int affectedRows = this.examClassService.updateExamClass(examClass);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddExamClass(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改考试分类");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看考试分类
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewExamClass")
	public String viewExamClass(HttpServletRequest request, String id) {

		Map map = this.examClassService.getExamClassById(id);

		request.setAttribute("map", map);

		return "/admin/examClass/viewExamClass.jsp";
	}

	/**
	 * 分页搜索考试分类列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchExamClass")
	public String searchExamClass(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		ExamClass examClass = new ExamClass();
		BeanUtil.populate(examClass, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.examClassService.searchExamClass(page, examClass);
		
		
		
		request.setAttribute("page", page);
		request.setAttribute("bean", examClass);

		return "/admin/examClass/listExamClass.jsp";
	}
	
	/**
	 * 导出考试分类数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportExamClassList")
	public String exportExamClassList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要考试分类的考试分类列表
		ExamClass examClass = new ExamClass();
		BeanUtil.populate(examClass, request.getParameterMap());
		List<ExamClass> excelContent = this.examClassService.searchExamClassForList(examClass);

		String[] columnNames = new String[] { "Name" ,"OrderNumber" ,"TotalMark" ,"RadioSubjectNum" ,"Status" ,"RadioSubjectMark" ,"CheckboxSubjectNumber" ,"CheckboxSubjectMark" ,"JudgeSubjectNumber" ,"JudgeSubjectMark" ,"BlankSubjectNumber" ,"BlankSubjectMark" ,"CreateTime" ,"CreateUserId" ,"CreateUserName" };

		String titleName = "examClassList.xls";// 不创建说明;
		String sheetName = "考试分类列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.examClassService.exportExamClassList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
