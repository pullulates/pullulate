package top.pullulate.web.data.vo.monitor;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   操作日志参数接收类
 * @Author: xuyong
 * @Date: 2020/7/21 17:13
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulOperationRecordVo {

    /** 日志主键 */
    private String recordId;

    /** 日志名称 */
    private String title;

    /** 请求ip */
    private String ip;

    /** 浏览器 */
    private String browser;

    /** 终端类型 */
    private String os;

    /** 是否发生异常：0-正常；1-异常 */
    private String hasOccurException;

    /** 用户主键 */
    private String userId;

    /** 用户名称 */
    private String userName;

    /** 部门主键 */
    private String deptId;

    /** 部门名称 */
    private String deptName;

    /** 操作时间 */
    private LocalDateTime operateTime;
}
