package top.pullulate.core.exception;

/**
 * @功能描述:   redis缓存异常
 * @Author: xuyong
 * @Date: 2020/6/18 14:59
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public class RedisCacheException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RedisCacheException(String message) {
        super(message);
    }

    public RedisCacheException(Throwable cause) {
        super(cause);
    }

    public RedisCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisCacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
