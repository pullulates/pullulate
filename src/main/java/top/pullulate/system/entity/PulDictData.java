package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @功能描述:   字典数据实体类
 * @Author: xuyong
 * @Date: 2020/7/2 15:58
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulDictData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
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

    /** 删除状态：0-正常；1-已删除 */
    private String deleteFlag;

    /** 备注信息 */
    private String remark;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private LocalDateTime createAt;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    private LocalDateTime updateAt;
}
