package top.pullulate.web.api.wechat;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import top.pullulate.common.constants.WechatConstant;
import top.pullulate.core.exception.ApiException;

/**
 * @功能描述:   接口调用凭证api
 * @Author: xuyong
 * @Date: 2020/9/12 9:47
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
public class AccessTokenApi {

    /**
     * 获取接口调用凭证
     *
     * @param appId         第三方用户唯一凭证
     * @param appSecret     第三方用户唯一凭证密钥
     * @return  接口调用凭证
     */
    public static String getAccessToken(String appId, String appSecret) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 获取接口调用凭证方法开始执行");
        log.info("第三方用户唯一凭证：{}，凭证密钥：{}", appId, appSecret);
        String url = WechatConstant.ACCESS_TOKEN_URL + "?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        log.info("请求地址：{}", url);
        String result = HttpUtil.get(url);
        log.info("获取到响应结果：{}", result);
        if (StrUtil.isBlank(result)) {
            throw new ApiException("获取接口调用凭证失败");
        }
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (result.contains(WechatConstant.ERROR_CODE)) {
            throw new ApiException(StrFormatter.format("获取接口调用凭证失败，错误码：{}，错误信息：{}",
                    jsonObject.getStr(WechatConstant.ERROR_CODE), jsonObject.getStr(WechatConstant.ERROR_MSG)));
        }
        String accessToken = jsonObject.getStr("access_token");
        log.info("成功获取到接口调用凭证：{}", accessToken);
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 获取接口调用凭证方法执行结束");
        return accessToken;
    }
}
