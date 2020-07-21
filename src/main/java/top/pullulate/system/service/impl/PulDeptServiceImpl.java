package top.pullulate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.pullulate.common.constants.Constant;
import top.pullulate.common.service.DeptCacheService;
import top.pullulate.core.utils.TokenUtils;
import top.pullulate.system.entity.PulDept;
import top.pullulate.system.entity.PulUserDept;
import top.pullulate.system.mapper.PulDeptMapper;
import top.pullulate.system.mapper.PulUserDeptMapper;
import top.pullulate.system.service.IPulDeptService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.PulDeptViewVo;
import top.pullulate.web.data.vo.PulDeptVo;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @功能描述:   部门服务接口实现类
 * @Author: xuyong
 * @Date: 2020/6/18 10:33
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PulDeptServiceImpl extends ServiceImpl<PulDeptMapper, PulDept> implements IPulDeptService {

    private final DeptCacheService deptCacheService;

    private final TokenUtils tokenUtils;

    private final PulUserDeptMapper userDeptMapper;

    /**
     * 根据用户主键获取其所在部门信息
     * 包含被禁用，不包含被删除的数据
     *
     * @param userId    用户主键
     * @return  所在部门信息
     */
    @Override
    public PulDept getUserDeptByUserId(String userId) {
        return baseMapper.selectUserDeptByUserId(userId);
    }

    /**
     * 获取所有部门信息
     *
     * @return
     */
    @Override
    public List<PulDeptViewVo> getAllDepts() {
        List<PulDeptViewVo> depts = deptCacheService.getAllDepts();
        if (CollectionUtil.isEmpty(depts)) {
            depts = list().stream().map(dept-> BeanUtil.toBean(dept, PulDeptViewVo.class))
                    .collect(Collectors.toList());
            deptCacheService.setAllDepts(depts);
        }
        Collections.sort(depts);
        return depts;
    }

    /**
     * 获取部门树列表
     *
     * @return
     */
    @Override
    public List<PulDeptViewVo> getDeptTreeList() {
        List<PulDeptViewVo> deptTreeList = deptCacheService.getDeptListTree();
        if (CollectionUtil.isNotEmpty(deptTreeList)) {
            Collections.sort(deptTreeList);
            return deptTreeList;
        }
        List<PulDeptViewVo> allDepts = getAllDepts();
        Set<String> dupMenuSet = new HashSet<>(allDepts.size());
        List<PulDeptViewVo> tree = buildDeptTreeList(allDepts, allDepts, dupMenuSet);
        deptCacheService.setDeptListTree(tree);
        return tree;
    }

    /**
     * 获取建议的排序编号
     *
     * @param deptId  部门主键
     * @return
     */
    @Override
    public int getSuggestOrderNum(String deptId) {
        int suggestOrderNum = count(Wrappers.<PulDept>lambdaQuery().eq(PulDept::getParentId, deptId));
        return ++suggestOrderNum;
    }

    /**
     * 获取部门选择树
     *
     * @return
     */
    @Override
    public List<Tree> getTreeSelect() {
        List<Tree> tree = deptCacheService.getDeptTreeSelect();
        if (CollectionUtil.isNotEmpty(tree)) {
            return tree;
        }
        List<PulDeptViewVo> allDepts = getAllDepts();
        Set<String> dupMenuSet = new HashSet<>(allDepts.size());
        tree = buildDeptTreeSelect(allDepts, allDepts, dupMenuSet);
        deptCacheService.setDeptTreeSelect(tree);
        return tree;
    }

    /**
     * 保存部门信息
     *
     * @param deptVo    部门信息
     * @return
     */
    @Override
    public P saveDept(PulDeptVo deptVo) {
        PulDept dept = BeanUtil.toBean(deptVo, PulDept.class);
        int count = count(Wrappers.<PulDept>lambdaQuery()
                .eq(PulDept::getDeptNo, dept.getDeptNo())
                .or()
                .eq(PulDept::getDeptName, dept.getDeptName()));
        if (count > 0) {
            return P.error("部门信息已存在，请检查部门编号或部门名称是否重复！");
        }
        dept.setCreateBy(tokenUtils.getUserName());
        dept.setCreateAt(LocalDateTime.now());
        boolean result = save(dept);
        if (result) {
            refreshCache();
        }
        return result ? P.success() : P.error();
    }

    /**
     * 修改部门信息
     *
     * @param deptVo    部门信息
     * @return
     */
    @Override
    public P updateDept(PulDeptVo deptVo) {
        PulDept dept = BeanUtil.toBean(deptVo, PulDept.class);
        int count = count(Wrappers.<PulDept>lambdaQuery()
                .ne(PulDept::getDeptId, dept.getDeptId())
                .and(wrapper -> wrapper
                        .eq(PulDept::getDeptNo, dept.getDeptNo())
                        .or()
                        .eq(PulDept::getDeptName, dept.getDeptName())
                ));
        if (count > 0) {
            return P.error("部门信息已存在，请检查部门编号或部门名称是否重复！");
        }
        dept.setUpdateBy(tokenUtils.getUserName());
        dept.setUpdateAt(LocalDateTime.now());
        boolean result = updateById(dept);
        if (result) {
            refreshCache();
        }
        return result ? P.success() : P.error();
    }

    /**
     * 修改部门状态
     *
     * @param deptVo   部门信息
     * @return
     */
    @Override
    public P updateDeptStatus(PulDeptVo deptVo) {
        PulDept dept = BeanUtil.toBean(deptVo, PulDept.class);
        dept.setUpdateAt(LocalDateTime.now());
        dept.setUpdateBy(tokenUtils.getUserName());
        boolean result = update(dept, Wrappers.<PulDept>lambdaUpdate().eq(PulDept::getDeptId, deptVo.getDeptId()));
        if (result) {
            refreshCache();
        }
        return result ? P.success() : P.error();
    }

    /**
     * 删除部门
     *
     * @param deptId   部门主键
     * @return
     */
    @Override
    public P deleteDept(String deptId) {
        // 存在下级部门及部门存在用户时，不可以删除
        int count = count(Wrappers.<PulDept>lambdaQuery().eq(PulDept::getParentId, deptId));
        if (count > 0) {
            return P.error("存在下级部门，不可以删除！");
        }
        count = userDeptMapper.selectCount(Wrappers.<PulUserDept>lambdaQuery().eq(PulUserDept::getDeptId, deptId));
        if (count > 0) {
            return P.error("部门存在已分配用户，不可以删除！");
        }
        boolean result = remove(Wrappers.<PulDept>lambdaQuery().eq(PulDept::getDeptId, deptId));
        if (result) {
            refreshCache();
        }
        return result ? P.success() : P.error();
    }

    /**
     * 构建部门选择树
     *
     * @param depts         待构建部门集合
     * @param allDepts      所有部门集合
     * @param dupMenuSet    已构建部门集合
     * @return
     */
    private List<Tree> buildDeptTreeSelect(List<PulDeptViewVo> depts, List<PulDeptViewVo> allDepts, Set<String> dupMenuSet) {
        List<Tree> trees = new ArrayList<>(allDepts.size());
        depts.forEach(dept -> {
            if (!dupMenuSet.contains(dept.getDeptId())) {
                dupMenuSet.add(dept.getDeptId());
                List<PulDeptViewVo> children = allDepts.stream()
                        .filter(item -> item.getParentId().equals(dept.getDeptId()))
                        .collect(Collectors.toList());
                Tree tree = new Tree(dept.getDeptNo().concat("-").concat(dept.getDeptName()), dept.getDeptId(), dept.getDeptId());
                tree.setChildren(buildDeptTreeSelect(children, allDepts, dupMenuSet));
                children.forEach(child -> dupMenuSet.add(child.getDeptId()));
                trees.add(tree);
            }
        });
        return trees;
    }

    /**
     * 构建部门树列表
     *
     * @param depts     待构建部门集合
     * @param allDepts  所有部门集合
     * @param dupMenuSet    已构建部门集合
     * @return  部门树列表
     */
    private List<PulDeptViewVo> buildDeptTreeList(List<PulDeptViewVo> depts, List<PulDeptViewVo> allDepts, Set<String> dupMenuSet) {
        List<PulDeptViewVo> list = new ArrayList<>(depts.size());
        depts.forEach(dept -> {
            if (!dupMenuSet.contains(dept.getDeptId())) {
                dupMenuSet.add(dept.getDeptId());
                List<PulDeptViewVo> children = allDepts.stream()
                        .filter(item -> item.getParentId().equals(dept.getDeptId()))
                        .collect(Collectors.toList());
                dept.setChildren(buildDeptTreeList(children, allDepts, dupMenuSet));
                children.forEach(child -> dupMenuSet.add(dept.getDeptId()));
                list.add(dept);
            }
        });
        return list;
    }

    /**
     * 刷新部门信息的缓存
     */
    private void refreshCache() {
        deptCacheService.deleteAllDepts();
        getAllDepts();
        deptCacheService.deleteDeptListTree();
        getDeptTreeList();
        deptCacheService.deleteDeptTreeSelect();
        getTreeSelect();
    }
}
