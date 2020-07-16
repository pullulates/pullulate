package top.pullulate.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.system.service.IPulMenuService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.PulMenuViewVo;
import top.pullulate.web.data.vo.PulMenuVo;

import java.util.List;

/**
 * @功能描述:   路由前端控制器
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
     * 获取路由树列表
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
     * 获取路由下拉选择树
     *
     * @return
     */
    @GetMapping("/tree-select")
    public P getMenuTreeSelect() {
        List<Tree> trees = menuService.getMenuTreeSelect();
        return P.data(trees);
    }

    /**
     * 保存路由
     *
     * @param menuVo    路由信息
     * @return
     */
    @PostMapping
    @OperationRecord(title = "路由管理-保存路由")
    public P saveMenu(@RequestBody @Validated PulMenuVo menuVo) {
        return menuService.saveMenu(menuVo);
    }

    /**
     * 修改路由
     *
     * @param menuVo    路由信息
     * @return
     */
    @PutMapping
    @OperationRecord(title = "路由管理-修改路由")
    public P updateMenu(@RequestBody @Validated PulMenuVo menuVo) {
        return menuService.updateMenu(menuVo);
    }

    /**
     * 修改路由的状态
     *
     * @param menuVo    路由参数
     * @return
     */
    @PatchMapping
    @OperationRecord(title = "路由管理-修改路由状态")
    public P updateMenuStatus(@RequestBody PulMenuVo menuVo) {
        return menuService.updateMenuStatus(menuVo);
    }

    /**
     * 删除路由信息
     *
     * @param menuId    路由主键
     * @return
     */
    @DeleteMapping
    @OperationRecord(title = "路由管理-删除路由")
    public P deleteMenu(String menuId) {
        return menuService.deleteMenu(menuId);
    }
}
