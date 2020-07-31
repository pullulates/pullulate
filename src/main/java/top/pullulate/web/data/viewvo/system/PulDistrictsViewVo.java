package top.pullulate.web.data.viewvo.system;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @功能描述:   地区视图类
 * @Author: xuyong
 * @Date: 2020/7/31 12:52
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Getter
@Setter
public class PulDistrictsViewVo {

    /** 地区主键 */
    private String districtId;

    /** 上级地区主键 */
    private String parentId;

    /** 行政区名称 */
    private String name;

    /** 行政区划级别 */
    private String level;

    /** 城市编码 */
    private String citycode;

    /** 区域编码 */
    private String adcode;

    /** 区域中心点 */
    private String center;

    /** 行政区边界坐标点 */
    private String polyline;

    /** 创建时间 */
    private LocalDateTime createAt;

    /** 下级行政区列表 */
    private transient List<PulDistrictsViewVo> children;
}
