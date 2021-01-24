package com.example.springback.util.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class RequestResponseRestTemplateLogInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        log.info("RestTemplate-Request: {\"uri\":\"" + request.getURI() + "\", \"requestHeaders\":"
                + headerToJson(request.getHeaders().toString()) + ", \"method\":\"" + request.getMethod()
                + "\", \"request\":" + new String(body, "UTF-8") + "}");
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        log.info("RestTemplate-Response: {\"statusCode\":\"" + response.getStatusCode() + "\", \"statusText\":\""
                + response.getStatusText() + "\", \"responseHeaders\":" + headerToJson(response.getHeaders().toString())
                + ", \"response\": " + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()) + "}");
    }

    private String headerToJson(String headers) {
        headers = headers.replace("\", ", "\", \"");
        headers = headers.replace(":\"", "\":\"");
        headers = headers.replace("[", "{\"");
        headers = headers.replace("]", "}");
        return headers;
    }
}