package top.pullulate.web.data.viewvo.system;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @功能描述:   部门视图实体类
 * @Author: xuyong
 * @Date: 2020/6/29 13:16
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Getter
@Setter
public class PulDeptViewVo implements Comparable<PulDeptViewVo> {

    /** 部门主键 */
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

    /** 备注信息 */
    private String remark;

    /** 创建时间 */
    private LocalDateTime createAt;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PulDeptViewVo> children;

    @Override
    public int compareTo(PulDeptViewVo o) {
        int result = this.getOrderNum() - o.getOrderNum();
        if (result == 0) {
            result = this.getCreateAt().compareTo(o.getCreateAt());
        }
        return result;
    }

    public PulDeptViewVo() {
    }

    public PulDeptViewVo(String deptId, String deptNo, String deptName, String parentId, Integer orderNum, String status, String remark, LocalDateTime createAt) {
        this.deptId = deptId;
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.parentId = parentId;
        this.orderNum = orderNum;
        this.status = status;
        this.remark = remark;
        this.createAt = createAt;
    }
}
