package top.pullulate.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @功能描述:   角色菜单实体类
 * @Author: pullulates
 * @Date: 2020/6/13 0013 10:07
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulRoleMenu implements Serializable {

    /** 角色主键 */
    private String roleId;

    /** 菜单主键 */
    private String menuId;
}
