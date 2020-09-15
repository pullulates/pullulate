package top.pullulate.common.constants;

/**
 * @功能描述:   微信公众号常量类
 * @Author: xuyong
 * @Date: 2020/9/12 9:42
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public class WechatConstant {

    /**
     * 微信官方api返回错误码key
     */
    public static final String ERROR_CODE = "errcode";

    /**
     * 微信官方api返回错误信息key
     */
    public static final String ERROR_MSG = "errmsg";

    /**
     * 微信官方api返回成功码
     */
    public static final String SUCCESS_CODE = "0";

    /**
     * 获取接口调用凭证的接口地址
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={}&secret={}";

    /**
     * 获取微信公众号关注者列表的接口地址
     */
    public static final String WOA_USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={}&next_openid={}";

    /**
     * 批量获取微信公众号关注者详细信息的接口地址
     */
    public static final String WOA_USER_INFO_BATCH_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token={}";

    /**
     * 获取微信公众号已创建标签列表的接口地址
     */
    public static final String WOA_TAG_LIST_URL = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token={}";

    /**
     * 微信公众号创建标签的接口地址
     */
    public static final String WOA_TAG_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token={}";

    /**
     * 微信公众号修改标签的接口地址
     */
    public static final String WOA_TAG_UPDATE_URL = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token={}";

    /**
     * 微信公众号删除标签的接口地址
     */
    public static final String WOA_TAG_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token={}";
}
