package com.dragonsoft.cube.config.filter;

import edu.yale.its.tp.cas.client.filter.CASFilter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created: 2016/8/23.
 * Author: Qiannan Lu
 */
public class DidsFilter extends CASFilter {
	// 是否开启dids
	public static Boolean openDidsFilter = true;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws ServletException, IOException {
		//开放给智搜的调用接口参数
		HttpSession session = ((HttpServletRequest) request).getSession();
		String interFace = request.getParameter("interface");
		String tableNames = request.getParameter("tableNames");
		if (StringUtils.isNotBlank(interFace) && StringUtils.isNotBlank(tableNames)) {
			session.setAttribute("interface", interFace);
			session.setAttribute("tableNames", tableNames);
		}
		if (openDidsFilter) {
			super.doFilter(request, response, fc);
		} else {
			fc.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		if (openDidsFilter) {
			super.init(config);
		}
	}
}
