package top.pullulate.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.pullulate.web.data.dto.response.P;

/**
 * @功能描述:   全局异常处理
 * @Author: xuyong
 * @Date: 2020/6/16 10:49
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统未声明异常处理
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(Throwable.class)
    public P unDeclaredExceptionHandler(Throwable e){
        log.error("发生未知异常：", e);
        return P.error("exception.undeclared.error");
    }

    /**
     * 系统未声明异常处理
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(AccessDeniedException.class)
    public P unDeclaredExceptionHandler(AccessDeniedException e){
        log.error("用户权限控制异常：", e);
        return P.error(e.getMessage());
    }

    /**
     * spring security抛出的登录异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(BadCredentialsException.class)
    public P badCredentialsExceptionHandler(BadCredentialsException e){
        log.error("用户登录时发生异常：", e);
        return P.error("exception.login.credential.error");
    }

    /**
     * 用户被锁定异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(LockedException.class)
    public P lockedExceptionHandler(LockedException e){
        log.error("用户登录时发生异常：", e);
        return P.error("login.user.has.locked");
    }

    /**
     * 工具类异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(UtilException.class)
    public P utilExceptionHandler(UtilException e){
        log.error("使用工具类时发生异常：", e);
        return P.error("exception.util.error");
    }

    /**
     * 通用的业务异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(BusinessException.class)
    public P businessExceptionHandler(BusinessException e){
        log.error("业务异常：", e);
        return P.error(e.getMessage());
    }

    /**
     * 缓存异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(RedisCacheException.class)
    public P redisCacheExceptionHandler(RedisCacheException e){
        log.error("缓存异常：", e);
        return P.error(e.getMessage());
    }

    /**
     * 定时任务异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(QuartzJobException.class)
    public P quartzJobExceptionHandler(QuartzJobException e){
        log.error("定时任务异常：", e);
        return P.error(e.getMessage());
    }

    /**
     * sql注入异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(SqlInjectException.class)
    public P sqlInjectExceptionHandler(SqlInjectException e){
        log.error("sql注入异常：", e);
        return P.error(e.getMessage());
    }

    /**
     * 数据校验异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public P bindExceptionHandler(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.error("数据校验异常：{}", fieldError.getDefaultMessage());
        return P.error(fieldError.getDefaultMessage());
    }

    /**
     * api异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(ApiException.class)
    public P apiExceptionHandler(ApiException e){
        log.error("api异常：", e);
        return P.error(e.getMessage());
    }
}
