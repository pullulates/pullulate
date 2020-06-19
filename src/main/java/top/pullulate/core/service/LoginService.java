package top.pullulate.core.service;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.common.constants.RSAConstant;
import top.pullulate.common.enums.LockFlag;
import top.pullulate.common.enums.LoginType;
import top.pullulate.core.user.UserInfo;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.core.utils.TokeUtils;
import top.pullulate.system.entity.PulDept;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.system.entity.PulRole;
import top.pullulate.system.service.IPulDeptService;
import top.pullulate.system.service.IPulMenuService;
import top.pullulate.system.service.IPulRoleService;
import top.pullulate.utils.security.RSAUtils;
import top.pullulate.web.data.dto.P;
import top.pullulate.web.data.vo.LoginVo;
import top.pullulate.web.data.vo.RouterVo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @功能描述: 用户登录
 * @Author: pullulates
 * @Date: 2020/6/10 0010 13:05
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginService {

    private final RedisUtils redisUtils;

    private final TokeUtils tokeUtils;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final IPulRoleService pulRoleService;

    private final IPulMenuService pulMenuService;

    private final IPulDeptService pulDeptService;

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
                loginVo.setCredential(RSAUtils.decryptByPrivateKey(RSAConstant.PRIVATE_KEY, loginVo.getCredential()));
            } catch (Exception e) {
                log.warn("用户：{}登录时无法解密登录凭证，登录失败。异常信息：{}", loginVo.getUserName(), e);
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
        // 获取用户信息
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        // 在保证用户输入的登录凭证是正确的情况下，再去拉取其它用户信息
        PulDept dept = pulDeptService.getUserDeptByUserId(userInfo.getUserId());
        List<PulRole> roles = pulRoleService.getUserRolesByUserId(userInfo.getUserId());
        List<RouterVo> routers = pulMenuService.buildRoutersByUserId(userInfo.getUserId());
        Set<String> permissions = pulMenuService.getUserPermissionsByUserId(userInfo.getUserId());
        userInfo.setDept(dept);
        userInfo.setRoles(roles);
        userInfo.setRouters(routers);
        userInfo.setPermissions(permissions);
        String token = tokeUtils.createToken(userInfo);
        return P.token(token);
    }
}
