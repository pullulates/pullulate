package top.pullulate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.web.data.dto.P;
import top.pullulate.web.data.viewvo.PulDictDataViewVo;
import top.pullulate.web.data.viewvo.PulDictTypeViewVo;
import top.pullulate.web.data.vo.PulDictTypeVo;

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
    IPage<List<PulDictTypeViewVo>> getDictTypePage(PulDictTypeVo dictVo, Page page);

    /**
     * 获取字典数据分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    IPage<List<PulDictDataViewVo>> getDictDataPage(PulDictTypeVo dictVo, Page page);

    /**
     * 获取建议的排序编号
     *
     * @param type  类型，代表获取的是字典类别还是字典数据的排序编号
     * @param dictTypeId  字典类别主键
     * @return
     */
    int getSuggestOrderNum(String type, String dictTypeId);

    /**
     * 保存字典类别数据
     *
     * @param dictTypeVo    字典类别数据
     * @return
     */
    P saveDictType(PulDictTypeVo dictTypeVo);
}
