package top.pullulate.core.exception;

/**
 * @功能描述:   工具类异常
 * @Author: pullulates
 * @Date: 2020/6/10 0010 11:20
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
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
