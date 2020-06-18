package top.pullulate.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @功能描述:   用户部门实体类
 * @Author: pullulates
 * @Date: 2020/6/13 0013 9:57
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulUserDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户主键 */
    private String userId;

    /** 部门主键 */
    private String deptId;
}
