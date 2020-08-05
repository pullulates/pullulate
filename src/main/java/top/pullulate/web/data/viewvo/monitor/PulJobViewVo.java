package top.pullulate.web.data.viewvo.monitor;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   定时任务视图类
 * @Author: xuyong
 * @Date: 2020/8/4 22:56
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulJobViewVo {

    /** 任务ID */
    private String jobId;

    /** 任务名称 */
    private String name;

    /** 分组名称 */
    private String groupName;

    /** 调用目标 */
    private String invokeTarget;

    /** 任务状态：1-执行中；2-暂停 */
    private String status;

    /** 执行表达式 */
    private String cron;

    /** 备注信息 */
    private String remark;

    /** 创建时间 */
    private LocalDateTime createAt;
}
