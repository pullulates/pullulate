package top.pullulate.web.controller.wechat;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.web.data.dto.response.P;

/**
 * @功能描述:   微信公众号前端控制器
 * @Author: pullulate
 * @Date: 2020年9月1日17:36:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/wechat/official-account")
public class WechatOfficialAccountController {

    @GetMapping
    @PreAuthorize("hasAuthority('wechat.official.account.query')")
    public P getOfficialAccountList() {
        return null;
    }

}

