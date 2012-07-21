package com.tkxwz.esruocetile.core.page;

import java.io.Serializable;
import java.util.List;

import com.tkxwz.esruocetile.core.constant.CommonConstants;

/**
 * @author Po Kong 
 * @since 2012-2-24 上午10:51:35
 */
public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	private int currentPageNum = 1;// 当前页页码

	private boolean hasPreviousPage = false;// 是否有上一页

	private int previousPageNum;// 上一页页码

	private boolean hasNextPage = false;// 是否有下一页

	private int nextPageNum;// 下一页页码

	private boolean hasFirstPage;

	private boolean hasLastPage;

	private int totalPages = 0;// 总页数

	private int totalRecords = 0;// 总记录数

	private int pageSize = CommonConstants.DEFAULT_PAGE_SIZE;// 每页显示记录条数

	@SuppressWarnings("rawtypes")
	private List pageDatas;// 分页数据

	/**
	 * 以"默认当前页码"与"每页显示记录数"创建分页对象
	 */
	public Page() {
		this.currentPageNum = CommonConstants.DEFAULT_CURRENT_PAGE_NUM;
		this.pageSize = CommonConstants.DEFAULT_PAGE_SIZE;

	}

	/**
	 * 以当前页码创建对象，设置当前页为默认当前页<br />
	 * 
	 * @param currentPageNum
	 *        当前页码
	 */
	public Page(int currentPageNum) {
		this.currentPageNum = currentPageNum;
		this.pageSize = CommonConstants.DEFAULT_PAGE_SIZE;
	}

	/**
	 * 以传入参数当前页码与每页显示记录数创建对象，如果当前页码小于0,则使用默认当前页设置<br />
	 * 如果每页显示记录数小于0,则使用默认每页显示记录数设置
	 * 
	 * @param currentPageNum
	 *        当前页码
	 * @param pageSize
	 *        每页显示记录数
	 */
	public Page(int currentPageNum, int pageSize) {
		this.currentPageNum = currentPageNum > 0 ? currentPageNum
				: CommonConstants.DEFAULT_CURRENT_PAGE_NUM;
		this.pageSize = pageSize > 0 ? pageSize
				: CommonConstants.DEFAULT_PAGE_SIZE;
	}

	/**
	 * 以传入总记录数、当前页码与每页显示记录数创建对象，如果当前页码小于0,则使用默认当前页设置<br />
	 * 如果每页显示记录数小于0,则使用默认每页显示记录数设置
	 * 
	 * @param totalRecords
	 *        总记录数
	 * @param pageSize
	 *        每页显示记录数
	 * @param currentPageNum
	 *        当前页码
	 */
	public Page(int totalRecords, int pageSize, int currentPageNum) {
		this.totalRecords = totalRecords;
		this.pageSize = pageSize > 0 ? pageSize
				: CommonConstants.DEFAULT_PAGE_SIZE;
		this.currentPageNum = currentPageNum > 0 ? currentPageNum
				: CommonConstants.DEFAULT_CURRENT_PAGE_NUM;
	}

	public int getNextPageNum() {
		return currentPageNum == totalPages ? totalPages : currentPageNum + 1;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = currentPageNum + 1;
	}

	public int getPreviousPageNum() {
		return currentPageNum - 1;
	}

	public void setPreviousPageNum(int previousPageNum) {
		this.previousPageNum = previousPageNum;
	}

	/**
	 * 得到当前页码
	 * 
	 * @author Po Kong 
	 * @since 2012-2-24 上午11:25:29
	 * @return 当前页码
	 */
	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = ("".equals(currentPageNum) || 1 > currentPageNum) ? 1
				: currentPageNum;
	}

	/**
	 * 得到总页数,如果每页显示数为0条，则总页数返回0
	 * 
	 * @author Po Kong 
	 * @since 2012-2-24 上午11:17:08
	 * @return 总页数
	 */

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isHasNextPage() {
		return currentPageNum == this.getTotalPages() ? false : true;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return currentPageNum == 1 ? false : true;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public int getTotalPages() {
		return pageSize == 0 ? 0 : (totalRecords + pageSize - 1) / pageSize;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@SuppressWarnings("rawtypes")
	public List getPageDatas() {
		return pageDatas;
	}

	@SuppressWarnings("rawtypes")
	public void setPageDatas(List pageDatas) {
		this.pageDatas = pageDatas;
	}

	public boolean isHasFirstPage() {
		return currentPageNum > 1;
	}

	public void setHasFirstPage(boolean hasFirstPage) {
		this.hasFirstPage = hasFirstPage;
	}

	public boolean isHasLastPage() {
		return currentPageNum < totalPages;
	}

	public void setHasLastPage(boolean hasLastPage) {
		this.hasLastPage = hasLastPage;
	}

}
