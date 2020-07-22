package top.pullulate.web.data.vo.system;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @功能描述:   系统用户参数接收类
 * @Author: xuyong
 * @Date: 2020/6/29 12:51
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulUserVo {

    /** 用户主键 */
    private String userId;

    /** 账号名称 */
    @NotBlank(message = "用户名称不能为空")
    @Size(max = 20, message = "用户名称最多20字")
    private String userName;

    /** 姓名 */
    @Size(max = 20, message = "姓名最多20字")
    private String name;

    /** 证件号码 */
    @Size(max = 18, message = "证件号码最多18字")
    private String idcard;

    /** 手机号码 */
    @Size(max = 11, message = "手机号码最多11字")
    private String phone;

    /** 性别 */
    @Size(max = 1, message = "手机号码最多1个字符")
    private String sex;

    /** 头像 */
    private String headImg;

    /** 出生日期 */
    private LocalDate birth;

    /** 登录密码 */
    private String password;

    /** 锁定标识 */
    private String lockFlag;

    /** 错误登录次数 */
    private Integer errorLoginCount;

    /** 错误登录原因 */
    private String errorLoginReason;

    /** 删除标识 */
    private String deleteFlag;

    /** 创建时间 */
    private LocalDateTime createAt;

    /** 备注信息 */
    @Size(max = 1, message = "备注信息最多250个字符")
    private String remark;
}
