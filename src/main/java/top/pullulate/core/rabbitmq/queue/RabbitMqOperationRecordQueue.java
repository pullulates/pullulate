package top.pullulate.core.rabbitmq.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.pullulate.common.constants.RabbitMqConstant;

/**
 * @功能描述:   操作日志记录队列类
 * @Author: pullulate
 * @Date: 2020/7/3 0003 21:08
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Configuration
public class RabbitMqOperationRecordQueue {

    @Bean(name = "operationRecordQueue")
    public Queue operationRecordQueue() {
        return new Queue(RabbitMqConstant.QUEUE_RECORD_OPERATION, true);
    }

    @Bean
    public DirectExchange operationRecordExchange() {
        return new DirectExchange(RabbitMqConstant.EXCHANGE_RECORD_OPERATION, true, false);
    }

    @Bean
    public Binding operationRecordBinding() {
        return BindingBuilder.bind(operationRecordQueue()).to(operationRecordExchange()).with(RabbitMqConstant.ROUTING_KEY_RECORD_OPERATION);
    }
}
