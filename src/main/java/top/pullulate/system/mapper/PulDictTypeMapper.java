package top.pullulate.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.pullulate.system.entity.PulDictType;
import top.pullulate.web.data.viewvo.system.PulDictTypeViewVo;
import top.pullulate.web.data.vo.system.PulDictTypeVo;

import java.util.List;

/**
 * @功能描述:   字典类别数据层
 * @Author: xuyong
 * @Date: 2020/7/2 16:03
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public interface PulDictTypeMapper extends BaseMapper<PulDictType> {

    /**
     * 获取字典类别分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    IPage<List<PulDictTypeViewVo>> selectDictTypePage(@Param("dictVo") PulDictTypeVo dictVo, Page page);
}
