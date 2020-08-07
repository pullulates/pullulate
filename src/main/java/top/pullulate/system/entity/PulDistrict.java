package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @功能描述:   地区实体类
 * @Author: xuyong
 * @Date: 2020/7/30 22:07
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulDistrict implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 地区主键 */
    @TableId
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
    private transient List<PulDistrict> districts;
}
