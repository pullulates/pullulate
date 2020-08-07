package top.pullulate.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.core.io.ClassPathResource;
import top.pullulate.core.quartz.factory.QuartzJobFactory;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @功能描述:   定时任务配置类
 * @Author: xuyong
 * @Date: 2020/8/4 21:45
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Configuration
@RequiredArgsConstructor
public class QuartzConfig {

    private final QuartzJobFactory jobFactory;

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws IOException {
        //获取配置属性
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/config/quartz.yml"));
        propertiesFactoryBean.afterPropertiesSet();
        //创建SchedulerFactoryBean
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(propertiesFactoryBean.getObject());
        factory.setJobFactory(jobFactory);
        factory.setAutoStartup(true);
        factory.setStartupDelay(10);
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        return factory;
    }
}
