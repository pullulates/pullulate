package top.pullulate.wechat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountUserVo;
import top.pullulate.wechat.entity.WechatOfficialAccountUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @功能描述:   微信公众号服务接口实现类
 * @Author: pullulate
 * @Date: 2020年9月1日17:36:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface IWechatOfficialAccountUserService extends IService<WechatOfficialAccountUser> {

    /**
     * 获取微信公众号用户分页数据
     *
     * @param userVo    查询参数
     * @param page      分页参数
     * @return
     */
    IPage<List<WechatOfficialAccountViewVo>> getUserPage(WechatOfficialAccountUserVo userVo, Page page);

    /**
     * 同步用户信息
     *
     * @param woaId    微信公众号主键
     * @return
     */
    P syncUser(String woaId);
}
