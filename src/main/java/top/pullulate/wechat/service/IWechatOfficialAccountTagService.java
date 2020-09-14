package top.pullulate.wechat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountTagViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountTagVo;
import top.pullulate.wechat.entity.WechatOfficialAccountTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @功能描述:   微信公众号实体类
 * @Author: pullulate
 * @Date: 2020年9月14日09:46:49
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface IWechatOfficialAccountTagService extends IService<WechatOfficialAccountTag> {

    /**
     * 获取用户标签分页数据
     *
     * @param tagVo 查询参数
     * @param page  分页参数
     * @return
     */
    IPage<List<WechatOfficialAccountTagViewVo>> getTagPage(WechatOfficialAccountTagVo tagVo, Page page);
}
