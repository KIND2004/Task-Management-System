package com.abdullah.task.config;

import com.abdullah.task.dto.RequestMetaDTO;
import com.abdullah.task.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptorConfig implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;
    RequestMetaDTO requestMetaDTO;

    public JwtInterceptorConfig(RequestMetaDTO requestMetaDTO) {
        this.requestMetaDTO = requestMetaDTO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("authorization");
        System.out.println(request.getRequestURI());
        if (!(
                request.getRequestURI().contains("auth/")
        )) {
            Claims claims = jwtUtil.verifyAccessToken(authorization);

            requestMetaDTO.setId(Long.valueOf(claims.getIssuer()));
            requestMetaDTO.setFullName(claims.get("fullName").toString());
            requestMetaDTO.setEmail(claims.get("email").toString());
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
