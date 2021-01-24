package com.example.springback.util.interceptor;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.springback.util.utils.Utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PrePostHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Utils.genTraceCode();
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);

		// Para no acceder a las rutinas si la petición viene de swagger
		if (!requestWrapper.getRequestURI().contains("swagger")
				&& !requestWrapper.getRequestURI().contains("favicon.ico")
				&& !("/v2/api-docs").equals(requestWrapper.getRequestURI())
				&& !("/error").equals(requestWrapper.getRequestURI())
				&& !("/csrf").equals(requestWrapper.getRequestURI()) && !("/").equals(requestWrapper.getRequestURI())) {
			log.info("ExecutionTime : " + getExecutionTime());
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	private String getExecutionTime() throws Exception {
		String fechaInicialString = Utils.getTraceCode().split("-")[0];
		Date fechaInicialDate = new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(fechaInicialString);
		Double diff = ((double) new Date().getTime() - (double) fechaInicialDate.getTime()) / 1000;
		return diff.toString();
	}
}