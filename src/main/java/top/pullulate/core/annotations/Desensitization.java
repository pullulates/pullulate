package top.pullulate.core.annotations;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import top.pullulate.common.enums.SensitiveType;
import top.pullulate.core.safe.desensitization.DesensitizationConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @功能描述:   数据脱敏注解
 * @Author: xuyong
 * @Date: 2020/8/26 11:07
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationConfig.class)
public @interface Desensitization {

    /**
     * 敏感数据类型
     */
    SensitiveType type();
}
