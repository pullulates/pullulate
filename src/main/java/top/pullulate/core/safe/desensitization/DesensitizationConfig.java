package top.pullulate.core.safe.desensitization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.apache.commons.lang3.StringUtils;
import top.pullulate.common.enums.SensitiveType;
import top.pullulate.core.annotations.Desensitization;

import java.io.IOException;
import java.util.Objects;

/**
 * @功能描述:   数据脱敏配置
 * @Author: xuyong
 * @Date: 2020/8/26 11:13
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public class DesensitizationConfig extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveType type;

    public DesensitizationConfig() {
    }

    public DesensitizationConfig(SensitiveType type) {
        this.type = type;
    }

    @Override
    public void serialize(String value, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        switch (this.type) {
            case MOBILE:
                generator.writeString(DesensitizationUtil.mobile(value));
                break;
            case EMAIL:
                generator.writeString(DesensitizationUtil.email(value));
                break;
            case NAME:
                generator.writeString(DesensitizationUtil.name(value));
                break;
            case IDCARD:
                generator.writeString(DesensitizationUtil.idCard(value));
                break;
            default:
                generator.writeString(DesensitizationUtil.COMMON);
                break;
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        if (property != null) {
            if (Objects.equals(property.getType().getRawClass(), String.class)) {
                Desensitization desensitization = property.getAnnotation(Desensitization.class);
                if (desensitization == null) {
                    desensitization = property.getContextAnnotation(Desensitization.class);
                }
                if (desensitization != null) {
                    return new DesensitizationConfig(desensitization.type());
                }
            }
            return provider.findValueSerializer(property.getType(), property);
        } else {
            return provider.findNullValueSerializer(null);
        }
    }
}

class DesensitizationUtil {

    public final static String COMMON = "******";

    /**
     * 对手机号的处理
     *
     * @param mobile 手机号
     * @return 脱敏后的手机号
     */
    public static String mobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return "";
        }
        return StringUtils.left(mobile, 3).concat(
                StringUtils.removeStart(
                        StringUtils.leftPad(
                                StringUtils.right(mobile, 4), StringUtils.length(mobile), "*"),
                        "***"));
    }


    /**
     * 对身份证的处理
     *
     * @param idCard 身份证号
     * @return 脱敏后的身份证号
     */
    public static String idCard(String idCard) {
        if (StringUtils.isBlank(idCard)) {
            return "";
        }
        return StringUtils.left(idCard, 4)
                .concat(StringUtils.removeStart(
                        StringUtils.leftPad(StringUtils.right(idCard, 4), StringUtils.length(idCard), "*"),
                        "***"));
    }

    /**
     * 对email的处理
     *
     * @param email 邮箱
     * @return 脱敏后的邮箱
     */
    public static String email(String email) {
        return COMMON;
    }

    /**
     * 姓名
     */
    public static String name(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        return StringUtils.left(name,1).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(name,1),StringUtils.length(name),"*"),"*"));
    }
}
