package top.pullulate.web.data.vo.system;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * @功能描述:   部门参数接收类
 * @Author: xuyong
 * @Date: 2020/7/21 10:33
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulDeptVo {

    /** 部门主键 */
    private String deptId;

    /** 部门编号 */
    @NotBlank(message = "部门编号不能为空")
    @Size(max = 20, message = "部门编号最多20字")
    private String deptNo;

    /** 部门名称 */
    @NotBlank(message = "部门名称不能为空")
    @Size(max = 50, message = "部门名称最多50字")
    private String deptName;

    /** 父级主键 */
    private String parentId;

    /** 排序编号 */
    @NotNull(message = "排序编号不能为空")
    @Min(value = 1, message = "排序编号低于最小值1")
    @Max(value = 999, message = "排序编号超出最大值999")
    private Integer orderNum;

    /** 部门状态 */
    private String status;

    /** 备注信息 */
    @Size(max = 250, message = "备注信息最多250字")
    private String remark;

    /** 创建时间 */
    private LocalDateTime createAt;
}
