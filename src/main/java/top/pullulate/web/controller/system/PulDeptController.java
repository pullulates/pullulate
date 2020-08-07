package top.pullulate.web.controller.system;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.system.service.IPulDeptService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.system.PulDeptViewVo;
import top.pullulate.web.data.vo.system.PulDeptVo;

import java.util.List;

/**
 * @功能描述:   部门前端控制器
 * @Author: xuyong
 * @Date: 2020/7/20 15:10
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/depts")
public class PulDeptController {

    private final IPulDeptService deptService;

    /**
     * 获取部门树列表
     *
     * @return
     */
    @GetMapping
    public P getDeptTreeList() {
        List<PulDeptViewVo> depts = deptService.getDeptTreeList();
        return P.data(depts);
    }

    /**
     * 获取建议的排序编号
     *
     * @param deptId  部门主键
     * @return
     */
    @GetMapping("/suggest-ordernum")
    public P getSuggestOrderNum(String deptId) {
        int orderNum = deptService.getSuggestOrderNum(deptId);
        return P.data(orderNum);
    }

    /**
     * 获取部门选择树
     *
     * @return
     */
    @GetMapping("/tree-select")
    public P getTreeSelect() {
        List<Tree> tree = deptService.getTreeSelect();
        return P.data(tree);
    }

    /**
     * 保存部门信息
     *
     * @param deptVo    部门信息
     * @return
     */
    @PostMapping
    @OperationRecord(title = "部门机构-保存部门信息")
    public  P saveDept(@RequestBody @Validated PulDeptVo deptVo) {
        return deptService.saveDept(deptVo);
    }

    /**
     * 修改部门信息
     *
     * @param deptVo    部门信息
     * @return
     */
    @PutMapping
    @OperationRecord(title = "部门机构-修改部门信息")
    public P updateDept(@RequestBody @Validated PulDeptVo deptVo) {
        return deptService.updateDept(deptVo);
    }

    /**
     * 修改部门状态
     *
     * @param deptVo   部门信息
     * @return
     */
    @PatchMapping
    @OperationRecord(title = "部门机构-修改部门状态")
    public P updateDeptStatus(@RequestBody PulDeptVo deptVo) {
        return deptService.updateDeptStatus(deptVo);
    }

    /**
     * 删除部门
     *
     * @param deptId   部门主键
     * @return
     */
    @DeleteMapping
    @OperationRecord(title = "部门机构-删除部门信息")
    public P deleteDept(String deptId) {
        return deptService.deleteDept(deptId);
    }
}
