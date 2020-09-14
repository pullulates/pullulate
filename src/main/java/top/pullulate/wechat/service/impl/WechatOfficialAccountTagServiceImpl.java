package top.pullulate.wechat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import top.pullulate.common.constants.ParamConstant;
import top.pullulate.web.api.wechat.AccessTokenApi;
import top.pullulate.web.api.wechat.WechatOfficialAccountTagApi;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountTagViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountTagVo;
import top.pullulate.wechat.entity.WechatOfficialAccount;
import top.pullulate.wechat.entity.WechatOfficialAccountTag;
import top.pullulate.wechat.mapper.WechatOfficialAccountMapper;
import top.pullulate.wechat.mapper.WechatOfficialAccountTagMapper;
import top.pullulate.wechat.service.IWechatOfficialAccountTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @功能描述:   微信公众号实体类
 * @Author: pullulate
 * @Date: 2020年9月14日09:46:49
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Service
@RequiredArgsConstructor
public class WechatOfficialAccountTagServiceImpl extends ServiceImpl<WechatOfficialAccountTagMapper, WechatOfficialAccountTag> implements IWechatOfficialAccountTagService {

    private final AccessTokenApi accessTokenApi;

    private final WechatOfficialAccountMapper officialAccountMapper;

    /**
     * 获取用户标签分页数据
     *
     * @param tagVo 查询参数
     * @param page  分页参数
     * @return
     */
    @Override
    public IPage<List<WechatOfficialAccountTagViewVo>> getTagPage(WechatOfficialAccountTagVo tagVo, Page page) {
        LambdaQueryWrapper<WechatOfficialAccountTag> wrappers = Wrappers.lambdaQuery();
        if (!(StrUtil.isBlank(tagVo.getWoaId()) || ParamConstant.TOP_ID.equals(tagVo.getWoaId()))) {
            wrappers.eq(WechatOfficialAccountTag::getWoaId, tagVo.getWoaId());
        }
        IPage<List<WechatOfficialAccountTagViewVo>> pages = page(page, wrappers);
        return pages;
    }

    /**
     * 同步用户标签信息
     *
     * @param woaId    微信公众号主键
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P syncTag(String woaId) {
        WechatOfficialAccount officialAccount = officialAccountMapper.selectById(woaId);
        if (ObjectUtil.isNull(officialAccount)) {
            return P.error("未查询到微信公众号信息");
        }
        String accessToken = accessTokenApi.getAccessToken(officialAccount.getAppId(), officialAccount.getAppSecret());
        List<WechatOfficialAccountTag> tags = WechatOfficialAccountTagApi.getTags(accessToken, woaId);
        baseMapper.delete(Wrappers.lambdaQuery());
        saveBatch(tags);
        return P.success();
    }

    /**
     * 创建用户标签信息
     *
     * @param tagVo    微信公众号信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P saveTag(WechatOfficialAccountTagVo tagVo) {
        WechatOfficialAccountTag tag = BeanUtil.toBean(tagVo, WechatOfficialAccountTag.class);
        WechatOfficialAccount officialAccount = officialAccountMapper.selectById(tag.getWoaId());
        if (ObjectUtil.isNull(officialAccount)) {
            return P.error("未查询到微信公众号信息");
        }
        String accessToken = accessTokenApi.getAccessToken(officialAccount.getAppId(), officialAccount.getAppSecret());
        String id = WechatOfficialAccountTagApi.createTag(accessToken, tag.getName());
        tag.setId(id);
        return P.p(save(tag));
    }

    /**
     * 编辑用户标签信息
     *
     * @param tagVo    微信公众号信息
     * @return
     */
    @Override
    public P updateTag(WechatOfficialAccountTagVo tagVo) {
        WechatOfficialAccountTag tag = BeanUtil.toBean(tagVo, WechatOfficialAccountTag.class);
        WechatOfficialAccount officialAccount = officialAccountMapper.selectById(tag.getWoaId());
        if (ObjectUtil.isNull(officialAccount)) {
            return P.error("未查询到微信公众号信息");
        }
        String accessToken = accessTokenApi.getAccessToken(officialAccount.getAppId(), officialAccount.getAppSecret());
        Boolean result = WechatOfficialAccountTagApi.updateTag(accessToken, tag);
        return null;
    }
}
