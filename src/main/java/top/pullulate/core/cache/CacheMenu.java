package top.pullulate.core.cache;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.pullulate.common.service.MenuCacheService;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.system.service.IPulMenuService;
import top.pullulate.web.data.viewvo.system.PulMenuViewVo;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @功能描述:   项目启动时，缓存菜单信息
 * @Author: xuyong
 * @Date: 2020/7/9 15:38
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CacheMenu {

    private final MenuCacheService menuCacheService;

    private final IPulMenuService menuService;

    @PostConstruct
    public void init() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 启动缓存系统菜单信息任务");
        log.info(">>>>>>>>>>>>>>>> 缓存所有菜单信息");
        menuCacheService.deleteAllMenus();
        List<PulMenuViewVo> pulMenus = menuService.list(Wrappers.<PulMenu>lambdaQuery()
                .orderByAsc(PulMenu::getMenuType)
                .orderByAsc(PulMenu::getOrderNum))
                .stream().map(menu-> BeanUtil.toBean(menu, PulMenuViewVo.class))
                .collect(Collectors.toList());
        menuCacheService.setAllMenus(pulMenus);
        log.info(">>>>>>>>>>>>>>>> 缓存前端菜单列表树信息");
        menuCacheService.deleteMenuListTree();
        menuService.getMenuTreeList();
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 缓存系统菜单信息任务结束");
    }

    @PreDestroy
    public void destroy() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 启动销毁系统菜单缓存任务");
        log.info(">>>>>>>>>>>>>>>> 销毁菜单信息缓存");
        menuCacheService.deleteAllMenus();
        log.info(">>>>>>>>>>>>>>>> 销毁前端菜单列表树信息");
        menuCacheService.deleteMenuListTree();
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 销毁系统菜单缓存任务结束");
    }

}
