package com.dragonsoft.cube.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created: 2016/9/5.
 * Author: Qiannan Lu
 */
@Configuration
public class ScheduleConfig {

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) throws Exception {
		SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
		factoryBean.setApplicationContext(applicationContext);
		factoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
		factoryBean.afterPropertiesSet();
		return factoryBean;
	}
}
