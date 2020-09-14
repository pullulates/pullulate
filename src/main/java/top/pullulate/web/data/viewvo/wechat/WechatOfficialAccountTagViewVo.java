package top.pullulate.web.data.viewvo.wechat;

import lombok.Data;

/**
 * @功能描述:   微信公众号视图显示类
 * @Author: pullulate
 * @Date: 2020年9月14日09:46:49
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class WechatOfficialAccountTagViewVo {

    /**
     * 标签主键
     */
    private String woaTagId;

    /**
     * 微信公众号主键
     */
    private String woaId;

    /**
     * 微信标签id
     */
    private String id;

    /**
     * 标签名称
     */
    private String name;


}
