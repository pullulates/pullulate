package top.pullulate.web.data.viewvo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   字典类别视图类
 * @Author: xuyong
 * @Date: 2020/7/2 16:14
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulDictTypeViewVo {

    private String dictTypeId;

    /** 字典类别键 */
    private String key;

    /** 字典类别描述 */
    private String description;

    /** 排序编号 */
    private Integer orderNum;

    /** 状态：0-正常；1-禁用 */
    private String status;

    /** 是否默认：0-默认；1-非默认 */
    private String willDefault;

    /** 备注信息 */
    private String remark;

    /** 创建时间 */
    private LocalDateTime createAt;
}
