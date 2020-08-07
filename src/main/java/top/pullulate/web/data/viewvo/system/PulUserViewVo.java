package top.pullulate.web.data.viewvo.system;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @功能描述:   系统用户视图实体类
 * @Author: xuyong
 * @Date: 2020/6/29 12:56
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulUserViewVo {

    /** 用户主键 */
    private String userId;

    /** 账号名称 */
    private String userName;

    /** 姓名 */
    private String name;

    /** 证件号码 */
    private String idcard;

    /** 手机号码 */
    private String phone;

    /** 性别 */
    private String sex;

    /** 头像 */
    private String headImg;

    /** 出生日期 */
    private LocalDate birth;

    /** 锁定标识 */
    private String lockFlag;

    /** 状态 */
    private String status;

    /** 错误登录次数 */
    private Integer errorLoginCount;

    /** 错误登录原因 */
    private String errorLoginReason;

    /** 备注信息 */
    private String remark;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createAt;

    private List<PulRoleViewVo> roleList;

    private PulDeptViewVo dept;
}
