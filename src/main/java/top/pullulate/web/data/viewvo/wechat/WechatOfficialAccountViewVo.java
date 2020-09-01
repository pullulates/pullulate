package top.pullulate.web.data.viewvo.wechat;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @功能描述:   微信公众号视图类
 * @Author: xuyong
 * @Date: 2020/9/1 18:07
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class WechatOfficialAccountViewVo {

    /**
     * 微信公众号主键
     */
    private String woaId;

    /**
     * 名称
     */
    private String name;

    /**
     * 公众号类型：0-订阅号；1-服务号
     */
    private String woaType;

    /**
     * 认证结果：0-未认证；1-已认证
     */
    private String certificationResult;

    /**
     * 原始id
     */
    private String originalId;

    /**
     * appId
     */
    private String appId;

    /**
     * appSecret
     */
    private String appSecret;

    /**
     * token
     */
    private String token;

    /**
     * 接口消息加解密秘钥
     */
    private String encryptKey;

    /**
     * 主体名称
     */
    private String subjectName;

    /**
     * 主体类型：0-个人；1-企业
     */
    private String subjectType;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    private String createBy;
}
