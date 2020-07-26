package top.pullulate.web.data.viewvo.system;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @功能描述:   网站配置视图类
 * @Author: xuyong
 * @Date: 2020/7/26 20:09
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class PulSiteConfigViewVo {

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
