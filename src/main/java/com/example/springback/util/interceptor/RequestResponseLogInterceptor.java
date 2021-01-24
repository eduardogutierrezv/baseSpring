package com.example.springback.util.interceptor;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestResponseLogInterceptor extends DispatcherServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
		// Create a JSON object to store HTTP log information
		ObjectNode rootNode = mapper.createObjectNode();

		// Para no acceder a las rutinas si la petición viene de swagger
		if (!requestWrapper.getRequestURI().contains("swagger")
				&& !requestWrapper.getRequestURI().contains("favicon.ico")
				&& !("/v2/api-docs").equals(requestWrapper.getRequestURI())
				&& !("/error").equals(requestWrapper.getRequestURI())
				&& !("/csrf").equals(requestWrapper.getRequestURI()) && !("/").equals(requestWrapper.getRequestURI())) {

			rootNode.put("uri", requestWrapper.getRequestURI());
			rootNode.put("clientIp", requestWrapper.getRemoteAddr());

			rootNode.set("requestHeaders", mapper.valueToTree(getRequestHeaders(requestWrapper)));
			String method = requestWrapper.getMethod();
			rootNode.put("method", method);
			try {
				super.doDispatch(requestWrapper, responseWrapper);
			} finally {
				if (method.equals("GET")) {
					rootNode.set("request", mapper.valueToTree(requestWrapper.getParameterMap()));
				} else {
					JsonNode newNode = mapper.readTree(requestWrapper.getContentAsByteArray());
					rootNode.set("request", newNode);
				}

				rootNode.put("status", responseWrapper.getStatus());
				JsonNode newNode = mapper.readTree(responseWrapper.getContentAsByteArray());
				rootNode.set("response", newNode);

				responseWrapper.copyBodyToResponse();

				rootNode.set("responseHeaders", mapper.valueToTree(getResponsetHeaders(responseWrapper)));

				log.info("Request-Response: " + rootNode.toString());
			}
		} else {
			super.doDispatch(request, response);
		}
	}

	private Map<String, Object> getRequestHeaders(HttpServletRequest request) {
		Map<String, Object> headers = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headers.put(headerName, request.getHeader(headerName));
		}
		return headers;

	}

	private Map<String, Object> getResponsetHeaders(ContentCachingResponseWrapper response) {
		Map<String, Object> headers = new HashMap<>();
		Collection<String> headerNames = response.getHeaderNames();
		for (String headerName : headerNames) {
			headers.put(headerName, response.getHeader(headerName));
		}
		return headers;
	}

}