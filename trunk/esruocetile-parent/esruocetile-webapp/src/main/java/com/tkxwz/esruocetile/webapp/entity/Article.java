package com.tkxwz.esruocetile.webapp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:12:51
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer columnId;

	private String title;
	private String subTitle;
	
	public String getSubTitle() {
		return subTitle;
	}

	
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	private String content;

	private String author;

	private Date insertTime;

	private String keywords;

	private String copyFrom;

	private Integer source;

	private Integer hitCount;

	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCopyFrom() {
		return copyFrom;
	}

	public void setCopyFrom(String copyFrom) {
		this.copyFrom = copyFrom;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getHitCount() {
		return hitCount;
	}

	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
