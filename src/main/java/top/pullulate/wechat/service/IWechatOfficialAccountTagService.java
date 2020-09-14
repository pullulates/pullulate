package top.pullulate.wechat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.pullulate.web.data.dto.response.P;
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

    /**
     * 同步用户标签信息
     *
     * @param woaId    微信公众号主键
     * @return
     */
    P syncTag(String woaId);

    /**
     * 创建用户标签信息
     *
     * @param tagVo    微信公众号信息
     * @return
     */
    P saveTag(WechatOfficialAccountTagVo tagVo);

    /**
     * 编辑用户标签信息
     *
     * @param tagVo    微信公众号信息
     * @return
     */
    P updateTag(WechatOfficialAccountTagVo tagVo);
}
