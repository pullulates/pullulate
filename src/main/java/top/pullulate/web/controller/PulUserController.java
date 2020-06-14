package top.pullulate.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.system.entity.PulUser;
import top.pullulate.system.service.IPulUserService;
import top.pullulate.web.data.dto.P;

/**
 * @功能描述:   用户前端控制器
 * @Author: pullulates
 * @Date: 2020/6/12 0012 19:06
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequestMapping("/pul/users")
@RequiredArgsConstructor
public class PulUserController {

    private final IPulUserService userService;

    /**
     * 获取用户信息
     *
     * @param userId 用户主键
     * @return
     */
    @GetMapping("/get/{userId}")
    public P getUserInfo(@PathVariable("userId") String userId) {
        PulUser pulUser = userService.getUserInfoByUserId(userId);
        return P.success(pulUser);
    }
}
