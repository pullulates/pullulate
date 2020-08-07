package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @功能描述:   网站配置实体类
 * @Author: xuyong
 * @Date: 2020/7/26 20:06
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulSiteConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    /** 配置主键 */
    private String configId;

    /** 配置名称 */
    private String configName;

    /** 配置键 */
    private String configKey;

    /** 配置值 */
    private String configValue;

    /** 备注信息 */
    private String remark;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    private LocalDateTime updateAt;
}
