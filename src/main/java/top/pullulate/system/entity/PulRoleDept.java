package top.pullulate.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @功能描述:   角色部门实体类
 * @Author: pullulate
 * @Date: 2020/6/13 0013 10:08
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulRoleDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 角色主键 */
    private String roleId;

    /** 部门主键 */
    private String deptId;
}
