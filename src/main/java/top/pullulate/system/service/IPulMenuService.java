package top.pullulate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.system.PulMenuViewVo;
import top.pullulate.web.data.vo.system.PulMenuVo;
import top.pullulate.web.data.dto.route.Router;

import java.util.List;
import java.util.Set;

/**
 * @功能描述:   菜单服务接口
 * @Author: xuyong
 * @Date: 2020/6/15 12:42
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 * @Company: 合肥瑞智电力电子有限公司
 */
public interface IPulMenuService extends IService<PulMenu> {

    /**
     * 根据用户主键查询用户菜单信息
     * 包含了菜单和按钮
     * 不包含那些被禁用或被删除的菜单信息
     *
     * @param userId    用户主键
     * @return  用户菜单信息
     */
    List<PulMenu> getUserMenusByUserId(String userId);

    /**
     * 构建前端路由信息
     *
     * @param menus    用户菜单
     * @return  前端路由信息
     */
    List<Router> getRouters(List<PulMenu> menus);

    /**
     * 根据用户菜单获取权限集合
     *
     * @param menus    用户菜单
     * @return  权限集合
     */
    Set<String> getPermissions(List<PulMenu> menus);

    /**
     * 获取菜单树列表
     *
     * @return
     */
    List<PulMenuViewVo> getMenuTreeList();

    /**
     * 获取菜单下拉选择树
     *
     * @return
     */
    List<Tree> getMenuTreeSelect();

    /**
     * 保存路由
     *
     * @param menuVo    路由信息
     * @return
     */
    P saveMenu(PulMenuVo menuVo);

    /**
     * 修改路由
     *
     * @param menuVo    路由信息
     * @return
     */
    P updateMenu(PulMenuVo menuVo);

    /**
     * 修改路由的状态
     *
     * @param menuVo    路由参数
     * @return
     */
    P updateMenuStatus(PulMenuVo menuVo);

    /**
     * 删除路由信息
     *
     * @param menuId    路由主键
     * @return
     */
    P deleteMenu(String menuId);

    /**
     * 获取所有父级菜单的主键集合
     *
     * @return
     */
    Set<String> getParentMenuIds();
}
