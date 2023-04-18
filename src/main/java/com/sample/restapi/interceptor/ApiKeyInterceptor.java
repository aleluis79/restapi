package com.sample.restapi.interceptor;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), SkipApiKeyValidation.class) != null
                    || AnnotationUtils.findAnnotation(handlerMethod.getMethod(), SkipApiKeyValidation.class) != null) {
                return true;
            }
        }

        String apiKeyValue = request.getHeader("X-API-Key");
        if (StringUtils.isNotBlank(apiKeyValue)) {
            if (!"SECRETO".equals(apiKeyValue)) {
                response.resetBuffer();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader(HttpHeaders.CONTENT_TYPE, "application/text");
                response.getOutputStream().print("Invalid API key");
                response.flushBuffer();
    
                return false;
            }
        } else {
            response.resetBuffer();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/text");
            response.getOutputStream().print("API key not provided");
            response.flushBuffer();
            return false;
        }
        return true;
    }
}