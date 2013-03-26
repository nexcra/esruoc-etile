package com.tkxgz.elitecourse.core.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.tkxgz.elitecourse.core.constant.CommonConstants;
import com.tkxgz.elitecourse.core.page.Page;

/**
 * @author Po Kong
 * @since 2012-2-26 下午9:15:13
 */
public class PageTag extends TagSupport {

	private static Logger logger = Logger.getLogger(PageTag.class);

	private static final long serialVersionUID = 1L;

	private StringBuilder pageHtml = null;// 分页信息html

	private String className = "";// 分页div的class样式名字

	private String url = "";// 分页的链接

	private String firstPageName = "首页";

	private String previousPageName = "上一页";

	private String nextPageName = "下一页";

	private String lastPageName = "末页";

	private Page page;// 分页对象

	private int currentPageNum = CommonConstants.DEFAULT_CURRENT_PAGE_NUM;// 第一页页码

	/**
	 * 标签处理开始，构建一个存放分页信息html的字符串缓冲区
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午9:52:11
	 * @return 忽略标签之间的内容
	 * @throws JspException
	 *         jsp异常
	 */
	@Override
	public int doStartTag() throws JspException {
		pageHtml = new StringBuilder();
		return SKIP_BODY;
	}

	/**
	 * 分页标签处理逻辑
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午9:59:11
	 * @return 按正常流程继续处理jsp页面
	 * @throws JspException
	 *         jsp异常
	 */

	@Override
	public int doEndTag() throws JspException {
		// 分页div开始标记
		getStartDiv();
		// 分页信息
		getPageInfo();
		// 分页div结束标记
		getEndDiv();
		// 写日志
		writeLog();
		// 将分页信息返回给页面
		writePageHtmlToPage();
		return EVAL_PAGE;
	}

	/**
	 * 得到分页div开始标记
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午10:03:25
	 */
	private void getStartDiv() {
		pageHtml = pageHtml.append("<div ");
		if (StringUtils.isNotEmpty(StringUtils.trim(className))) {
			pageHtml.append("class=\"");
			pageHtml.append(className);
			pageHtml.append("\"");
		}
		pageHtml.append(">");

	}

	/**
	 * 得到分页信息
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午10:03:29
	 */
	private void getPageInfo() {
		getFirstPageInfo();// 首页
		this.space();
		getPreviousPageInfo();// 上一页
		this.space();
		getNextPageInfo();// 下一页
		this.space();
		getLastPageInfo();// 末页
		this.space();
		getPageSizeInfo();// 每页xx条
		getTotalRecordsInfo();// 共xx条
		this.space();
		getNavigatePageInfo();// 第XX页/共xx页
		this.space();
		goToPage();// 转到第几页
	}

	/**
	 * 文字之间空白
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 下午2:50:59
	 */
	private void space() {
		pageHtml.append(" ");
	}

	/**
	 * 得到分页链接url串
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 下午12:51:38
	 * @param pageNum
	 *        页码
	 * @param pageName
	 *        页名（比如"首页","下一页")等
	 */
	private void getPageLinkUrl(int pageNum, String pageName) {
		pageHtml.append("<a href=\"");
		pageHtml.append(url);
		if (url.lastIndexOf("?") > 0) {
			pageHtml.append("&");
		} else {
			pageHtml.append("?");
		}
		pageHtml.append(CommonConstants.CURRENT_PAGE_LINK_NAME);
		pageHtml.append("=");
		pageHtml.append(pageNum);
		pageHtml.append("");
		pageHtml.append("\">");
		pageHtml.append(pageName);
		pageHtml.append("</a>");
	}

	/**
	 * 得到第一页分页信息,如果当前页为首页，则只显示文字，否则显示链接
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午11:37:15
	 */
	private void getFirstPageInfo() {
		if (CommonConstants.DEFAULT_CURRENT_PAGE_NUM == this.page
				.getCurrentPageNum()) {
			pageHtml.append(this.firstPageName);
		} else {
			this.getPageLinkUrl(1, this.firstPageName);
		}

	}

	/**
	 * 得到上一页分页信息，如果当前页为首页，则只显示文字，否则显示链接
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午11:37:28
	 */
	private void getPreviousPageInfo() {
		if (CommonConstants.DEFAULT_CURRENT_PAGE_NUM == this.page
				.getCurrentPageNum()) {
			pageHtml.append(this.previousPageName);
		} else {
			this.getPageLinkUrl(page.getPreviousPageNum(),
					this.previousPageName);
		}
	}

	/**
	 * 得到下一页分页信息，如果当前页为末页，则只显示文字，否则显示链接
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午11:37:54
	 */
	private void getNextPageInfo() {
		if (page.getCurrentPageNum() == page.getTotalPages()
				|| page.getTotalRecords() == 0) {
			pageHtml.append(this.nextPageName);
		} else {
			this.getPageLinkUrl(page.getNextPageNum(), this.nextPageName);
		}
	}

	/**
	 * 得到末页分页信息，如果当前页为末页，则只显示文字，否则显示链接
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午11:38:36
	 */
	private void getLastPageInfo() {
		if (page.getCurrentPageNum() == page.getTotalPages()
				|| page.getTotalRecords() == 0) {
			pageHtml.append(this.lastPageName);
		} else {
			this.getPageLinkUrl(page.getTotalPages(), this.lastPageName);
		}

	}

	/**
	 * @author Po Kong
	 * @since 2012-2-27 上午11:38:39
	 */
	private void getPageSizeInfo() {
		pageHtml.append("每页");
		pageHtml.append(page.getPageSize());
		pageHtml.append("条/");
	}

	/**
	 * @author Po Kong
	 * @since 2012-2-27 上午11:38:45
	 */
	private void getTotalRecordsInfo() {
		pageHtml.append("共");
		pageHtml.append(page.getTotalRecords());
		pageHtml.append("条");

	}

	/**
	 * @author Po Kong
	 * @since 2012-2-27 上午11:38:42
	 */
	private void getNavigatePageInfo() {
		pageHtml.append("当前第");
		pageHtml.append(page.getCurrentPageNum());
		pageHtml.append("页/共");
		pageHtml.append(page.getTotalPages());
		pageHtml.append("页");
	}

	/**
	 * 转到第几页
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 下午2:51:43
	 */
	private void goToPage() {
		/*
		 * String goToPageScript = "window.open(\"" + this.url + "&"
		 * + CommonConstants.CURRENT_PAGE_LINK_NAME + "="
		 * + "document.getElementById(\"goToPage_div\").value())\"";
		 * pageHtml.append("转到:<input type='text' name='goToPage_div' id='goToPage_div' /> ");
		 * pageHtml.append("<a href='#' onclick='");
		 * pageHtml.append(goToPageScript);
		 * pageHtml.append("' style='text-decoration: none;'>go</a>");
		 */

		// this.getPageLinkUrl(page.getNextPageNum(), "转到");

		pageHtml.append("转到第<input type='text' style='width:25px;'   name='goToPage_div' id='goToPage_div' /> 页");
		this.space();
		pageHtml.append("<a   id='goToPageButton' ");
		pageHtml.append(" onclick='var goToPageButton = document.getElementById(\"goToPageButton\").href;  document.location.href=goToPageButton.replace(\"pageNumToReplace\",document.getElementById(\"goToPage_div\").value);return false'   href=\"");
		pageHtml.append(url);
		if (url.lastIndexOf("?") > 0) {
			pageHtml.append("&");
		} else {
			pageHtml.append("?");
		}
		pageHtml.append(CommonConstants.CURRENT_PAGE_LINK_NAME);
		pageHtml.append("=");
		pageHtml.append("pageNumToReplace");
		pageHtml.append("");
		pageHtml.append("\">");

		pageHtml.append("跳转");
		pageHtml.append("</a>");

	}

	/**
	 * 写日志
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午10:03:41
	 */
	private void writeLog() {
		//logger.debug("生成的分页代码: " + pageHtml);
	}

	/**
	 * 得到分页div结束标记
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午10:03:39
	 */
	private void getEndDiv() {
		pageHtml.append("</div>");
	}

	/**
	 * 将分页信息返回给页面
	 * 
	 * @author Po Kong
	 * @since 2012-2-27 上午10:03:45.
	 */
	private void writePageHtmlToPage() {
		try {
			pageContext.getOut().print(pageHtml);
		} catch (IOException e) {
			logger.error("写入分页信息html到页面失败", e);
		}

	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setFirstPageName(String firstPageName) {
		this.firstPageName = firstPageName;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
