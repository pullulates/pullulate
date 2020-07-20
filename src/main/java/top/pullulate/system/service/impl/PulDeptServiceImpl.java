package top.pullulate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.pullulate.common.service.DeptCacheService;
import top.pullulate.system.entity.PulDept;
import top.pullulate.system.mapper.PulDeptMapper;
import top.pullulate.system.service.IPulDeptService;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.PulDeptViewVo;
import top.pullulate.web.data.viewvo.PulMenuViewVo;

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
//        List<Tree> tree = buildDeptTreeList(allDepts, allDepts, dupMenuSet);
        return null;
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
                List<PulDeptViewVo> children = depts.stream()
                        .filter(item -> item.getDeptId().equals(dept.getDeptId()))
                        .map(item -> BeanUtil.toBean(item, PulDeptViewVo.class))
                        .collect(Collectors.toList());
                dept.setChildren(buildDeptTreeList(children, allDepts, dupMenuSet));
                children.forEach(child -> dupMenuSet.add(dept.getDeptId()));
                list.add(dept);
            }
        });
        return list;
    }
}
