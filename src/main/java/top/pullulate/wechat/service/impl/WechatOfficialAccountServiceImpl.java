package top.pullulate.wechat.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountVo;
import top.pullulate.wechat.entity.WechatOfficialAccount;
import top.pullulate.wechat.mapper.WechatOfficialAccountMapper;
import top.pullulate.wechat.service.IWechatOfficialAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @功能描述:   微信公众号服务接口实现类
 * @Author: pullulate
 * @Date: 2020年9月1日17:36:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Service
public class WechatOfficialAccountServiceImpl extends ServiceImpl<WechatOfficialAccountMapper, WechatOfficialAccount> implements IWechatOfficialAccountService {

    /**
     * 获取公众号的分页数据
     *
     * @param officialAccountVo 查询参数
     * @param page  分页参数
     * @return
     */
    @Override
    public IPage<List<WechatOfficialAccountViewVo>> getOfficialAccountList(WechatOfficialAccountVo officialAccountVo, Page page) {
        IPage<List<WechatOfficialAccountViewVo>> pages = baseMapper.selectOfficialAccountPage(officialAccountVo, page);
        return pages;
    }
}
