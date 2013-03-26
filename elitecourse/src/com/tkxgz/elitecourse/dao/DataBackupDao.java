package com.tkxgz.elitecourse.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkxgz.elitecourse.dao.base.DataBackupBaseDao;

/**
 * 数据备份Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class DataBackupDao extends DataBackupBaseDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());

}
