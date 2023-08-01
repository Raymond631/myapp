package cn.edu.csu.ycepspring.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.edu.csu.ycepspring.common.constants.HttpStatus;
import cn.edu.csu.ycepspring.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 未登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public CommonResponse handleNotPermissionException(NotLoginException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限码校验失败'{}'", requestURI, e.getMessage());
        return CommonResponse.error(HttpStatus.UNAUTHORIZED, "未登录或token已过期");
    }

    /**
     * 权限异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public CommonResponse handleNotPermissionException(NotPermissionException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限码校验失败'{}'", requestURI, e.getMessage());
        return CommonResponse.error(HttpStatus.FORBIDDEN, "没有访问权限");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public CommonResponse handleServiceException(ServiceException e, HttpServletRequest request) {
        Integer code = e.getCode();
        log.error(e.getMessage(), e);
        return (code != null) ? CommonResponse.error(code, e.getMessage()) : CommonResponse.error(e.getMessage());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(BindException.class)
    public CommonResponse handleBindException(BindException e) {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        log.error(e.getMessage(), e);
        return CommonResponse.error(message);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public CommonResponse handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return CommonResponse.error("服务器开了个小差，请稍后再试~");
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return CommonResponse.error("服务器繁忙，请稍后再试~");
    }
}
