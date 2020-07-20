package top.pullulate.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.system.service.IPulDeptService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.PulDeptViewVo;

import java.util.List;

/**
 * @功能描述:   部门前端控制器
 * @Author: xuyong
 * @Date: 2020/7/20 15:10
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
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
}
