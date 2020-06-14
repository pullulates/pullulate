package top.pullulate.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @功能描述:   mybatis plus信息配置
 * @Author: pullulates
 * @Date: 2020/6/13 0013 10:28
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Configuration
@MapperScan("top.pullulate.**.mapper")
@EnableTransactionManagement
public class MyBatisPlusConfiguration {
}
