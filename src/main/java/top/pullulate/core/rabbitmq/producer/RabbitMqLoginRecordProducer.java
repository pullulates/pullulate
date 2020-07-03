package top.pullulate.core.rabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @功能描述:   RabbitMq登录日志生产者
 * @Author: pullulates
 * @Date: 2020/7/3 0003 23:26
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Component
public class RabbitMqLoginRecordProducer implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("回调id：{}，消费结果：{}，描述信息：{}", correlationData, ack ? "成功" : "失败", cause);
    }
}
