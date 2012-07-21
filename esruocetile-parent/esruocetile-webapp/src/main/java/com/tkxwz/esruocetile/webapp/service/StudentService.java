package com.tkxwz.esruocetile.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.webapp.dao.StudentDao;

/**
 * @author Po Kong 
 * @since 2012-7-6 下午4:48:14
 */
@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

}
