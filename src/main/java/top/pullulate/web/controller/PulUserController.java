package top.pullulate.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.core.user.UserInfo;
import top.pullulate.core.utils.TokeUtils;
import top.pullulate.system.entity.PulUser;
import top.pullulate.system.service.IPulUserService;
import top.pullulate.web.data.dto.P;
import javax.servlet.http.HttpServletRequest;

/**
 * @功能描述:   用户前端控制器
 * @Author: pullulates
 * @Date: 2020/6/12 0012 19:06
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequestMapping("/system/users")
@RequiredArgsConstructor
public class PulUserController {

    private final IPulUserService userService;

    private final TokeUtils tokeUtils;

    /**
     * 获取当前登录用户的用户信息
     * 包含了角色、部门、菜单、权限信息
     *
     * @return  当前登录用户的用户信息
     */
    @GetMapping("/get")
    public P getCurrentLoginUserInfo(HttpServletRequest request) {
        UserInfo userInfo = tokeUtils.getUserInfo(request);
        return P.data(userInfo);
    }

    /**
     * 根据用户主键获取用户信息
     *
     * @param userId    用户主键
     * @return  用户信息
     */
    @GetMapping("/info/{userId}")
    public P getUserInfoByUserId(@PathVariable("userId") String userId) {
        PulUser pulUser = userService.getUserInfoByUserId(userId);
        return P.data(pulUser);
    }
}
