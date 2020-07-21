package top.pullulate.web.data.vo.system;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @功能描述:   登录参数接收类
 * @Author: pullulates
 * @Date: 2020/6/13 0013 10:46
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Getter
@Setter
public class LoginVo {

    /** 用户名或手机号*/
    @NotBlank(message = "{username}{param.not.null}")
    private String userName;

    /** 密码或手机验证码 */
    @NotBlank(message = "{credential}{param.not.null}")
    private String credential;

    /** 图形验证码 */
    @NotBlank(message = "{captcha}{param.not.null}")
    private String captcha;

    /** 唯一标识 */
    private String uuid;

    /** 登录方式：0-账号密码；1-手机 */
    private String type;
}
