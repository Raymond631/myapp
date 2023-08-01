package cn.edu.csu.ycepspring.common.filter;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.edu.csu.ycepspring.common.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * [Sa-Token 权限认证] 配置类
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    @Autowired
    private LogService logService;

    // 注册 Sa-Token 的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 日志
            int account = StpUtil.getTokenValue() != null ? StpUtil.getLoginIdAsInt() : -1;
            String path = SaHolder.getRequest().getRequestPath();
            logService.saveLog(account, path);
            // 鉴权
            SaRouter.match("/**").notMatch("/user/login", "/user/register", "/user/code").check(r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");
    }
}
