package top.pullulate.common.constants;

/**
 * @功能描述:   缓存常量定义类
 * @Author: pullulates
 * @Date: 2020/6/10 0010 19:33
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
public class CacheConstant {

    /**
     * 会话凭证缓存过期时间，单位：分钟
     */
    public static final int CACHE_TOKEN_EXPIRE_TIME = 30;

    /**
     * 会话凭证缓存刷新阈值，单位：分钟
     */
    public static final int CACHE_TOKEN_REFRESH_THRESHOLD_TIME = 10;

    /**
     * 其他业务缓存过期时间，单位：分钟
     */
    public static final int CACHE_BUSINESS_EXPIRE_TIME = -1;

    /**
     * 用户信息缓存键前缀
     */
    public static final String CACHE_USER_INFO_PREFFIX = "pullulate_user_info_";

    /**
     * 请求携带会话凭证的键名
     */
    public static final String CACHE_REQUEST_TOKEN_KEY = "PULLULATE_ACCESS_TOKEN";

    /**
     * 会话凭证的密钥
     */
    public static final String CACHE_TOKEN_SECRET = "PULLULATE";

    /**
     * 会话凭证的统一前缀
     */
    public static final String CACHE_TOKEN_PREFFIX = "pullulate";

    /**
     * 图形验证码缓存键统一前缀
     */
    public static final String CACHE_IMAGE_CAPTCHA_PREFFIX = "pullulate_image_captcha_";

    /**
     * 图形验证码缓存过期时间，单位：分钟
     */
    public static final int CACHE_IMAGE_CAPTCHA_EXPIRE_TIME = 2;

    /**
     * 手机验证码缓存键统一前缀
     */
    public static final String CACHE_PHONE_CAPTCHA_PREFFIX = "pullulate_phone_captcha_";

    /**
     * 数据字典缓存键统一前缀
     */
    public static final String CACHE_DICT_PREFFIX = "pullulate_dict_";

    /**
     * 用户登录后返回的数据字典缓存键
     */
    public static final String CACHE_DICT_FRONT_KEY = "pullulate_dict_front";
}
