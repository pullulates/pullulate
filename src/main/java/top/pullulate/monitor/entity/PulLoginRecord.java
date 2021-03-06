package top.pullulate.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import top.pullulate.utils.MessageUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @功能描述:   登录日志实体类
 * @Author: pullulate
 * @Date: 2020/7/4 0004 10:25
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
@NoArgsConstructor
public class PulLoginRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    /** 日志主键 */
    private String recordId;

    /** 登录账号 */
    private String userName;

    /** 登录IP */
    private String ip;

    /** 登录地址 */
    private String location;

    /** 浏览器 */
    private String browser;

    /** 终端类型 */
    private String os;

    /** 登录结果 */
    private String result;

    /** 失败原因 */
    private String promtMsg;

    /** 登录时间 */
    private LocalDateTime loginTime;

    public PulLoginRecord(String recordId, String userName, String ip, String location, String browser, String os, String result) {
        this.recordId = recordId;
        this.userName = userName;
        this.ip = ip;
        this.location = location;
        this.browser = browser;
        this.os = os;
        this.result = result;
        this.loginTime = LocalDateTime.now();
    }
}
