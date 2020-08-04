package top.pullulate.web.data.vo.monitor;

import lombok.Data;
import top.pullulate.common.validate.PatchStatus;
import top.pullulate.common.validate.Save;
import top.pullulate.common.validate.Update;
import top.pullulate.common.validate.user.ResetPassword;
import top.pullulate.common.validate.user.UpdatePassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @功能描述:   定时任务参数接收类
 * @Author: xuyong
 * @Date: 2020/8/4 22:57
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulJobVo {

    /** 任务ID */
    @NotBlank(message = "任务标识不能为空", groups = {Update.class, PatchStatus.class})
    @Size(max = 32, message = "非法的任务标识", groups = {Update.class, PatchStatus.class})
    private String jobId;

    /** 任务名称 */
    @NotBlank(message = "任务名称不能为空", groups = {Save.class, Update.class})
    @Size(max = 30, message = "任务名称最多30字", groups = {Save.class, Update.class})
    private String name;

    /** 分组名称 */
    @NotBlank(message = "分组名称不能为空", groups = {Save.class, Update.class})
    @Size(max = 30, message = "分组名称最多30字", groups = {Save.class, Update.class})
    private String groupName;

    /** 调用目标 */
    @NotBlank(message = "调用目标不能为空", groups = {Save.class, Update.class})
    @Size(max = 50, message = "姓名最多50字", groups = {Save.class, Update.class})
    private String invokeTarget;

    /** 任务状态：1-执行中；2-暂停 */
    @NotBlank(message = "状态不能为空", groups = {PatchStatus.class})
    @Size(max = 1, message = "状态最多1个字符", groups = {PatchStatus.class})
    private String status;

    /** 执行表达式 */
    @NotBlank(message = "执行表达式不能为空", groups = {Save.class, Update.class})
    @Size(max = 30, message = "执行表达式最多30字", groups = {Save.class, Update.class})
    private String cron;

    /** 备注信息 */
    @Size(max = 250, message = "备注信息名称最多250字", groups = {Save.class, Update.class})
    private String remark;
}
