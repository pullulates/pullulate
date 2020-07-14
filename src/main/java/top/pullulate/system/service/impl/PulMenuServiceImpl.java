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
import top.pullulate.common.enums.HiddenHeaderContent;
import top.pullulate.common.enums.KeepAlive;
import top.pullulate.common.enums.MenuType;
import top.pullulate.common.enums.Show;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.system.mapper.PulMenuMapper;
import top.pullulate.system.service.IPulMenuService;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.PulMenuViewVo;
import top.pullulate.web.data.vo.PulMenuVo;
import top.pullulate.web.data.dto.route.Meta;
import top.pullulate.web.data.dto.route.Router;
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
        if (userId == null) {
            log.warn("获取用户菜单信息，用户主键为空");
            return null;
        }
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

}
