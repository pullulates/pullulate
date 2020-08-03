package top.pullulate.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.pullulate.common.constants.Constant;
import top.pullulate.system.entity.PulDistrict;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @功能描述:   地区工具类
 * @Author: xuyong
 * @Date: 2020/7/30 21:49
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Slf4j
@Component
public class DistrictUtils {

    @Value("${gaode.key}")
    private String key;

    @Value("${gaode.districtUrl}")
    private String districtUrl;

    private int timeOut = 10000;

    /**
     * 获取升级地区信息
     *
     * @return
     */
    public List<PulDistrict> getProvinceDistrictInfo() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>> 请求高德平台获取省级地区信息");
        String result = HttpUtil.get(districtUrl + key + "&subdistrict=1", timeOut);
        if (StrUtil.isBlank(result)) {
            log.info("<<<<<<<<<<<<<<<<<<<<<<<< 高德平台未响应数据或请求超时，请求结束");
            return null;
        }
        JSONObject json = JSONUtil.parseObj(result);
        log.info("高德平台响应状态码：{}，响应消息：{}", json.getStr("status"), json.getStr("info"));
        if (json.getStr("status").equals("1")) {
            JSONArray jsonArray = json.getJSONArray("districts");
            List<PulDistrict> districts = buildDistricts(jsonArray.toList(PulDistrict.class).get(0).getDistricts(), Constant.LEVEL_DATA_PARENT_ID);
            log.info("<<<<<<<<<<<<<<<<<<<<<<<< 成功处理响应数据，请求结束");
            return districts;
        } else {
            log.info("<<<<<<<<<<<<<<<<<<<<<<<< 未获取到响应数据，请求结束");
            return new ArrayList<>();
        }
    }

    /**
     * 获取省级以下地区信息
     *
     * @return
     */
    public List<PulDistrict> getProvinceChildrenDistrictInfo(String keywords, String parentId) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>> 请求高德平台获取省级以下地区信息");
        String result = HttpUtil.get(districtUrl + key + "&subdistrict=2&keywords=" + keywords, timeOut);
        if (StrUtil.isBlank(result)) {
            log.info("<<<<<<<<<<<<<<<<<<<<<<<< 高德平台未响应数据或请求超时，请求结束");
            return null;
        }
        JSONObject json = JSONUtil.parseObj(result);
        log.info("高德平台响应状态码：{}，响应消息：{}", json.getStr("status"), json.getStr("info"));
        if (json.getStr("status").equals("1")) {
            JSONArray jsonArray = json.getJSONArray("districts");
            List<PulDistrict> districts = buildDistricts(jsonArray.toList(PulDistrict.class).get(0).getDistricts(), parentId);
            log.info("<<<<<<<<<<<<<<<<<<<<<<<< 成功处理响应数据，请求结束");
            return districts;
        } else {
            log.info("<<<<<<<<<<<<<<<<<<<<<<<< 未获取到响应数据，请求结束");
            return new ArrayList<>();
        }
    }

    private List<PulDistrict> buildDistricts(List<PulDistrict> districts, String parentId) {
        LocalDateTime now = LocalDateTime.now();
        districts.forEach(district -> {
            district.setDistrictId(IdUtil.fastSimpleUUID());
            district.setParentId(parentId);
            district.setCreateAt(now);
            if ("[]".equals(district.getCitycode())) {
                district.setCitycode(null);
            }
            if (CollectionUtil.isNotEmpty(district.getDistricts())) {
                List<PulDistrict> children = buildDistricts(district.getDistricts(), district.getDistrictId());
                district.setDistricts(children);
            }
        });
        return districts;
    }
}
