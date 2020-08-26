package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @功能描述:   角色实体类
 * @Author: pullulate
 * @Date: 2020/6/10 0010 13:34
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 角色主键 */
    @TableId
    private String roleId;

    /** 角色名称 */
    private String roleName;

    /** 角色标识 */
    private String roleKey;

    /** 角色状态 */
    private String status;

    /** 删除标识 */
    private String deleteFlag;

    /** 备注信息 */
    private String remark;

    /** 创建人 */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /** 更新人 */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    /** 更新时间 */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateAt;
}
