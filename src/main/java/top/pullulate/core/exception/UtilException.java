package top.pullulate.core.exception;

/**
 * @功能描述:   工具类异常
 * @Author: pullulate
 * @Date: 2020/6/10 0010 11:20
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public class UtilException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UtilException(String message) {
        super(message);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
