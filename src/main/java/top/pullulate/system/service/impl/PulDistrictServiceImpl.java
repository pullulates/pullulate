package top.pullulate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pullulate.common.service.DistrictCacheService;
import top.pullulate.system.entity.PulDistrict;
import top.pullulate.system.mapper.PulDistrictMapper;
import top.pullulate.system.service.IPulDistrictService;
import top.pullulate.utils.DistrictUtils;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulDistrictsViewVo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @功能描述:   地区服务接口实现类
 * @Author: xuyong
 * @Date: 2020/7/31 12:55
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Service
@RequiredArgsConstructor
public class PulDistrictServiceImpl extends ServiceImpl<PulDistrictMapper, PulDistrict> implements IPulDistrictService {

    private final DistrictCacheService districtCacheService;

    private final DistrictUtils districtUtils;

    /**
     * 获取地区树列表数据
     *
     * @return
     */
    @Override
    public List<PulDistrictsViewVo> getDisctrictTreeList() {
        List<PulDistrictsViewVo> districtsViewVos = districtCacheService.getDistrictTreeList();
        if (CollectionUtil.isEmpty(districtsViewVos)) {
            List<PulDistrictsViewVo> allDistricts = getAllDistricts();
            Set<String> dupDistrictSet = new HashSet<>(allDistricts.size());
            districtsViewVos = buildDistrictTreeList(allDistricts, allDistricts, dupDistrictSet);
            districtCacheService.setDistrictTreeList(districtsViewVos);
        }
        return districtsViewVos;
    }

    /**
     * 获取所有的地区信息
     *
     * @return
     */
    @Override
    public List<PulDistrictsViewVo> getAllDistricts() {
        List<PulDistrictsViewVo> districtsViewVos = districtCacheService.getAllDistricts();
        if (CollectionUtil.isEmpty(districtsViewVos)) {
            districtsViewVos = list(Wrappers.<PulDistrict>lambdaQuery().orderByAsc(PulDistrict::getAdcode).orderByAsc(PulDistrict::getCreateAt))
                    .stream().map(district -> BeanUtil.toBean(district, PulDistrictsViewVo.class))
                    .collect(Collectors.toList());
            districtCacheService.setAllDistricts(districtsViewVos);
        }
        return districtsViewVos;
    }

    /**
     * 同步省级地区数据
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P syncProvinceDistricts() {
        List<PulDistrict> districts = districtUtils.getProvinceDistrictInfo();
        if (CollectionUtil.isEmpty(districts)) {
            return P.error("同步省级地区数据失败");
        }
        remove(Wrappers.lambdaQuery());
        saveBatch(districts);
        districtCacheService.deleteAllDistricts();
        districtCacheService.deleteDistrictTreeList();
        return P.success();
    }

    /**
     * 同步省级以下地区数据
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P syncProvinceChildrenDistricts(PulDistrict district) {
        List<PulDistrict> districts = districtUtils.getProvinceChildrenDistrictInfo(district.getName(), district.getDistrictId());
        if (CollectionUtil.isEmpty(districts)) {
            return P.error("同步省级以下地区数据失败");
        }
        remove(Wrappers.<PulDistrict>lambdaQuery()
                .ne(PulDistrict::getDistrictId, district.getDistrictId())
                .likeLeft(PulDistrict::getAdcode, district.getAdcode().substring(0, 2)));
        saveBatch(districts);
        saveGaodeDistrict(districts);

        districtCacheService.deleteAllDistricts();
        districtCacheService.deleteDistrictTreeList();
        return P.success();
    }

    /**
     * 保存高德的地区数据
     *
     * @param districts             地区集合
     */
    private void saveGaodeDistrict(List<PulDistrict> districts) {
        districts.forEach(district -> {
            if (CollectionUtil.isNotEmpty(district.getDistricts())) {
                saveBatch(district.getDistricts());
                saveGaodeDistrict(district.getDistricts());
            }
        });
    }

    /**
     * 构建地区树列表
     *
     * @param districts         待构建集合
     * @param allDistricts      所有数据
     * @param dupDistrictSet    已构建集合
     * @return
     */
    private List<PulDistrictsViewVo> buildDistrictTreeList(List<PulDistrictsViewVo> districts, List<PulDistrictsViewVo> allDistricts, Set<String> dupDistrictSet) {
        List<PulDistrictsViewVo> list = new ArrayList<>(districts.size());
        districts.forEach(district -> {
            if (!dupDistrictSet.contains(district.getDistrictId())) {
                dupDistrictSet.add(district.getDistrictId());
                List<PulDistrictsViewVo> children = allDistricts.stream()
                        .filter(item -> item.getParentId().equals(district.getDistrictId()))
                        .collect(Collectors.toList());
                district.setChildren(buildDistrictTreeList(children, allDistricts, dupDistrictSet));
                children.forEach(child -> dupDistrictSet.add(district.getDistrictId()));
                list.add(district);
            }
        });
        return list;
    }
}
