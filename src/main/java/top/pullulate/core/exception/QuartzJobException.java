package top.pullulate.core.exception;

/**
 * @功能描述:   定时任务异常
 * @Author: xuyong
 * @Date: 2020/8/4 22:38
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public class QuartzJobException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public QuartzJobException(String message) {
        super(message);
    }

    public QuartzJobException(Throwable cause) {
        super(cause);
    }

    public QuartzJobException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuartzJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
