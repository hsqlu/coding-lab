package com.dragonsoft.cube.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Created: 2016/9/7.
 * Author: Qiannan Lu
 */
@Configuration
@EnableAsync(proxyTargetClass = true)
@ComponentScan(
		basePackages = "com.dragonsoft.cube",
		excludeFilters = @ComponentScan.Filter({
				Controller.class, Configuration.class,
				Repository.class, ControllerAdvice.class
		}))
public class ServiceConfig {

}
