package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @功能描述:   短信模板实体类
 * @Author: pullulate
 * @Date: 2020/6/28 0028 22:02
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulSmsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String templateId;

    /** 模板名称 */
    private String name;

    /** 模板内容 */
    private String content;

    /** 短信类型：0-普通短信；1-营销短信 */
    private String smsType;

    /** 是否国际港澳台短信：0-国内短信；1-国际港澳台短信 */
    private String international;

    /** 模板状态：0-正常；1-禁用 */
    private String status;

    /** 删除状态：0-正常；1-已删除 */
    private String deleteFlag;

    /** 备注信息 */
    private String remark;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createAt;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    private LocalDateTime updateAt;
}
