package top.pullulate.wechat.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.common.constants.ParamConstant;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountTagViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountTagVo;
import top.pullulate.wechat.entity.WechatOfficialAccountTag;
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
public class WechatOfficialAccountTagServiceImpl extends ServiceImpl<WechatOfficialAccountTagMapper, WechatOfficialAccountTag> implements IWechatOfficialAccountTagService {

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
}
