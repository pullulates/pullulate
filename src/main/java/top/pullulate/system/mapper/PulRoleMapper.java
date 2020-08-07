package top.pullulate.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.pullulate.system.entity.PulRole;
import top.pullulate.web.data.viewvo.system.PulRoleViewVo;
import top.pullulate.web.data.vo.system.PulRoleVo;

import java.util.List;

/**
 * @功能描述:   角色数据层
 * @Author: pullulate
 * @Date: 2020/6/12 0012 19:21
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface PulRoleMapper extends BaseMapper<PulRole> {

    /**
     * 根据用户主键获取用户角色集合
     *
     * @param userId    用户主键
     * @return  用户角色集合
     */
    List<PulRole> selectUserRolesByUserId(String userId);

    /**
     * 获取角色分页数据
     *
     * @param roleVo    查询参数
     * @param page  分页参数
     * @return
     */
    IPage<List<PulRoleViewVo>> selectRolePage(@Param("roleVo") PulRoleVo roleVo, Page page);
    
}
