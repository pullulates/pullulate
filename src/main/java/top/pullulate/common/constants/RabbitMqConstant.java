package top.pullulate.common.constants;

/**
 * @功能描述:   RabbitMq常量类
 * @Author: pullulates
 * @Date: 2020/7/3 0003 21:33
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public class RabbitMqConstant {

    /**
     * 登录日志队列
     */
    public static final String QUEUE_RECORD_LOGIN = "rabbitmq.queue.record.login";
    /**
     * 登录日志交换机名称
     */
    public static final String EXCHANGE_RECORD_LOGIN = "rabbitmq.exchange.record.login";
    /**
     * 登录日志路由关键字
     */
    public static final String ROUTING_KEY_RECORD_LOGIN = "rabbitmq.routing.key.record.login";
}
