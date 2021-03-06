package top.pullulate.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.CacheConstant;
import top.pullulate.core.utils.RedisUtils;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.system.PulDeptViewVo;

import java.util.List;

/**
 * @功能描述:   部门缓存服务类
 * @Author: xuyong
 * @Date: 2020/7/20 16:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Component
@RequiredArgsConstructor
public class DeptCacheService {

    private final RedisUtils redisUtils;

    /**
     * 获取所有部门的缓存数据
     */
    public List<PulDeptViewVo> getAllDepts() {
        List<PulDeptViewVo> depts = redisUtils.getCacheList(CacheConstant.CACHE_DEPT_ALL);
        return depts;
    }

    /**
     * 设置所有部门的缓存
     */
    public void setAllDepts(List<PulDeptViewVo> depts) {
        redisUtils.setCacheList(CacheConstant.CACHE_DEPT_ALL, depts);
    }

    /**
     * 删除所有部门的缓存
     */
    public void deleteAllDepts() {
        redisUtils.deleteObject(CacheConstant.CACHE_DEPT_ALL);
    }

    /**
     * 获取部门列表树的缓存数据
     */
    public List<PulDeptViewVo> getDeptListTree() {
        List<PulDeptViewVo> depts = redisUtils.getCacheList(CacheConstant.CACHE_DEPT_LIST_TREE);
        return depts;
    }

    /**
     * 设置部门列表树的缓存
     */
    public void setDeptListTree(List<PulDeptViewVo> depts) {
        redisUtils.setCacheList(CacheConstant.CACHE_DEPT_LIST_TREE, depts);
    }

    /**
     * 删除部门列表树的缓存
     */
    public void deleteDeptListTree() {
        redisUtils.deleteObject(CacheConstant.CACHE_DEPT_LIST_TREE);
    }

    /**
     * 获取部门树选择的缓存
     */
    public List<Tree> getDeptTreeSelect() {
        List<Tree> tree = redisUtils.getCacheList(CacheConstant.CACHE_DEPT_TREE_SELECT);
        return tree;
    }

    /**
     * 设置部门树选择的缓存
     */
    public void setDeptTreeSelect(List<Tree> tree) {
        redisUtils.setCacheList(CacheConstant.CACHE_DEPT_TREE_SELECT, tree);
    }

    /**
     * 删除部门树选择的缓存
     */
    public void deleteDeptTreeSelect() {
        redisUtils.deleteObject(CacheConstant.CACHE_DEPT_TREE_SELECT);
    }

}
