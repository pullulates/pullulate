package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @功能描述:   用户实体类
 * @Author: pullulates
 * @Date: 2020/6/10 0010 13:24
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulUser {

    /** 用户主键 */
    @TableId
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

    /** 密码加密盐 */
    private String salt;

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

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createAt;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    private LocalDateTime updateAt;
}
