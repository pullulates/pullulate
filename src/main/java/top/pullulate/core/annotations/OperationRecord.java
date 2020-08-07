package top.pullulate.core.annotations;

import java.lang.annotation.*;

/**
 * @功能描述:   操作日志注解
 * @Author: xuyong
 * @Date: 2020/7/4 17:29
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.METHOD })
public @interface OperationRecord {

    /**
     * 日志名称
     */
    String title();
}
