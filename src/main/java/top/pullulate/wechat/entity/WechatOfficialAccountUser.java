package top.pullulate.wechat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 微信公众号粉丝
 * </p>
 *
 * @author xuyong
 * @since 2020-09-11
 */
@Getter
@Setter
@NoArgsConstructor
public class WechatOfficialAccountUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户主键
     */
    @TableId
    private String woaUserId;

    /**
     * 微信公众号主键
     */
    private String woaId;

    /**
     * 用户微信唯一标识
     */
    private String unionId;

    /**
     * 用户的标识，对当前公众号唯一
     */
    private String openId;

    /**
     * 用户的昵称
     */
    private String nickName;

    /**
     * 性别：0-未知；1-男；2-女
     */
    private String sex;

    /**
     * 用户是否关注公众号：0-未关注；1-关注
     */
    private String subscribe;

    /**
     * 关注时间
     */
    private LocalDateTime subscribeTime;

    /**
     * 关注的渠道来源
     */
    private String subscribeScene;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 语言
     */
    private String language;

    /**
     * 头像
     */
    private String headImgUrl;

    /**
     * 对粉丝的备注
     */
    private String remark;

    /**
     * 用户所在的分组id
     */
    private String groupId;

    /**
     * 是否拉黑
     */
    private String inBlackList;
}
