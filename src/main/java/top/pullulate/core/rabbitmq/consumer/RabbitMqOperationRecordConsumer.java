package top.pullulate.core.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.RabbitMqConstant;
import top.pullulate.monitor.entity.PulOperationRecord;
import top.pullulate.monitor.service.IPulOperationRecordService;

/**
 * @功能描述:   操作日志消息队列消费者类
 * @Author: pullulates
 * @Date: 2020/7/4 0004 10:35
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(queues = {RabbitMqConstant.QUEUE_RECORD_OPERATION})
public class RabbitMqOperationRecordConsumer {

    private final IPulOperationRecordService operationRecordService;

    @RabbitHandler
    public void process(PulOperationRecord operationRecord) {
        log.info("{} / {} / {} / {} / {} / {} / {} / {} / {}",
                operationRecord.getTitle(), operationRecord.getPath(), operationRecord.getDeptName(), operationRecord.getUserName(),
                operationRecord.getIp(), operationRecord.getLocation(), operationRecord.getBrowser(),
                operationRecord.getOs(), "耗时：" + operationRecord.getCostTime());
        operationRecordService.save(operationRecord);
    }
}
