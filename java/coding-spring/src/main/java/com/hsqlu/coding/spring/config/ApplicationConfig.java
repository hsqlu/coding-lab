package com.hsqlu.coding.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
@EnableWebMvc
@ComponentScan(value = "com.hsqlu.coding.spring", includeFilters = @ComponentScan.Filter(Controller.class))
public class ApplicationConfig {
}
