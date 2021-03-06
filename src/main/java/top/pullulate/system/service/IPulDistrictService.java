package top.pullulate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.pullulate.system.entity.PulDistrict;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulDistrictsViewVo;

import java.util.List;

/**
 * @功能描述:  地区服务接口
 * @Author: xuyong
 * @Date: 2020/7/31 12:54
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
public interface IPulDistrictService extends IService<PulDistrict> {

    /**
     * 获取地区树列表数据
     *
     * @return
     */
    List<PulDistrictsViewVo> getDisctrictTreeList();

    /**
     * 获取所有的地区信息
     *
     * @return
     */
    List<PulDistrictsViewVo> getAllDistricts();

    /**
     * 同步省级地区数据
     *
     * @return
     */
    P syncProvinceDistricts();

    /**
     * 同步省级以下地区数据
     *
     * @return
     */
    P syncProvinceChildrenDistricts(PulDistrict district);
}
