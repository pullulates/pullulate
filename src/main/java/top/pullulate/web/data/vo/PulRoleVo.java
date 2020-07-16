package top.pullulate.web.data.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @功能描述:   角色参数接收类
 * @Author: xuyong
 * @Date: 2020/7/16 15:27
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Getter
@Setter
public class PulRoleVo {

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
}
