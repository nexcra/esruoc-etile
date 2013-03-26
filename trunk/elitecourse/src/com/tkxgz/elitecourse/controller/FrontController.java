package com.tkxgz.elitecourse.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxgz.elitecourse.bean.Article;
import com.tkxgz.elitecourse.bean.Column;
import com.tkxgz.elitecourse.bean.Friendsite;
import com.tkxgz.elitecourse.bean.Log;
import com.tkxgz.elitecourse.bean.Node;
import com.tkxgz.elitecourse.bean.Notice;
import com.tkxgz.elitecourse.bean.QuestionAnswer;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.bean.Video;
import com.tkxgz.elitecourse.core.constant.CommonConstants;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.ArticleService;
import com.tkxgz.elitecourse.service.ColumnService;
import com.tkxgz.elitecourse.service.CourseIntroductionService;
import com.tkxgz.elitecourse.service.FriendsiteService;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.service.NodeService;
import com.tkxgz.elitecourse.service.NoticeService;
import com.tkxgz.elitecourse.service.QuestionAnswerService;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.VideoService;
import com.tkxgz.elitecourse.util.ApplicationUtils;

/**
 * 前台Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/front/index.do")
public class FrontController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ColumnService columnService;

	@Autowired
	private CourseIntroductionService courseIntroductionService;

	@Autowired
	private NodeService nodeService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private FriendsiteService friendsiteService;

	@Autowired
	private VideoService videoService;

	@Autowired
	private QuestionAnswerService questionAnswerService;

	@Autowired
	private LogService logService;

	@Autowired
	private UserService userService;

	/**
	 * 默认action
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String defaultAction(HttpServletRequest request) {

		return this.toIndex(request, null);
	}

	/**
	 * 转到首页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "action=toIndex")
	public String toIndex(HttpServletRequest request, String parentId) {
		String templateName = ApplicationUtils.getCurrentTemplate(request);
		this.commonIndexData(request, parentId);

		return "/template/" + templateName + "/index.jsp";
	}

	@RequestMapping(params = "action=readNode")
	public String readNodeContent(HttpServletRequest request, String parentId) {
		String templateName = ApplicationUtils.getCurrentTemplate(request);
		this.commonIndexData(request, parentId);

		List<Node> childNodeList = this.nodeService
				.listChildNodesByParentId(parentId);

		request.getSession().setAttribute("childNodeList", childNodeList);
		return "/template/" + templateName + "/nodeSubList.jsp";

	}

	@RequestMapping(params = "action=readNodeById")
	public String readNodeById(HttpServletRequest request, String parentId,
			String id) {
		String templateName = ApplicationUtils.getCurrentTemplate(request);
		this.commonIndexData(request, parentId);

		Map map = this.nodeService.getNodeById(id);

		request.getSession().setAttribute("node", map);

		List<Node> childNodeList = this.nodeService
				.listChildNodesByParentId(parentId);

		request.getSession().setAttribute("childNodeList", childNodeList);

		return "/template/" + templateName + "/nodeContent.jsp";

	}

	@RequestMapping(params = "action=readNoticeById")
	public String readNoticeById(HttpServletRequest request, String parentId,
			String id) {
		String templateName = ApplicationUtils.getCurrentTemplate(request);
		this.commonIndexData(request, parentId);

		Map map = this.noticeService.getNoticeById(id);

		request.getSession().setAttribute("notice", map);

		return "/template/" + templateName + "/noticeContent.jsp";

	}

	@RequestMapping(params = "action=listVideo")
	public String listVideo(HttpServletRequest request, String parentId,
			String currentPageNum) {
		String templateName = ApplicationUtils.getCurrentTemplate(request);
		this.commonIndexData(request, parentId);

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.videoService.listVideo(page);

		request.setAttribute("page", page);

		return "/template/" + templateName + "/videoList.jsp";

	}

	@RequestMapping(params = "action=listQuestionAnswer")
	public String listQuestionAnswer(HttpServletRequest request,
			String parentId, String currentPageNum) {
		String templateName = ApplicationUtils.getCurrentTemplate(request);
		this.commonIndexData(request, parentId);

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.questionAnswerService.listQuestionAnswer(page);

		request.setAttribute("page", page);

		return "/template/" + templateName + "/questionAnswerList.jsp";

	}

	@RequestMapping(params = "action=readVideoById")
	public String readVideoById(HttpServletRequest request, String parentId,
			String id) {
		String templateName = ApplicationUtils.getCurrentTemplate(request);
		this.commonIndexData(request, parentId);
		Map video = this.videoService.getVideoById(id);
		request.setAttribute("video", video);
		return "/template/" + templateName + "/videoContent.jsp";

	}

	@RequestMapping(params = "action=readQuestionAndwerById")
	public String readQuestionAndwerById(HttpServletRequest request,
			String parentId, String id) {
		String templateName = ApplicationUtils.getCurrentTemplate(request);
		this.commonIndexData(request, parentId);
		Map video = this.questionAnswerService.getQuestionAnswerById(id);
		request.setAttribute("questionAnswer", video);
		return "/template/" + templateName + "/questionAnswerContent.jsp";

	}

	@RequestMapping(params = "action=toAddQuestionAnswer")
	public String toAddQuestionAnswer(HttpServletRequest request,
			String parentId) {
		String templateName = ApplicationUtils.getCurrentTemplate(request);
		this.commonIndexData(request, parentId);
		return "/template/" + templateName + "/questionAnswerAdd.jsp";

	}

	@RequestMapping(params = "action=addQuestionAndwer")
	public String addQuestionAndwer(HttpServletRequest request,
			String parentId, String id) throws IllegalAccessException,
			InvocationTargetException {
		String templateName = ApplicationUtils.getCurrentTemplate(request);
		this.commonIndexData(request, parentId);
		// 获取操作人信息
		User admin = (User) request.getSession().getAttribute("student");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/front/index.do?action=listQuestionAnswer";

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
			this.toAddQuestionAnswer(request, null);

		}

		Log log = new Log();
		log.setModule("答疑管理");
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加疑问");

		this.logService.addLog(log);

		return result;

	}

	@RequestMapping(params = "action=listArticleByColumnId")
	public String listArticleByColumnId(HttpServletRequest request,
			String parentId, String currentPageNum, String columnId) {
		this.commonIndexData(request, parentId);
		String templateName = ApplicationUtils.getCurrentTemplate(request);

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.articleService.listArticleByColumnId(columnId, page);
		request.setAttribute("page", page);

		return "/template/" + templateName + "/articleList.jsp";

	}

	@RequestMapping(params = "action=readArticle")
	public String readArticle(HttpServletRequest request, String parentId,
			String id) {

		this.commonIndexData(request, parentId);
		String templateName = ApplicationUtils.getCurrentTemplate(request);

		Map article = this.articleService.getArticleById(id);

		request.getSession().setAttribute("article", article);
		return "/template/" + templateName + "/articleContent.jsp";

	}

	public String commonIndexData(HttpServletRequest request, String parentId) {

		// 获取模板名
		ApplicationUtils.setCurrentTemplate(request, "default");

		if (StringUtils.isEmpty(parentId)) {
			parentId = "0";
		}

		// 所有节点
		List<Node> nodeList = this.nodeService.listNodeTreeByParentId("0");

		// 根据父级查询出来的节点
		List<Node> nodeSubList = this.nodeService
				.listNodeTreeByParentId(parentId);

		// 课程简介
		Map introductionMap = this.courseIntroductionService
				.getCourseIntroductionById("1");

		// 获取所有文章
		List<Article> articleList = this.articleService.listAllArticle();

		// 获取所有栏目
		List<Column> columnList = this.columnService.listAllColumn();

		// 获取所有公告

		List<Notice> noticeList = this.noticeService.listAllNotice();

		// 获取所有友情链接

		List<Friendsite> friendSiteList = this.friendsiteService
				.listAllFriendsite();

		// 获取所有视频列表
		List<Video> videoList = this.videoService.listAllVideo();

		request.setAttribute("introduction", introductionMap);
		request.getSession().setAttribute("nodeList", nodeList);
		request.getSession().setAttribute("nodeSubList", nodeSubList);
		request.getSession().setAttribute("articleList", articleList);
		request.getSession().setAttribute("columnList", columnList);
		request.getSession().setAttribute("noticeList", noticeList);
		request.getSession().setAttribute("friendSiteList", friendSiteList);
		request.getSession().setAttribute("videoList", videoList);

		return null;
	}

}
