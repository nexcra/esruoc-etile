package com.tkxgz.elitecourse.codegenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Soyi Yao
 */
public class AllGenerator {

	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<String>();
		/*
		 * list.add("t_article@文章");
		 * list.add("t_assignment@作业");
		 * list.add("t_assignment_class@作业分类");
		 * list.add("t_classes@班级");
		 * list.add("t_column@栏目");
		 * list.add("t_course_introduction@课程简介");
		 * list.add("t_dict@字典");
		 * list.add("t_exam_class@考试分类");
		 * list.add("t_faq@常见问题");
		 * list.add("t_friendsite@友情链接");
		 * list.add("t_group@组");
		 * list.add("t_group_privilege@组权限关联");
		 * // list.add("t_log@日志");
		 * //list.add("t_node@节点");
		 * list.add("t_notice@公告");
		 * list.add("t_privilege@权限");
		 * list.add("t_question_answer@答疑");
		 * list.add("t_system_config@系统配置");
		 * list.add("t_user@用户");
		 * list.add("t_user_group@用户组");
		 */
//		list.add("t_template@模板");
		//list.add("t_data_backup@数据备份");
		list.add("t_video@视频");

		boolean replaceable = true;
		String tableName = "";
		String tableDescription = "";

		for (String table : list) {
			String str[] = table.split("@");
			tableName = str[0];
			tableDescription = str[1];
			JspGenerator.generateCRUDPage(tableName, tableDescription,
					replaceable);
			JavaGenerator.generateAllJava(tableName, tableDescription,
					replaceable);
		}

		// JspGenerator.generateMenuJsp(list, replaceable);

		System.err.println("------------生成代码完成------------------");
	}

}
