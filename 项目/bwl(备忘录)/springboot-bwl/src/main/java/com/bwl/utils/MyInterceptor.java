package com.bwl.utils;

import com.bwl.exceptions.TokenException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //处理预检请求  处理跨域请求一定要处理预处理请求,不然获取的header为空（因为拦截器会先处理预检请求,则获取的header为空）
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        //非跨域请求只有swagger的测试请求
        String origin = request.getHeader("Origin");
        if(origin == null){
            return true;
        }

        String token = request.getHeader("Token");
        if(!TokenUtils.validateToken(token)) {
            throw new TokenException("token错误");
        }
        Claims claims =  TokenUtils.parseToken(token);
        //将token中负载的信息放在request中
        request.setAttribute("uId", claims.get("id"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}

