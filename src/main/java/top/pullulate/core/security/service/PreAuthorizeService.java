package top.pullulate.core.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.pullulate.core.utils.TokenUtils;

import java.util.Set;

/**
 * @功能描述:   权限注解服务类
 * @Author: xuyong
 * @Date: 2020/8/13 10:43
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Service("auth")
@RequiredArgsConstructor
public class PreAuthorizeService {

    private final TokenUtils tokenUtils;

    public boolean check(String permission) {
        Set<String> permissions = tokenUtils.getUserInfo().getPermissions();
        return permissions.contains(permission);
    }
}
