package top.pullulate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.system.entity.PulRole;
import top.pullulate.web.data.viewvo.PulRoleViewVo;
import top.pullulate.web.data.vo.PulRoleVo;

import java.util.List;

/**
 * @功能描述:   角色服务接口
 * @Author: pullulates
 * @Date: 2020/6/14 0014 16:36
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
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
}
