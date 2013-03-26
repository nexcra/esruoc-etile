package com.tkxgz.elitecourse.test;

import com.tkxgz.elitecourse.codegenerator.CodeGeneratorUtils;

/**
 * @author Soyi Yao
 */
public class Test {

	/**
	 * @author Soyi Yao
	 * @param args
	 */
	public static void main(String[] args) {
		System.out
				.print(CodeGeneratorUtils
						.readSource("E:\\JavaWorkSpaces\\eclipse3.7_newFramework\\elitecourse\\codegenerator\\com\\tkxgz\\elitecourse\\codegenerator\\template\\addJsp.txt"));
	}
}
