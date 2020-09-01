package top.pullulate.wechat.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * @功能描述:   微信公众号实体类
 * @Author: pullulate
 * @Date: 2020年9月1日17:36:53
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class WechatOfficialAccount implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 微信公众号主键
     */
    @TableId
    private String woaId;

    /**
     * 名称
     */
    private String name;

    /**
     * 公众号类型：0-订阅号；1-服务号
     */
    private String woaType;

    /**
     * 认证结果：0-未认证；1-已认证
     */
    private String certificationResult;

    /**
     * 原始id
     */
    private String originalId;

    /**
     * appId
     */
    private String appId;

    /**
     * appSecret
     */
    private String appSecret;

    /**
     * token
     */
    private String token;

    /**
     * 接口消息加解密秘钥
     */
    private String encryptKey;

    /**
     * 主体名称
     */
    private String subjectName;

    /**
     * 主体类型：0-个人；1-企业
     */
    private String subjectType;

    /**
     * 删除状态：0-未删除；1-已删除
     */
    private String deleteFlag;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateAt;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;


}
