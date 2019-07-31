package com.example.autocodetemplate.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHandlerInterceptor implements HandlerInterceptor {
	private final Logger logger = LoggerFactory.getLogger(RequestHandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		request.setAttribute("_processStartTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

		response.setHeader("Access-Control-Allow-Origin", "*.car-house.cn");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
		Object processStartTime = request.getAttribute("_processStartTime");
		long processFinishTime = System.currentTimeMillis();
		if (processStartTime != null) {
			long startTime = Long.parseLong(processStartTime.toString());
			long processTime = processFinishTime - startTime;
			logger.info("Process time:{}, Path: {}", processTime, request.getRequestURI());
		}
	}
}
