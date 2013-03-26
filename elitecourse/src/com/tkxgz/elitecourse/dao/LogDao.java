package com.tkxgz.elitecourse.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkxgz.elitecourse.dao.base.LogBaseDao;

/**
 * 日志Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class LogDao extends LogBaseDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());

}
