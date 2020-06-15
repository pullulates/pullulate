package top.pullulate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.system.entity.PulMenu;

import java.util.List;

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
     *
     * @param userId    用户主键
     * @return  用户菜单信息
     */
    List<PulMenu> getUserMenusByUserId(String userId);
}
