package top.pullulate.web.data.vo.wechat;

import lombok.Data;
import top.pullulate.common.validate.Common;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @功能描述:   微信公众号参数接收类
 * @Author: xuyong
 * @Date: 2020/9/1 18:06
 * @CopyRight: pullulates
 * @GitHub: https://github.com/pullulates
 * @Gitee: https://gitee.com/pullulates
 */
@Data
public class WechatOfficialAccountVo {

    /**
     * 微信公众号主键
     */
    @NotBlank(message = "微信公众号标识不能为空", groups = {Common.Update.class})
    @Size(max = 32, message = "非法的微信公众号信息", groups = {Common.Update.class})
    private String woaId;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 50, message = "名称最多50字", groups = {Common.Save.class, Common.Update.class})
    private String name;

    /**
     * 公众号类型：0-订阅号；1-服务号
     */
    @NotBlank(message = "公众号类型不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 1, message = "公众号类型最多1个字符", groups = {Common.Save.class, Common.Update.class})
    private String woaType;

    /**
     * 认证结果：0-未认证；1-已认证
     */
    @NotBlank(message = "认证结果不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 1, message = "认证结果最多1个字符", groups = {Common.Save.class, Common.Update.class})
    private String certificationResult;

    /**
     * 原始id
     */
    @NotBlank(message = "原始id不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 32, message = "原始id最多32个字符", groups = {Common.Save.class, Common.Update.class})
    private String originalId;

    /**
     * appId
     */
    @NotBlank(message = "appId不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 32, message = "appId最多32个字符", groups = {Common.Save.class, Common.Update.class})
    private String appId;

    /**
     * appSecret
     */
    @NotBlank(message = "appSecret不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 32, message = "appSecret最多32个字符", groups = {Common.Save.class, Common.Update.class})
    private String appSecret;

    /**
     * token
     */
    @NotBlank(message = "token不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 50, message = "token最多50个字符", groups = {Common.Save.class, Common.Update.class})
    private String token;

    /**
     * 接口消息加解密秘钥
     */
    @NotBlank(message = "秘钥不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 100, message = "秘钥最多100个字符", groups = {Common.Save.class, Common.Update.class})
    private String encryptKey;

    /**
     * 主体名称
     */
    @NotBlank(message = "主体名称不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 100, message = "主体名称最多100个字符", groups = {Common.Save.class, Common.Update.class})
    private String subjectName;

    /**
     * 主体类型：0-个人；1-企业
     */
    @NotBlank(message = "主体类型不能为空", groups = {Common.Save.class, Common.Update.class})
    @Size(max = 1, message = "主体类型最多1个字符", groups = {Common.Save.class, Common.Update.class})
    private String subjectType;

    /**
     * 删除状态：0-未删除；1-已删除
     */
    private String deleteFlag;

    /**
     * 备注信息
     */
    @Size(max = 250, message = "备注信息最多250个字符", groups = {Common.Save.class, Common.Update.class})
    private String remark;
}
