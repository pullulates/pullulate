package top.pullulate.web.controller.wechat;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountUserViewVo;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountUserVo;
import top.pullulate.wechat.service.IWechatOfficialAccountUserService;

import java.util.List;

/**
 * @功能描述:   微信公众号服务接口实现类
 * @Author: pullulate
 * @Date: 2020年9月1日17:36:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/wechat/official-account/users")
public class WechatOfficialAccountUserController {

    private final IWechatOfficialAccountUserService wechatOfficialAccountUserService;

    /**
     * 获取微信公众号用户分页数据
     *
     * @param userVo    查询参数
     * @param page      分页参数
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('woa.user.query')")
    public P getUserPage(WechatOfficialAccountUserVo userVo, Page page) {
        IPage<List<WechatOfficialAccountUserViewVo>> pages = wechatOfficialAccountUserService.getUserPage(userVo, page);
        return P.data(pages);
    }

    /**
     * 同步用户信息
     *
     * @param userVo    微信公众号主键
     * @return
     */
    @PostMapping("/sync")
    @PreAuthorize("hasAuthority('woa.user.sync')")
    public P syncUser(@RequestBody @Validated WechatOfficialAccountUserVo userVo) {
        return wechatOfficialAccountUserService.syncUser(userVo.getWoaId());
    }

}

