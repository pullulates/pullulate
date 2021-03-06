package top.pullulate.web.controller.system;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.pullulate.core.annotations.OperationRecord;
import top.pullulate.system.entity.PulDistrict;
import top.pullulate.system.service.IPulDistrictService;
import top.pullulate.web.data.dto.response.P;
import top.pullulate.web.data.viewvo.system.PulDistrictsViewVo;

import java.util.List;

/**
 * @功能描述:   地区前端控制器
 * @Author: xuyong
 * @Date: 2020/7/31 12:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/districts")
public class PulDistrictController {

    private final IPulDistrictService districtService;

    /**
     * 获取地区树列表数据
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('system.district.query')")
    public P getDisctrictTreeList() {
        List<PulDistrictsViewVo> districts = districtService.getDisctrictTreeList();
        return P.data(districts);
    }

    /**
     * 同步省级地区数据
     *
     * @return
     */
    @PostMapping("/sync-province")
    @OperationRecord(title = "地区管理-同步省级数据")
    @PreAuthorize("hasAuthority('system.district.sync')")
    public P syncDistricts() {
        return districtService.syncProvinceDistricts();
    }

    /**
     * 同步省级以下地区数据
     *
     * @return
     */
    @PostMapping("/sync-province-children")
    @OperationRecord(title = "地区管理-同步省级以下数据")
    @PreAuthorize("hasAuthority('system.district.sync')")
    public P syncProvinceChildrenDistricts(@RequestBody PulDistrict district) {
        return districtService.syncProvinceChildrenDistricts(district);
    }
}
