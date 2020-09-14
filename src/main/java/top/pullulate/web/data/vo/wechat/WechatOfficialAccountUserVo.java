package top.pullulate.web.data.vo.wechat;

import lombok.Data;
import top.pullulate.common.validate.wechat.WechatOfficial;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @功能描述:   微信公众号用户参数接收类
 * @Author: xuyong
 * @Date: 2020/9/11 11:07
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class WechatOfficialAccountUserVo {

    /**
     * 主键
     */
    private String woaUserId;

    /**
     * 微信公众号主键
     */
    @NotBlank(message = "请选择微信公众号", groups = {WechatOfficial.Sync.class})
    @Size(max = 32, message = "非法的微信公众号信息", groups = {WechatOfficial.Sync.class})
    private String woaId;

    /**
     * 对粉丝的备注
     */
    private String remark;
}
