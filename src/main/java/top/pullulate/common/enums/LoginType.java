package top.pullulate.common.enums;

/**
 * @功能描述: 登陆方式
 * @Author: pullulate
 * @Date: 2020/6/14 0014 13:14
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public enum LoginType {

    USERNAME_PASSWORD("0", "login.type.username.password"), PHONE_CAPTCHA("1", "login.type.phone.captcha");

    private String code;

    private String label;

    LoginType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Boolean loginWithUserNamePassword(String code) {
        return USERNAME_PASSWORD.code.equals(code);
    }

    public static Boolean loginWithPhoneCaptcha(String code) {
        return PHONE_CAPTCHA.code.equals(code);
    }
}
