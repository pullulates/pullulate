package top.pullulate.wechat.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @功能描述:   微信公众号实体类
 * @Author: pullulate
 * @Date: 2020年9月14日09:46:49
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class WechatOfficialAccountTag implements Serializable {

    private static final long serialVersionUID=1L;

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
