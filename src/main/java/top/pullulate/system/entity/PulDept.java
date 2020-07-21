package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @功能描述:   部门实体类
 * @Author: pullulates
 * @Date: 2020/6/13 0013 9:48
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 部门主键 */
    @TableId
    private String deptId;

    /** 部门编号 */
    private String deptNo;

    /** 部门名称 */
    private String deptName;

    /** 父级主键 */
    private String parentId;

    /** 排序编号 */
    private Integer orderNum;

    /** 部门状态 */
    private String status;

    /** 删除标识 */
    private String deleteFlag;

    /** 备注信息 */
    private String remark;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createAt;

    /** 修改人 */
    private String updateBy;

    /** 修改时间 */
    private LocalDateTime updateAt;
}
