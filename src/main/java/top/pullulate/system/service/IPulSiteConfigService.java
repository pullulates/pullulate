package top.pullulate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.system.entity.PulSiteConfig;
import top.pullulate.web.data.viewvo.system.PulSiteConfigViewVo;

import java.util.List;

/**
 * @功能描述:   网站配置服务接口
 * @Author: xuyong
 * @Date: 2020/7/26 20:13
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public interface IPulSiteConfigService extends IService<PulSiteConfig> {

    /**
     * 获取所有的网站配置信息
     *
     * @return
     */
    List<PulSiteConfigViewVo> getAllSiteConfig();
}
