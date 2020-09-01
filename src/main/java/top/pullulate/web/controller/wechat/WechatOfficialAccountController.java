package top.pullulate.web.controller.wechat;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @PreAuthorize("hasAuthority('wechat.official.account.query')")
    public P getOfficialAccountList(WechatOfficialAccountVo officialAccountVo, Page page) {
        IPage<List<WechatOfficialAccountViewVo>> pages = officialAccountService.getOfficialAccountList(officialAccountVo, page);
        return P.data(pages);
    }

}

