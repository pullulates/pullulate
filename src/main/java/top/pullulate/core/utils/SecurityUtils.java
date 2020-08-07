package top.pullulate.core.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import top.pullulate.core.security.user.UserInfo;

/**
 * @功能描述: security安全工具类
 * @Author: pullulate
 * @Date: 2020/6/10 0010 13:03
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public UserInfo getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserInfo) {
            return (UserInfo) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public UserInfo getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }
}
