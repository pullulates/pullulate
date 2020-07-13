package top.pullulate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.common.constants.ParamConstant;
import top.pullulate.common.enums.DictType;
import top.pullulate.core.security.user.UserInfo;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.core.utils.TokeUtils;
import top.pullulate.system.entity.PulDictData;
import top.pullulate.system.entity.PulDictType;
import top.pullulate.system.mapper.PulDictDataMapper;
import top.pullulate.system.mapper.PulDictTypeMapper;
import top.pullulate.system.service.IPulDictService;
import top.pullulate.web.data.dto.P;
import top.pullulate.web.data.viewvo.PulDictDataViewVo;
import top.pullulate.web.data.viewvo.PulDictTypeViewVo;
import top.pullulate.web.data.vo.PulDictDataVo;
import top.pullulate.web.data.vo.PulDictTypeVo;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final RedisUtils redisUtils;

    /**
     * 构建前端字典缓存
     *
     * @return  前端字典
     */
    @Override
    public Map<String, List<PulDictDataViewVo>> buildFrontDictCache() {
        Map<String, List<PulDictDataViewVo>> map = redisUtils.getCacheMap(CacheConstant.CACHE_DICT_FRONT_KEY);
        if (CollectionUtil.isNotEmpty(map)) {
            return map;
        }
        List<PulDictType> dictTypes = dictTypeMapper.selectList(Wrappers.<PulDictType>lambdaQuery()
                .eq(PulDictType::getStatus, ParamConstant.NORMAL)
                .orderByAsc(PulDictType::getOrderNum));
        Map<String, List<PulDictDataViewVo>> frontDict = new HashMap<>(dictTypes.size());
        dictTypes.forEach(dictType -> {
            List<PulDictData> dictDatas = dictDataMapper.selectList(Wrappers.<PulDictData>lambdaQuery()
                    .eq(PulDictData::getDictTypeId, dictType.getDictTypeId())
                    .eq(PulDictData::getStatus, ParamConstant.NORMAL)
                    .orderByAsc(PulDictData::getOrderNum));
            List<PulDictDataViewVo> dictDataViewVos = dictDatas.stream()
                    .map(dictData -> BeanUtil.toBean(dictData, PulDictDataViewVo.class)).collect(Collectors.toList());
            frontDict.put(dictType.getDictKey(), dictDataViewVos);
        });
        redisUtils.setCacheMap(CacheConstant.CACHE_DICT_FRONT_KEY, frontDict);
        return frontDict;
    }

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
    public IPage<List<PulDictDataViewVo>> getDictDataPage(PulDictDataVo dictVo, Page page) {
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
            suggestOrderNum = dictTypeMapper.selectCount(Wrappers.<PulDictType>query().lambda());
        }
        if (ParamConstant.DICT_DATA.equals(type)) {
            suggestOrderNum = dictDataMapper.selectCount(Wrappers.<PulDictData>query().lambda()
                            .eq(PulDictData::getDictTypeId, dictTypeId));
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
                .eq(PulDictType::getDictKey, dictType.getDictKey()));
        if (count > 0) {
            return P.error("字典键已经存在");
        }
        dictType.setCreateBy(tokeUtils.getUserName());
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
        PulDictType dictType = BeanUtil.toBean(dictTypeVo, PulDictType.class);
        PulDictType check = dictTypeMapper.selectOne(Wrappers.<PulDictType>query().lambda()
                .eq(PulDictType::getDictTypeId, dictType.getDictTypeId()));
        if (ObjectUtil.isNull(check)) {
            return P.error("字典不存在");
        }
        UserInfo userInfo = tokeUtils.getUserInfo();
        if (DictType.willDefault(check.getWillDefault()) && !userInfo.willSuperman()) {
            return P.error("您不能修改默认字典");
        }
        int count = dictTypeMapper.selectCount(Wrappers.<PulDictType>query().lambda()
                .eq(PulDictType::getDictKey, dictType.getDictKey())
                .ne(PulDictType::getDictTypeId, dictType.getDictTypeId()));
        if (count > 0) {
            return P.error("字典键已经存在");
        }
        dictType.setUpdateBy(userInfo.getUsername());
        dictType.setUpdateAt(LocalDateTime.now());
        return P.p(dictTypeMapper.updateById(dictType));
    }

    /**
     * 保存字典数据
     *
     * @param dictDataVo    字典数据
     * @return
     */
    @Override
    public P saveDictData(PulDictDataVo dictDataVo) {
        PulDictData dictData = BeanUtil.toBean(dictDataVo, PulDictData.class);
        PulDictType checkDictType = dictTypeMapper.selectOne(Wrappers.<PulDictType>query().lambda()
                .eq(PulDictType::getDictTypeId, dictData.getDictTypeId()));
        if (ObjectUtil.isNull(checkDictType)) {
            return P.error("字典类别不存在");
        }
        UserInfo userInfo = tokeUtils.getUserInfo();
        if (DictType.willDefault(checkDictType.getWillDefault()) && !userInfo.willSuperman()) {
            return P.error("您不能修改默认字典");
        }
        int count = dictDataMapper.selectCount(Wrappers.<PulDictData>query().lambda()
                .eq(PulDictData::getDictTypeId, dictData.getDictTypeId())
                .eq(PulDictData::getDictValue, dictData.getDictValue()));
        if (count > 0) {
            return P.error("字典值已经存在");
        }
        dictData.setCreateBy(userInfo.getUsername());
        dictData.setCreateAt(LocalDateTime.now());
        return P.p(dictDataMapper.insert(dictData));
    }

    /**
     * 修改字典数据
     *
     * @param dictDataVo    字典数据
     * @return
     */
    @Override
    public P updateDictData(PulDictDataVo dictDataVo) {
        PulDictData dictData = BeanUtil.toBean(dictDataVo, PulDictData.class);
        PulDictType checkDictType = dictTypeMapper.selectOne(Wrappers.<PulDictType>query().lambda()
                .eq(PulDictType::getDictTypeId, dictData.getDictTypeId()));
        if (ObjectUtil.isNull(checkDictType)) {
            return P.error("字典类别不存在");
        }
        UserInfo userInfo = tokeUtils.getUserInfo();
        if (DictType.willDefault(checkDictType.getWillDefault()) && !userInfo.willSuperman()) {
            return P.error("您不能修改默认字典");
        }
        int count = dictDataMapper.selectCount(Wrappers.<PulDictData>query().lambda()
                .ne(PulDictData::getDictDataId, dictData.getDictDataId())
                .eq(PulDictData::getDictTypeId, dictData.getDictTypeId())
                .eq(PulDictData::getDictValue, dictData.getDictValue()));
        if (count > 0) {
            return P.error("字典值已经存在");
        }
        dictData.setUpdateBy(userInfo.getUsername());
        dictData.setUpdateAt(LocalDateTime.now());
        return P.p(dictDataMapper.updateById(dictData));
    }

    /**
     * 修改字典数据的状态
     *
     * @param dictDataVo    字典数据主键 目标状态
     * @return
     */
    @Override
    public P updateDictDataStatus(PulDictDataVo dictDataVo) {
        PulDictData dictData = BeanUtil.toBean(dictDataVo, PulDictData.class);
        dictData.setUpdateBy(tokeUtils.getUserName());
        dictData.setUpdateAt(LocalDateTime.now());
        return P.p(dictDataMapper.update(dictData, Wrappers.<PulDictData>lambdaUpdate()
                .eq(PulDictData::getDictDataId, dictData.getDictDataId())));
    }

    /**
     * 删除字典数据
     *
     * @param dictDataId    字典数据主键
     * @return
     */
    @Override
    public P deleteDictData(String dictDataId) {
        return P.p(dictDataMapper.deleteById(dictDataId));
    }

    /**
     * 修改字典类别的状态
     *
     * @param dictTypeVo    字典类别主键 目标状态
     * @return
     */
    @Override
    public P updateDictTypeStatus(PulDictTypeVo dictTypeVo) {
        PulDictType dictType = BeanUtil.toBean(dictTypeVo, PulDictType.class);
        dictType.setUpdateBy(tokeUtils.getUserName());
        dictType.setUpdateAt(LocalDateTime.now());
        return P.p(dictTypeMapper.update(dictType, Wrappers.<PulDictType>lambdaUpdate()
                .eq(PulDictType::getDictTypeId, dictType.getDictTypeId())));
    }

    /**
     * 删除字典类别
     *
     * @param dictTypeId    字典类别主键
     * @return
     */
    @Override
    public P deleteDictType(String dictTypeId) {
        int count = dictDataMapper.selectCount(Wrappers.<PulDictData>lambdaQuery()
                .eq(PulDictData::getDictTypeId, dictTypeId));
        if (count > 0) {
            return P.error("存在字典数据，不能删除");
        }
        return P.p(dictTypeMapper.deleteById(dictTypeId));
    }
}
