package top.pullulate.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * 获取地理位置
 * 
 */
public class LocationUtils {

    private static final String ak = "5U7mhhfsTS1Ha8Y651OyBlvNcBZ0XgGG";

    public static final String url = "http://api.map.baidu.com/location/ip";

    public static String getLocation(String ip) {
        String location = "未知";
        if (IPUtils.internalIp(ip)) {
            return location;
        }
        String rspStr = HttpUtil.get(url + "?ip=" + ip + "&ak=" + ak);
        if (StrUtil.isBlank(rspStr)) {
            return location;
        }
        JSONObject obj = JSONUtil.parseObj(rspStr);
        if (obj.getInt("status") == 0) {
            JSONObject content = obj.getJSONObject("content");
            JSONObject addressDetail = content.getJSONObject("address_detail");
            location = UnicodeUtils.unicodeDecode(addressDetail.getStr("province"))
                    + UnicodeUtils.unicodeDecode(addressDetail.getStr("city"))
                    + UnicodeUtils.unicodeDecode(addressDetail.getStr("district"));
        }
        return location;
    }
}
