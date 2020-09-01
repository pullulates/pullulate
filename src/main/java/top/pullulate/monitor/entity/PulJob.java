package top.pullulate.monitor.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @功能描述:   定时任务实体类
 * @Author: xuyong
 * @Date: 2020/8/4 22:36
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Getter
@Setter
public class PulJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
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

    /** 删除状态 */
    private String deleteFlag;

    /** 执行表达式 */
    private String cron;

    /** 备注信息 */
    private String remark;

    /** 创建人 */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /** 修改人 */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    /** 修改时间 */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateAt;
}
