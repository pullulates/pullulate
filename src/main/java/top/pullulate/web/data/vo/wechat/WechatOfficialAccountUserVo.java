package top.pullulate.web.data.vo.wechat;

import lombok.Data;
import top.pullulate.common.validate.wechat.WechatOfficialAccount;
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
    @NotBlank(message = "微信用户标识不能为空", groups = {WechatOfficialAccount.UpdateUserRemark.class, WechatOfficialAccount.BlackUser.class})
    @Size(max = 32, message = "非法的微信用户标识", groups = {WechatOfficialAccount.UpdateUserRemark.class, WechatOfficialAccount.BlackUser.class})
    private String woaUserId;

    /**
     * 微信公众号主键
     */
    @NotBlank(message = "请选择微信公众号", groups = {WechatOfficialAccount.Sync.class, WechatOfficialAccount.UpdateUserRemark.class, WechatOfficialAccount.BlackUser.class})
    @Size(max = 32, message = "非法的微信公众号信息", groups = {WechatOfficialAccount.Sync.class, WechatOfficialAccount.UpdateUserRemark.class, WechatOfficialAccount.BlackUser.class})
    private String woaId;

    /**
     * 对粉丝的备注
     */
    @NotBlank(message = "备注信息不能为空", groups = {WechatOfficialAccount.UpdateUserRemark.class})
    @Size(max = 30, message = "备注信息至多30个字符", groups = {WechatOfficialAccount.UpdateUserRemark.class})
    private String remark;

    /**
     * 是否拉黑
     */
    private String inBlackList;
}
