package top.pullulate.web.controller.monitor;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.core.security.user.UserInfo;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.core.utils.TokenUtils;
import top.pullulate.web.data.dto.response.P;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @功能描述:   在线用户前端控制器
 * @Author: xuyong
 * @Date: 2020/7/28 20:42
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/monitor/online")
public class OnlineController {

    private final RedisUtils redisUtils;

    private final TokenUtils tokenUtils;

    /**
     * 获取在线用户
     *
     * @param userName  登录名称
     * @return
     */
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('monitor.online.user.query')")
    public P getOnlineUsers(String userName) {
        Collection<String> keys = redisUtils.keys(CacheConstant.CACHE_USER_INFO_PREFFIX + "*");
        List<UserInfo> userInfos = redisUtils.getCacheListByPreffixKey(keys);
        if (StrUtil.isNotBlank(userName)) {
            return P.data(userInfos.stream()
                    .filter(userInfo -> userInfo.getUsername().equals(userName))
                    .collect(Collectors.toList()));
        }
        return P.data(userInfos);
    }

    /**
     * 强制用户下线
     *
     * @param token 会话凭证
     * @return
     */
    @DeleteMapping("/users")
    @PreAuthorize("hasAuthority('monitor.online.user.del')")
    @OperationRecord(title = "在线用户-强制下线")
    public P kickedOut(String token) {
        String curToken = tokenUtils.getUserInfo().getToken();
        if (curToken.equals(token)) {
            return P.error("不能将自己踢出会话！");
        }
        String tokenKey = tokenUtils.getTokenKey(token);
        redisUtils.deleteObject(tokenKey);
        return P.success();
    }
}
