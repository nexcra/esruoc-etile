package com.tkxgz.elitecourse.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkxgz.elitecourse.dao.base.FaqBaseDao;

/**
 * 常见问题Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class FaqDao extends FaqBaseDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());

}
