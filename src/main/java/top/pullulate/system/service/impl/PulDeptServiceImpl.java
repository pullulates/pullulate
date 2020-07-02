package top.pullulate.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.pullulate.system.entity.PulDept;
import top.pullulate.system.mapper.PulDeptMapper;
import top.pullulate.system.service.IPulDeptService;

/**
 * @功能描述:   部门服务接口实现类
 * @Author: xuyong
 * @Date: 2020/6/18 10:33
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PulDeptServiceImpl extends ServiceImpl<PulDeptMapper, PulDept> implements IPulDeptService {

    /**
     * 根据用户主键获取其所在部门信息
     * 包含被禁用，不包含被删除的数据
     *
     * @param userId    用户主键
     * @return  所在部门信息
     */
    @Override
    public PulDept getUserDeptByUserId(String userId) {
        if (userId == null) {
            log.warn("获取用户部门信息，用户主键为空");
            return null;
        }
        return baseMapper.selectUserDeptByUserId(userId);
    }
}
