package com.tkxwz.esruocetile.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.webapp.dao.IndexDao;

/**
 * @author 孔沛洪
 * @since 2012-5-10 下午4:48:18
 */
@Service
public class IndexService {

	Logger logger = LoggerFactory.getLogger(IndexService.class);

	@Autowired
	private IndexDao indexDao;

	public String index() {
		logger.info("测试一下slf4j");
		return this.indexDao.index();
	}
}
