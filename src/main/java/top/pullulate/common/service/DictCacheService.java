package top.pullulate.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.web.data.viewvo.PulDictDataViewVo;

import java.util.List;
import java.util.Map;

/**
 * @功能描述:   数据字典缓存服务类
 * @Author: xuyong
 * @Date: 2020/7/2 16:08
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DictCacheService {

    private final RedisUtils redisUtils;

    /**
     * 获取前端字典缓存
     */
    public Map<String, List<PulDictDataViewVo>> getFrontDictCache() {
        Map<String, List<PulDictDataViewVo>> map = redisUtils.getCacheMap(CacheConstant.CACHE_DICT_FRONT_KEY);
        return map;
    }

    /**
     * 设置前端字典缓存
     */
    public void setFrontDictCache(Map<String, List<PulDictDataViewVo>> map) {
        redisUtils.setCacheMap(CacheConstant.CACHE_DICT_FRONT_KEY, map);
    }

    /**
     * 删除前端字典缓存
     */
    public void deleteFrontDictCache() {
        redisUtils.deleteObject(CacheConstant.CACHE_DICT_FRONT_KEY);
    }

    /**
     * 刷新前端字典缓存
     */
    public void refreshFrontDictCache(Map<String, List<PulDictDataViewVo>> map) {
        deleteFrontDictCache();
        setFrontDictCache(map);
    }
}
