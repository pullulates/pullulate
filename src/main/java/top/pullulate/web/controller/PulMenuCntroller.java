package top.pullulate.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pullulate.system.service.IPulMenuService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.PulMenuViewVo;
import top.pullulate.web.data.vo.PulMenuVo;

import java.util.List;

/**
 * @功能描述:   菜单前端控制器
 * @Author: xuyong
 * @Date: 2020/7/9 8:19
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@RestController
@RequestMapping("/system/menus")
@RequiredArgsConstructor
public class PulMenuCntroller {

    private final IPulMenuService menuService;

    /**
     * 获取菜单树列表
     *
     * @param menuVo    查询参数
     * @return
     */
    @GetMapping
    public P getMenuTreeList(PulMenuVo menuVo) {
        List<PulMenuViewVo> menuViewVos = menuService.getMenuTreeList(menuVo);
        return P.data(menuViewVos);
    }

    /**
     * 获取菜单下拉选择树
     *
     * @return
     */
    @GetMapping("/tree-select")
    public P getMenuTreeSelect() {
        List<Tree> trees = menuService.getMenuTreeSelect();
        return P.data(trees);
    }
}
