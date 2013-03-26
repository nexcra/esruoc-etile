package com.tkxgz.elitecourse.core.common;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;


/**
 * @author Po Kong  
 * @time 2012-2-21 下午5:29:45
 */
public class CustomAnnotationBeanNameGenerator extends
		AnnotationBeanNameGenerator {

	@Override
	protected String buildDefaultBeanName(BeanDefinition definition) {
		return definition.getBeanClassName();
	}
}
