package top.pullulate.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.pullulate.system.entity.PulRole;
import top.pullulate.system.mapper.PulRoleMapper;
import top.pullulate.system.service.IPulRoleService;

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

    private final PulRoleMapper pulRoleMapper;

    /**
     * 根据用户主键获取用户角色集合
     *
     * @param userId    用户主键
     * @return  用户角色集合
     */
    @Override
    public List<PulRole> getUserRolesByUserId(String userId) {
        return pulRoleMapper.selectUserRolesByUserId(userId)
                .stream().distinct().collect(Collectors.toList());
    }
}
