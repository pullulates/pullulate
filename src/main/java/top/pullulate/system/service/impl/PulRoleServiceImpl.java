package top.pullulate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pullulate.core.utils.TokenUtils;
import top.pullulate.system.entity.PulRole;
import top.pullulate.system.entity.PulRoleMenu;
import top.pullulate.system.entity.PulUserRole;
import top.pullulate.system.mapper.PulRoleMapper;
import top.pullulate.system.mapper.PulUserRoleMapper;
import top.pullulate.system.service.IPulRoleMenuService;
import top.pullulate.system.service.IPulRoleService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulRoleViewVo;
import top.pullulate.web.data.vo.system.PulRoleMenuVo;
import top.pullulate.web.data.vo.system.PulRoleVo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @功能描述:   角色服务接口实现类
 * @Author: pullulates
 * @Date: 2020/6/14 0014 20:21
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Service
@RequiredArgsConstructor
public class PulRoleServiceImpl extends ServiceImpl<PulRoleMapper, PulRole> implements IPulRoleService {

    private final TokenUtils tokenUtils;

    private final PulUserRoleMapper userRoleMapper;

    private final IPulRoleMenuService roleMenuService;

    /**
     * 根据用户主键获取用户角色集合
     * 不包含那些被禁用或被删除的角色信息
     *
     * @param userId    用户主键
     * @return  用户角色集合
     */
    @Override
    public List<PulRole> getUserRolesByUserId(String userId) {
        return baseMapper.selectUserRolesByUserId(userId)
                .stream().distinct().collect(Collectors.toList());
    }

    /**
     * 获取角色分页数据
     *
     * @param roleVo    查询参数
     * @param page  分页参数
     * @return
     */
    @Override
    public IPage<List<PulRoleViewVo>> getRolePage(PulRoleVo roleVo, Page page) {
        return baseMapper.selectRolePage(roleVo, page);
    }

    /**
     * 保存角色信息
     *
     * @param roleVo    角色信息
     * @return
     */
    @Override
    public P saveRole(PulRoleVo roleVo) {
        int count = count(Wrappers.<PulRole>lambdaQuery()
                .eq(PulRole::getRoleName, roleVo.getRoleName())
                .or()
                .eq(PulRole::getRoleKey, roleVo.getRoleKey()));
        if (count > 0) {
            return P.error("角色信息已存在!");
        }
        PulRole role = BeanUtil.toBean(roleVo, PulRole.class);
        role.setCreateAt(LocalDateTime.now());
        role.setCreateBy(tokenUtils.getUserName());
        boolean result = save(role);
        return result ? P.success() : P.error();
    }

    /**
     * 修改角色信息
     *
     * @param roleVo    角色信息
     * @return
     */
    @Override
    public P updateRole(PulRoleVo roleVo) {
        int count = count(Wrappers.<PulRole>lambdaQuery()
                .ne(PulRole::getRoleId, roleVo.getRoleId())
                .and(wrapper -> wrapper
                        .eq(PulRole::getRoleName, roleVo.getRoleName())
                        .or()
                        .eq(PulRole::getRoleKey, roleVo.getRoleKey()))
                );
        if (count > 0) {
            return P.error("角色信息已存在!");
        }
        PulRole role = BeanUtil.toBean(roleVo, PulRole.class);
        role.setUpdateAt(LocalDateTime.now());
        role.setUpdateBy(tokenUtils.getUserName());
        boolean result = updateById(role);
        return result ? P.success() : P.error();
    }

    /**
     * 修改角色状态
     *
     * @param roleVo    角色信息
     * @return
     */
    @Override
    public P updateRoleStatus(PulRoleVo roleVo) {
        PulRole role = BeanUtil.toBean(roleVo, PulRole.class);
        role.setUpdateAt(LocalDateTime.now());
        role.setUpdateBy(tokenUtils.getUserName());
        boolean result = update(role, Wrappers.<PulRole>lambdaUpdate().eq(PulRole::getRoleId, role.getRoleId()));
        return result ? P.success() : P.error();
    }

    /**
     * 删除角色
     *
     * @param roleId    角色主键
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P deleteRole(String roleId) {
        int count = userRoleMapper.selectCount(Wrappers.<PulUserRole>lambdaQuery().eq(PulUserRole::getRoleId, roleId));
        if (count > 0) {
            return P.error("存在已分配的用户，不能直接删除!");
        }
        removeById(roleId);
        // 删除角色已分配的路由信息
        roleMenuService.remove(Wrappers.<PulRoleMenu>lambdaQuery().eq(PulRoleMenu::getRoleId, roleId));
        return P.success();
    }

    /**
     * 获取角色的菜单信息
     *
     *
     * @param roleId    角色主键
     * @return
     */
    @Override
    public Set<String> getRoleMenuIds(String roleId) {
        Set<String> menuIds = roleMenuService.list(Wrappers.<PulRoleMenu>lambdaQuery()
                .eq(PulRoleMenu::getRoleId, roleId))
                .stream()
                .map(roleMenu -> roleMenu.getMenuId())
                .collect(Collectors.toSet());
        return menuIds;
    }

    /**
     * 修改角色菜单信息
     *
     * @param roleMenuVo    角色菜单信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P updateRoleMenus(PulRoleMenuVo roleMenuVo) {
        // 重新分配之前删除已有的角色菜单信息
        roleMenuService.remove(Wrappers.<PulRoleMenu>lambdaQuery()
                .eq(PulRoleMenu::getRoleId, roleMenuVo.getRoleId()));
        // 分配新的角色菜单
        if (StrUtil.isNotBlank(roleMenuVo.getMenuIds())) {
            String[] menuIds = Convert.toStrArray(roleMenuVo.getMenuIds());
            List<PulRoleMenu> roleMenus = new ArrayList<>(menuIds.length);
            Arrays.stream(menuIds).forEach(menuId -> roleMenus.add(new PulRoleMenu(roleMenuVo.getRoleId(), menuId)));
            roleMenuService.saveBatch(roleMenus);
        }
        return P.success();
    }

    /**
     * 获取角色选择下拉框数据
     *
     * @return
     */
    @Override
    public List<PulRoleViewVo> getRoleSelect() {
        List<PulRoleViewVo> list = list().stream()
                .map(item -> BeanUtil.toBean(item, PulRoleViewVo.class))
                .collect(Collectors.toList());
        return list;
    }
}
