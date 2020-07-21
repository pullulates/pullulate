package top.pullulate.web.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.system.service.IPulRoleService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulRoleViewVo;
import top.pullulate.web.data.vo.system.PulRoleMenuVo;
import top.pullulate.web.data.vo.system.PulRoleVo;
import java.util.List;
import java.util.Set;

/**
 * @功能描述:   角色前端控制器
 * @Author: xuyong
 * @Date: 2020/7/16 15:17
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequestMapping("/system/roles")
@RequiredArgsConstructor
public class PulRoleController {

    private final IPulRoleService roleService;

    /**
     * 获取角色分页数据
     *
     * @param roleVo    查询参数
     * @param page  分页参数
     * @return
     */
    @GetMapping
    public P getRolePage(PulRoleVo roleVo, Page page) {
        IPage<List<PulRoleViewVo>> roles = roleService.getRolePage(roleVo, page);
        return P.data(roles);
    }

    /**
     * 保存角色信息
     *
     * @param roleVo    角色信息
     * @return
     */
    @PostMapping
    @OperationRecord(title = "角色管理-保存角色")
    public P saveRole(@RequestBody @Validated PulRoleVo roleVo) {
        return roleService.saveRole(roleVo);
    }

    /**
     * 修改角色信息
     *
     * @param roleVo    角色信息
     * @return
     */
    @PutMapping
    @OperationRecord(title = "角色管理-修改角色")
    public P updateRole(@RequestBody @Validated PulRoleVo roleVo) {
        return roleService.updateRole(roleVo);
    }

    /**
     * 修改角色状态
     *
     * @param roleVo    角色信息
     * @return
     */
    @PatchMapping
    @OperationRecord(title = "角色管理-修改角色状态")
    public P updateRoleStatus(@RequestBody PulRoleVo roleVo) {
        return roleService.updateRoleStatus(roleVo);
    }

    /**
     * 删除角色
     *
     * @param roleId    角色主键
     * @return
     */
    @DeleteMapping
    @OperationRecord(title = "角色管理-删除角色")
    public P deleteRole(String roleId) {
        return roleService.deleteRole(roleId);
    }

    /**
     * 获取角色拥有的菜单主键集合
     *
     * @param roleId    角色主键
     * @return
     */
    @GetMapping("/role-menuids")
    public P getRoleMenus(String roleId) {
        Set<String> menuIds = roleService.getRoleMenuIds(roleId);
        return P.data(menuIds);
    }

    /**
     * 修改角色菜单信息
     *
     * @param roleMenuVo    角色菜单信息
     * @return
     */
    @PostMapping("/role-menu")
    @OperationRecord(title = "角色管理-修改角色菜单")
    public P updateRoleMenus(@RequestBody @Validated PulRoleMenuVo roleMenuVo) {
        return roleService.updateRoleMenus(roleMenuVo);
    }
}
