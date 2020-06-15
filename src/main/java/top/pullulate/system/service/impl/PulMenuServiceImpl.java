package top.pullulate.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.system.mapper.PulMenuMapper;
import top.pullulate.system.service.IPulMenuService;

import java.util.List;

/**
 * @功能描述:   菜单服务接口实现类
 * @Author: xuyong
 * @Date: 2020/6/15 12:43
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 * @Company: 合肥瑞智电力电子有限公司
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PulMenuServiceImpl extends ServiceImpl<PulMenuMapper, PulMenu> implements IPulMenuService {

    private final PulMenuMapper pulMenuMapper;

    /**
     * 根据用户主键查询用户菜单信息
     * 包含了菜单和按钮
     *
     * @param userId    用户主键
     * @return  用户菜单信息
     */
    @Override
    public List<PulMenu> getUserMenusByUserId(String userId) {
        if (userId == null) {
            log.warn("获取用户菜单信息，用户主键为空");
            return null;
        }
        return pulMenuMapper.selectUserMenusByUserId(userId);
    }
}
