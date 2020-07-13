package top.pullulate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.web.data.dto.P;
import top.pullulate.web.data.viewvo.PulDictDataViewVo;
import top.pullulate.web.data.viewvo.PulDictTypeViewVo;
import top.pullulate.web.data.vo.PulDictDataVo;
import top.pullulate.web.data.vo.PulDictTypeVo;

import java.util.List;
import java.util.Map;

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
     * 构建前端字典缓存
     *
     * @return  前端字典
     */
    Map<String, List<PulDictDataViewVo>> buildFrontDictCache();

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
    IPage<List<PulDictDataViewVo>> getDictDataPage(PulDictDataVo dictVo, Page page);

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

    /**
     * 修改字典类别数据
     *
     * @param dictTypeVo    字典类别数据
     * @return
     */
    P updateDictType(PulDictTypeVo dictTypeVo);

    /**
     * 保存字典数据
     *
     * @param dictDataVo    字典数据
     * @return
     */
    P saveDictData(PulDictDataVo dictDataVo);

    /**
     * 修改字典数据
     *
     * @param dictDataVo    字典数据
     * @return
     */
    P updateDictData(PulDictDataVo dictDataVo);

    /**
     * 修改字典数据的状态
     *
     * @param dictDataVo    字典数据主键 目标状态
     * @return
     */
    P updateDictDataStatus(PulDictDataVo dictDataVo);

    /**
     * 删除字典数据
     *
     * @param dictDataId    字典数据主键
     * @return
     */
    P deleteDictData(String dictDataId);

    /**
     * 修改字典类别的状态
     *
     * @param dictTypeVo    字典类别主键 目标状态
     * @return
     */
    P updateDictTypeStatus(PulDictTypeVo dictTypeVo);

    /**
     * 删除字典类别
     *
     * @param dictTypeId    字典类别主键
     * @return
     */
    P deleteDictType(String dictTypeId);
}
