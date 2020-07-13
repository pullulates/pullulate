package top.pullulate.web.data.vo;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * @功能描述:   字典数据参数接收类
 * @Author: pullulates
 * @Date: 2020/7/6 0006 20:34
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulDictDataVo {

    private String dictDataId;

    /** 字典类别主键 */
    @NotBlank(message = "字典类别主键不能为空")
    @Size(max = 32, message = "字典类别主键最多32字")
    private String dictTypeId;

    /** 值 */
    @NotBlank(message = "字典值不能为空")
    @Size(max = 20, message = "字典值最多50字")
    private String dictValue;

    /** 标签 */
    @NotBlank(message = "字典标签不能为空")
    @Size(max = 20, message = "字典标签最多20字")
    private String dictLabel;

    /** 排序编号 */
    @NotNull(message = "排序编号不能为空")
    @Min(value = 1, message = "排序编号低于最小值1")
    @Max(value = 999, message = "排序编号超出最大值999")
    private Integer orderNum;

    /** 状态：0-正常；1-禁用 */
    private String status;

    /** 字典样式 */
    @NotBlank(message = "字典样式不能为空")
    @Size(max = 20, message = "字典样式最多20字")
    private String dictCss;

    /** 删除状态：0-正常；1-已删除 */
    private String deleteFlag;

    /** 备注信息 */
    @Size(max = 250, message = "备注信息最多250字")
    private String remark;
}
