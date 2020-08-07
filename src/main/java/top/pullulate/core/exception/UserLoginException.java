package top.pullulate.core.exception;

/**
 * @功能描述:   用户登录异常
 * @Author: xuyong
 * @Date: 2020/6/16 10:37
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public class UserLoginException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserLoginException(String message) {
        super(message);
    }

    public UserLoginException(Throwable cause) {
        super(cause);
    }

    public UserLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
