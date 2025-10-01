package com.lyrics.ecs.filter;

import com.lyrics.ecs.bean.exceptions.AuthException;
import com.lyrics.ecs.service.AuthService;
import com.lyrics.ecs.utils.ObjectUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 排除不需要验证的路径（如登录、注册接口）
        String path = request.getRequestURI();
        if (path.contains("/login") || path.contains("/register")) {
            filterChain.doFilter(request, response);
            return;
        }


        try {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                System.out.println(headerName);
            }
            String token = request.getHeader("token");
            if (ObjectUtils.isEmpty(token) || ObjectUtils.isEmpty(authService.verifyToken(token))) {
                throw new AuthException("Unauthorized");
            }

            doFilter(request, response, filterChain);

            System.out.println(token);
        } catch (AuthException e) {
            logger.warn("认证异常：{}" + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":" + e.getCode() + ",\"message\":\"" + e.getMessage() + "\",\"data\":null}");
        } catch (Exception e) {
            logger.error("过滤器异常", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":500,\"message\":\"服务器内部错误\",\"data\":null}");
        }


    }
}
