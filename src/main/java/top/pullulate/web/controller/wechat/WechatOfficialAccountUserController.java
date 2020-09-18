package top.pullulate.web.controller.wechat;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.pullulate.common.validate.wechat.WechatOfficialAccount;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountUserViewVo;
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
    @OperationRecord(title = "微信公众号用户管理-同步用户")
    public P syncUser(@RequestBody @Validated(WechatOfficialAccount.Sync.class) WechatOfficialAccountUserVo userVo) {
        return wechatOfficialAccountUserService.syncUser(userVo.getWoaId());
    }

    /**
     * 设置用户备注
     *
     * @param userVo    用户信息
     * @return
     */
    @PatchMapping("/remark")
    @PreAuthorize("hasAuthority('woa.user.remark')")
    @OperationRecord(title = "微信公众号用户管理-设置备注")
    public P updateUserRemark(@RequestBody @Validated(WechatOfficialAccount.UpdateUserRemark.class) WechatOfficialAccountUserVo userVo) {
        return wechatOfficialAccountUserService.updateUserRemark(userVo);
    }

    /**
     * 拉黑用户
     *
     * @param userVo    用户信息
     * @return
     */
    @PatchMapping("/black")
    @PreAuthorize("hasAuthority('woa.user.black')")
    @OperationRecord(title = "微信公众号用户管理-拉黑用户")
    public P blackUser(@RequestBody @Validated(WechatOfficialAccount.BlackUser.class) WechatOfficialAccountUserVo userVo) {
        return wechatOfficialAccountUserService.blackUser(userVo);
    }

    /**
     * 取消拉黑用户
     *
     * @param userVo    用户信息
     * @return
     */
    @PatchMapping("/unblack")
    @PreAuthorize("hasAuthority('woa.user.unblack')")
    @OperationRecord(title = "微信公众号用户管理-取消拉黑用户")
    public P unblackUser(@RequestBody @Validated(WechatOfficialAccount.BlackUser.class) WechatOfficialAccountUserVo userVo) {
        return wechatOfficialAccountUserService.unblackUser(userVo);
    }

}

