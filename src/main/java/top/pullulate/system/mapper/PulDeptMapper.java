package top.pullulate.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.pullulate.system.entity.PulDept;

/**
 * @功能描述:   部门数据层
 * @Author: xuyong
 * @Date: 2020/6/18 10:34
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface PulDeptMapper extends BaseMapper<PulDept> {

    /**
     * 根据用户主键获取其所在部门信息
     *
     * @param userId    用户主键
     * @return  所在部门信息
     */
    PulDept selectUserDeptByUserId(String userId);
}
