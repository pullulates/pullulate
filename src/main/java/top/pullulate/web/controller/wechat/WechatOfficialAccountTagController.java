package top.pullulate.web.controller.wechat;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.common.validate.Common;
import top.pullulate.common.validate.wechat.WechatOfficial;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountTagViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountTagVo;
import top.pullulate.wechat.service.IWechatOfficialAccountTagService;
import java.util.List;

/**
 * @功能描述:   微信公众号实体类
 * @Author: pullulate
 * @Date: 2020年9月14日09:46:49
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/wechat/official-account/tags")
public class WechatOfficialAccountTagController {

    private final IWechatOfficialAccountTagService tagService;

    /**
     * 获取用户标签分页数据
     *
     * @param tagVo 查询参数
     * @param page  分页参数
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('woa.tag.query')")
    public P getTagPage(WechatOfficialAccountTagVo tagVo, Page page) {
        IPage<List<WechatOfficialAccountTagViewVo>> pages = tagService.getTagPage(tagVo, page);
        return P.data(pages);
    }

    /**
     * 同步用户标签信息
     *
     * @param tagVo    微信公众号主键
     * @return
     */
    @PostMapping("/sync")
    @PreAuthorize("hasAuthority('woa.tag.sync')")
    public P syncTag(@RequestBody @Validated(WechatOfficial.Sync.class) WechatOfficialAccountTagVo tagVo) {
        return tagService.syncTag(tagVo.getWoaId());
    }

    /**
     * 创建用户标签信息
     *
     * @param tagVo    微信公众号信息
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('woa.tag.save')")
    public P saveTag(@RequestBody @Validated(Common.Save.class) WechatOfficialAccountTagVo tagVo) {
        return tagService.saveTag(tagVo);
    }

    /**
     * 编辑用户标签信息
     *
     * @param tagVo    微信公众号信息
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('woa.tag.edit')")
    public P updateTag(@RequestBody @Validated(Common.Update.class) WechatOfficialAccountTagVo tagVo) {
        return tagService.updateTag(tagVo);
    }

}

