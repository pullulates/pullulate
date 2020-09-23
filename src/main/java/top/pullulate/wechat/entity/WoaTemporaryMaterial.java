package top.pullulate.wechat.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * @功能描述:   微信公众号临时素材实体类
 * @Author: pullulate
 * @Date: 2020年9月23日15:01:58
 * @CopyRight: pullulate
 * @GitHub: https://github.com/pullulate
 * @Gitee: https://gitee.com/pullulate
 */
@Data
public class WoaTemporaryMaterial implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 素材主键
     */
    @TableId
    private String materialId;

    /**
     * 微信公众号主键
     */
    private String woaId;

    /**
     * 微信返回的素材标识
     */
    private String mediaId;

    /**
     * 素材类型
     */
    private String materialType;

    /**
     * 上传时间
     */
    private LocalDateTime uploadAt;

    /**
     * 过期时间
     */
    private LocalDateTime expireAt;

    /**
     * 本地存储地址
     */
    private String locationUrl;

    /**
     * 名称
     */
    private String name;

    /**
     * 原名
     */
    private String originalName;

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
