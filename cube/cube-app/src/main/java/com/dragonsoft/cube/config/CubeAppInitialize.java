package com.dragonsoft.cube.config;

import com.dragonsoft.cube.config.filter.DidsFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * Created: 2016/8/30.
 * Author: Qiannan Lu
 */
public class CubeAppInitialize extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class, DBConfig.class, ServiceConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		this.initCharacterEncodingFilter(servletContext);
		this.addFilter(servletContext, "didsFilter", new DidsFilter());
		super.onStartup(servletContext);
	}

	protected void initCharacterEncodingFilter(ServletContext servletContext) {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		this.addFilter(servletContext, "characterEncodingFilter", characterEncodingFilter);
	}

	protected void addFilter(ServletContext servletContext, String filterName, Filter filter) {
		javax.servlet.FilterRegistration.Dynamic filterRegistration = servletContext.addFilter(filterName, filter);
		filterRegistration.setAsyncSupported(this.isAsyncSupported());
		filterRegistration.addMappingForUrlPatterns(this.getDispatcherTypes(), false, "/*");
	}

	protected EnumSet<DispatcherType> getDispatcherTypes() {
		return this.isAsyncSupported() ? EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC) : EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
	}
}
