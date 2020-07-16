package top.pullulate.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.pullulate.system.entity.PulRole;
import top.pullulate.system.mapper.PulRoleMapper;
import top.pullulate.system.service.IPulRoleService;
import top.pullulate.web.data.viewvo.PulRoleViewVo;
import top.pullulate.web.data.vo.PulRoleVo;

import java.util.List;
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
        return baseMapper.getRolePage(roleVo, page);
    }
}
