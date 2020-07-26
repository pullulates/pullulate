package top.pullulate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pullulate.system.entity.PulSiteConfig;
import top.pullulate.system.mapper.PulSiteConfigMapper;
import top.pullulate.system.service.IPulSiteConfigService;
import top.pullulate.web.data.viewvo.system.PulSiteConfigViewVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @功能描述:   网站配置服务接口实现类
 * @Author: xuyong
 * @Date: 2020/7/26 20:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Service
public class PulSiteConfigServiceImpl extends ServiceImpl<PulSiteConfigMapper, PulSiteConfig> implements IPulSiteConfigService {

    /**
     * 获取所有的网站配置信息
     *
     * @return
     */
    @Override
    public List<PulSiteConfigViewVo> getAllSiteConfig() {
        return list().stream()
                .map(item -> BeanUtil.toBean(item, PulSiteConfigViewVo.class))
                .collect(Collectors.toList());
    }
}
