package top.pullulate.wechat.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.sql.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import top.pullulate.common.constants.ParamConstant;
import top.pullulate.web.api.wechat.AccessTokenApi;
import top.pullulate.web.api.wechat.WechatOfficialAccountUserApi;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountUserVo;
import top.pullulate.wechat.entity.WechatOfficialAccount;
import top.pullulate.wechat.entity.WechatOfficialAccountUser;
import top.pullulate.wechat.mapper.WechatOfficialAccountMapper;
import top.pullulate.wechat.mapper.WechatOfficialAccountUserMapper;
import top.pullulate.wechat.service.IWechatOfficialAccountUserService;
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
@RequiredArgsConstructor
public class WechatOfficialAccountUserServiceImpl extends ServiceImpl<WechatOfficialAccountUserMapper, WechatOfficialAccountUser> implements IWechatOfficialAccountUserService {

    private final AccessTokenApi accessTokenApi;

    private final WechatOfficialAccountMapper officialAccountMapper;

    /**
     * 获取微信公众号用户分页数据
     *
     * @param userVo    查询参数
     * @param page      分页参数
     * @return
     */
    @Override
    public IPage<List<WechatOfficialAccountViewVo>> getUserPage(WechatOfficialAccountUserVo userVo, Page page) {
        LambdaQueryWrapper<WechatOfficialAccountUser> wrappers = Wrappers.lambdaQuery();
        if (!(StrUtil.isBlank(userVo.getWoaId()) || ParamConstant.TOP_ID.equals(userVo.getWoaId()))) {
            wrappers.eq(WechatOfficialAccountUser::getWoaId, userVo.getWoaId());
        }
        IPage<List<WechatOfficialAccountViewVo>> pages = page(page, wrappers);
        return pages;
    }

    /**
     * 同步用户信息
     *
     * @param woaId    微信公众号主键
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public P syncUser(String woaId) {
        WechatOfficialAccount officialAccount = officialAccountMapper.selectById(woaId);
        if (ObjectUtil.isNull(officialAccount)) {
            return P.error("未查询到微信公众号信息");
        }
        String accessToken = accessTokenApi.getAccessToken(officialAccount.getAppId(), officialAccount.getAppSecret());
        List<String> openIds = WechatOfficialAccountUserApi.getUsers(accessToken, "");
        List<WechatOfficialAccountUser> users = WechatOfficialAccountUserApi.batchGetUserInfo(woaId, openIds, accessToken);
        baseMapper.truncateUser();
        saveBatch(users);
        return P.success();
    }
}
