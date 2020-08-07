package top.pullulate.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.pullulate.system.entity.PulMenu;

import java.util.List;

/**
 * @功能描述:   菜单数据层
 * @Author: xuyong
 * @Date: 2020/6/15 12:44
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 * @Company: 合肥瑞智电力电子有限公司
 */
public interface PulMenuMapper extends BaseMapper<PulMenu> {

    /**
     * 根据用户主键查询用户菜单信息
     * 包含了菜单和按钮
     *
     * @param userId    用户主键
     * @return  用户菜单信息
     */
    List<PulMenu> selectUserMenusByUserId(String userId);
}
