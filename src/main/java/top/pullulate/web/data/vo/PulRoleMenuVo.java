package top.pullulate.web.data.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @功能描述:   角色菜单参数接收类
 * @Author: xuyong
 * @Date: 2020/7/20 8:18
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulRoleMenuVo {

    @NotBlank(message = "角色信息不能为空")
    @Size(max = 32, message = "不合法的角色标识")
    private String roleId;

    private String menuId;

    /** 菜单主键，多个以，分割 */
    private String menuIds;
}
