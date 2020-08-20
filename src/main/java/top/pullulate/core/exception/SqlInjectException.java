package top.pullulate.core.exception;

/**
 * @功能描述:   sql注入异常
 * @Author: xuyong
 * @Date: 2020/8/20 14:16
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public class SqlInjectException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SqlInjectException(String message) {
        super(message);
    }

    public SqlInjectException(Throwable cause) {
        super(cause);
    }

    public SqlInjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlInjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
