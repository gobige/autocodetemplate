package com.example.autocodetemplate.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestHandlerInterceptor implements HandlerInterceptor {
	// StopWatch是  spring自带工具类，展示每项任务耗时与占用总时间的百分比，展示结果直观 性能消耗相对较小，start时直接指定任务名字
	private ThreadLocal<StopWatch> stopWatch = new ThreadLocal<>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		StopWatch sw = new StopWatch();
		stopWatch.set(sw);
		sw.start("pre");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
		stopWatch.get().stop();
		stopWatch.get().start("post");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		StopWatch sw = stopWatch.get();
		sw.stop();
		String method = handler.getClass().getSimpleName();
		if (handler instanceof HandlerMethod) {
			String beanType = ((HandlerMethod) handler).getBeanType().getName();
			String methodName = ((HandlerMethod) handler).getMethod().getName();
			method = beanType + "." + methodName;
		}
		log.info("request url:{};request method---:{};response status---:{};request exception---:{};total time----{}ms;---{}ms;last task time---{}ms", request.getRequestURI(), method,
				response.getStatus(), ex == null ? "-" : ex.getClass().getSimpleName(),
				sw.getTotalTimeMillis(), sw.getTotalTimeMillis() - sw.getLastTaskTimeMillis(),
				sw.getLastTaskTimeMillis());
//		log.info(sw.prettyPrint()); // 各任务百分比
		stopWatch.remove();
	}
}
