package top.pullulate.system.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @功能描述:   用户部门实体类
 * @Author: pullulate
 * @Date: 2020/6/13 0013 9:57
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Getter
@Setter
public class PulUserDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户主键 */
    private String userId;

    /** 部门主键 */
    private String deptId;

    public PulUserDept() {
    }

    public PulUserDept(String userId, String deptId) {
        this.userId = userId;
        this.deptId = deptId;
    }
}
