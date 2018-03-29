package com.dragonsoft.cube.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created: 2016/8/30.
 * Author: Qiannan Lu
 */
@Configuration
@EnableAsync
@PropertySource("classpath:cube.properties")
public class RootConfig {
	public static final String PROJECT_NAME = "Big Data Magic Cube";
}
