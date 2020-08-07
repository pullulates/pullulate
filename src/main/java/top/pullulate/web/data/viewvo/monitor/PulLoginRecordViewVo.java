package top.pullulate.web.data.viewvo.monitor;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   登录日志视图类
 * @Author: xuyong
 * @Date: 2020/7/21 16:42
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulLoginRecordViewVo {

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

}
