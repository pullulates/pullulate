package top.pullulate.web.controller.wechat;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.common.validate.Common;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountVo;
import top.pullulate.wechat.service.IWechatOfficialAccountService;
import java.util.List;

/**
 * @功能描述:   微信公众号前端控制器
 * @Author: pullulate
 * @Date: 2020年9月1日17:36:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/wechat/official-account")
public class WechatOfficialAccountController {

    private final IWechatOfficialAccountService officialAccountService;

    /**
     * 获取公众号的分页数据
     *
     * @param officialAccountVo 查询参数
     * @param page  分页参数
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('woa.manager.query')")
    public P getOfficialAccountList(WechatOfficialAccountVo officialAccountVo, Page page) {
        IPage<List<WechatOfficialAccountViewVo>> pages = officialAccountService.getOfficialAccountList(officialAccountVo, page);
        return P.data(pages);
    }

    /**
     * 保存我的微信公众号信息
     *
     * @param officialAccountVo 微信公众号信息
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('woa.manager.save')")
    @OperationRecord(title = "我的微信公众号-保存微信公众号")
    public P saveOfficialAccount(@RequestBody @Validated(Common.Save.class) WechatOfficialAccountVo officialAccountVo) {
        return officialAccountService.saveOfficialAccount(officialAccountVo);
    }

    /**
     * 修改我的微信公众号信息
     *
     * @param officialAccountVo 微信公众号信息
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('woa.manager.edit')")
    @OperationRecord(title = "我的微信公众号-修改微信公众号")
    public P updateOfficialAccount(@RequestBody @Validated(Common.Update.class) WechatOfficialAccountVo officialAccountVo) {
        return officialAccountService.updateOfficialAccount(officialAccountVo);
    }

    /**
     * 删除我的微信公众号信息
     *
     * @param woaId 微信公众号主键
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('woa.manager.del')")
    @OperationRecord(title = "我的微信公众号-删除微信公众号")
    public P deleteOfficialAccount(String woaId) {
        return officialAccountService.deleteOfficialAccount(woaId);
    }

}

