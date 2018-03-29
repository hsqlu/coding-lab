package com.dragonsoft.cube.config.filter;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * Created: 2016/8/23.
 * Author: Qiannan Lu
 */
public class LogMDCFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Random random = new Random();
		int beginIndex = random.nextInt(16);
		String requestId = UUID.randomUUID().toString().replaceAll("\\-", "").substring(beginIndex, beginIndex + 16)
				+ '@'
				+ Thread.currentThread().getId();
		MDC.put("RequestId", requestId);
		filterChain.doFilter(request, response);
	}
}
