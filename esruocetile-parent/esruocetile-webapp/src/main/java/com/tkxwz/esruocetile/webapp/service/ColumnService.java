package com.tkxwz.esruocetile.webapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.dao.ColumnDao;
import com.tkxwz.esruocetile.webapp.entity.Column;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:14:39
 */
@Service
public class ColumnService {

	@Autowired
	private ColumnDao columnDao;

	public Page listColumn(Page page) {
		return this.columnDao.listColumn(page);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 12:51:56
	 * @param column
	 */
	public int addColumn(Column column) {
		return this.columnDao.addColumn(column);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:12:54
	 * @param ids
	 */
	public int deleteColumnById(String id) {
		return this.columnDao.deleteColumnById(id);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:14:08
	 * @param ids
	 */
	public int batchDeleteColumn(String[] ids) {
		int result = 0;
		for (String id : ids) {
			this.deleteColumnById(id);
			result++;
		}
		return result;

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:55:43
	 * @param id
	 */
	public Map getColumnById(String id) {
		return this.columnDao.getColumnById(id);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 23:25:01
	 * @param column
	 */
	public int updateColumn(Column column) {
		return this.columnDao.updateColumn(column);

	}

	/**
	 * @author Po Kong
	 * @since 24 Jul 2012 22:21:51
	 */
	public List<Column> listAllColumnForNav() {
		return this.columnDao.listAllColumnForNav();

	}
	public List<Column> listAllColumnForArticle() {
		return this.columnDao.listAllColumnForArticle();
		
	}
	public List<Column> listAllColumnForColumn() {
		return this.columnDao.listAllColumnForColumn();
		
	}

	public Page listArticleByColumnId(Page page, Integer columnId) {
		return this.columnDao.columnList(page, columnId);

	}

	 /**
	 * 
	 * 
	 * @author Po Kong
	 * @since 2012-8-15 下午10:23:48
	 * @param columnName
	 * @return
	 */
	public boolean isUserExist(String columnName) {
		int result = this.columnDao.checkUserCount(columnName);
		return result > 0;
	}

	 /**
	 * 
	 * 
	 * @author Po Kong
	 * @since 2012-9-5 上午12:00:34
	 * @param page
	 * @param columnName
	 */
	public Page listArticleByColumnName(Page page, String columnName) {
		return this.columnDao.columnListByColumnName(page, columnName);
		
	}

	 

}
