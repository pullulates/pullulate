package top.pullulate.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.web.data.viewvo.PulMenuViewVo;
import java.util.List;

/**
 * 菜单缓存服务类
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MenuCacheService {

    private final RedisUtils redisUtils;

    /**
     * 获取所有菜单的缓存数据
     */
    public List<PulMenuViewVo> getAllMenus() {
        List<PulMenuViewVo> menus = redisUtils.getCacheList(CacheConstant.CACHE_MENU_ALL);
        return menus;
    }

    /**
     * 设置所有菜单的缓存
     */
    public void setAllMenus(List<PulMenuViewVo> menus) {
        redisUtils.setCacheList(CacheConstant.CACHE_MENU_ALL, menus);
    }

    /**
     * 删除所有菜单的缓存
     */
    public void deleteAllMenus() {
        redisUtils.deleteObject(CacheConstant.CACHE_MENU_ALL);
    }

    /**
     * 获取菜单列表树的缓存
     */
    public List<PulMenuViewVo> getMenuListTree() {
        List<PulMenuViewVo> menuListTree = redisUtils.getCacheList(CacheConstant.CACHE_MENU_LIST_TREE);
        return menuListTree;
    }

    /**
     * 设置菜单列表树的缓存
     */
    public void setMenuListTree(List<PulMenuViewVo> menuListTree) {
        redisUtils.setCacheList(CacheConstant.CACHE_MENU_LIST_TREE, menuListTree);
    }

    /**
     * 删除菜单列表树的缓存
     */
    public void deleteMenuListTree() {
        redisUtils.deleteObject(CacheConstant.CACHE_MENU_LIST_TREE);
    }

    /**
     * 刷新缓存
     */
    public void refreshMenuCache(List<PulMenuViewVo> menus, List<PulMenuViewVo> tree) {
        log.info("--->刷新所有路由的缓存");
        deleteAllMenus();
        setAllMenus(menus);
        log.info("--->刷新路由列表树的缓存");
        deleteMenuListTree();
        setMenuListTree(tree);
    }

}
