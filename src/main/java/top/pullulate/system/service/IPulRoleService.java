package top.pullulate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.system.entity.PulRole;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulRoleViewVo;
import top.pullulate.web.data.vo.system.PulRoleMenuVo;
import top.pullulate.web.data.vo.system.PulRoleVo;
import java.util.List;
import java.util.Set;

/**
 * @功能描述:   角色服务接口
 * @Author: pullulate
 * @Date: 2020/6/14 0014 16:36
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface IPulRoleService extends IService<PulRole> {

    /**
     * 根据用户主键获取用户角色集合
     * 不包含那些被禁用或被删除的角色信息
     *
     * @param userId    用户主键
     * @return  用户角色集合
     */
    List<PulRole> getUserRolesByUserId(String userId);

    /**
     * 获取角色分页数据
     *
     * @param roleVo    查询参数
     * @param page  分页参数
     * @return
     */
    IPage<List<PulRoleViewVo>> getRolePage(PulRoleVo roleVo, Page page);

    /**
     * 保存角色信息
     *
     * @param roleVo    角色信息
     * @return
     */
    P saveRole(PulRoleVo roleVo);

    /**
     * 修改角色信息
     *
     * @param roleVo    角色信息
     * @return
     */
    P updateRole(PulRoleVo roleVo);

    /**
     * 修改角色状态
     *
     * @param roleVo    角色信息
     * @return
     */
    P updateRoleStatus(PulRoleVo roleVo);

    /**
     * 删除角色
     *
     * @param roleId    角色主键
     * @return
     */
    P deleteRole(String roleId);

    /**
     * 获取角色的菜单主键集合
     *
     * @param roleId    角色主键
     * @return
     */
    Set<String> getRoleMenuIds(String roleId);

    /**
     * 修改角色菜单信息
     *
     * @param roleMenuVo    角色菜单信息
     * @return
     */
    P updateRoleMenus(PulRoleMenuVo roleMenuVo);

    /**
     * 获取角色选择下拉框数据
     *
     * @return
     */
    List<PulRoleViewVo> getRoleSelect();
}
