package top.pullulate.web.data.vo;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @功能描述:   数据字典参数接收类
 * @Author: xuyong
 * @Date: 2020/7/2 16:11
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulDictTypeVo {

    private String dictTypeId;

    @NotBlank(message = "字典键不能为空")
    @Size(max = 50, message = "字典键最多50字")
    private String key;

    @NotBlank(message = "字典描述不能为空")
    @Size(max = 100, message = "字典描述最多100字")
    private String description;

    @NotNull(message = "排序编号不能为空")
    @Min(value = 1, message = "排序编号低于最小值1")
    @Max(value = 999, message = "排序编号超出最大值999")
    private Integer orderNum;

    private String status;

    @NotBlank(message = "是否默认不能为空")
    private String willDefault;

    private String deleteFlag;


    private String remark;
}
