package top.pullulate.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.core.service.LoginService;
import top.pullulate.web.data.dto.P;
import top.pullulate.web.data.vo.LoginVo;

/**
 * @功能描述: 用户登录前端控制器
 * @Author: pullulates
 * @Date: 2020/6/10 0010 11:55
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public P login(@Validated @RequestBody LoginVo loginVo) {
        return loginService.login(loginVo);
    }
}
