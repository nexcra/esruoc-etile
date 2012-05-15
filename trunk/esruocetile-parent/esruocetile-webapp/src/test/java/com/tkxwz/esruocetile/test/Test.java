package com.tkxwz.esruocetile.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 孔沛洪
 * @since 2012-5-10 下午5:51:58
 */
public class Test {

	final Logger logger = LoggerFactory.getLogger(Test.class);

	public void hello() {
		logger.info("testdfdfdfdsd");
	}

	public static void main(String[] args) {
		Test t = new Test();
		t.hello();
		
		Logger logger = LoggerFactory.getLogger(Test.class);
	   // logger.info("Hello World");
	}
}
