package top.pullulate.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @功能描述:   jackson序列化配置类
 * @Author: xuyong
 * @Date: 2020/7/2 13:01
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper()
                .setLocale(Locale.CHINA)
                .registerModule(new Jdk8Module())
                .registerModule(new LocaDateTimeConfiguration());
        return objectMapper;
    }
}
