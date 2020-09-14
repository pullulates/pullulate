package top.pullulate.web.api.wechat;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import top.pullulate.common.constants.WechatConstant;
import top.pullulate.core.exception.ApiException;
import top.pullulate.wechat.entity.WechatOfficialAccountTag;

import java.util.List;

/**
 * @功能描述:   微信公众号用户标签api类
 * @Author: xuyong
 * @Date: 2020/9/14 15:23
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
public class WechatOfficialAccountTagApi {

    /**
     * 获取微信公众号用户标签列表
     *
     * @param accessToken   接口调用凭证
     * @param woaId         微信公众号主键
     * @return
     */
    public static List<WechatOfficialAccountTag> getTags(String accessToken, String woaId) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 获取微信公众号关注用户标签列表方法开始执行");
        log.info("接口调用凭证：{}", accessToken);
        String url = StrFormatter.format(WechatConstant.WOA_TAG_LIST_URL, accessToken);
        log.info("请求地址：{}", url);
        String result = HttpUtil.get(url);
        log.info("获取到响应结果：{}", result);
        if (StrUtil.isBlank(result)) {
            throw new ApiException("获取微信公众号关注用户标签列表失败");
        }
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (result.contains(WechatConstant.ERROR_CODE)) {
            throw new ApiException(StrFormatter.format("获取微信公众号关注用户标签列表失败，错误码：{}，错误信息：{}",
                    jsonObject.getStr(WechatConstant.ERROR_CODE), jsonObject.getStr(WechatConstant.ERROR_MSG)));
        }
        JSONArray array = jsonObject.getJSONArray("tags");
        List<WechatOfficialAccountTag> tags = array.toList(WechatOfficialAccountTag.class);
        tags.forEach(tag -> tag.setWoaId(woaId));
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 获取微信公众号关注用户标签列表方法执行结束");
        return tags;
    }

    /**
     * 创建用户标签
     *
     * @param accessToken   接口调用凭证
     * @param name          标签名称
     * @return
     */
    public static String createTag(String accessToken, String name) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 创建用户标签方法开始执行");
        log.info("接口调用凭证：{}，标签名称：{}", accessToken, name);
        String url = StrFormatter.format(WechatConstant.WOA_TAG_CREATE_URL, accessToken);
        log.info("请求地址：{}", url);
        JSONObject param = JSONUtil.createObj();
        param.set("tag", JSONUtil.createObj().set("name", name));
        log.info("请求参数：{}", param.toString());
        String result = HttpUtil.post(url, param.toString());
        log.info("获取到响应结果：{}", result);
        if (StrUtil.isBlank(result)) {
            throw new ApiException("创建用户标签失败");
        }
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (result.contains(WechatConstant.ERROR_CODE)) {
            throw new ApiException(StrFormatter.format("创建用户标签失败，错误码：{}，错误信息：{}",
                    jsonObject.getStr(WechatConstant.ERROR_CODE), jsonObject.getStr(WechatConstant.ERROR_MSG)));
        }
        String id = jsonObject.getJSONObject("tag").getStr("id");
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 创建用户标签方法执行结束");
        return id;
    }

    public static Boolean updateTag(String accessToken, WechatOfficialAccountTag tag) {
        return true;
    }
}
