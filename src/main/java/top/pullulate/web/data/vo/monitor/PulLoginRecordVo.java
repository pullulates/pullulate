package top.pullulate.web.data.vo.monitor;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   登录日志参数接收类
 * @Author: xuyong
 * @Date: 2020/7/21 16:40
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulLoginRecordVo {

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

    /** 登录时间 */
    private LocalDateTime loginTime;
}