package top.pullulate.web.data.vo.system;

import lombok.Data;
import top.pullulate.common.validate.*;

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
    @NotBlank(message = "用户标识不能为空", groups = {Update.class, UpdatePassword.class, PatchStatus.class, ResetPassword.class})
    private String userId;

    /** 账号名称 */
    @NotBlank(message = "用户名称不能为空", groups = {Save.class, Update.class})
    @Size(max = 20, message = "用户名称最多20字", groups = {Save.class, Update.class})
    private String userName;

    /** 姓名 */
    @NotBlank(message = "姓名不能为空", groups = {Save.class, Update.class})
    @Size(max = 20, message = "姓名最多20字", groups = {Save.class, Update.class})
    private String name;

    /** 证件号码 */
    @NotBlank(message = "证件号码不能为空", groups = {Save.class, Update.class})
    @Size(max = 18, message = "证件号码最多18字", groups = {Save.class, Update.class})
    private String idcard;

    /** 手机号码 */
    @NotBlank(message = "手机号码不能为空", groups = {Save.class, Update.class})
    @Size(max = 11, message = "手机号码最多11字", groups = {Save.class, Update.class})
    private String phone;

    /** 性别 */
    private String sex;

    /** 头像 */
    private String headImg;

    /** 出生日期 */
    private LocalDate birth;

    /** 原密码 */
    @NotBlank(message = "原密码不能为空", groups = {UpdatePassword.class})
    private String oldPassword;

    /** 登录密码 */
    @NotBlank(message = "登录密码不能为空", groups = {UpdatePassword.class})
    private String password;

    /** 锁定标识 */
    private String lockFlag;

    /** 状态 */
    @NotBlank(message = "状态不能为空", groups = {PatchStatus.class})
    @Size(max = 1, message = "状态最多1个字符", groups = {PatchStatus.class})
    private String status;

    /** 错误登录次数 */
    private Integer errorLoginCount;

    /** 错误登录原因 */
    private String errorLoginReason;

    /** 删除标识 */
    private String deleteFlag;

    /** 创建时间 */
    private LocalDateTime createAt;

    /** 备注信息 */
    @Size(max = 250, message = "备注信息最多250个字符", groups = {Save.class, Update.class})
    private String remark;
}
