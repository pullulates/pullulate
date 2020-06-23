package top.pullulate.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.pullulate.common.enums.HiddenHeaderContent;
import top.pullulate.common.enums.KeepAlive;
import top.pullulate.common.enums.MenuType;
import top.pullulate.common.enums.Show;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.system.mapper.PulMenuMapper;
import top.pullulate.system.service.IPulMenuService;
import top.pullulate.web.data.vo.MetaVo;
import top.pullulate.web.data.vo.RouterVo;
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

    private final PulMenuMapper pulMenuMapper;

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
        return pulMenuMapper.selectUserMenusByUserId(userId);
    }

    /**
     * 构建前端路由信息
     *
     * @param menus    用户菜单集合
     * @return  前端路由信息
     */
    @Override
    public List<RouterVo> getRouters(List<PulMenu> menus) {
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
     * 构建前端路由所需要的菜单
     *
     * @param menus 需要构建的菜单列表
     * @param allMenus 所有菜单列表
     * @param dupMenus 重复的菜单key集合
     * @return 路由列表
     */
    public List<RouterVo> buildRouters(List<PulMenu> menus, List<PulMenu> allMenus, Set<String> dupMenus) {
        List<RouterVo> routers = new ArrayList<RouterVo>();
        for (PulMenu menu : menus) {
            if (dupMenus.contains(menu.getName())) {
                continue;
            }
            RouterVo router = new RouterVo(
                        menu.getName(),
                        menu.getPath(),
                        !Show.show(menu.getHidden()),
                        menu.getRedirect(),
                        menu.getComponent(),
                        new MetaVo(
                                menu.getTitle(),
                                menu.getIcon(),
                                new String[]{menu.getPermission()},
                                KeepAlive.keepAlive(menu.getKeepAlive()),
                                HiddenHeaderContent.hiddenHeaderContent(menu.getHiddenHeaderContent()),
                                Show.show(menu.getHidden()),
                                menu.getTarget()
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
     * 获取子菜单集合
     * 不包含按钮菜单，因按钮菜单不显示在左侧菜单
     *
     * @param parentMenu 父菜单信息
     * @param menus 所有菜单集合
     * @return 子菜单集合
     */
    private List<PulMenu> getChildrenMenuList(PulMenu parentMenu, List<PulMenu> menus) {
        if (!MenuType.button(parentMenu.getMenuType())) {
            List<PulMenu> childrenMenus = new ArrayList<>();
            for (PulMenu menu:menus) {
                if (parentMenu.getMenuId().equals(menu.getParentId())) {
                    childrenMenus.add(menu);
                }
            }
            return childrenMenus;
        }
        return null;
    }

}
