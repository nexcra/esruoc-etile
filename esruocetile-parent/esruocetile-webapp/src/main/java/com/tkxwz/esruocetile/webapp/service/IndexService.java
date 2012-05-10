package com.tkxwz.esruocetile.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.webapp.dao.IndexDao;

/**
 * @author 孔沛洪
 * @since 2012-5-10 下午4:48:18
 */
@Service
public class IndexService {

	@Autowired
	private IndexDao indexDao;

	public String index() {
		return this.indexDao.index();
	}
}
