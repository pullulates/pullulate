package top.pullulate.core.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.system.entity.PulSiteConfig;
import top.pullulate.system.service.IPulSiteConfigService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @功能描述:   项目启动时，缓存网站参数
 * @Author: xuyong
 * @Date: 2020/7/26 20:24
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CacheSiteConfig {

    private final RedisUtils redisUtils;

    private final IPulSiteConfigService siteConfigService;

    @PostConstruct
    public void init() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 启动缓存网站参数任务");
        List<PulSiteConfig> siteConfigs = siteConfigService.list();
        siteConfigs.forEach(config -> {
            redisUtils.deleteObject(CacheConstant.CACHE_SITE_CONFIG_PREFFIX + config.getConfigKey());
            redisUtils.setCacheObject(CacheConstant.CACHE_SITE_CONFIG_PREFFIX + config.getConfigKey(), config.getConfigValue());
        });
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 缓存网站参数任务结束");
    }

    @PreDestroy
    public void destroy() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 启动销毁网站参数任务");
        List<PulSiteConfig> siteConfigs = siteConfigService.list();
        siteConfigs.forEach(config -> redisUtils.deleteObject(CacheConstant.CACHE_SITE_CONFIG_PREFFIX + config.getConfigKey()));
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 销毁网站参数任务结束");
    }
}
