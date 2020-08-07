package top.pullulate.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.pullulate.system.entity.PulUser;
import top.pullulate.web.data.viewvo.system.PulUserViewVo;
import top.pullulate.web.data.vo.system.PulUserVo;

import java.util.List;

/**
 * @功能描述:   用户数据层
 * @Author: pullulate
 * @Date: 2020/6/12 0012 19:21
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface PulUserMapper extends BaseMapper<PulUser> {

    /**
     * 分页查询系统用户
     *
     * @param pulUserVo 查询参数
     * @param page  分页参数
     * @return  分页数据
     */
    IPage<List<PulUserViewVo>> selectUserPage(@Param("userVo") PulUserVo pulUserVo, Page page);
}
