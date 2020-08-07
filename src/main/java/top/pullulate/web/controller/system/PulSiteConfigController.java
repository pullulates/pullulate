package top.pullulate.web.controller.system;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.system.service.IPulSiteConfigService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulSiteConfigViewVo;

import java.util.List;

/**
 * @功能描述:   网站配置前端控制器
 * @Author: xuyong
 * @Date: 2020/7/26 20:11
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/siteconfigs")
public class PulSiteConfigController {

    private final IPulSiteConfigService siteConfigService;

    /**
     * 获取所有的网站配置信息
     *
     * @return
     */
    @GetMapping
    public P getAllSiteConfig() {
        List<PulSiteConfigViewVo> siteConfigViewVos = siteConfigService.getAllSiteConfig();
        return P.data(siteConfigViewVos);
    }
}
