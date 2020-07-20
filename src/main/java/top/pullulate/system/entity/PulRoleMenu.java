package top.pullulate.system.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @功能描述:   角色菜单实体类
 * @Author: pullulates
 * @Date: 2020/6/13 0013 10:07
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Getter
@Setter
public class PulRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 角色主键 */
    private String roleId;

    /** 菜单主键 */
    private String menuId;

    public PulRoleMenu() {
    }

    public PulRoleMenu(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
