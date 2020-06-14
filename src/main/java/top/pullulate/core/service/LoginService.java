package top.pullulate.core.service;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.common.constants.RSAConstant;
import top.pullulate.common.enums.LoginType;
import top.pullulate.core.user.UserInfo;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.core.utils.TokeUtils;
import top.pullulate.utils.security.RSAUtils;
import top.pullulate.web.data.dto.P;
import top.pullulate.web.data.vo.LoginVo;

/**
 * @功能描述: 用户登录
 * @Author: pullulates
 * @Date: 2020/6/10 0010 13:05
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Component
@RequiredArgsConstructor
public class LoginService {

    private final RedisUtils redisUtils;

    private final TokeUtils tokeUtils;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public P login(LoginVo loginVo) {
        // 验证图形验证码
        String captchaKey = CacheConstant.CACHE_IMAGE_CAPTCHA_PREFFIX.concat(loginVo.getUuid());
        String captcha = redisUtils.getCacheObject(captchaKey);
        if (StrUtil.isBlank(captcha)) {
            return P.error("captcha.has.expired");
        }
        redisUtils.deleteObject(captchaKey);
        if (!StrUtil.equalsIgnoreCase(captcha,loginVo.getCaptcha())) {
            return P.error("captcha.text.error");
        }
        if (LoginType.loginWithPhoneCaptcha(loginVo.getType()))
        // 如果需要，验证手机验证码
        {
            String smsCaptchaKey = CacheConstant.CACHE_PHONE_CAPTCHA_PREFFIX.concat(loginVo.getUuid());
            String smsCaptcha = redisUtils.getCacheObject(smsCaptchaKey);
            if (StrUtil.isBlank(smsCaptcha)) {
                return P.error("sms.captcha.has.expired");
            }
            redisUtils.deleteObject(smsCaptchaKey);
            if (!smsCaptcha.equals(loginVo.equals(loginVo.getCredential()))) {
                return P.error("sms.captcha.text.error");
            }
        }
        else if (LoginType.loginWithUserNamePassword(loginVo.getType()))
        // 密码解密
        {
            try {
                loginVo.setCredential(RSAUtils.encryptByPrivateKey(RSAConstant.PRIVATE_KEY, loginVo.getCredential()));
            } catch (Exception e) {
                return P.error("login.password.error");
            }
        } else
        // 不支持的登录方式
        {
            return P.error("login.type.unsupport");
        }
        // 构建登录信息
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginVo.getUserName(), loginVo.getCredential());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        String token = tokeUtils.createToken(userInfo);
        return P.success(token);
    }
}
