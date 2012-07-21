package com.tkxwz.esruocetile.webapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.webapp.dao.DictDao;

/**
 * @author Po Kong 
 * @since 2012-7-5 下午9:26:58
 */
@Service
public class DictService {

	@Autowired
	private DictDao dictDao;

	public String getDictValueByDictName(String dictName) {
		return this.dictDao.getDictValueByDictName(dictName);
	}

	public List<Map<String, Object>> getDictValuesByDictName(String dictName) {
		return this.dictDao.getDictValuesByDictName(dictName);
	}
}
