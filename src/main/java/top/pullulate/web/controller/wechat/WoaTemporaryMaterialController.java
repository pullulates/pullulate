package top.pullulate.web.controller.wechat;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.wechat.WoaTemporaryMaterialViewVo;
import top.pullulate.web.data.vo.wechat.WoaTemporaryMaterialVo;
import top.pullulate.wechat.service.IWoaTemporaryMaterialService;

import java.util.List;

/**
 * @功能描述:   微信公众号临时素材前端控制器
 * @Author: pullulate
 * @Date: 2020年9月23日15:01:58
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/wechat/official-account/temporary-material")
public class WoaTemporaryMaterialController {

    private final IWoaTemporaryMaterialService temporaryMaterialService;

    /**
     * 获取临时素材的分页数据
     *
     * @param materialVo    查询参数
     * @param page          分页参数
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('woa.material.temporary.query')")
    public P getTemporaryMaterialPage(WoaTemporaryMaterialVo materialVo, Page page) {
        IPage<List<WoaTemporaryMaterialViewVo>> pages = temporaryMaterialService.getTemporaryMaterialPage(materialVo, page);
        return P.data(pages);
    }

}

