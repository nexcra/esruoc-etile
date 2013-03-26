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

import com.tkxgz.elitecourse.bean.Article;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.ArticleService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.bean.Column;
import com.tkxgz.elitecourse.service.ColumnService;

/**
 * 文章Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/article.do")
public class ArticleController {

	private static final String MODULE_NAME = "文章管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private UserService userService;

	@Autowired
	private ColumnService columnService;

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

		return this.listArticle(request, currentPageNum);
	}

	/**
	 * 查询文章列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listArticle")
	public String listArticle(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.articleService.listArticle(page);

		request.setAttribute("page", page);

		List<Column> columnList = this.columnService
				.listColumnTreeByParentId("0");

		request.setAttribute("columnList", columnList);


		return "/admin/article/listArticle.jsp";
	}

	/**
	 * 转到新增文章页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddArticle")
	public String toAddArticle(HttpServletRequest request) {

		List<Column> columnList = this.columnService
				.listColumnTreeByParentId("0");

		request.setAttribute("columnList", columnList);


		return "/admin/article/addArticle.jsp";
	}

	/**
	 * 新增文章
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addArticle")
	public String addArticle(HttpServletRequest request) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/article.do?action=listArticle";

		// 获取表单参数
		Article article = new Article();
		BeanUtil.populate(article, request.getParameterMap());

		// 设置操作管理员
		article.setCreateUserId(admin.getId());
		article.setCreateUserName(admin.getName());

		int affectedRows = this.articleService.addArticle(article);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddArticle(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加文章");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 删除文章
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteArticle")
	public String deleteArticle(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.articleService.batchDeleteArticle(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除文章");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改文章页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateArticle")
	public String toUpdateArticle(HttpServletRequest request, String id) {

		Map map = this.articleService.getArticleById(id);

		request.setAttribute("map", map);

		List<Column> columnList = this.columnService
				.listColumnTreeByParentId("0");

		request.setAttribute("columnList", columnList);


		return "/admin/article/updateArticle.jsp";
	}

	/**
	 * 修改文章
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
	@RequestMapping(params = "action=updateArticle")
	public String updateArticle(HttpServletRequest request, String id)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/article.do?action=listArticle";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Article article = this.articleService.findArticleById(id);
		BeanUtil.populate(article, request.getParameterMap());

		int affectedRows = this.articleService.updateArticle(article);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddArticle(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改文章");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看文章
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewArticle")
	public String viewArticle(HttpServletRequest request, String id) {

		Map map = this.articleService.getArticleById(id);

		request.setAttribute("map", map);

		return "/admin/article/viewArticle.jsp";
	}

	/**
	 * 分页搜索文章列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchArticle")
	public String searchArticle(HttpServletRequest request,
			String currentPageNum) throws IllegalAccessException,
			InvocationTargetException {

		Article article = new Article();
		BeanUtil.populate(article, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.articleService.searchArticle(page, article);

		List<Column> columnList = this.columnService.listAllColumn();
		request.setAttribute("columnList", columnList);

		request.setAttribute("page", page);
		request.setAttribute("bean", article);

		return "/admin/article/listArticle.jsp";
	}

	/**
	 * 导出文章数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportArticleList")
	public String exportArticleList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要文章的文章列表
		Article article = new Article();
		BeanUtil.populate(article, request.getParameterMap());
		List<Article> excelContent = this.articleService
				.searchArticleForList(article);

		String[] columnNames = new String[] { "标题", "栏目", "副标题",
				"来源", "内容",  
				"创建用户", "创建时间" };

		String titleName = "articleList.xls";// 不创建说明;
		String sheetName = "文章列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.articleService.exportArticleList(request, excelContent,
				columnNames, sheetName, outputStream);

		return null;
	}

}
