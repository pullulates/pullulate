package top.pullulate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.pullulate.common.constants.ParamConstant;
import top.pullulate.common.enums.DictType;
import top.pullulate.core.security.user.UserInfo;
import top.pullulate.core.utils.TokeUtils;
import top.pullulate.system.entity.PulDictData;
import top.pullulate.system.entity.PulDictType;
import top.pullulate.system.mapper.PulDictDataMapper;
import top.pullulate.system.mapper.PulDictTypeMapper;
import top.pullulate.system.service.IPulDictService;
import top.pullulate.utils.ServletUtils;
import top.pullulate.web.data.dto.P;
import top.pullulate.web.data.viewvo.PulDictDataViewVo;
import top.pullulate.web.data.viewvo.PulDictTypeViewVo;
import top.pullulate.web.data.vo.PulDictTypeVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @功能描述:   数据字典接口服务实现类
 * @Author: xuyong
 * @Date: 2020/7/2 16:08
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PulDictServiceImpl implements IPulDictService {

    private final PulDictTypeMapper dictTypeMapper;

    private final PulDictDataMapper dictDataMapper;

    private final TokeUtils tokeUtils;

    /**
     * 获取字典类别分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    @Override
    public IPage<List<PulDictTypeViewVo>> getDictTypePage(PulDictTypeVo dictVo, Page page) {
        return dictTypeMapper.selectDictTypePage(dictVo, page);
    }

    /**
     * 获取字典数据分页数据
     *
     * @param dictVo    查询参数
     * @param page  分页参数
     * @return
     */
    @Override
    public IPage<List<PulDictDataViewVo>> getDictDataPage(PulDictTypeVo dictVo, Page page) {
        return dictDataMapper.selectDictDataPage(dictVo, page);
    }

    /**
     * 获取建议的排序编号
     *
     * @param type  类型，代表获取的是字典类别还是字典数据的排序编号
     * @param dictTypeId  字典类别主键
     * @return
     */
    @Override
    public int getSuggestOrderNum(String type, String dictTypeId) {
        int suggestOrderNum = 0;
        if (ParamConstant.DICT_TYPE.equals(type)) {
            suggestOrderNum = dictTypeMapper.selectCount(Wrappers.<PulDictType>query().lambda()
                    .eq(PulDictType::getDeleteFlag, ParamConstant.NOT_DELETED));
        }
        if (ParamConstant.DICT_DATA.equals(type)) {
            suggestOrderNum = dictDataMapper.selectCount(Wrappers.<PulDictData>query().lambda()
                            .eq(PulDictData::getDictTypeId, dictTypeId)
                            .eq(PulDictData::getDeleteFlag, ParamConstant.NOT_DELETED));
        }
        return ++suggestOrderNum;
    }

    /**
     * 保存字典类别数据
     *
     * @param dictTypeVo    字典类别数据
     * @return
     */
    @Override
    public P saveDictType(PulDictTypeVo dictTypeVo) {
        PulDictType dictType = BeanUtil.toBean(dictTypeVo, PulDictType.class);
        int count = dictTypeMapper.selectCount(Wrappers.<PulDictType>query().lambda()
                .eq(PulDictType::getDictKey, dictType.getDictKey())
                .eq(PulDictType::getDeleteFlag, ParamConstant.NOT_DELETED));
        if (count > 0) {
            return P.error("字典键已经存在");
        }
        dictType.setCreateBy(tokeUtils.getUserInfo(ServletUtils.getRequest()).getUsername());
        dictType.setCreateAt(LocalDateTime.now());
        return P.p(dictTypeMapper.insert(dictType));
    }

    /**
     * 修改字典类别数据
     *
     * @param dictTypeVo    字典类别数据
     * @return
     */
    @Override
    public P updateDictType(PulDictTypeVo dictTypeVo) {
        PulDictType check = dictTypeMapper.selectById(dictTypeVo.getDictTypeId());
        if (ObjectUtil.isNull(check)) {
            return P.error("字典不存在");
        }
        UserInfo userInfo = tokeUtils.getUserInfo(ServletUtils.getRequest());
        if (DictType.willDefault(check.getWillDefault()) && !userInfo.willSuperman()) {
            return P.error("您不能修改默认字典");
        }
        PulDictType dictType = BeanUtil.toBean(dictTypeVo, PulDictType.class);
        int count = dictTypeMapper.selectCount(Wrappers.<PulDictType>query().lambda()
                .eq(PulDictType::getDictKey, dictType.getDictKey())
                .ne(PulDictType::getDictTypeId, dictType.getDictTypeId())
                .eq(PulDictType::getDeleteFlag, ParamConstant.NOT_DELETED));
        if (count > 0) {
            return P.error("字典键已经存在");
        }
        dictType.setUpdateBy(userInfo.getUsername());
        dictType.setUpdateAt(LocalDateTime.now());
        return P.p(dictTypeMapper.updateById(dictType));
    }
}
