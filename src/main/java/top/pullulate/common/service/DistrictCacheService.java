package top.pullulate.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.web.data.viewvo.system.PulDistrictsViewVo;

import java.util.List;

/**
 * @功能描述:   地区缓存服务类
 * @Author: xuyong
 * @Date: 2020/7/31 13:03
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Component
@RequiredArgsConstructor
public class DistrictCacheService {

    private final RedisUtils redisUtils;

    /**
     * 获取所有地区的缓存信息
     *
     * @return
     */
    public List<PulDistrictsViewVo> getAllDistricts() {
        List<PulDistrictsViewVo> districtsViewVos = redisUtils.getCacheList(CacheConstant.CACHE_DISTRICT_ALL);
        return districtsViewVos;
    }

    /**
     * 获取地区树列表的缓存信息
     *
     * @return
     */
    public List<PulDistrictsViewVo> getDistrictTreeList() {
        List<PulDistrictsViewVo> districtsViewVos = redisUtils.getCacheList(CacheConstant.CACHE_DISTRICT_LIST_TREE);
        return districtsViewVos;
    }

    /**
     * 设置地区树列表的缓存信息
     *
     * @return
     */
    public void setAllDistricts(List<PulDistrictsViewVo> districts) {
        redisUtils.setCacheList(CacheConstant.CACHE_DISTRICT_ALL, districts);
    }

    /**
     * 设置地区树列表的缓存信息
     *
     * @return
     */
    public void setDistrictTreeList(List<PulDistrictsViewVo> districts) {
        redisUtils.setCacheList(CacheConstant.CACHE_DISTRICT_LIST_TREE, districts);
    }

    /**
     * 删除所有地区的缓存信息
     *
     * @return
     */
    public void deleteAllDistricts() {
        redisUtils.deleteObject(CacheConstant.CACHE_DISTRICT_ALL);
    }

    /**
     * 删除地区树列表的缓存信息
     *
     * @return
     */
    public void deleteDistrictTreeList() {
        redisUtils.deleteObject(CacheConstant.CACHE_DISTRICT_LIST_TREE);
    }
}
