package top.pullulate.web.data.viewvo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   部门视图实体类
 * @Author: xuyong
 * @Date: 2020/6/29 13:16
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulDeptViewVo {

    /** 部门主键 */
    private String deptId;

    /** 部门编号 */
    private String deptNo;

    /** 部门名称 */
    private String deptName;

    /** 父级主键 */
    private String parentId;

    /** 祖级主键 */
    private String accestorIds;

    /** 排序编号 */
    private Integer orderNum;

    /** 部门状态 */
    private String status;

    /** 备注信息 */
    private String remark;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createTime;
}
