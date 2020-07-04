package top.pullulate.core.rabbitmq.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.pullulate.common.constants.RabbitMqConstant;

/**
 * @功能描述:   登录日志记录队列类
 * @Author: pullulates
 * @Date: 2020/7/3 0003 21:08
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Configuration
public class RabbitMqLoginRecordQueue {

    @Bean(name = "loginRecordQueue")
    public Queue loginRecordQueue() {
        return new Queue(RabbitMqConstant.QUEUE_RECORD_LOGIN, true);
    }

    @Bean
    public DirectExchange loginRecordExchange() {
        return new DirectExchange(RabbitMqConstant.EXCHANGE_RECORD_LOGIN, true, false);
    }

    @Bean
    public Binding loginRecordNoOneBinding() {
        return BindingBuilder.bind(loginRecordQueue()).to(loginRecordExchange()).with(RabbitMqConstant.ROUTING_KEY_RECORD_LOGIN);
    }
}
