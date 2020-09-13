package top.pullulate.web.api.wechat;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.pullulate.common.constants.WechatConstant;
import top.pullulate.core.exception.ApiException;
import top.pullulate.wechat.entity.WechatOfficialAccountUser;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @功能描述:   微信公众号用户api类
 * @Author: xuyong
 * @Date: 2020/9/12 15:39
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
public class WechatOfficialAccountUserApi {

    /**
     * 获取微信公众号关注用户列表
     * 一次最多拉取10000个用户
     *
     * @param accessToken   接口调用凭证
     * @param nextOpenId    第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     */
    public static List<String> getUsers(String accessToken, String nextOpenId) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 获取微信公众号关注用户列表方法开始执行");
        log.info("接口调用凭证：{}，第一个拉取的OPENID：{}", accessToken, nextOpenId);
        String url = StrFormatter.format(WechatConstant.WOA_USER_LIST_URL, accessToken, nextOpenId);
        log.info("请求地址：{}", url);
        String result = HttpUtil.get(url);
        log.info("获取到响应结果：{}", result);
        if (StrUtil.isBlank(result)) {
            throw new ApiException("获取微信公众号关注用户列表失败");
        }
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (result.contains(WechatConstant.ERROR_CODE)) {
            throw new ApiException(StrFormatter.format("获取微信公众号关注用户列表失败，错误码：{}，错误信息：{}",
                    jsonObject.getStr(WechatConstant.ERROR_CODE), jsonObject.getStr(WechatConstant.ERROR_MSG)));
        }
        int total = jsonObject.getInt("total");
        if (total == 0) {
            throw new ApiException("微信公众号暂时没有关注用户");
        }
        List<String> openIds = jsonObject.getJSONObject("data").getJSONArray("openid").toList(String.class);
        if (total > 10000) {
            String wxNextOpenId = jsonObject.getStr("next_openid");
            openIds = getLeftUsers(accessToken, wxNextOpenId, openIds);
        }
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 获取微信公众号关注用户列表方法执行结束");
        return openIds;
    }

    /**
     * 批量获取微信公众号关注用户的详细信息
     * 一次最多获取100个
     *
     * @param woaId         微信公众号主键
     * @param openIds       关注用户的openid集合
     * @param accessToken   接口调用凭证
     * @return
     */
    public static List<WechatOfficialAccountUser> batchGetUserInfo(String woaId, List<String> openIds, String accessToken) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 批量获取微信公众号关注用户的详细信息方法开始执行");
        String url = StrFormatter.format(WechatConstant.WOA_USER_INFO_BATCH_URL, accessToken);
        log.info("请求地址：{}", url);
        JSONArray jsonArray = JSONUtil.createArray();
        JSONArray paramArray = JSONUtil.createArray();
        int size = openIds.size();
        for (int i = 1; i < size + 1; i++) {
            JSONObject param = JSONUtil.createObj();
            param.set("openid", openIds.get(i-1));
            param.set("lang", "zh_CN");
            paramArray.add(param);
            if (i%100 == 0 || i == size) {
                JSONObject userListParam = JSONUtil.createObj();
                userListParam.set("user_list", paramArray);
                log.info("请求参数：{}", userListParam.toString());
                String result = HttpUtil.post(url, userListParam.toString());
                log.info("获取到响应结果：{}", result);
                if (StrUtil.isBlank(result)) {
                    throw new ApiException("批量获取微信公众号关注用户的详细信息失败");
                }
                JSONObject jsonObject = JSONUtil.parseObj(result);
                if (result.contains(WechatConstant.ERROR_CODE)) {
                    throw new ApiException(StrFormatter.format("批量获取微信公众号关注用户的详细信息失败，错误码：{}，错误信息：{}",
                            jsonObject.getStr(WechatConstant.ERROR_CODE), jsonObject.getStr(WechatConstant.ERROR_MSG)));
                }
                jsonArray.addAll(jsonObject.getJSONArray("user_info_list"));
                paramArray.clear();
            }
        }
        List<WechatOfficialAccountUser> users = new ArrayList<>(openIds.size());
        log.info("成功获取所有用户详细信息，开始转换信息格式:{}", jsonArray.size());
        List<WechatUserInfo> userInfos = jsonArray.toList(WechatUserInfo.class);
        userInfos.forEach(userInfo -> {
            WechatOfficialAccountUser user = BeanUtil.toBean(userInfo, WechatOfficialAccountUser.class);
            user.setWoaId(woaId);
            user.setUnionId(userInfo.getUnionid());
            user.setOpenId(userInfo.getOpenid());
            user.setNickName(userInfo.getNickname());
            user.setSubscribeTime(DateUtil.toLocalDateTime(DateUtil.date(userInfo.getSubscribe_time())));
            user.setSubscribeScene(userInfo.getSubscribe_scene());
            user.setHeadImgUrl(userInfo.getHeadimgurl());
            user.setGroupId(userInfo.getGroupid());
            users.add(user);
        });
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 批量获取微信公众号关注用户的详细信息方法执行结束");
        return users;
    }

    /**
     * 获取剩余关注者列表
     *
     * @param accessToken   接口调用凭证
     * @param nextOpenId    第一个拉取的OPENID，不填默认从头开始拉取
     * @param openIds       已拉取的OPENID集合
     * @return
     */
    public static List<String> getLeftUsers(String accessToken, String nextOpenId, List<String> openIds) {
        String url = StrFormatter.format(WechatConstant.WOA_USER_LIST_URL, accessToken, nextOpenId);
        log.info("请求地址：{}", url);
        String result = HttpUtil.get(url);
        log.info("获取到响应结果：{}", result);
        if (StrUtil.isBlank(result)) {
            throw new ApiException("获取微信公众号关注用户列表失败");
        }
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (result.contains(WechatConstant.ERROR_CODE)) {
            throw new ApiException(StrFormatter.format("获取微信公众号关注用户列表失败，错误码：{}，错误信息：{}",
                    jsonObject.getStr(WechatConstant.ERROR_CODE), jsonObject.getStr(WechatConstant.ERROR_MSG)));
        }
        List<String> leftOpenIds = jsonObject.getJSONObject("data").getJSONArray("openid").toList(String.class);
        openIds.addAll(leftOpenIds);
        String wxNextOpenId = jsonObject.getStr("next_openid");
        if (StrUtil.isNotBlank(wxNextOpenId)) {
            openIds = getLeftUsers(accessToken, wxNextOpenId, openIds);
        }
        return openIds;
    }
}

@Data
class WechatUserInfo {

    private String unionid;

    private String openid;

    private String nickname;

    private String sex;

    private String subscribe;

    private long subscribe_time;

    private String subscribe_scene;

    private String country;

    private String province;

    private String city;

    private String language;

    private String headimgurl;

    private String remark;

    private String groupid;

    private String[] tagid_list;
}
