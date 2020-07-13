package top.pullulate.web.data.viewvo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   字典数据视图类
 * @Author: xuyong
 * @Date: 2020/7/2 16:15
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulDictDataViewVo {

    private String dictDataId;

    /** 字典类别主键 */
    private String dictTypeId;

    /** 值 */
    private String dictValue;

    /** 标签 */
    private String dictLabel;

    /** 排序编号 */
    private Integer orderNum;

    /** 状态：0-正常；1-禁用 */
    private String status;

    /** 字典样式 */
    private String dictCss;

    /** 备注信息 */
    private String remark;

    /** 创建时间 */
    private LocalDateTime createAt;
}
