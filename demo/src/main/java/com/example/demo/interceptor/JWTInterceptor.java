package com.example.demo.interceptor;

import com.example.demo.utils.jwt.JWTUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private JWTUtils jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if(StringUtils.isNotEmpty(token)) {
            Claims claims = jwtUtil.validateJWT(token);
            request.setAttribute("claims",claims);
        }
        //无论如何都放行
        return true;
    }

}