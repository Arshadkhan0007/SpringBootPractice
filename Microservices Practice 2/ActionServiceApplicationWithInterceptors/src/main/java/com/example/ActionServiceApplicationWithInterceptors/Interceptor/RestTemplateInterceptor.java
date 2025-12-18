package com.example.ActionServiceApplicationWithInterceptors.Interceptor;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String traceId = MDC.get("traceId");
        if(traceId != null) {
            request.getHeaders().add("X-Trace-Id", traceId);
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes != null) {
            String authToken = attributes.getRequest().getHeader("Authorization");
            if(authToken != null) {
                request.getHeaders().add("Authorization", authToken);
            }
        }

        return execution.execute(request, body);
    }
}
