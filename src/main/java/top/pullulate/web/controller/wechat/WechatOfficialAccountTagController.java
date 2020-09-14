package top.pullulate.web.controller.wechat;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public P getTagPage(WechatOfficialAccountTagVo tagVo, Page page) {
        IPage<List<WechatOfficialAccountTagViewVo>> pages = tagService.getTagPage(tagVo, page);
        return P.data(pages);
    }

}

