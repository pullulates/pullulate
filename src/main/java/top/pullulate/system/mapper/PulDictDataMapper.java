package top.pullulate.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.system.entity.PulDictData;
import top.pullulate.web.data.viewvo.system.PulDictDataViewVo;
import top.pullulate.web.data.vo.system.PulDictDataVo;

import java.util.List;

/**
 * @功能描述:   字典数据数据层
 * @Author: xuyong
 * @Date: 2020/7/2 16:03
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public interface PulDictDataMapper extends BaseMapper<PulDictData> {

    /**
     * 获取字典数据分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    IPage<List<PulDictDataViewVo>> selectDictDataPage(PulDictDataVo dictVo, Page page);
}
