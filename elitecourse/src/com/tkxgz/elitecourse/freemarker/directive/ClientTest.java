package com.tkxgz.elitecourse.freemarker.directive;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 客户端测试模板输入类
 */
public class ClientTest {
	public static void main(String[] args) {
		Map<String,Object> root=new HashMap<String, Object>();

		root.put("upper", new UpperDirective());
		
		FreeMarkertUtil.processTemplate("src","test.ftl", "UTF-8", root, new OutputStreamWriter(System.out));
		
	}
}
