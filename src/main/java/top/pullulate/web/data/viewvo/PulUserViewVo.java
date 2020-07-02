package top.pullulate.web.data.viewvo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @功能描述:   系统用户视图实体类
 * @Author: xuyong
 * @Date: 2020/6/29 12:56
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
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

    /** 错误登录次数 */
    private Integer errorLoginCount;

    /** 错误登录原因 */
    private String errorLoginReason;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createAt;

    private List<PulRoleViewVo> roleList;
}
