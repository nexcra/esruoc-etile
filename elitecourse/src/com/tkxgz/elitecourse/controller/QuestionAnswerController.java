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

import com.tkxgz.elitecourse.bean.QuestionAnswer;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.QuestionAnswerService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;

/**
 * 答疑Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/questionAnswer.do")
public class QuestionAnswerController {

	private static final String MODULE_NAME = "答疑管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

	@Autowired
	private QuestionAnswerService questionAnswerService;

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

		return this.listQuestionAnswer(request, currentPageNum);
	}

	/**
	 * 查询答疑列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listQuestionAnswer")
	public String listQuestionAnswer(HttpServletRequest request,
			String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.questionAnswerService.listQuestionAnswer(page);

		request.setAttribute("page", page);

		return "/admin/questionAnswer/listQuestionAnswer.jsp";
	}

	/**
	 * 转到新增答疑页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddQuestionAnswer")
	public String toAddQuestionAnswer(HttpServletRequest request) {

		return "/admin/questionAnswer/addQuestionAnswer.jsp";
	}

	/**
	 * 新增答疑
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addQuestionAnswer")
	public String addQuestionAnswer(HttpServletRequest request)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/questionAnswer.do?action=listQuestionAnswer";

		// 获取表单参数
		QuestionAnswer questionAnswer = new QuestionAnswer();
		BeanUtil.populate(questionAnswer, request.getParameterMap());

		questionAnswer.setCreateUserIp(request.getRemoteAddr());

		// 设置操作管理员
		questionAnswer.setCreateUserId(admin.getId());
		questionAnswer.setCreateUserName(admin.getName());

		int affectedRows = this.questionAnswerService
				.addQuestionAnswer(questionAnswer);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddQuestionAnswer(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加答疑");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 删除答疑
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteQuestionAnswer")
	public String deleteQuestionAnswer(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.questionAnswerService.batchDeleteQuestionAnswer(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除答疑");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改答疑页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateQuestionAnswer")
	public String toUpdateQuestionAnswer(HttpServletRequest request, String id) {

		Map map = this.questionAnswerService.getQuestionAnswerById(id);

		request.setAttribute("map", map);

		return "/admin/questionAnswer/updateQuestionAnswer.jsp";
	}

	/**
	 * 修改答疑
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
	@RequestMapping(params = "action=updateQuestionAnswer")
	public String updateQuestionAnswer(HttpServletRequest request, String id)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/questionAnswer.do?action=listQuestionAnswer";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		QuestionAnswer questionAnswer = this.questionAnswerService
				.findQuestionAnswerById(id);
		BeanUtil.populate(questionAnswer, request.getParameterMap());

		questionAnswer.setReUserId(admin.getId());

		questionAnswer.setReUserName(admin.getName());

		int affectedRows = this.questionAnswerService
				.updateQuestionAnswer(questionAnswer);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddQuestionAnswer(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改答疑");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看答疑
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewQuestionAnswer")
	public String viewQuestionAnswer(HttpServletRequest request, String id) {

		Map map = this.questionAnswerService.getQuestionAnswerById(id);

		request.setAttribute("map", map);

		return "/admin/questionAnswer/viewQuestionAnswer.jsp";
	}

	/**
	 * 分页搜索答疑列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchQuestionAnswer")
	public String searchQuestionAnswer(HttpServletRequest request,
			String currentPageNum) throws IllegalAccessException,
			InvocationTargetException {

		QuestionAnswer questionAnswer = new QuestionAnswer();
		BeanUtil.populate(questionAnswer, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.questionAnswerService.searchQuestionAnswer(page, questionAnswer);

		request.setAttribute("page", page);
		request.setAttribute("bean", questionAnswer);

		return "/admin/questionAnswer/listQuestionAnswer.jsp";
	}

	/**
	 * 导出答疑数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportQuestionAnswerList")
	public String exportQuestionAnswerList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要答疑的答疑列表
		QuestionAnswer questionAnswer = new QuestionAnswer();
		BeanUtil.populate(questionAnswer, request.getParameterMap());
		List<QuestionAnswer> excelContent = this.questionAnswerService
				.searchQuestionAnswerForList(questionAnswer);

		String[] columnNames = new String[] { "Title", "Content",
				"CreateUserId", "CreateUserName", "CreateTime", "CreateUserIp",
				"ReContent", "ReUserId", "ReUserName", "ReTime" };

		String titleName = "questionAnswerList.xls";// 不创建说明;
		String sheetName = "答疑列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.questionAnswerService.exportQuestionAnswerList(request,
				excelContent, columnNames, sheetName, outputStream);

		return null;
	}

}
