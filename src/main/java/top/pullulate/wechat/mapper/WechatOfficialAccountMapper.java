package top.pullulate.wechat.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.pullulate.web.data.viewvo.wechat.WechatOfficialAccountViewVo;
import top.pullulate.web.data.vo.wechat.WechatOfficialAccountVo;
import top.pullulate.wechat.entity.WechatOfficialAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @功能描述:   微信公众号数据层
 * @Author: pullulate
 * @Date: 2020年9月1日17:36:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface WechatOfficialAccountMapper extends BaseMapper<WechatOfficialAccount> {

    /**
     * 获取公众号的分页数据
     *
     * @param officialAccountVo 查询参数
     * @param page  分页参数
     * @return
     */
    IPage<List<WechatOfficialAccountViewVo>> selectOfficialAccountPage(@Param("officialAccountVo") WechatOfficialAccountVo officialAccountVo, Page page);
}
