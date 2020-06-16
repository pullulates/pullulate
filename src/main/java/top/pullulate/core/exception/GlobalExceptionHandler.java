package top.pullulate.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.pullulate.web.data.dto.P;

/**
 * @功能描述:   全局异常处理
 * @Author: xuyong
 * @Date: 2020/6/16 10:49
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
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
    public P utiExceptionHandler(UtilException e){
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
}
