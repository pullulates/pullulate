package top.pullulate.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @功能描述:   短信配置实体类
 * @Author: pullulate
 * @Date: 2020/6/13 0013 9:48
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class PulSmsConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String configId;

    private String appId;

    private String secretId;

    private String secretKey;

    private String remark;
}
