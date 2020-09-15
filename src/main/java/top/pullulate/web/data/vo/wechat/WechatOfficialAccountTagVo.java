package top.pullulate.web.data.vo.wechat;

import lombok.Data;
import top.pullulate.common.validate.Common;
import top.pullulate.common.validate.wechat.WechatOfficial;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @功能描述:   微信公众号参数接收类
 * @Author: pullulate
 * @Date: 2020年9月14日09:46:49
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class WechatOfficialAccountTagVo {

    /**
     * 标签主键
     */
    @NotBlank(message = "标签主键不能为空", groups = {Common.Update.class, Common.Delete.class})
    @Size(max = 32, message = "非法的标签主键", groups = {Common.Update.class, Common.Delete.class})
    private String woaTagId;

    /**
     * 微信公众号主键
     */
    @NotBlank(message = "微信公众号主键不能为空", groups = {Common.Save.class, Common.Update.class, WechatOfficial.Sync.class})
    @Size(max = 32, message = "非法的微信公众号主键", groups = {Common.Save.class, Common.Update.class, WechatOfficial.Sync.class})
    private String woaId;

    /**
     * 微信标签id
     */
    @NotBlank(message = "微信标签id不能为空", groups = {Common.Update.class, Common.Delete.class})
    @Size(max = 32, message = "非法的微信标签id", groups = {Common.Update.class, Common.Delete.class})
    private String id;

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 32, message = "非法的标签名称", groups = {Common.Save.class, Common.Update.class})
    private String name;


}
