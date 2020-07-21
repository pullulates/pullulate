package top.pullulate.web.data.vo.system;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 20, message = "角色名称最多20字")
    private String roleName;

    /** 角色标识 */
    @NotBlank(message = "角色标识不能为空")
    @Size(max = 20, message = "角色标识最多20字")
    private String roleKey;

    /** 角色状态 */
    private String status;

    /** 删除标识 */
    private String deleteFlag;

    /** 备注信息 */
    @Size(max = 250, message = "备注信息最多250字")
    private String remark;
}
