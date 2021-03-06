package top.pullulate.wechat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountVo;
import top.pullulate.wechat.entity.WechatOfficialAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @功能描述:   微信公众号服务接口
 * @Author: pullulate
 * @Date: 2020年9月1日17:36:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface IWechatOfficialAccountService extends IService<WechatOfficialAccount> {

    /**
     * 获取公众号的分页数据
     *
     * @param officialAccountVo 查询参数
     * @param page  分页参数
     * @return
     */
    IPage<List<WechatOfficialAccountViewVo>> getOfficialAccountList(WechatOfficialAccountVo officialAccountVo, Page page);

    /**
     * 保存我的微信公众号信息
     *
     * @param officialAccountVo 微信公众号信息
     * @return
     */
    P saveOfficialAccount(WechatOfficialAccountVo officialAccountVo);

    /**
     * 修改我的微信公众号信息
     *
     * @param officialAccountVo 微信公众号信息
     * @return
     */
    P updateOfficialAccount(WechatOfficialAccountVo officialAccountVo);

    /**
     * 删除我的微信公众号信息
     *
     * @param woaId 微信公众号主键
     * @return
     */
    P deleteOfficialAccount(String woaId);

    /**
     * 获取微信公众号树结构数据
     *
     * @return
     */
    List<Tree> getOfficialAccountTree();
}
