package top.pullulate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.system.entity.PulMenu;
import top.pullulate.web.data.vo.RouterVo;

import java.util.List;
import java.util.Set;

/**
 * @功能描述:   菜单服务接口
 * @Author: xuyong
 * @Date: 2020/6/15 12:42
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
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
     * @param userId    用户主键
     * @return  前端路由信息
     */
    List<RouterVo> buildRoutersByUserId(String userId);

    /**
     * 根据用户主键查询权限集合
     *
     * @param userId    用户主键
     * @return  权限集合
     */
    Set<String> getUserPermissionsByUserId(String userId);
}
