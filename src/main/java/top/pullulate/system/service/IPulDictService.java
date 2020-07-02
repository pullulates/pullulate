package top.pullulate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.web.data.viewvo.PulDictDataViewVo;
import top.pullulate.web.data.viewvo.PulDictTypeViewVo;
import top.pullulate.web.data.vo.PulDictVo;

import java.util.List;

/**
 * @功能描述:   数据字典服务接口
 * @Author: xuyong
 * @Date: 2020/7/2 16:06
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public interface IPulDictService {

    /**
     * 获取字典类别分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    IPage<List<PulDictTypeViewVo>> getDictTypePage(PulDictVo dictVo, Page page);

    /**
     * 获取字典数据分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    IPage<List<PulDictDataViewVo>> getDictDataPage(PulDictVo dictVo, Page page);
}
