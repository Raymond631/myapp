package cn.edu.csu.ycepspring.common.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 允许指定域访问跨域资源
        resp.addHeader("Access-Control-Allow-Origin", "*");
        // 允许所有请求方式
        resp.addHeader("Access-Control-Allow-Methods", "*");
        // 允许的header参数
        resp.addHeader("Access-Control-Allow-Headers", "*");
        // 有效时间
        resp.addHeader("Access-Control-Max-Age", "3600");
        // 如果是预检请求，则立即返回到前端
        if (((HttpServletRequest) servletRequest).getMethod().equals("OPTIONS")) {
            servletResponse.getWriter().println("ok");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
