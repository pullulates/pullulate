package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @功能描述:   字典类型实体类
 * @Author: xuyong
 * @Date: 2020/7/2 15:52
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulDictType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
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
    private String defaulted;

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
