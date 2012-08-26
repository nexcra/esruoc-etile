package com.tkxwz.esruocetile.webapp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.dao.ConfigDao;
import com.tkxwz.esruocetile.webapp.entity.Config;

/**
 * @author Po Kong
 * @since 2012-10-26 下午1:02:27
 */
@Service
public class ConfigService {

	@Autowired
	private ConfigDao configDao;

	/**
	 * @author Po Kong
	 * @since 2012-10-26 下午1:04:07
	 * @param page
	 */
	public Page listConfig(Page page) {
		return this.configDao.listConfig(page);

	}

	/**
	 * @author Po Kong
	 * @since 2012-10-26 下午1:04:15
	 * @param id
	 * @return
	 */
	public Map getConfigById(String id) {

		return this.configDao.getConfigById(id);
	}

	/**
	 * @author Po Kong
	 * @since 2012-10-26 下午1:04:37
	 * @param config
	 * @return
	 */
	public int updateConfig(Config config) {

		return this.configDao.updateConfig(config);
	}

	/**
	 * @author Po Kong
	 * @since 2012-10-26 下午3:11:11
	 * @param id
	 * @return
	 */
	public Map getConfigByCode(String code) {
		return this.configDao.getConfigByCode(code);
	}

}
