package top.pullulate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.system.entity.PulDept;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.system.PulDeptViewVo;
import top.pullulate.web.data.vo.system.PulDeptVo;

import java.util.List;

/**
 * @功能描述:   部门服务接口
 * @Author: xuyong
 * @Date: 2020/6/18 10:32
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface IPulDeptService extends IService<PulDept> {

    /**
     * 根据用户主键获取其所在部门信息
     * 包含被禁用，不包含被删除的数据
     *
     * @param userId    用户主键
     * @return  所在部门信息
     */
    PulDept getUserDeptByUserId(String userId);

    /**
     * 获取所有部门信息
     *
     * @return
     */
    List<PulDeptViewVo> getAllDepts();

    /**
     * 获取部门树列表
     *
     * @return
     */
    List<PulDeptViewVo> getDeptTreeList();

    /**
     * 获取建议的排序编号
     *
     * @param deptId  部门主键
     * @return
     */
    int getSuggestOrderNum(String deptId);

    /**
     * 获取部门选择树
     *
     * @return
     */
    List<Tree> getTreeSelect();

    /**
     * 保存部门信息
     *
     * @param deptVo    部门信息
     * @return
     */
    P saveDept(PulDeptVo deptVo);

    /**
     * 修改部门信息
     *
     * @param deptVo    部门信息
     * @return
     */
    P updateDept(PulDeptVo deptVo);

    /**
     * 修改部门状态
     *
     * @param deptVo   部门信息
     * @return
     */
    P updateDeptStatus(PulDeptVo deptVo);

    /**
     * 删除部门
     *
     * @param deptId   部门主键
     * @return
     */
    P deleteDept(String deptId);
}
