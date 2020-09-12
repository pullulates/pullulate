package top.pullulate.wechat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.common.constants.ParamConstant;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.dto.tree.Tree;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountVo;
import top.pullulate.wechat.entity.WechatOfficialAccount;
import top.pullulate.wechat.mapper.WechatOfficialAccountMapper;
import top.pullulate.wechat.service.IWechatOfficialAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 保存我的微信公众号信息
     *
     * @param officialAccountVo 微信公众号信息
     * @return
     */
    @Override
    public P saveOfficialAccount(WechatOfficialAccountVo officialAccountVo) {
        int count = count(Wrappers.<WechatOfficialAccount>lambdaQuery()
                .eq(WechatOfficialAccount::getName, officialAccountVo.getName())
                .or()
                .eq(WechatOfficialAccount::getAppId, officialAccountVo.getAppId())
                .or()
                .eq(WechatOfficialAccount::getOriginalId, officialAccountVo.getOriginalId()));
        if (count > 0) {
            return P.error("微信公众号已存在，请检查公众号的名称、原始ID或开发者ID是否重复");
        }
        WechatOfficialAccount officialAccount = BeanUtil.toBean(officialAccountVo, WechatOfficialAccount.class);
        return P.p(save(officialAccount));
    }

    /**
     * 修改我的微信公众号信息
     *
     * @param officialAccountVo 微信公众号信息
     * @return
     */
    @Override
    public P updateOfficialAccount(WechatOfficialAccountVo officialAccountVo) {
        int count = count(Wrappers.<WechatOfficialAccount>lambdaQuery()
                .ne(WechatOfficialAccount::getWoaId, officialAccountVo.getWoaId())
                .and(wrapper -> wrapper
                    .eq(WechatOfficialAccount::getName, officialAccountVo.getName())
                    .or()
                    .eq(WechatOfficialAccount::getAppId, officialAccountVo.getAppId())
                    .or()
                    .eq(WechatOfficialAccount::getOriginalId, officialAccountVo.getOriginalId())
                ));
        if (count > 0) {
            return P.error("微信公众号已存在，请检查公众号的名称、原始ID或开发者ID是否重复");
        }
        WechatOfficialAccount officialAccount = BeanUtil.toBean(officialAccountVo, WechatOfficialAccount.class);
        return P.p(updateById(officialAccount));
    }

    /**
     * 删除我的微信公众号信息
     *
     * @param woaId 微信公众号主键
     * @return
     */
    @Override
    public P deleteOfficialAccount(String woaId) {
        return P.p(removeById(woaId));
    }

    /**
     * 获取微信公众号树结构数据
     *
     * @return
     */
    @Override
    public List<Tree> getOfficialAccountTree() {
        List<WechatOfficialAccount> wechatOfficialAccounts = list();
        List<Tree> trees = new ArrayList<>(1);
        // 添加我的公众号作为顶级树结构
        Tree tree = new Tree("我的公众号", ParamConstant.TOP_ID, ParamConstant.TOP_ID);
        List<Tree> children = wechatOfficialAccounts.stream()
                .map(item -> new Tree(item.getName(), item.getWoaId(), item.getWoaId()))
                .collect(Collectors.toList());
        tree.setChildren(children);
        trees.add(tree);
        return trees;
    }
}
