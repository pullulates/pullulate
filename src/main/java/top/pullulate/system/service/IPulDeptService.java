package top.pullulate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.system.entity.PulDept;
import top.pullulate.web.data.viewvo.PulDeptViewVo;

import java.util.List;

/**
 * @功能描述:   部门服务接口
 * @Author: xuyong
 * @Date: 2020/6/18 10:32
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
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
}
