package com.dragonsoft.cube.common;

import org.springframework.context.ApplicationContext;

public class ApplicationContextHolder {
	private static ApplicationContext applicationContext;

	private ApplicationContextHolder() {
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextHolder.applicationContext = applicationContext;
	}
}
