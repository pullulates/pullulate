package top.pullulate.web.controller.system;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.core.security.service.LoginService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.vo.system.LoginVo;

/**
 * @功能描述: 用户登录前端控制器
 * @Author: pullulate
 * @Date: 2020/6/10 0010 11:55
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
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
