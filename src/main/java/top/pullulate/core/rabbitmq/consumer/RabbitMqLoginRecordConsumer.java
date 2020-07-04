package top.pullulate.core.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.RabbitMqConstant;
import top.pullulate.common.enums.OperResult;
import top.pullulate.system.entity.PulLoginRecord;
import top.pullulate.system.service.IPulLoginRecordService;

/**
 * @功能描述:   登录日志消息队列消费者类
 * @Author: pullulates
 * @Date: 2020/7/4 0004 10:35
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(queues = {RabbitMqConstant.QUEUE_RECORD_LOGIN})
public class RabbitMqLoginRecordConsumer {

    private final IPulLoginRecordService loginRecordService;

    @RabbitHandler
    public void process(PulLoginRecord loginRecord) {
        log.info("登录用户：{}，登录ip：{}，登录地址：{}，登录浏览器：{}，终端类型：{}，登陆结果：{}，消息提示：{}",
                loginRecord.getUserName(), loginRecord.getIp(), loginRecord.getLocation(), loginRecord.getBrowser(),
                loginRecord.getOs(), OperResult.getLabel(loginRecord.getResult()), loginRecord.getPromtMsg());
        loginRecordService.save(loginRecord);
    }
}
