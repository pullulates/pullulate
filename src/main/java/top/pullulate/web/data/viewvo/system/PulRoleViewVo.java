package top.pullulate.web.data.viewvo.system;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   角色视图实体类
 * @Author: xuyong
 * @Date: 2020/6/29 13:13
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulRoleViewVo {

    /** 角色主键 */
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
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createAt;
}
