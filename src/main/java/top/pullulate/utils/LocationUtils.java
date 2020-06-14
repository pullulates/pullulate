package top.pullulate.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * 获取地理位置
 * 
 */
public class LocationUtils {

    public static final String URL = "http://whois.pconline.com.cn/ipJson.jsp";

    public static final String UNKNOWN = "未知";

    public static String getRealAddressByIP(String ip) {
        String address = UNKNOWN;
        if (IPUtils.internalIp(ip)) {
            return address;
        }
        try {
            String rspStr = HttpUtil.get(URL + "?ip=" + ip + "&json=true", CharsetUtil.CHARSET_GBK);
            if (StrUtil.isBlank(rspStr)) {
                return address;
            }
            JSONObject json = JSONUtil.parseObj(rspStr);
            String region = json.getStr("pro");
            String city = json.getStr("city");
            return String.format("%s %s", region, city);
        } catch (Exception e) {
            return address;
        }
    }
}
