package top.pullulate.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.pullulate.system.entity.PulRoleMenu;
import top.pullulate.system.mapper.PulRoleMenuMapper;
import top.pullulate.system.service.IPulRoleMenuService;

/**
 * @功能描述:   角色菜单服务接口实现类
 * @Author: xuyong
 * @Date: 2020/7/20 10:56
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Service
@RequiredArgsConstructor
public class PulRoleMenuServiceImpl extends ServiceImpl<PulRoleMenuMapper, PulRoleMenu> implements IPulRoleMenuService {
}
