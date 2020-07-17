package top.pullulate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.common.constants.ParamConstant;
import top.pullulate.common.enums.*;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.core.utils.TokenUtils;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.system.mapper.PulMenuMapper;
import top.pullulate.system.service.IPulMenuService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.PulMenuViewVo;
import top.pullulate.web.data.vo.PulMenuVo;
import top.pullulate.web.data.dto.route.Meta;
import top.pullulate.web.data.dto.route.Router;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @功能描述:   菜单服务接口实现类
 * @Author: xuyong
 * @Date: 2020/6/15 12:43
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PulMenuServiceImpl extends ServiceImpl<PulMenuMapper, PulMenu> implements IPulMenuService {

    private final RedisUtils redisUtils;

    private final TokenUtils tokenUtils;

    /**
     * 根据用户主键查询用户菜单信息
     * 包含了菜单和按钮
     * 不包含那些被禁用或被删除的菜单信息
     *
     * @param userId    用户主键
     * @return  用户菜单信息
     */
    @Override
    public List<PulMenu> getUserMenusByUserId(String userId) {
        return baseMapper.selectUserMenusByUserId(userId);
    }

    /**
     * 构建前端路由信息
     *
     * @param menus    用户菜单集合
     * @return  前端路由信息
     */
    @Override
    public List<Router> getRouters(List<PulMenu> menus) {
        return buildRouters(menus, menus, new HashSet<String>());
    }

    /**
     * 根据用户菜单获取权限集合
     *
     * @param pulMenus    用户菜单
     * @return  权限集合
     */
    @Override
    public Set<String> getPermissions(List<PulMenu> pulMenus) {
        return pulMenus.stream().map(pulMenu -> pulMenu.getPermission()).collect(Collectors.toSet());
    }

    /**
     * 获取菜单树列表
     *
     * @param menuVo    查询参数
     * @return
     */
    @Override
    public List<PulMenuViewVo> getMenuTreeList(PulMenuVo menuVo) {
        List<PulMenuViewVo> menuListTree = redisUtils.getCacheList(CacheConstant.CACHE_MENU_LIST_TREE);
        if (ObjectUtil.isNull(menuVo) && CollectionUtil.isNotEmpty(menuListTree)) {
            Collections.sort(menuListTree);
            return menuListTree;
        }
        List<PulMenuViewVo> allMenus = getAllMenus();
        Set<String> dupMenuSet = new HashSet<>(allMenus.size());
        List<PulMenuViewVo> tree = buildMenuListTree(allMenus, allMenus, dupMenuSet);
        if (ObjectUtil.isNull(menuVo) && CollectionUtil.isEmpty(menuListTree)) {
            redisUtils.setCacheList(CacheConstant.CACHE_MENU_LIST_TREE, tree);
        }
        return tree;
    }

    /**
     * 获取菜单下拉选择树
     *
     * @return
     */
    @Override
    public List<Tree> getMenuTreeSelect() {
        List<PulMenuViewVo> allMenus = getAllMenus();
        Set<String> dupMenuSet = new HashSet<>(allMenus.size());
        List<Tree> tree = buildMenuTreeSelect(allMenus, allMenus, dupMenuSet);
        return tree;
    }

    /**
     * 保存路由
     *
     * @param menuVo    路由信息
     * @return
     */
    @Override
    public P saveMenu(PulMenuVo menuVo) {
        int count = count(Wrappers.<PulMenu>lambdaQuery()
                .eq(PulMenu::getName, menuVo.getName())
                .or()
                .eq(PulMenu::getPermission, menuVo.getPermission()));
        if (count > 0) {
            return P.error("路由已存在，请检查路由名称或权限标识");
        }
        PulMenu menu = buildMenuByMenuType(menuVo);
        if (ObjectUtil.isNull(menu)) {
            return P.error("上级菜单不存在或已被禁用！");
        }
        menu.setCreateBy(tokenUtils.getUserName());
        menu.setCreateAt(LocalDateTime.now());
        boolean result = save(menu);
        if (!result) {
            return P.error();
        }
        refreshMenuCache();
        return P.success();
    }

    /**
     * 修改路由
     *
     * @param menuVo    路由信息
     * @return
     */
    @Override
    public P updateMenu(PulMenuVo menuVo) {
        int count = count(Wrappers.<PulMenu>lambdaQuery()
                .ne(PulMenu::getMenuId, menuVo.getMenuId())
                .and(wrapper -> wrapper
                        .eq(PulMenu::getName, menuVo.getName())
                        .or()
                        .eq(PulMenu::getPermission, menuVo.getPermission())));
        if (count > 0) {
            return P.error("路由已存在，请检查路由名称或权限标识");
        }
        PulMenu menu = buildMenuByMenuType(menuVo);
        if (ObjectUtil.isNull(menu)) {
            return P.error("上级菜单不存在或已被禁用！");
        }
        menu.setMenuId(menuVo.getMenuId());
        menu.setUpdateBy(tokenUtils.getUserName());
        menu.setUpdateAt(LocalDateTime.now());
        boolean result = updateById(menu);
        if (!result) {
            return P.error();
        }
        refreshMenuCache();
        return P.success();
    }

    /**
     * 修改路由的状态
     *
     * @param menuVo    路由参数
     * @return
     */
    @Override
    public P updateMenuStatus(PulMenuVo menuVo) {
        PulMenu menu = BeanUtil.toBean(menuVo, PulMenu.class);
        menu.setUpdateBy(tokenUtils.getUserName());
        menu.setUpdateAt(LocalDateTime.now());
        boolean result = update(menu, Wrappers.<PulMenu>lambdaUpdate().eq(PulMenu::getMenuId, menu.getMenuId()));
        if (result) {
            refreshMenuCache();
        }
        return P.p(result);
    }

    /**
     * 删除路由信息
     *
     * @param menuId    路由主键
     * @return
     */
    @Override
    public P deleteMenu(String menuId) {
        int count = count(Wrappers.<PulMenu>lambdaQuery().eq(PulMenu::getParentId, menuId));
        if (count > 0) {
            return P.error("存在下级路由，不能直接删除");
        }
        boolean result = removeById(menuId);
        if (result) {
            refreshMenuCache();
        }
        return P.p(result);
    }

    /**
     * 构建菜单下拉选择树
     *
     * @param menus 待处理菜单
     * @param allMenus  所有菜单
     * @param dupMenuSet    已处理的菜单
     * @return
     */
    private List<Tree> buildMenuTreeSelect(List<PulMenuViewVo> menus, List<PulMenuViewVo> allMenus, Set<String> dupMenuSet) {
        List<Tree> trees = new ArrayList<>(allMenus.size());
        menus.forEach(menu -> {
            if (!dupMenuSet.contains(menu.getPermission())) {
                dupMenuSet.add(menu.getPermission());
                List<PulMenuViewVo> children = menus.stream()
                        .filter(item -> item.getParentId().equals(menu.getMenuId()))
                        .map(item -> BeanUtil.toBean(item, PulMenuViewVo.class))
                        .collect(Collectors.toList());
                Tree tree = new Tree(menu.getTitle(), menu.getMenuId(), menu.getMenuId());
                tree.setChildren(buildMenuTreeSelect(children, allMenus, dupMenuSet));
                children.forEach(child -> dupMenuSet.add(child.getPermission()));
                trees.add(tree);
            }
        });
        return trees;
    }

    /**
     * 构建菜单列表数
     *
     * @param menus 待处理菜单
     * @param allMenus  所有菜单
     * @param dupMenuSet    已处理的菜单
     * @return
     */
    private List<PulMenuViewVo> buildMenuListTree(List<PulMenuViewVo> menus, List<PulMenuViewVo> allMenus, Set<String> dupMenuSet) {
        List<PulMenuViewVo> menuViewVos = new ArrayList<>(allMenus.size());
        menus.forEach(menu -> {
            if (!dupMenuSet.contains(menu.getPermission())) {
                dupMenuSet.add(menu.getPermission());
                List<PulMenuViewVo> children = menus.stream()
                        .filter(item -> item.getParentId().equals(menu.getMenuId()))
                        .map(item -> BeanUtil.toBean(item, PulMenuViewVo.class))
                        .collect(Collectors.toList());
                PulMenuViewVo pulMenuViewVo = BeanUtil.toBean(menu, PulMenuViewVo.class);
                pulMenuViewVo.setChildren(buildMenuListTree(children, allMenus, dupMenuSet));
                children.forEach(child -> dupMenuSet.add(child.getPermission()));
                menuViewVos.add(pulMenuViewVo);
            }
        });
        return menuViewVos;
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 需要构建的菜单列表
     * @param allMenus 所有菜单列表
     * @param dupMenus 重复的菜单key集合
     * @return 路由列表
     */
    public List<Router> buildRouters(List<PulMenu> menus, List<PulMenu> allMenus, Set<String> dupMenus) {
        List<Router> routers = new ArrayList<Router>();
        for (PulMenu menu : menus) {
            if (dupMenus.contains(menu.getName())) {
                continue;
            }
            Router router = new Router(
                        menu.getName(),
                        menu.getPath(),
                        !Show.show(menu.getHidden()),
                        menu.getRedirect(),
                        menu.getComponent(),
                        new Meta(
                                menu.getTitle(),
                                menu.getUsTitle(),
                                menu.getIcon(),
                                new String[]{menu.getPermission()},
                                KeepAlive.keepAlive(menu.getKeepAlive()),
                                HiddenHeaderContent.hiddenHeaderContent(menu.getHiddenHeaderContent()),
                                Show.show(menu.getHidden()),
                                ParamConstant._BLANK.equals(menu.getTarget()) ? ParamConstant._BLANK : null
                        ),
                        !Show.show(menu.getHideChildrenInMenu())
            );
            List<PulMenu> childrenMenus = getChildrenMenuList(menu, allMenus);
            if (CollectionUtil.isNotEmpty(childrenMenus)) {
                router.setChildren(buildRouters(childrenMenus, allMenus, dupMenus));
                childrenMenus.forEach(childrenMenu -> dupMenus.add(childrenMenu.getName()));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取所有菜单信息
     *
     * @return
     */
    private List<PulMenuViewVo> getAllMenus() {
        List<PulMenuViewVo> allMenus = redisUtils.getCacheList(CacheConstant.CACHE_MENU_ALL);
        if (CollectionUtil.isEmpty(allMenus)) {
            allMenus = list(Wrappers.<PulMenu>lambdaQuery()
                    .orderByAsc(PulMenu::getMenuType)
                    .orderByAsc(PulMenu::getOrderNum))
                    .stream().map(menu-> BeanUtil.toBean(menu, PulMenuViewVo.class))
                    .collect(Collectors.toList());
            redisUtils.setCacheList(CacheConstant.CACHE_MENU_ALL, allMenus);
        }
        Collections.sort(allMenus);
        return allMenus;
    }

    /**
     * 获取子菜单集合
     * 不包含按钮菜单，因按钮菜单不显示在左侧菜单
     *
     * @param parentMenu 父菜单信息
     * @param menus 所有菜单集合
     * @return 子菜单集合
     */
    private List<PulMenu> getChildrenMenuList(PulMenu parentMenu, List<PulMenu> menus) {
        if (!MenuType.button(parentMenu.getMenuType())) {
            return menus.stream().filter(menu -> parentMenu.getMenuId().equals(menu.getParentId())).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 构建菜单信息
     *
     * @param menuVo    菜单数据
     * @return
     */
    private PulMenu buildMenuByMenuType(PulMenuVo menuVo) {
        PulMenu menu;
        if (MenuType.directory(menuVo.getMenuType())) {
            menu = new PulMenu(ParamConstant.TOP_MENU_ID, menuVo.getTitle(), menuVo.getUsTitle(), menuVo.getName(), menuVo.getPath(),
                    menuVo.getRedirect(), menuVo.getComponent(), KeepAlive.UNALIVE.getCode(), menuVo.getPermission(), null,
                    menuVo.getHidden(), menuVo.getHideChildrenInMenu(), Show.SHOW.getCode(), menuVo.getIcon(), menuVo.getMenuType(),
                    menuVo.getOrderNum(), menuVo.getRemark());
        } else {
            PulMenu parentMenu = getById(menuVo.getParentId());
            if (ObjectUtil.isNull(parentMenu) || DataStatus.disabled(parentMenu.getStatus())) {
                return null;
            }
            if (MenuType.button(menuVo.getMenuType())) {
                menu = new PulMenu(menuVo.getParentId(), menuVo.getTitle(), menuVo.getUsTitle(), menuVo.getName(), null,
                        null, null, null, menuVo.getPermission(), null,
                        Show.HIDE.getCode(), null, null, null, menuVo.getMenuType(),
                        menuVo.getOrderNum(), menuVo.getRemark());
            } else {
                menu = BeanUtil.toBean(menuVo, PulMenu.class);
                menu.setRedirect(null);
            }
        }
        return menu;
    }

    /**
     * 刷新缓存
     */
    private void refreshMenuCache() {
        log.info("--->刷新所有路由的缓存");
        redisUtils.deleteObject(CacheConstant.CACHE_MENU_ALL);
        List<PulMenuViewVo> allMenus = list(Wrappers.<PulMenu>lambdaQuery()
                .orderByAsc(PulMenu::getMenuType)
                .orderByAsc(PulMenu::getOrderNum))
                .stream().map(menu-> BeanUtil.toBean(menu, PulMenuViewVo.class))
                .collect(Collectors.toList());
        redisUtils.setCacheList(CacheConstant.CACHE_MENU_ALL, allMenus);

        log.info("--->刷新路由列表树的缓存");
        redisUtils.deleteObject(CacheConstant.CACHE_MENU_LIST_TREE);
        Set<String> dupMenuSet = new HashSet<>(allMenus.size());
        List<PulMenuViewVo> tree = buildMenuListTree(allMenus, allMenus, dupMenuSet);
        redisUtils.setCacheList(CacheConstant.CACHE_MENU_LIST_TREE, tree);
    }

}
