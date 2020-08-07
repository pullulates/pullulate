package top.pullulate.core.rabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.RabbitMqConstant;
import top.pullulate.monitor.entity.PulLoginRecord;

/**
 * @功能描述:   RabbitMq登录日志生产者
 * @Author: pullulate
 * @Date: 2020/7/3 0003 23:26
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Slf4j
@Component
public class RabbitMqLoginRecordProducer implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    public RabbitMqLoginRecordProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendLoginInfor(PulLoginRecord loginRecord) {
        CorrelationData correlationId = new CorrelationData(loginRecord.getRecordId());
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE_RECORD_LOGIN, RabbitMqConstant.ROUTING_KEY_RECORD_LOGIN, loginRecord, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("回调id：{}，消费结果：{}，描述信息：{}", correlationData, ack ? "成功" : "失败", cause);
    }
}
