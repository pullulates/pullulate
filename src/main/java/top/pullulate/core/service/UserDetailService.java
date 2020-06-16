package top.pullulate.core.service;

import cn.hutool.core.util.ReUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.RegexConstant;
import top.pullulate.core.user.UserInfo;
import top.pullulate.system.entity.PulUser;
import top.pullulate.system.service.IPulUserService;

/**
 * @功能描述:   用户信息获取
 * @Author: pullulates
 * @Date: 2020/6/10 0010 12:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final IPulUserService pulUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        PulUser pulUser = null;
        if (ReUtil.isMatch(RegexConstant.PHONE, userName)){
            pulUser = pulUserService.getUserInfoByPhone(userName);
        }
        // 为了防止用户名与手机号码相似，导致查询不到，这里单独查询
        if (pulUser == null) {
            pulUser = pulUserService.getUserInfoByUserName(userName);
        }
        if (pulUser == null) {
            throw new UsernameNotFoundException("login.user.not.found");
        }
        return new UserInfo(pulUser.getUserId(), pulUser);
    }
}
