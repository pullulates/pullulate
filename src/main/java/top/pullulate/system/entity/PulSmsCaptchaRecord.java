package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @功能描述:   短信验证码记录实体类
 * @Author: pullulates
 * @Date: 2020/6/28 0028 22:10
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulSmsCaptchaRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String recordId;

    /** 手机号码 */
    private String phone;

    /** 验证码 */
    private String captcha;

    /** 模板id */
    private String templateId;

    /** 短信内容 */
    private String content;

    /** 发送时间 */
    private LocalDateTime sendTime;

    /** 过期时间 */
    private LocalDateTime expireTime;

    /** 过期状态：0-未过期；1-已过期 */
    private String hasExpired;

    /** 使用状态：0-未使用；1-已使用 */
    private String status;

    /** 校验时间 */
    private LocalDateTime checkTime;

    /** 异步回执编号 */
    private String asyncNo;
}
