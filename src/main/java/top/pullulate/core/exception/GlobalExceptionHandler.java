package top.pullulate.core.exception;

import cn.hutool.core.text.StrFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.pullulate.web.data.dto.response.P;

import java.util.List;

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

    /**
     * 通用的业务异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(RedisCacheException.class)
    public P businessExceptionHandler(RedisCacheException e){
        log.error("缓存异常：", e);
        return P.error(e.getMessage());
    }

    /**
     * 数据校验异常
     *
     * @param e 异常信息
     * @return  P
     */
    @ExceptionHandler(BindException.class)
    public P bindExceptionHandler(BindException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        sb.append(StrFormatter.format("数据校验异常，以下为校验提示信息：</br>"));
        fieldErrors.forEach(fieldError -> sb.append(fieldError.getDefaultMessage()));
        log.error("数据校验异常：", sb.toString());
        return P.error(sb.toString());
    }
}
