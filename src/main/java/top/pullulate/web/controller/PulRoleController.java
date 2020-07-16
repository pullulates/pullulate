package top.pullulate.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.system.service.IPulRoleService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.PulRoleViewVo;
import top.pullulate.web.data.vo.PulRoleVo;

import java.util.List;

/**
 * @功能描述:   角色前端控制器
 * @Author: xuyong
 * @Date: 2020/7/16 15:17
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequestMapping("/system/roles")
@RequiredArgsConstructor
public class PulRoleController {

    private final IPulRoleService roleService;

    /**
     * 获取角色分页数据
     *
     * @param roleVo    查询参数
     * @param page  分页参数
     * @return
     */
    @GetMapping
    public P getRolePage(PulRoleVo roleVo, Page page) {
        IPage<List<PulRoleViewVo>> roles = roleService.getRolePage(roleVo, page);
        return P.data(roles);
    }
}
