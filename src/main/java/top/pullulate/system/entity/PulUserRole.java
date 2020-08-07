package top.pullulate.system.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @功能描述:   用户角色实体类
 * @Author: pullulate
 * @Date: 2020/6/13 0013 0:18
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Getter
@Setter
public class PulUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户主键 */
    private String userId;

    /** 角色主键 */
    private String roleId;

    public PulUserRole() {
    }

    public PulUserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
